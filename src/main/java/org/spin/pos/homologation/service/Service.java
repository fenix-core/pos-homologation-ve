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
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPOS;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.spin.base.Version;
import org.spin.base.util.ConvertUtil;
import org.spin.base.util.ValueUtil;
import org.spin.model.MADAppRegistration;
import org.spin.pos.service.order.OrderManagement;
import org.spin.pos.service.order.OrderUtil;
import org.spin.proto.pos.homologation.Order;
import org.spin.proto.pos.homologation.PrintTicketResponse;
import org.spin.proto.pos.homologation.ProcessWithoutPrintRequest;
import org.spin.proto.pos.homologation.SimulateProcessOrderRequest;
import org.spin.proto.pos.homologation.SystemInfo;
import org.spin.service.grpc.util.value.StringManager;
import org.spin.service.grpc.util.value.TimeManager;
import org.spin.service.grpc.util.value.ValueManager;
import org.spin.service.pos.POS;
import org.spin.support.FiscalPrintLocalAPI;
import org.spin.util.fp.FiscalPrinterUtil;
import org.spin.util.text.DataUtils;

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
				salesOrder.getDocStatus();
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
				e.printStackTrace();
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
			request.getIsOpenRefund()
		);

		// overwrite document no on Invoice
		if (order.getC_Invoice_ID() > 0) {
			MInvoice invoice = new MInvoice(Env.getCtx(), order.getC_Invoice_ID(), order.get_TrxName());
			invoice.set_ValueOfColumn(FiscalPrinterUtil.COLUMNNAME_FiscalDocumentNo, request.getFiscalDocumentNo());
			invoice.set_ValueOfColumn(FiscalPrinterUtil.COLUMNNAME_PrintFiscalDocument, "Y");
			invoice.set_ValueOfColumn(FiscalPrinterUtil.COLUMNNAME_FiscalClosingNo, request.getClosingNo());
			// if (request.getPrintDate() != null) {
			// 	invoice.set_ValueOfColumn(FiscalPrinterUtil.COLUMNNAME_FiscalPrintDate, request.getPrintDate());
			// }
			invoice.setDocumentNo(request.getFiscalDocumentNo());
			invoice.saveEx();

			int appPrinterId = invoice.get_ValueAsInt(FiscalPrinterUtil.COLUMNNAME_FiscalPrinter_ID);
			if (appPrinterId <= 0) {
				appPrinterId = pos.get_ValueAsInt(
					FiscalPrinterUtil.COLUMNNAME_FiscalPrinter_ID
				);
			}
			if(appPrinterId > 0) {
				String printerSerialNo = request.getFiscalPrinterSerialNo();
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
				final String completeFiscalDocumentNo = DataUtils.leftPadding(printerSerialNo, 4, "0") + "-" + request.getFiscalDocumentNo();
				invoice.setDocumentNo(completeFiscalDocumentNo);
				invoice.saveEx();
			}
		}

		Order.Builder builder = ConvertUtil.convertOrder(order);
		return builder;
	}

}
