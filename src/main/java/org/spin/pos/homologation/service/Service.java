/************************************************************************************
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                     *
 * Contributor(s): Yamel Senih ysenih@erpya.com                                     *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 2 of the License, or                *
 * (at your option) any later version.                                              *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the                     *
 * GNU General Public License for more details.                                     *
 * You should have received a copy of the GNU General Public License                *
 * along with this program. If not, see <https://www.gnu.org/licenses/>.            *
 ************************************************************************************/
package org.spin.pos.homologation.service;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPOS;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.spin.base.Version;
import org.spin.base.util.ConvertUtil;
import org.spin.base.util.ValueUtil;
import org.spin.pos.service.order.OrderManagement;
import org.spin.pos.service.order.OrderUtil;
import org.spin.pos.service.order.ReverseSalesTransaction;
import org.spin.proto.pos.homologation.Order;
import org.spin.proto.pos.homologation.PrintTicketResponse;
import org.spin.proto.pos.homologation.ProcessReverseSalesWithoutPrintRequest;
import org.spin.proto.pos.homologation.ProcessWithoutPrintRequest;
import org.spin.proto.pos.homologation.SimulateProcessOrderRequest;
import org.spin.proto.pos.homologation.SimulateReverseSalesRequest;
import org.spin.proto.pos.homologation.SystemInfo;
import org.spin.service.grpc.util.value.StringManager;
import org.spin.service.grpc.util.value.TimeManager;
import org.spin.service.grpc.util.value.ValueManager;
import org.spin.service.pos.POS;
import org.spin.support.FiscalPrintLocalAPI;
import org.spin.util.fp.FiscalPrinterUtil;

import com.google.protobuf.Value;

public class Service {

	final private static String ITS_OK_SIMULATION = "_Its Ok_";


	public static SystemInfo.Builder getSystemInfo() {
		SystemInfo.Builder builder = SystemInfo.newBuilder();

		// backend info
		builder.setDateVersion(
				ValueManager.getTimestampFromDate(
					TimeManager.getTimestampFromString(
						Version.DATE_VERSION
					)
				)
			)
			.setMainVersion(
				StringManager.getValidString(
					Version.MAIN_VERSION
				)
			)
			.setImplementationVersion(
				StringManager.getValidString(
					Version.IMPLEMENTATION_VERSION
				)
			)
		;
		return builder;
	}


	public static PrintTicketResponse.Builder simulateProcessOrder(SimulateProcessOrderRequest request) {
		PrintTicketResponse.Builder builder = PrintTicketResponse.newBuilder();
		MPOS pos = POS.validateAndGetPOS(request.getPosId(), true);
		OrderUtil.validateAndGetOrder(request.getId());

		AtomicReference<MInvoice> invoiceReference = new AtomicReference<MInvoice>();
		AtomicReference<MOrder> orderReference = new AtomicReference<MOrder>();
		try {
			Trx.run(transactionName -> {
				MOrder salesOrder = OrderManagement.simulateProcessOrder(
					pos,
					request.getId(),
					request.getIsOpenRefund(),
					transactionName
				);
				orderReference.set(salesOrder);

				int invoiceId = salesOrder.getC_Invoice_ID();
				if (invoiceId > 0) {
					MInvoice salesInvoice = new MInvoice(Env.getCtx(), invoiceId, transactionName);
					invoiceReference.set(salesInvoice);
					FiscalPrintLocalAPI fiscalPrintApi = FiscalPrintLocalAPI.newInstance()
						.setAppRegistrationId(
							pos.get_ValueAsInt(
								FiscalPrinterUtil.COLUMNNAME_FiscalPrinter_ID
							)
						)
					;
					Map<String, Object> printDocument = fiscalPrintApi.printFiscalDocument(
						invoiceReference.get()
					);
					Value.Builder printDocumentBuilder = ValueUtil.getProtoValueFromObject(printDocument);
					builder.setResultValues(printDocumentBuilder);
				}

				// Break this simulation and rollback all transactions
				throw new AdempiereException(ITS_OK_SIMULATION);
			});
		} catch (Exception e) {
			if (e.getMessage().equals(ITS_OK_SIMULATION)) {
				// nothing here
			} else {
				// e.printStackTrace();
				throw new AdempiereException(e);
			}
		}

		return builder;
	}


