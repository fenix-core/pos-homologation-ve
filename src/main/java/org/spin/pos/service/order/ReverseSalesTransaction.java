package org.spin.pos.service.order;

import java.sql.Timestamp;
import java.util.Optional;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPOS;
import org.compiere.process.DocAction;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.spin.base.util.DocumentUtil;
import org.spin.model.MADAppRegistration;
import org.spin.service.grpc.util.value.TimeManager;
import org.spin.util.fp.FiscalPrinterUtil;
import org.spin.util.text.DataUtils;

public class ReverseSalesTransaction {

	/**
	 * Create a Return order and cancel all payments
	 * @param pos
	 * @param sourceOrderId
	 * @param description
	 * @return
	 */
	public static MOrder returnCompleteOrder(MPOS pos, int sourceOrderId, String description, String transactionName) {
		MOrder sourceOrder = new MOrder(Env.getCtx(), sourceOrderId, transactionName);
		//	Validate source document
		if(DocumentUtil.isDrafted(sourceOrder) 
				|| DocumentUtil.isClosed(sourceOrder)
				|| sourceOrder.isReturnOrder()
				|| !OrderUtil.isValidOrder(sourceOrder)) {
			throw new AdempiereException("@ActionNotAllowedHere@");
		}
		MOrder returnOrder = createReturnOrder(pos, sourceOrder, transactionName);
		if(!Util.isEmpty(description)) {
			returnOrder.setDescription(description);
			returnOrder.saveEx();
		}
		//	Close all
		if(!sourceOrder.processIt(MOrder.DOCACTION_Close)) {
			throw new AdempiereException(sourceOrder.getProcessMsg());
		}
		sourceOrder.saveEx();
		if(!returnOrder.processIt(MOrder.DOCACTION_Close)) {
			throw new AdempiereException(returnOrder.getProcessMsg());
		}
		returnOrder.saveEx();

		OrderManagement.processPayments(returnOrder, pos, true, transactionName);

		return returnOrder;
	}

	/**
	 * Create return order
	 * @param pos
	 * @param sourceOrder
	 * @param transactionName
	 * @return
	 */
	private static MOrder createReturnOrder(MPOS pos, MOrder sourceOrder, String transactionName) {
		MOrder returnOrder = RMAUtil.copyRMAFromOrder(pos, sourceOrder, transactionName);
		RMAUtil.createReturnOrderLines(sourceOrder, returnOrder, transactionName);
		//	Process return Order
		if(!returnOrder.processIt(DocAction.ACTION_Complete)) {
			throw new AdempiereException("@ProcessFailed@ :" + returnOrder.getProcessMsg());
		}
		returnOrder.saveEx();

		//	Generate Return
		RMAUtil.generateReturnFromRMA(returnOrder, transactionName);

		//	Generate Credit Memo
		RMAUtil.generateCreditMemoFromRMA(returnOrder, transactionName);

		//	Return all payments
		RMAUtil.createReversedPayments(pos, sourceOrder, returnOrder, transactionName);
		return returnOrder;
	}


	public static MInvoice fillCreditMemo(MPOS pos, MOrder reverseSales, String closingNo, String fiscalDocumentNo, String fiscalPrinterSerialNo, String printDate, String transactionName) {
		if (reverseSales == null || reverseSales.getC_Invoice_ID() <= 0) {
			return null;
		}
		// overwrite document no on Invoice
		MInvoice creditMemo = new MInvoice(Env.getCtx(), reverseSales.getC_Invoice_ID(), transactionName);
		creditMemo.set_ValueOfColumn(FiscalPrinterUtil.COLUMNNAME_FiscalDocumentNo, fiscalDocumentNo);
		creditMemo.set_ValueOfColumn(FiscalPrinterUtil.COLUMNNAME_PrintFiscalDocument, "Y");
		creditMemo.set_ValueOfColumn(FiscalPrinterUtil.COLUMNNAME_FiscalClosingNo, closingNo);
		if (!Util.isEmpty(printDate, true)) {
			Timestamp printDateTimestamp = TimeManager.getTimestampFromString(printDate);
			reverseSales.set_ValueOfColumn(FiscalPrinterUtil.COLUMNNAME_FiscalPrintDate, printDateTimestamp);
		}
		reverseSales.setDocumentNo(fiscalDocumentNo);
		reverseSales.saveEx();

		int appPrinterId = reverseSales.get_ValueAsInt(FiscalPrinterUtil.COLUMNNAME_FiscalPrinter_ID);
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
			reverseSales.setDocumentNo(completeFiscalDocumentNo);
			reverseSales.saveEx();
		}
		return creditMemo;
	}

}
