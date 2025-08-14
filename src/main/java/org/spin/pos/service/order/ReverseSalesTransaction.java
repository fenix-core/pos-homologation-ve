package org.spin.pos.service.order;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MOrder;
import org.compiere.model.MPOS;
import org.compiere.process.DocAction;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.spin.base.util.DocumentUtil;

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

}
