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
 * Contributor(s): Yamel Senih www.erpya.com                                         *
 *************************************************************************************/
package org.spin.pos.homologation.service;

import org.adempiere.core.domains.models.I_AD_Ref_List;
import org.compiere.model.MRefList;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.spin.model.MADAppRegistration;
import org.spin.model.MFPLog;
import org.spin.proto.pos.homologation.PirnterDeviceInfo;
import org.spin.proto.pos.homologation.PrinterLog;
import org.spin.proto.pos.homologation.PrinterModel;
import org.spin.service.grpc.util.value.StringManager;
import org.spin.service.grpc.util.value.TimeManager;
import org.spin.support.FiscalPrintLocalAPI;
import org.spin.util.fp.FiscalPrinterUtil;

/**
 * Class for convert any document
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class Converter {

	public static PrinterModel.Builder convertPinrterModel(String printerModelValue) {
		PrinterModel.Builder builder = PrinterModel.newBuilder();
		if (Util.isEmpty(printerModelValue, true)) {
			return builder;
		}
		final int keyReferenceId = 54322;
		MRefList referenceList = MRefList.get(Env.getCtx(), keyReferenceId, printerModelValue, null);
		if (referenceList == null || referenceList.getAD_Ref_List_ID() <= 0) {
			return builder;
		}
		builder = convertPinrterModel(referenceList);
		return builder;
	}
	public static PrinterModel.Builder convertPinrterModel(MRefList printerModel) {
		PrinterModel.Builder builder = PrinterModel.newBuilder();
		if (printerModel == null || printerModel.getAD_Ref_List_ID() <= 0) {
			return builder;
		}
		builder.setId(
				printerModel.getAD_Ref_List_ID()
			)
			.setUuid(
				StringManager.getValidString(
					printerModel.getUUID()
				)
			)
			.setValue(
				StringManager.getValidString(
					printerModel.getValue()
				)
			)
			.setName(
				StringManager.getValidString(
					printerModel.get_Translation(I_AD_Ref_List.COLUMNNAME_Name)
				)
			)
			.setDescription(
				StringManager.getValidString(
					printerModel.get_Translation(I_AD_Ref_List.COLUMNNAME_Description)
				)
			)
			.setIsActive(
				printerModel.isActive()
			)
		;
		return builder;
	}

	/*
	public static PrinterPort.Builder convertPinrterPort(String printerPortValue) {
		PrinterPort.Builder builder = PrinterPort.newBuilder();
		if (Util.isEmpty(printerPortValue, true)) {
			return builder;
		}
		final int keyReferenceId = 54323;
		MRefList referenceList = MRefList.get(Env.getCtx(), keyReferenceId, printerPortValue, null);
		if (referenceList == null || referenceList.getAD_Ref_List_ID() <= 0) {
			return builder;
		}
		builder = convertPinrterPort(referenceList);
		return builder;
	}
	public static PrinterPort.Builder convertPinrterPort(MRefList printerPort) {
		PrinterPort.Builder builder = PrinterPort.newBuilder();
		if (printerPort == null || printerPort.getAD_Ref_List_ID() <= 0) {
			return builder;
		}
		builder.setId(
				printerPort.getAD_Ref_List_ID()
			)
			.setUuid(
				StringManager.getValidString(
					printerPort.getUUID()
				)
			)
			.setValue(
				StringManager.getValidString(
					printerPort.getValue()
				)
			)
			.setName(
				StringManager.getValidString(
					printerPort.get_Translation(I_AD_Ref_List.COLUMNNAME_Name)
				)
			)
			.setDescription(
				StringManager.getValidString(
					printerPort.get_Translation(I_AD_Ref_List.COLUMNNAME_Description)
				)
			)
			.setIsActive(
				printerPort.isActive()
			)
		;
		return builder;
	}
	*/

	public static PirnterDeviceInfo.Builder convertPrinterDeviceInfo(MADAppRegistration appRegistration) {
		PirnterDeviceInfo.Builder builder = PirnterDeviceInfo.newBuilder();
		if (appRegistration == null || appRegistration.getAD_AppRegistration_ID() <= 0) {
			return builder;
		}
		builder.setId(
				appRegistration.getAD_AppRegistration_ID()
			)
			.setUuid(
				StringManager.getValidString(
					appRegistration.getUUID()
				)
			)
			.setSerialNo(
				StringManager.getValidString(
					appRegistration.getValue()
				)
			)
			.setName(
				StringManager.getValidString(
					appRegistration.getName()
				)
			)
			.setDescription(
				StringManager.getValidString(
					appRegistration.getDescription()
				)
			)
			.setIsActive(
				appRegistration.isActive()
			)
			.setCustomPrinterPort(
				appRegistration.get_ValueAsString(
					FiscalPrinterUtil.COLUMNNAME_ECA05_CustomLocalPort
				)
			)
			.setHostName(
				StringManager.getValidString(
					appRegistration.getHost()
				)
			)
			.setHostPort(
				appRegistration.getPort()
			)
			.setRequestTimeout(
				appRegistration.getTimeout()
			)
			.setIsReadResponseAfterSend(
				appRegistration.get_ValueAsBoolean(
					FiscalPrinterUtil.COLUMNNAME_ECA05_ReadResponseAfterSend
				)
			)
		;

		String printerName = FiscalPrintLocalAPI.getPrinterName(appRegistration);
		builder.setPrinterName(
			StringManager.getValidString(printerName)
		);

		String printerResponseName = FiscalPrintLocalAPI.getPrinterResponseName(appRegistration);
		builder.setPrinterResponseName(
			StringManager.getValidString(printerResponseName)
		);

		final String printerModel = appRegistration.get_ValueAsString(
			FiscalPrinterUtil.COLUMNNAME_ECA05_Model
		);
		if (!Util.isEmpty(printerModel, true)) {
			PrinterModel.Builder printerModelBuilder = convertPinrterModel(printerModel);
			builder.setPrinterModel(printerModelBuilder);
		}

		String printerPort = FiscalPrintLocalAPI.getPortrName(appRegistration);
		builder.setPrinterPort(
			StringManager.getValidString(printerPort)
		);

		return builder;
	}


	public static PrinterLog.Builder convertPrinterLog(MFPLog printerLog) {
		PrinterLog.Builder builder = PrinterLog.newBuilder();
		if (printerLog == null || printerLog.getFP_Log_ID() <= 0) {
			return builder;
		}

		builder.setId(
				printerLog.getFP_Log_ID()
			)
			.setUuid(
				StringManager.getValidString(
					printerLog.getUUID()
				)
			)
			.setFiscalPrinterCode(
				StringManager.getValidString(
					printerLog.getFiscalPrinterCode()
				)
			)
			.setFiscalPrinterName(
				StringManager.getValidString(
					printerLog.getFiscalPrinterName()
				)
			)
			.setFiscalPrinterResutl(
				StringManager.getValidString(
					printerLog.getFiscalPrinterResult()
				)
			)
			.setPrintDate(
				TimeManager.convertDateToValue(
					printerLog.getFiscalPrintDate()
				)
			)
			.setDocumentUuid(
				StringManager.getValidString(
					printerLog.getFiscalDocumentUUID()
				)
			)
			.setDocumentNo(
				StringManager.getValidString(
					printerLog.getFiscalDocumentNo()
				)
			)
			.setLastInvoiceNo(
				StringManager.getStringFromObject(
					printerLog.getLastFiscalInvoiceNo()
				)
			)
			.setLastCreditMemoNo(
				StringManager.getValidString(
					printerLog.getLastFiscalCreditMemoNo()
				)
			)
			.setIsError(
				printerLog.isError()
			)
		;
		return builder;
	}

}