	/**
	 * Process Order from Point of Sales
	 * @param request
	 * @return
	 */
	public static Order.Builder processWithoutPrint(ProcessWithoutPrintRequest request) {
		MPOS pos = POS.validateAndGetPOS(request.getPosId(), true);
		OrderUtil.validateAndGetOrder(request.getId());

		MOrder order = OrderManagement.processOrder(
			pos,
			request.getId(),
			request.getIsOpenRefund(),
			request.getClosingNo(),
			request.getFiscalDocumentNo(),
			request.getFiscalPrinterSerialNo(),
			request.getPrintDate()
		);

		Order.Builder builder = ConvertUtil.convertOrder(order);
		return builder;
	}


	public static PrintTicketResponse.Builder simulateReverseSales(SimulateReverseSalesRequest request) {
		PrintTicketResponse.Builder builder = PrintTicketResponse.newBuilder();
		MPOS pos = POS.validateAndGetPOS(request.getPosId(), true);
		OrderUtil.validateAndGetOrder(request.getId());

		AtomicReference<MInvoice> invoiceReference = new AtomicReference<MInvoice>();
		AtomicReference<MOrder> orderReference = new AtomicReference<MOrder>();
		try {
			Trx.run(transactionName -> {
				int orderId = request.getId();
				MOrder salesOrder = ReverseSalesTransaction.returnCompleteOrder(
					pos,
					orderId,
					request.getDescription(),
					transactionName
				);
				orderReference.set(salesOrder);

				int invoiceId = salesOrder.getC_Invoice_ID();
				if (invoiceId > 0) {
					MInvoice salesInvoice = new MInvoice(Env.getCtx(), invoiceId, transactionName);
					invoiceReference.set(salesInvoice);
					FiscalPrintLocalAPI fiscalPrintApi = FiscalPrintLocalAPI.newInstance()
						.setAppRegistrationId(
							pos.get_ValueAsInt(
								FiscalPrinterUtil.COLUMNNAME_FiscalPrinter_ID
							)
						)
					;
					Map<String, Object> printDocument = fiscalPrintApi.printFiscalDocument(
						invoiceReference.get()
					);
					Value.Builder printDocumentBuilder = ValueUtil.getProtoValueFromObject(printDocument);
					builder.setResultValues(printDocumentBuilder);
				}

				// Break this simulation and rollback all transactions
				throw new AdempiereException(ITS_OK_SIMULATION);
			});
		} catch (Exception e) {
			if (e.getMessage().equals(ITS_OK_SIMULATION)) {
				// nothing here
			} else {
				// e.printStackTrace();
				throw new AdempiereException(e);
			}
		}

		return builder;
	}


	public static Order.Builder processReverseSalesWithoutPrint(ProcessReverseSalesWithoutPrintRequest request) {
		if(request.getId() <= 0) {
			throw new AdempiereException("@C_Order_ID@ @NotFound@");
		}
		int orderId = request.getId();
		MPOS pos = POS.validateAndGetPOS(request.getPosId(), request.getPosUuid(), true);

		AtomicReference<MOrder> returnOrderReference = new AtomicReference<MOrder>();
		Trx.run(transactionName -> {
			MOrder returnOrder = ReverseSalesTransaction.returnCompleteOrder(
				pos,
				orderId,
				request.getDescription(),
				transactionName
			);
			returnOrderReference.set(returnOrder);
		});
		//	Default
		return ConvertUtil.convertOrder(
			returnOrderReference.get()
		);
	}

}
