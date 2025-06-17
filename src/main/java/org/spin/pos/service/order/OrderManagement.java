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
package org.spin.pos.service.order;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MCharge;
import org.compiere.model.MCurrency;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MPOS;
import org.compiere.model.MPayment;
import org.compiere.model.MPriceList;
import org.compiere.model.MTable;
import org.compiere.model.MTax;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.spin.base.util.DocumentUtil;
import org.spin.base.util.RecordUtil;
import org.spin.model.MADAppRegistration;
import org.spin.pos.service.cash.CashManagement;
import org.spin.pos.service.cash.CashUtil;
import org.spin.pos.util.ColumnsAdded;
import org.spin.service.grpc.util.value.TimeManager;
import org.spin.util.fp.FiscalPrinterUtil;
import org.spin.util.text.DataUtils;

/**
 * A util class for change values for documents
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class OrderManagement {

	public static MOrder simulateProcessOrder(MPOS pos, int orderId, boolean isRefundOpen, String transactionName) {
		if(orderId <= 0) {
			throw new AdempiereException("@C_Order_ID@ @NotFound@");
		}
		MOrder salesOrder = new MOrder(Env.getCtx(), orderId, transactionName);
		List<PO> paymentReferences = getPaymentReferences(salesOrder);
		if(!OrderUtil.isValidOrder(salesOrder)) {
			throw new AdempiereException("@ActionNotAllowedHere@");
		}
		if(DocumentUtil.isDrafted(salesOrder)) {
			// In case the Order is Invalid, set to In Progress; otherwise it will not be completed
			if (salesOrder.getDocStatus().equalsIgnoreCase(MOrder.STATUS_Invalid))  {
				salesOrder.setDocStatus(MOrder.STATUS_InProgress);
			}
			boolean isOpenRefund = isRefundOpen;
			if(getPaymentReferenceAmount(salesOrder, paymentReferences).compareTo(Env.ZERO) != 0) {
				isOpenRefund = true;
			}
			//	Set default values
			salesOrder.setDocAction(DocAction.ACTION_Complete);
			OrderUtil.setCurrentDate(salesOrder);
			salesOrder.saveEx();
			//	Update Process if exists
			if (!salesOrder.processIt(MOrder.DOCACTION_Complete)) {
				throw new AdempiereException("@ProcessFailed@ :" + salesOrder.getProcessMsg());
			}
			//	Release Order
			int invoiceId = salesOrder.getC_Invoice_ID();
			if(invoiceId > 0) {
				salesOrder.setIsInvoiced(true);
			}
			salesOrder.set_ValueOfColumn("AssignedSalesRep_ID", null);
			salesOrder.saveEx();
			processPayments(salesOrder, pos, isOpenRefund, transactionName);
		} else {
			boolean isOpenToRefund = isRefundOpen;
			if(getPaymentReferenceAmount(salesOrder, paymentReferences).compareTo(Env.ZERO) != 0) {
				isOpenToRefund = true;
			}
			processPayments(salesOrder, pos, isOpenToRefund, transactionName);
		}
		//	Process all references
		processPaymentReferences(salesOrder, pos, paymentReferences, transactionName);

		//	Create
		return salesOrder;
	}


	public static MOrder processOrder(MPOS pos, int orderId, boolean isRefundOpen, String closingNo, String fiscalDocumentNo, String fiscalPrinterSerialNo, String printDate) {
		AtomicReference<MOrder> orderReference = new AtomicReference<MOrder>();
		Trx.run(transactionName -> {
			if(orderId <= 0) {
				throw new AdempiereException("@C_Order_ID@ @NotFound@");
			}
			MOrder salesOrder = new MOrder(Env.getCtx(), orderId, transactionName);
			List<PO> paymentReferences = getPaymentReferences(salesOrder);
			if(!OrderUtil.isValidOrder(salesOrder)) {
				throw new AdempiereException("@ActionNotAllowedHere@");
			}
			if(DocumentUtil.isDrafted(salesOrder)) {
				// In case the Order is Invalid, set to In Progress; otherwise it will not be completed
				if (salesOrder.getDocStatus().equalsIgnoreCase(MOrder.STATUS_Invalid))  {
					salesOrder.setDocStatus(MOrder.STATUS_InProgress);
				}
				boolean isOpenRefund = isRefundOpen;
				if(getPaymentReferenceAmount(salesOrder, paymentReferences).compareTo(Env.ZERO) != 0) {
					isOpenRefund = true;
				}
				//	Set default values
				salesOrder.setDocAction(DocAction.ACTION_Complete);
				OrderUtil.setCurrentDate(salesOrder);
				salesOrder.saveEx();
				//	Update Process if exists
				if (!salesOrder.processIt(MOrder.DOCACTION_Complete)) {
					throw new AdempiereException("@ProcessFailed@ :" + salesOrder.getProcessMsg());
				}
				//	Release Order
				int invoiceId = salesOrder.getC_Invoice_ID();
				if(invoiceId > 0) {
					salesOrder.setIsInvoiced(true);
				}
				salesOrder.set_ValueOfColumn("AssignedSalesRep_ID", null);
				salesOrder.saveEx();
				processPayments(salesOrder, pos, isOpenRefund, transactionName);
			} else {
				boolean isOpenToRefund = isRefundOpen;
				if(getPaymentReferenceAmount(salesOrder, paymentReferences).compareTo(Env.ZERO) != 0) {
					isOpenToRefund = true;
				}
				processPayments(salesOrder, pos, isOpenToRefund, transactionName);
			}
			//	Process all references
			processPaymentReferences(salesOrder, pos, paymentReferences, transactionName);

			// overwrite document no on Invoice
			if (salesOrder.getC_Invoice_ID() > 0) {
				MInvoice invoice = new MInvoice(Env.getCtx(), salesOrder.getC_Invoice_ID(), transactionName);
				invoice.set_ValueOfColumn(FiscalPrinterUtil.COLUMNNAME_FiscalDocumentNo, fiscalDocumentNo);
				invoice.set_ValueOfColumn(FiscalPrinterUtil.COLUMNNAME_PrintFiscalDocument, "Y");
				invoice.set_ValueOfColumn(FiscalPrinterUtil.COLUMNNAME_FiscalClosingNo, closingNo);
				if (!Util.isEmpty(printDate, true)) {
					Timestamp printDateTimestamp = TimeManager.getTimestampFromString(printDate);
					invoice.set_ValueOfColumn(FiscalPrinterUtil.COLUMNNAME_FiscalPrintDate, printDateTimestamp);
				}
				invoice.setDocumentNo(fiscalDocumentNo);
				invoice.saveEx();

				int appPrinterId = invoice.get_ValueAsInt(FiscalPrinterUtil.COLUMNNAME_FiscalPrinter_ID);
				if (appPrinterId <= 0) {
					appPrinterId = pos.get_ValueAsInt(
						FiscalPrinterUtil.COLUMNNAME_FiscalPrinter_ID
					);
				}
				if(appPrinterId > 0) {
					String printerSerialNo = fiscalPrinterSerialNo;
					if(Util.isEmpty(printerSerialNo)) {
						MADAppRegistration registeredApplication = MADAppRegistration.getById(
							Env.getCtx(),
							appPrinterId,
							null
						);
						if (registeredApplication != null && registeredApplication.getAD_AppRegistration_ID() > 0) {
							printerSerialNo = Optional.ofNullable(registeredApplication.getValue()).orElse("");
						}
					}
					if(printerSerialNo.length() > 4) {
						printerSerialNo = printerSerialNo.substring(printerSerialNo.length() - 4);
					}
					final String completeFiscalDocumentNo = DataUtils.leftPadding(printerSerialNo, 4, "0") + "-" + fiscalDocumentNo;
					invoice.setDocumentNo(completeFiscalDocumentNo);
					invoice.saveEx();
				}
			}

			//	Create
			orderReference.set(salesOrder);
		});
		return orderReference.get();
	}


	/**
	 * Process Payment references
	 * @param salesOrder
	 * @param pos
	 * @param paymentReferences
	 * @param transactionName
	 */
	private static void processPaymentReferences(MOrder salesOrder, MPOS pos, List<PO> paymentReferences, String transactionName) {
		paymentReferences.stream().filter(paymentReference -> {
			PO paymentMethodAlocation = getPaymentMethodAllocation(paymentReference.get_ValueAsInt("C_PaymentMethod_ID"), paymentReference.get_ValueAsInt("C_POS_ID"), paymentReference.get_TrxName());
			if(paymentMethodAlocation == null) {
				return false;
			}
			return paymentMethodAlocation.get_ValueAsBoolean("IsPaymentReference") && !paymentReference.get_ValueAsBoolean("IsAutoCreatedReference");
		}).forEach(paymentReference -> {
			paymentReference.set_ValueOfColumn("Processed", true);
			paymentReference.saveEx();
		});
	}

	/**
	 * Get Refund references from order
	 * @param order
	 * @return
	 * @return List<PO>
	 */
	private static List<PO> getPaymentReferences(MOrder order) {
		if(MTable.get(Env.getCtx(), "C_POSPaymentReference") == null) {
			return new ArrayList<PO>();
		}
		//	
		return new Query(
			order.getCtx(),
			"C_POSPaymentReference",
			"C_Order_ID = ? AND IsPaid = 'N' AND Processed = 'N'",
			order.get_TrxName()
		)
			.setParameters(order.getC_Order_ID())
			.list()
		;
	}

	/**
	 * Get Refund Reference Amount
	 * @param order
	 * @return
	 * @return BigDecimal
	 */
	private static BigDecimal getPaymentReferenceAmount(MOrder order, List<PO> paymentReferences) {
		Optional<BigDecimal> paymentReferenceAmount = paymentReferences.stream().map(refundReference -> {
			return OrderUtil.getConvetedAmount(order, refundReference, ((BigDecimal) refundReference.get_Value("Amount")));
		}).collect(Collectors.reducing(BigDecimal::add));
		if(paymentReferenceAmount.isPresent()) {
			return paymentReferenceAmount.get();
		}
		return Env.ZERO;
	}

	/**
	 * Get Payment Method allocation from payment
	 * @param payment
	 * @return
	 * @return PO
	 */
	private static PO getPaymentMethodAllocation(int paymentMethodId, int posId, String transactionName) {
		if(MTable.get(Env.getCtx(), "C_POSPaymentTypeAllocation") == null) {
			return null;
		}
		return new Query(Env.getCtx(), "C_POSPaymentTypeAllocation", "C_POS_ID = ? AND C_PaymentMethod_ID = ?", transactionName)
				.setParameters(posId, paymentMethodId)
				.setOnlyActiveRecords(true)
				.first();
	}

	private static void createCreditMemoReference(MOrder salesOrder, MPayment payment, String transactionName) {
		if(payment.get_ValueAsInt(ColumnsAdded.COLUMNNAME_ECA14_Invoice_Reference_ID) <= 0) {
			return;
		}
		MInvoice creditMemo = new MInvoice(salesOrder.getCtx(), payment.get_ValueAsInt(ColumnsAdded.COLUMNNAME_ECA14_Invoice_Reference_ID), transactionName);
		payment.setC_Invoice_ID(-1);
		payment.set_ValueOfColumn(ColumnsAdded.COLUMNNAME_ECA14_Reference_Amount, payment.getPayAmt());
		payment.setPayAmt(Env.ZERO);
		CashUtil.setCurrentDate(payment, true);
		payment.saveEx(transactionName);
		creditMemo.setC_Payment_ID(payment.getC_Payment_ID());
		creditMemo.saveEx(transactionName);
	}

	/**
	 * Create Credit Memo from payment
	 * @param salesOrder
	 * @param payment
	 * @param transactionName
	 * @return void
	 */
	private static void createCreditMemo(MOrder salesOrder, MPayment payment, String transactionName) {
		MInvoice creditMemo = new MInvoice(salesOrder.getCtx(), 0, transactionName);
		creditMemo.setBPartner((MBPartner) payment.getC_BPartner());
		creditMemo.setDescription(payment.getDescription());
		creditMemo.setIsSOTrx(true);
		creditMemo.setSalesRep_ID(payment.get_ValueAsInt("CollectingAgent_ID"));
		creditMemo.setAD_Org_ID(payment.getAD_Org_ID());
		creditMemo.setC_POS_ID(payment.getC_POS_ID());
		if(creditMemo.getM_PriceList_ID() <= 0
				|| creditMemo.getC_Currency_ID() != payment.getC_Currency_ID()) {
			String isoCode = MCurrency.getISO_Code(salesOrder.getCtx(), payment.getC_Currency_ID());
			MPriceList priceList = MPriceList.getDefault(salesOrder.getCtx(), true, isoCode);
			if(priceList == null) {
				throw new AdempiereException("@M_PriceList_ID@ @NotFound@ (@C_Currency_ID@ " + isoCode + ")");
			}
			creditMemo.setM_PriceList_ID(priceList.getM_PriceList_ID());
		}
		PO paymentTypeAllocation = getPaymentMethodAllocation(payment.get_ValueAsInt("C_PaymentMethod_ID"), payment.getC_POS_ID(), payment.get_TrxName());
		int chargeId = 0;
		if(paymentTypeAllocation != null) {
			chargeId = paymentTypeAllocation.get_ValueAsInt("C_Charge_ID");
			if(paymentTypeAllocation.get_ValueAsInt("C_DocTypeCreditMemo_ID") > 0) {
				creditMemo.setC_DocTypeTarget_ID(paymentTypeAllocation.get_ValueAsInt("C_DocTypeCreditMemo_ID"));
			}
		}
		//	Set if not exist
		if(creditMemo.getC_DocTypeTarget_ID() <= 0) {
			creditMemo.setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_ARCreditMemo);
		}
		creditMemo.setDocumentNo(payment.getDocumentNo());
		creditMemo.setC_ConversionType_ID(payment.getC_ConversionType_ID());
		creditMemo.setC_Order_ID(salesOrder.getC_Order_ID());
		creditMemo.setC_Payment_ID(payment.getC_Payment_ID());
		creditMemo.setDateInvoiced(payment.getDateTrx());
		creditMemo.setDateAcct(payment.getDateAcct());
		OrderUtil.copyAccountDimensions(salesOrder, creditMemo);
		creditMemo.saveEx(transactionName);
		//	Add line
		MInvoiceLine creditMemoLine = new MInvoiceLine(creditMemo);
		if(chargeId <= 0) {
			chargeId = new Query(salesOrder.getCtx(), MCharge.Table_Name, null, transactionName).setClient_ID().firstId();
		}
		MCharge charge = MCharge.get(payment.getCtx(), chargeId);
		Optional<MTax> optionalTax = Arrays.asList(MTax.getAll(Env.getCtx()))
				.stream()
				.filter(tax -> tax.getC_TaxCategory_ID() == charge.getC_TaxCategory_ID() 
									&& (tax.isSalesTax() 
											|| (!Util.isEmpty(tax.getSOPOType()) 
													&& (tax.getSOPOType().equals(MTax.SOPOTYPE_Both) 
															|| tax.getSOPOType().equals(MTax.SOPOTYPE_SalesTax)))))
				.findFirst();
		creditMemoLine.setC_Charge_ID(chargeId);
		creditMemoLine.setC_Tax_ID(optionalTax.get().getC_Tax_ID());
		creditMemoLine.setQty(Env.ONE);
		creditMemoLine.setPrice(payment.getPayAmt());
		creditMemoLine.setDescription(payment.getDescription());
		creditMemoLine.saveEx(transactionName);
		//	change payment
		payment.setC_Invoice_ID(-1);
		payment.setPayAmt(Env.ZERO);
		CashUtil.setCurrentDate(payment, true);
		payment.saveEx(transactionName);
		//	Process credit Memo
		if (!creditMemo.processIt(MInvoice.DOCACTION_Complete)) {
			throw new AdempiereException("@ProcessFailed@ :" + creditMemo.getProcessMsg());
		}
		creditMemo.setDocumentNo(payment.getDocumentNo());
		creditMemo.saveEx(transactionName);
	}


	/**
	 * Process payment of Order
	 * @param salesOrder
	 * @param pos
	 * @param isOpenRefund
	 * @param transactionName
	 * @return void
	 */
	public static void processPayments(MOrder salesOrder, MPOS pos, boolean isOpenRefund, String transactionName) {
		//	Get invoice if exists
		int invoiceId = salesOrder.getC_Invoice_ID();
		AtomicReference<BigDecimal> openAmount = new AtomicReference<BigDecimal>(OrderUtil.getTotalOpenAmount(salesOrder));
		AtomicReference<BigDecimal> currentAmount = new AtomicReference<BigDecimal>(salesOrder.getGrandTotal());
		List<Integer> paymentsIds = new ArrayList<Integer>();
		//	Complete Payments
		List<MPayment> payments = MPayment.getOfOrder(salesOrder);
		payments.stream().sorted(Comparator.comparing(MPayment::getCreated)).forEach(payment -> {
			BigDecimal convertedAmount = OrderUtil.getConvetedAmount(salesOrder, payment, payment.getPayAmt());
			//	Get current open amount
			AtomicReference<BigDecimal> multiplier = new AtomicReference<BigDecimal>(!salesOrder.isReturnOrder()? Env.ONE: Env.ONE.negate());
			if(!payment.isReceipt()) {
				multiplier.updateAndGet(value -> value.negate());
			}
			openAmount.updateAndGet(amount -> amount.subtract(convertedAmount.multiply(multiplier.get())));
			if(payment.isAllocated()) {
				currentAmount.updateAndGet(amount -> amount.subtract(convertedAmount.multiply(multiplier.get())));
			}
			if(DocumentUtil.isDrafted(payment)) {
				payment.setIsPrepayment(true);
				payment.setOverUnderAmt(Env.ZERO);
				if(payment.getTenderType().equals(MPayment.TENDERTYPE_CreditMemo)) {
					if(payment.get_ValueAsInt(ColumnsAdded.COLUMNNAME_ECA14_Invoice_Reference_ID) <= 0) {
						createCreditMemo(salesOrder, payment, transactionName);
					} else {
						createCreditMemoReference(salesOrder, payment, transactionName);
					}
				}
				payment.setDocAction(MPayment.DOCACTION_Complete);
				CashUtil.setCurrentDate(payment);
				payment.saveEx(transactionName);
				if (!payment.processIt(MPayment.DOCACTION_Complete)) {
					throw new AdempiereException("@ProcessFailed@ :" + payment.getProcessMsg());
				}
				payment.saveEx(transactionName);
				paymentsIds.add(payment.getC_Payment_ID());
				salesOrder.saveEx(transactionName);
			}
			CashManagement.addPaymentToCash(pos, payment);
		});
		//	Allocate all payments
		if(paymentsIds.size() > 0) {
			String description = Msg.parseTranslation(Env.getCtx(), "@C_POS_ID@: " + pos.getName() + " - " + salesOrder.getDocumentNo());
			//	
			MAllocationHdr paymentAllocation = new MAllocationHdr (Env.getCtx(), true, RecordUtil.getDate(), salesOrder.getC_Currency_ID(), description, transactionName);
			paymentAllocation.setAD_Org_ID(salesOrder.getAD_Org_ID());
			//	Set Description
			paymentAllocation.saveEx();
			AtomicBoolean isAllocationLineCreated = new AtomicBoolean(false);
			//	Add lines
			paymentsIds.stream().map(paymentId -> new MPayment(Env.getCtx(), paymentId, transactionName))
			.filter(payment -> payment.getC_POS_ID() != 0 && !payment.getTenderType().equals(MPayment.TENDERTYPE_CreditMemo))
			.forEach(payment -> {
				BigDecimal multiplier = Env.ONE;
				if(!payment.isReceipt()) {
					multiplier = Env.ONE.negate();
				}
				BigDecimal paymentAmount = OrderUtil.getConvetedAmount(salesOrder, payment, payment.getPayAmt());
				BigDecimal discountAmount = OrderUtil.getConvetedAmount(salesOrder, payment, payment.getDiscountAmt());
				BigDecimal overUnderAmount = OrderUtil.getConvetedAmount(salesOrder, payment, payment.getOverUnderAmt());
				BigDecimal writeOffAmount = OrderUtil.getConvetedAmount(salesOrder, payment, payment.getWriteOffAmt());
				if (overUnderAmount.signum() < 0 && paymentAmount.signum() > 0) {
					paymentAmount = paymentAmount.add(overUnderAmount);
				}
				MAllocationLine paymentAllocationLine = new MAllocationLine (paymentAllocation, paymentAmount.multiply(multiplier), discountAmount.multiply(multiplier), writeOffAmount.multiply(multiplier), overUnderAmount.multiply(multiplier));
				paymentAllocationLine.setDocInfo(salesOrder.getC_BPartner_ID(), salesOrder.getC_Order_ID(), invoiceId);
				paymentAllocationLine.setPaymentInfo(payment.getC_Payment_ID(), 0);
				paymentAllocationLine.saveEx();
				isAllocationLineCreated.set(true);
			});
			//	Allocate all Credit Memo
			paymentsIds.stream().map(paymentId -> new MPayment(Env.getCtx(), paymentId, transactionName))
			.filter(payment -> payment.getC_POS_ID() != 0 && payment.getTenderType().equals(MPayment.TENDERTYPE_CreditMemo))
			.forEach(payment -> {
				BigDecimal multiplier = Env.ONE.negate();
				MInvoice creditMemo = new Query(payment.getCtx(), MInvoice.Table_Name, "C_Payment_ID = ?", payment.get_TrxName()).setParameters(payment.getC_Payment_ID()).first();
				BigDecimal creditMemoAmount = Optional.ofNullable((BigDecimal) payment.get_Value(ColumnsAdded.COLUMNNAME_ECA14_Reference_Amount)).orElse(Env.ZERO);
				if(creditMemoAmount.compareTo(Env.ZERO) == 0) {
					creditMemoAmount = creditMemo.getGrandTotal();
				}
				BigDecimal paymentAmount = OrderUtil.getConvetedAmount(salesOrder, payment, creditMemoAmount);
				BigDecimal discountAmount = Env.ZERO;
				BigDecimal overUnderAmount = Env.ZERO;
				BigDecimal writeOffAmount = Env.ZERO;
				if (overUnderAmount.signum() < 0 && paymentAmount.signum() > 0) {
					paymentAmount = paymentAmount.add(overUnderAmount);
				}
				//	Create new Line
				if(!isAllocationLineCreated.get() || payment.get_ValueAsInt(ColumnsAdded.COLUMNNAME_ECA14_Invoice_Reference_ID) > 0) {
					MAllocationLine paymentAllocationLine = new MAllocationLine (paymentAllocation, paymentAmount, discountAmount, writeOffAmount, overUnderAmount);
					paymentAllocationLine.setDocInfo(salesOrder.getC_BPartner_ID(), salesOrder.getC_Order_ID(), invoiceId);
					paymentAllocationLine.saveEx();
				}
				MAllocationLine paymentAllocationLine = new MAllocationLine (paymentAllocation, paymentAmount.multiply(multiplier), discountAmount.multiply(multiplier), writeOffAmount.multiply(multiplier), overUnderAmount.multiply(multiplier));
				paymentAllocationLine.setDocInfo(salesOrder.getC_BPartner_ID(), salesOrder.getC_Order_ID(), creditMemo.getC_Invoice_ID());
				paymentAllocationLine.saveEx();
			});
			//	Add write off
			if(!isOpenRefund
					|| OrderUtil.isAutoWriteOff(salesOrder, openAmount.get())) {
				if(openAmount.get().compareTo(Env.ZERO) != 0) {
					MAllocationLine paymentAllocationLine = new MAllocationLine (paymentAllocation, Env.ZERO, Env.ZERO, openAmount.get(), Env.ZERO);
					paymentAllocationLine.setDocInfo(salesOrder.getC_BPartner_ID(), salesOrder.getC_Order_ID(), invoiceId);
					paymentAllocationLine.saveEx();
				}
			}
			//	Complete
			if (!paymentAllocation.processIt(MAllocationHdr.DOCACTION_Complete)) {
				throw new AdempiereException(paymentAllocation.getProcessMsg());
			}
			paymentAllocation.saveEx();
			//	Test allocation
			paymentsIds.stream().map(paymentId -> new MPayment(Env.getCtx(), paymentId, transactionName)).forEach(payment -> {
				payment.setIsAllocated(true);
				payment.setC_Invoice_ID(invoiceId);
				payment.saveEx();
				if(payment.setPaymentProcessor()) {
					payment.setIsApproved(false);
					boolean isOk = payment.processOnline();
					if(!isOk) {
						throw new AdempiereException(payment.getErrorMessage());
					}
					payment.setIsApproved(true);
					payment.saveEx();
				}
			});
		} else {
			//	Add write off
			if(!isOpenRefund) {
				if(openAmount.get().compareTo(Env.ZERO) != 0) {
					String description = Msg.parseTranslation(Env.getCtx(), "@C_POS_ID@: " + pos.getName() + " - " + salesOrder.getDocumentNo());
					MAllocationHdr paymentAllocation = new MAllocationHdr (Env.getCtx(), true, RecordUtil.getDate(), salesOrder.getC_Currency_ID(), description, transactionName);
					paymentAllocation.setAD_Org_ID(salesOrder.getAD_Org_ID());
					//	Set Description
					paymentAllocation.saveEx();
					MAllocationLine paymentAllocationLine = new MAllocationLine (paymentAllocation, Env.ZERO, Env.ZERO, openAmount.get(), Env.ZERO);
					paymentAllocationLine.setDocInfo(salesOrder.getC_BPartner_ID(), salesOrder.getC_Order_ID(), invoiceId);
					paymentAllocationLine.saveEx();
					//	Complete
					if (!paymentAllocation.processIt(MAllocationHdr.DOCACTION_Complete)) {
						throw new AdempiereException(paymentAllocation.getProcessMsg());
					}
					paymentAllocation.saveEx();
				}
			}
		}
	}

}
