/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.spin.pos.service.cash;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBankStatement;
import org.compiere.model.MBankStatementLine;
import org.compiere.model.MPOS;
import org.compiere.model.MPayment;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;

/**
 * This class was created for add all helper method for Cash Management
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class CashManagement {

	/**
	 * Process Payment
	 * @param pos
	 * @param payment
	 * @param transactionName
	 */
	public static void processPayment(MPOS pos, MPayment payment, String transactionName) {
		//	Create bank transfer
		MPayment relatedPayment = createRelatedPayment(pos, payment, transactionName);
		if(relatedPayment != null) {
			completePayment(pos, relatedPayment);
		}
		completePayment(pos, payment);
	}
	
	/**
	 * Process or complete payment and add to bank statement
	 * @param pos
	 * @param payment
	 * @return void
	 */
	public static void completePayment(MPOS pos, MPayment payment) {
		//	Process It
		if(!payment.isProcessed()) {
			payment.setDocStatus(MPayment.DOCSTATUS_Drafted);
			payment.setDocAction(MPayment.ACTION_Complete);
		}
		payment.saveEx();
		//	Complete It
		if(!payment.isProcessed()) {
			if(!payment.processIt(MPayment.ACTION_Complete)) {
				throw new AdempiereException(payment.getProcessMsg());
			}
			payment.saveEx();
		}
		CashManagement.addPaymentToCash(pos, payment);
	}
	
	/**
	 * Create Related Payment from payment
	 * @param pointOfSalesDefinition
	 * @param sourcePayment
	 * @param transactionName
	 * @return
	 */
	private static MPayment createRelatedPayment(MPOS pointOfSalesDefinition, MPayment sourcePayment, String transactionName) {
		if(sourcePayment.get_ValueAsInt("POSReferenceBankAccount_ID") <= 0) {
			return null;
		}
		MPayment relatedPayment = new MPayment(Env.getCtx(), 0, transactionName);
		PO.copyValues(sourcePayment, relatedPayment);
		//	
		relatedPayment.setAD_Org_ID(pointOfSalesDefinition.getAD_Org_ID());
		relatedPayment.set_ValueOfColumn("POSReferenceBankAccount_ID", null);
		relatedPayment.setC_BankAccount_ID(sourcePayment.get_ValueAsInt("POSReferenceBankAccount_ID"));
		relatedPayment.setRelatedPayment_ID(sourcePayment.getC_Payment_ID());
		int documentTypeId;
		if(!sourcePayment.isReceipt()) {
			documentTypeId = pointOfSalesDefinition.get_ValueAsInt("POSDepositDocumentType_ID");
		} else {
			documentTypeId = pointOfSalesDefinition.get_ValueAsInt("POSWithdrawalDocumentType_ID");
		}
		if(documentTypeId > 0) {
			relatedPayment.setC_DocType_ID(documentTypeId);
		} else {
			relatedPayment.setC_DocType_ID(!sourcePayment.isReceipt());
		}
		relatedPayment.saveEx();
		sourcePayment.setRelatedPayment_ID(relatedPayment.getC_Payment_ID());
		sourcePayment.saveEx();
		return relatedPayment;
	}

	/**
	 * Add payment to cash closing
	 * @param payment
	 */
	public static void addPaymentToCash(MPOS pos, MPayment payment) {
		//	validate document type
		int cashClosingDocumentTypeId = pos.get_ValueAsInt("POSCashClosingDocumentType_ID");
		//	Create Cash closing
		if(cashClosingDocumentTypeId <= 0) {
			throw new AdempiereException("@POSCashClosingDocumentType_ID@ @IsMandatory@");
		}
		//	Validate if exist on a bank statement
		MBankStatementLine bankStatementLine = payment.getBankStatementLine();
		if(bankStatementLine != null
				&& bankStatementLine.getC_BankStatement_ID() > 0) {
			return;
		}
		//	Validate previous cash closing
		validatePreviousCashClosing(pos, payment.getDateTrx(), payment.get_TrxName());
		//	Add
		MBankStatement bankStatement = getCurrentCashClosing(pos, payment.getDateTrx(), true, payment.get_TrxName());
		if(bankStatement != null) {
			bankStatementLine = new MBankStatementLine(bankStatement);
			bankStatementLine.setPayment(payment);
			bankStatementLine.setStatementLineDate(payment.getDateAcct());
			bankStatementLine.setDateAcct(payment.getDateAcct());
			bankStatementLine.saveEx();
		}
	}

	/**
	 * Validate if exists a previous cash closing opened
	 * @param pos
	 * @param validDate
	 * @param transactionName
	 */
	public static void validatePreviousCashClosing(MPOS pos, Timestamp validDate, String transactionName) {
		//	validate document type
		int cashClosingDocumentTypeId = pos.get_ValueAsInt("POSCashClosingDocumentType_ID");
		//	Create Cash closing
		if(cashClosingDocumentTypeId <= 0) {
			throw new AdempiereException("@POSCashClosingDocumentType_ID@ @IsMandatory@");
		}
		if(pos.getC_BankAccount_ID() <= 0) {
			throw new AdempiereException("@C_BankAccount_ID@ @NotFound@");
		}
		//	Validate
		if(!pos.get_ValueAsBoolean("IsValidatePOSPreviousCash")) {
			return;
		}
		//	Find
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(MBankStatement.COLUMNNAME_C_BankAccount_ID).append(" = ? AND StatementDate < ?")
				.append(" AND ").append(MBankStatement.COLUMNNAME_Processed).append(" = ?")
				.append(" AND ").append(MBankStatement.COLUMNNAME_C_DocType_ID).append(" = ?")
				.append(" AND ").append("C_POS_ID = ?");
		MBankStatement bankStatement = new Query(Env.getCtx(), MBankStatement.Table_Name, whereClause.toString(), transactionName)
				.setClient_ID()
				.setParameters(pos.getC_BankAccount_ID(), TimeUtil.getDay(validDate), false, cashClosingDocumentTypeId, pos.getC_POS_ID())
				.first();
		if (bankStatement != null && bankStatement.get_ID() > 0) {
			throw new AdempiereException("@POS.PreviousCashClosingOpened@: " + bankStatement.getDocumentNo());
		}
	}

	/**
	 * Get Current bank statement
	 * @param pos
	 * @param validDate
	 * @param validate
	 * @param transactionName
	 * @return
	 * @return MBankStatement
	 */
	public static MBankStatement getCurrentCashClosing(MPOS pos, Timestamp validDate, boolean validate, String transactionName) {
		//	validate document type
		int cashClosingDocumentTypeId = pos.get_ValueAsInt("POSCashClosingDocumentType_ID");
		//	Create Cash closing
		if(cashClosingDocumentTypeId <= 0) {
			throw new AdempiereException("@POSCashClosingDocumentType_ID@ @IsMandatory@");
		}
		if(pos.getC_BankAccount_ID() <= 0) {
			throw new AdempiereException("@C_BankAccount_ID@ @NotFound@");
		}
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(MBankStatement.COLUMNNAME_C_BankAccount_ID).append(" = ? AND ")
				.append("TRUNC(").append(MBankStatement.COLUMNNAME_StatementDate).append(",'DD') = ? AND ")
				.append(MBankStatement.COLUMNNAME_Processed).append(" = ?")
				.append(" AND ").append(MBankStatement.COLUMNNAME_C_DocType_ID).append(" = ?")
				.append(" AND ").append("C_POS_ID = ?");
		MBankStatement bankStatement = new Query(Env.getCtx() , MBankStatement.Table_Name , whereClause.toString(), transactionName)
				.setClient_ID()
				.setParameters(pos.getC_BankAccount_ID(), TimeUtil.getDay(validDate), false, cashClosingDocumentTypeId, pos.getC_POS_ID())
				.first();
		if (bankStatement == null || bankStatement.get_ID() <= 0) {
			if(pos.get_ValueAsBoolean("IsValidatePOSCashOpening") && validate) {
				throw new AdempiereException("@POS.CashClosingNotFound@");
			}
		}
		return bankStatement;
	}
	
	/**
	 * Create BankStatement based on default document type of point of sales
	 * @param pos
	 * @param payment
	 * @param documentTypeId
	 */
	public static MBankStatement createCashClosing(MPOS pos, MPayment payment) {
		//	validate document type
		int cashClosingDocumentTypeId = pos.get_ValueAsInt("POSCashClosingDocumentType_ID");
		//	Create Cash closing
		if(cashClosingDocumentTypeId <= 0) {
			throw new AdempiereException("@POSCashClosingDocumentType_ID@ @IsMandatory@");
		}
		MBankStatement bankStatement = getCurrentCashClosing(pos, payment.getDateTrx(), false, payment.get_TrxName());
		if (bankStatement == null || bankStatement.get_ID() <= 0) {
			bankStatement = new MBankStatement(payment.getCtx() , 0 , payment.get_TrxName());
			bankStatement.setC_BankAccount_ID(payment.getC_BankAccount_ID());
			bankStatement.setStatementDate(payment.getDateAcct());
			bankStatement.setC_DocType_ID(cashClosingDocumentTypeId);
			bankStatement.setAD_Org_ID(pos.getAD_Org_ID());
			bankStatement.set_ValueOfColumn("C_POS_ID", pos.getC_POS_ID());
			SimpleDateFormat format = DisplayType.getDateFormat(DisplayType.Date);
			bankStatement.setName(Msg.parseTranslation(payment.getCtx(), "@C_POS_ID@: " + pos.getName() + " @DateDoc@: " + format.format(System.currentTimeMillis())));
			bankStatement.saveEx();
		}
		return bankStatement;
	}
	
}
