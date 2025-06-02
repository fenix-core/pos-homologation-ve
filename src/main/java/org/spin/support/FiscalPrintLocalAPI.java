/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2020 E.R.P. Consultores y Asociados.                    *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.support;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.adempiere.exceptions.AdempiereException;
// import org.apache.kafka.clients.producer.ProducerRecord;
import org.adempiere.core.domains.models.I_C_Invoice;
import org.compiere.model.MClientInfo;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrg;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.spin.fp.util.event.PrinterEvent;
import org.spin.model.MADAppRegistration;
import org.spin.model.MFPLog;
import org.spin.support.fp.FiscalDocument;
import org.spin.support.fp.FiscalDocumentResult;
import org.spin.support.fp.FiscalReport;
import org.spin.support.fp.FiscalSetup;
import org.spin.support.fp.FiscalSetup.SetupType;
import org.spin.support.fp.IFiscalPrinterResponse;
import org.spin.util.fp.FiscalPrinterUtil;
import org.spin.util.text.DataUtils;

/**
 * Fiscal print API for send documents to fiscal print using gRPC protocol and FiscalPrint-Client project
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		@see https://gitlab.com/erpya-adempiere/fiscal-printer/FiscalPrinter-Client
 */
public class FiscalPrintLocalAPI
{

	// /**	Registration Id	*/
	// private int registrationId = 0;
	/** Static Logger					*/
	private CLogger log = CLogger.getCLogger (FiscalPrintLocalAPI.class);
	// /**	Port	*/
	// private int port;
	/**	Printer Name	*/
	private String printerName = null;
	// /**	Printer Response Name	*/
	// private String printerResponseName = null;
	/**	Port Name	*/
	private String portName = null;
	/**	Printer Model	*/
	private String printerModel = null;
	// /**	Host	*/
	// private String host = null;
	// /**	Timeout	*/
	// private int defaultTimeout = 0;
	/**	Printer Name	*/
	private final String PRINTER_NAME = "printer_name";
	/**	Printer Response Name	*/
	private final String PRINTER_RESPONSE_NAME = "printer_response_name";
	// /**	Port Name		*/
	// private final String PORT_NAME = "port_name";
	// /**	Read response Automatically	*/
	// private boolean readResponseAfterSend = false;
	/**	Date formatter	*/
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    // /**	Application Type	*/
    // private String applicationType = null;
    /**	Client Info	*/
    private MClientInfo clientInfo;



	public static FiscalPrintLocalAPI newInstance() {
		return new FiscalPrintLocalAPI();
	}


	// // @Override
	// public String testConnection() {
	// 	//	Send X Report
	// 	printFiscalReport(new FiscalReport(SupportedCommand.X_Report));
	// 	return "Ok";
	// }

	// // @Override
	// public void setAppRegistrationId(int registrationId) {
	// 	this.registrationId = registrationId;
	// 	MADAppRegistration registration = MADAppRegistration.getById(Env.getCtx(), getAppRegistrationId(), null);
	// 	applicationType = registration.getApplicationType();
	// 	port = registration.getPort();
	// 	host = registration.getHost();
	// 	portName = registration.get_ValueAsString(FiscalPrinterUtil.COLUMNNAME_ECA05_LocalPort);
	// 	printerModel = registration.get_ValueAsString(FiscalPrinterUtil.COLUMNNAME_ECA05_Model);
	// 	if(!Util.isEmpty(portName)) {
	// 		if(portName.equals(FiscalPrinterUtil.COLUMNNAME_CUSTOM_PORT)) {
	// 			portName = registration.get_ValueAsString(FiscalPrinterUtil.COLUMNNAME_ECA05_CustomLocalPort);
	// 		}
	// 	}
	// 	if(Util.isEmpty(portName)) {
	// 		portName = registration.getParameterValue(PORT_NAME);
	// 	}
	// 	printerName = getPrinterName(registration);
	// 	printerResponseName = getPrinterResponseName(registration);
	// 	defaultTimeout = registration.getTimeout();
	// 	readResponseAfterSend = registration.get_ValueAsBoolean(FiscalPrinterUtil.COLUMNNAME_ECA05_ReadResponseAfterSend);
	// 	clientInfo = MClientInfo.get(registration.getCtx(), registration.getAD_Client_ID());
	// }

	// // @Override
	// public int getAppRegistrationId() {
	// 	return registrationId;
	// }

	private boolean showSalesRep() {
		return clientInfo.get_ValueAsBoolean(FiscalPrinterUtil.COLUMNNAME_ECA05_ShowSalesRep);
	}

	private boolean showWarehouse() {
		return clientInfo.get_ValueAsBoolean(FiscalPrinterUtil.COLUMNNAME_ECA05_ShowWarehouse);
	}

	private boolean showTerminal() {
		return clientInfo.get_ValueAsBoolean(FiscalPrinterUtil.COLUMNNAME_ECA05_ShowTerminal);
	}

	private boolean showSOReference() {
		return clientInfo.get_ValueAsBoolean(FiscalPrinterUtil.COLUMNNAME_ECA05_ShowSOReference);
	}

	private boolean showDocumentNote() {
		return clientInfo.get_ValueAsBoolean(FiscalPrinterUtil.COLUMNNAME_ECA05_ShowDocumentNote);
	}

	private boolean showItemQuantity() {
		return clientInfo.get_ValueAsBoolean(FiscalPrinterUtil.COLUMNNAME_ECA05_ShowItemQuantity);
	}

	/**
	 * Get Invoice Header
	 * @param fiscalDocument
	 * @return
	 * @return Map<String,Object>
	 */
	private Map<String, Object> getInvoice(FiscalDocument fiscalDocument) {
		Map<String, Object> document = new HashMap<String, Object>();
		if(fiscalDocument.getDocumentType().equals(FiscalDocument.DocumentType.INVOICE)) {
			document.put("document_type", "invoice");
		} else if(fiscalDocument.getDocumentType().equals(FiscalDocument.DocumentType.CREDIT_MEMO)) {
			document.put("document_type", "credit_memo");
			//	Reversal document no
			if(!Util.isEmpty(fiscalDocument.getReversalDocumentNo())) {
				document.put("reversal_document_no", fiscalDocument.getReversalDocumentNo());
			}
			//	Reversal printer value
			if(!Util.isEmpty(fiscalDocument.getReversalFiscalPrinterNo())) {
				document.put("reversal_fiscal_printer_no", fiscalDocument.getReversalFiscalPrinterNo());
			}
			//	Reversal document date
			if(fiscalDocument.getReversalDocumentDate() != null) {
				document.put("reversal_document_date", DATE_FORMAT.format(fiscalDocument.getReversalDocumentDate()));
			}
		} else if(fiscalDocument.getDocumentType().equals(FiscalDocument.DocumentType.DEBIT_MEMO)) {
			document.put("document_type", "debit_memo");
		}
		//	Document No
		if(!Util.isEmpty(fiscalDocument.getDocumentNo())) {
			document.put("document_no", fiscalDocument.getDocumentNo());
		}
		//	Document Date
		if(fiscalDocument.getDocumentDate() != null) {
			document.put("document_date", DATE_FORMAT.format(fiscalDocument.getDocumentDate()));
		}
		//	Address 1
		if(!Util.isEmpty(fiscalDocument.getAddress1())) {
			document.put("address_1", fiscalDocument.getAddress1());
		}
		//	Address 2
		if(!Util.isEmpty(fiscalDocument.getAddress2())) {
			document.put("address_2", fiscalDocument.getAddress2());
		}
		//	Address 3
		if(!Util.isEmpty(fiscalDocument.getAddress3())) {
			document.put("address_3", fiscalDocument.getAddress3());
		}
		//	Address 4
		if(!Util.isEmpty(fiscalDocument.getAddress4())) {
			document.put("address_4", fiscalDocument.getAddress4());
		}
		//	Business Partner Name
		if(!Util.isEmpty(fiscalDocument.getBusinessPartnerName())) {
			document.put("business_partner_name", fiscalDocument.getBusinessPartnerName());
		}
		//	Tax ID
		if(!Util.isEmpty(fiscalDocument.getBusinessPartnerTaxId())) {
			document.put("business_partner_tax_id", fiscalDocument.getBusinessPartnerTaxId());
		}
		//	DUNS
		if(!Util.isEmpty(fiscalDocument.getBusinessPartnerDuns())) {
			document.put("business_partner_duns", fiscalDocument.getBusinessPartnerDuns());
		}
		//	City Name
		if(!Util.isEmpty(fiscalDocument.getCityName())) {
			document.put("city_name", fiscalDocument.getCityName());
		}
		//	Country Name
		if(!Util.isEmpty(fiscalDocument.getCountryName())) {
			document.put("country_name", fiscalDocument.getCountryName());
		}
		//	Region Name
		if(!Util.isEmpty(fiscalDocument.getRegionName())) {
			document.put("region_name", fiscalDocument.getRegionName());
		}
		//	Description
		if(!Util.isEmpty(fiscalDocument.getDescription())) {
			document.put("description", fiscalDocument.getDescription());
		}
		//	Document Note
		if(!Util.isEmpty(fiscalDocument.getDocumentNote()) && showDocumentNote()) {
			document.put("document_note", fiscalDocument.getDocumentNote());
		}
		//	Document UUID
		if(!Util.isEmpty(fiscalDocument.getDocumentUuid())) {
			document.put("document_uuid", fiscalDocument.getDocumentUuid());
		}
		//	PO Reference No
		if(!Util.isEmpty(fiscalDocument.getPoReferenceNo()) && showSOReference()) {
			document.put("po_reference_no", fiscalDocument.getPoReferenceNo());
		}
		//	SO Reference No
		if(!Util.isEmpty(fiscalDocument.getSoReferenceNo()) && showSOReference()) {
			document.put("so_reference_no", fiscalDocument.getSoReferenceNo());
		}
		//	Reversal Document No
		if(!Util.isEmpty(fiscalDocument.getReversalDocumentNo())) {
			document.put("reversal_document_no", fiscalDocument.getReversalDocumentNo());
		}
		//	Reversal Printer No
		if(!Util.isEmpty(fiscalDocument.getReversalFiscalPrinterNo())) {
			document.put("reversal_fiscal_printer_no", fiscalDocument.getReversalFiscalPrinterNo());
		}
		//	Reversal Printer No
		if(fiscalDocument.getReversalDocumentDate() != null) {
			document.put("reversal_document_date", DATE_FORMAT.format(fiscalDocument.getReversalDocumentDate()));
		}
		//	Sales representative value
		if(!Util.isEmpty(fiscalDocument.getSalesRepresentativeValue()) && showSalesRep()) {
			document.put("sales_representative_value", fiscalDocument.getSalesRepresentativeValue());
		}
		//	Sales representative name
		if(!Util.isEmpty(fiscalDocument.getSalesRepresentativeName()) && showSalesRep()) {
			document.put("sales_representative_name", fiscalDocument.getSalesRepresentativeName());
		}
		//	Sales Region
		if(!Util.isEmpty(fiscalDocument.getSalesRegionValue()) && showSalesRep()) {
			document.put("sales_region_value", fiscalDocument.getSalesRegionValue());
		}
		//	Sales Region Name
		if(!Util.isEmpty(fiscalDocument.getSalesRegionName()) && showSalesRep()) {
			document.put("sales_region_name", fiscalDocument.getSalesRegionName());
		}
		//	Payment Term
		if(!Util.isEmpty(fiscalDocument.getPaymentTerm())) {
			document.put("payment_term", fiscalDocument.getPaymentTerm());
		}
		//	Document Type Name
		if(!Util.isEmpty(fiscalDocument.getDocumentTypeName())) {
			document.put("document_type_name", fiscalDocument.getDocumentTypeName());
		}
		//	Delivery Address
		if(!Util.isEmpty(fiscalDocument.getDeliveryAddress())) {
			document.put("delivery_address", fiscalDocument.getDeliveryAddress());
		}
		//	Delivery Phone
		if(!Util.isEmpty(fiscalDocument.getDeliveryPhone())) {
			document.put("delivery_phone", fiscalDocument.getDeliveryPhone());
		}
		//	Warehouse Name
		if(!Util.isEmpty(fiscalDocument.getWarehouseName()) && showWarehouse()) {
			document.put("warehouse_name", fiscalDocument.getWarehouseName());
		}
		//	Amount in words
		if(!Util.isEmpty(fiscalDocument.getAmountInWords())) {
			document.put("amount_in_words", fiscalDocument.getAmountInWords());
		}
		//	Total lines
		if(fiscalDocument.getTotalLines() != null) {
			document.put("total_lines", fiscalDocument.getTotalLines().doubleValue());
		}
		//	Grand total
		if(fiscalDocument.getGrandTotal() != null) {
			document.put("grand_total", fiscalDocument.getGrandTotal().doubleValue());
		}
		//	POS Name
		if(!Util.isEmpty(fiscalDocument.getPosName()) && showTerminal()) {
			document.put("pos_name", fiscalDocument.getPosName());
		}
		//	Discount
		document.put("discount_printed", fiscalDocument.isDiscountPrinted());
		//	Product Quantity
		if(showItemQuantity()) {
			document.put("products_quantities", fiscalDocument.getProductQuantities());
		}
		return document;
	}
	
	/**
	 * Get Invoice Lines
	 * @param fiscalDocument
	 * @return
	 * @return Map<String,Object>
	 */
	private List<Map<String, Object>> getInvoiceLines(FiscalDocument fiscalDocument) {
		List<Map<String, Object>> lines = new ArrayList<Map<String,Object>>();
		fiscalDocument.getFiscalDocumentLines().forEach(fiscalDocumentLine -> {
			Map<String, Object> documentLine = new HashMap<String, Object>();
			//	Product Value
			if(!Util.isEmpty(fiscalDocumentLine.getProductValue())) {
				documentLine.put("product_value", fiscalDocumentLine.getProductValue());
			}
			//	Product Name
			if(!Util.isEmpty(fiscalDocumentLine.getProductName())) {
				documentLine.put("product_name", fiscalDocumentLine.getProductName());
			}
			//	Product Description
			if(!Util.isEmpty(fiscalDocumentLine.getProductDescription())) {
				documentLine.put("product_description", fiscalDocumentLine.getProductDescription());
			}
			//	Product BarCode
			if(!Util.isEmpty(fiscalDocumentLine.getProductBarCode())) {
				documentLine.put("product_barcode", fiscalDocumentLine.getProductBarCode());
			}
			//	Line Description
			if(!Util.isEmpty(fiscalDocumentLine.getLineDescription())) {
				documentLine.put("line_description", fiscalDocumentLine.getLineDescription());
			}
			//	Discount
			if(fiscalDocumentLine.getDiscount() != null) {
				documentLine.put("discount", fiscalDocumentLine.getDiscount().doubleValue());
			}
			//	Product Price
			documentLine.put("product_price", Optional.ofNullable(fiscalDocumentLine.getProductPrice()).orElse(Env.ZERO).doubleValue());
			//	Product Price List
			documentLine.put("product_price_list", Optional.ofNullable(fiscalDocumentLine.getProductPriceList()).orElse(Env.ZERO).doubleValue());
			//	Tax Rate
			if(fiscalDocumentLine.getTaxRate() != null) {
				documentLine.put("tax_rate", fiscalDocumentLine.getTaxRate().doubleValue());
			}
			//	Quantity
			if(fiscalDocumentLine.getQuantity() != null) {
				documentLine.put("quantity", fiscalDocumentLine.getQuantity().doubleValue());
			}
			//	Add Line
			lines.add(documentLine);
		});
		
		return lines;
	}
	
	/**
	 * Get Invoice Taxes
	 * @param fiscalDocument
	 * @return
	 * @return List<Map<String, Object>>
	 */
	private List<Map<String, Object>> getInvoiceTaxes(FiscalDocument fiscalDocument) {
		List<Map<String, Object>> lines = new ArrayList<Map<String,Object>>();
		//	Add Taxes
		fiscalDocument.getFiscalDocumentTaxes().forEach(fiscalDocumentTax -> {
			Map<String, Object> documentLine = new HashMap<String, Object>();
			//	Tax Value
			if(!Util.isEmpty(fiscalDocumentTax.getTaxValue())) {
				documentLine.put("tax_value", fiscalDocumentTax.getTaxValue());
			}
			//	Tax Name
			if(!Util.isEmpty(fiscalDocumentTax.getTaxName())) {
				documentLine.put("tax_name", fiscalDocumentTax.getTaxName());
			}
			//	Tax Rate
			if(fiscalDocumentTax.getTaxRate() != null) {
				documentLine.put("tax_rate", fiscalDocumentTax.getTaxRate().doubleValue());
			}
			//	Tax Base Amount
			if(fiscalDocumentTax.getTaxBaseAmount() != null) {
				documentLine.put("tax_base_amount", fiscalDocumentTax.getTaxBaseAmount().doubleValue());
			}
			//	Tax Amount
			if(fiscalDocumentTax.getTaxAmount() != null) {
				documentLine.put("tax_amount", fiscalDocumentTax.getTaxAmount().doubleValue());
			}
			//	Add to Document
			lines.add(documentLine);
		});
		return lines;
	}
	
	/**
	 * Get Invoice Payments
	 * @param fiscalDocument
	 * @return
	 * @return List<Map<String, Object>>
	 */
	private List<Map<String, Object>> getInvoicePayments(FiscalDocument fiscalDocument) {
		List<Map<String, Object>> lines = new ArrayList<Map<String,Object>>();
		//	Add Taxes
		fiscalDocument.getFiscalDocumentPayments().forEach(payment -> {
			Map<String, Object> documentLine = new HashMap<String, Object>();
			//	Reference No
			if(!Util.isEmpty(payment.getReferenceNo())) {
				documentLine.put("reference_no", payment.getReferenceNo());
			}
			//	Payment Date
			documentLine.put("payment_date", DATE_FORMAT.format(payment.getPaymentDate()));
			//	Description
			if(!Util.isEmpty(payment.getDescription())) {
				documentLine.put("description", payment.getDescription());
			}
			//	Currency Code
			if(!Util.isEmpty(payment.getCurrencyCode())) {
				documentLine.put("currency_code", payment.getCurrencyCode());
			}
			//	Tender Type
			if(!Util.isEmpty(payment.getTenderType())) {
				documentLine.put("tender_type_value", payment.getTenderType());
			}
			//	Tender Type Name
			if(!Util.isEmpty(payment.getTenderTypeName())) {
				documentLine.put("tender_type_name", payment.getTenderTypeName());
			}
			//	Payment Method
			if(!Util.isEmpty(payment.getPaymentMethodValue())) {
				documentLine.put("payment_method_value", payment.getPaymentMethodValue());
			}
			//	Tender Type Name
			if(!Util.isEmpty(payment.getPaymentMethodName())) {
				documentLine.put("payment_method_name", payment.getPaymentMethodName());
			}
			//	Amount
			documentLine.put("amount", Optional.ofNullable(payment.getAmount()).orElse(Env.ZERO).doubleValue());
			//	Converted Amount
			documentLine.put("converted_amount", Optional.ofNullable(payment.getConvertedAmount()).orElse(Env.ZERO).doubleValue());
			//	Add to Document
			lines.add(documentLine);
		});
		return lines;
	}


	public Map<String, Object> printFiscalDocument(MInvoice invoiceDocument) {
		Map<String, Object> document = this.printFiscalDocument(
			new FiscalDocument(invoiceDocument)
		);
		return document;
	}


	public Map<String, Object> printFiscalDocument(FiscalDocument fiscalDocument) {
		log.fine("Fiscal Document: " + fiscalDocument);
		Map<String, Object> document = new HashMap<String, Object>();
		try {
			document.put("printer_name", printerName);
			if(!Util.isEmpty(portName)) {
				document.put("port_name", portName);
			}
			if(!Util.isEmpty(printerModel)) {
				document.put("printer_model", printerModel);
			}
			//	Put header
			document.put("invoice", getInvoice(fiscalDocument));
			//	Lines
			document.put("lines", getInvoiceLines(fiscalDocument));
			//	Taxes
			document.put("taxes", getInvoiceTaxes(fiscalDocument));
			//	Invoice Payments
			document.put("payments", getInvoicePayments(fiscalDocument));
			//	
			// final ProducerRecord record = new ProducerRecord<String, Map<String , Object>>(printerName, "fiscal_printer_document", document);
			// producer.send(record);
			// readPrinterResponses();
		} catch (Exception e) {
			throw new AdempiereException(e);
		}
		return document;
	}



	public void printFiscalReport(FiscalReport fiscalReport) {
		log.fine("Fiscal Report: " + fiscalReport);
		try {
			Map<String, Object> report = new HashMap<String, Object>();
			report.put("printer_name", printerName);
			if(!Util.isEmpty(portName)) {
				report.put("port_name", portName);
			}
			if(!Util.isEmpty(printerModel)) {
				report.put("printer_model", printerModel);
			}
			//	Put Report
			if(fiscalReport.getReportType().equals(FiscalReport.ReportType.REPORT_X)) {
				report.put("document_type", "x_report");
			} else if(fiscalReport.getReportType().equals(FiscalReport.ReportType.REPORT_Z)) {
				report.put("document_type", "z_report");
			} else if(fiscalReport.getReportType().equals(FiscalReport.ReportType.REPORT_Z_DATE_RANGE)) {
				report.put("document_type", "z_report_date_range");
				report.put("z_report_date_from", DATE_FORMAT.format(fiscalReport.getReportDateFrom()));
				report.put("z_report_date_to", DATE_FORMAT.format(fiscalReport.getReportDateTo()));
			} else if(fiscalReport.getReportType().equals(FiscalReport.ReportType.REPORT_Z_NUMBER_RANGE)) {
				report.put("document_type", "z_report_number_range");
				report.put("z_report_number_from", fiscalReport.getReportNumberFrom());
				report.put("z_report_number_to", fiscalReport.getReportNumberTo());
			} else if(fiscalReport.getReportType().equals(FiscalReport.ReportType.REPRINT_INVOICE_DATE_RANGE)) {
				report.put("document_type", "reprint_invoice_report_date_range");
				report.put("reprint_invoice_report_date_from", DATE_FORMAT.format(fiscalReport.getReportDateFrom()));
				report.put("reprint_invoice_report_date_to", DATE_FORMAT.format(fiscalReport.getReportDateTo()));
			} else if(fiscalReport.getReportType().equals(FiscalReport.ReportType.REPRINT_INVOICE_NUMBER_RANGE)) {
				report.put("document_type", "reprint_invoice_report_number_range");
				report.put("reprint_invoice_report_number_from", fiscalReport.getReportNumberFrom());
				report.put("reprint_invoice_report_number_to", fiscalReport.getReportNumberTo());
			} else if(fiscalReport.getReportType().equals(FiscalReport.ReportType.REPRINT_DEBIT_MEMO_DATE_RANGE)) {
				report.put("document_type", "reprint_debit_memo_report_date_range");
				report.put("reprint_debit_memo_report_date_from", DATE_FORMAT.format(fiscalReport.getReportDateFrom()));
				report.put("reprint_debit_memo_report_date_to", DATE_FORMAT.format(fiscalReport.getReportDateTo()));
			} else if(fiscalReport.getReportType().equals(FiscalReport.ReportType.REPRINT_DEBIT_MEMO_NUMBER_RANGE)) {
				report.put("document_type", "reprint_debit_memo_report_number_range");
				report.put("reprint_debit_memo_report_number_from", fiscalReport.getReportNumberFrom());
				report.put("reprint_debit_memo_report_number_to", fiscalReport.getReportNumberTo());
			} else if(fiscalReport.getReportType().equals(FiscalReport.ReportType.REPRINT_CREDIT_MEMO_DATE_RANGE)) {
				report.put("document_type", "reprint_credit_memo_report_date_range");
				report.put("reprint_credit_memo_report_date_from", DATE_FORMAT.format(fiscalReport.getReportDateFrom()));
				report.put("reprint_credit_memo_report_date_to", DATE_FORMAT.format(fiscalReport.getReportDateTo()));
			} else if(fiscalReport.getReportType().equals(FiscalReport.ReportType.REPRINT_CREDIT_MEMO_NUMBER_RANGE)) {
				report.put("document_type", "reprint_credit_memo_report_number_range");
				report.put("reprint_credit_memo_report_number_from", fiscalReport.getReportNumberFrom());
				report.put("reprint_credit_memo_report_number_to", fiscalReport.getReportNumberTo());
			} else if(fiscalReport.getReportType().equals(FiscalReport.ReportType.REPRINT_DOCUMENT_TAX_ID)) {
				report.put("document_type", "report_reprint_document_by_taxid");
				report.put("report_reprint_document_taxid", fiscalReport.getTaxId());
			}
			//	For Historic
			if(fiscalReport.getReportType().equals(FiscalReport.ReportType.REPORT_Z_DATE_RANGE)
					|| fiscalReport.getReportType().equals(FiscalReport.ReportType.REPORT_Z_NUMBER_RANGE)) {
				if(fiscalReport.getReportType().equals(FiscalReport.ReportType.DETAILED)) {
					report.put("historic_document_type", "historic_document_type_detailed");
				} else if(fiscalReport.getReportType().equals(FiscalReport.ReportType.MONTHLY)) {
					report.put("historic_document_type", "historic_document_type_monthly");
				} else if(fiscalReport.getReportType().equals(FiscalReport.ReportType.SUMMARIZED)) {
					report.put("historic_document_type", "historic_document_type_summarized");
				}
			}
			//
		} catch (Exception e) {
			throw new AdempiereException(e);
		}
	}

	public void setupFiscalPrint(FiscalSetup setupCommand) {
		log.fine("Fiscal setup: " + setupCommand);
		try {
			Map<String, Object> setup = new HashMap<String, Object>();
			setup.put("printer_name", printerName);
			if(!Util.isEmpty(portName)) {
				setup.put("port_name", portName);
			}
			if(!Util.isEmpty(printerModel)) {
				setup.put("printer_model", printerModel);
			}
			//	
			if(setupCommand.getSetupType().equals(SetupType.CUT_PAPER)) {
				setup.put("type", "cut_paper");
			} else if(setupCommand.getSetupType().equals(SetupType.GET_STATUS)) {
				setup.put("type", "get_status");
			} else if(setupCommand.getSetupType().equals(SetupType.OPEN_DRAWER)) {
				setup.put("type", "open_drawer");
			} else if(setupCommand.getSetupType().equals(SetupType.RESET_PRINTER)) {
				setup.put("type", "reset_printer");
			} else if(setupCommand.getSetupType().equals(SetupType.SET_TIME)) {
				setup.put("type", "set_time");
			} else if(setupCommand.getSetupType().equals(SetupType.REVERSE_INVOICE)) {
				setup.put("type", "reverse_invoice");
			} else if(setupCommand.getSetupType().equals(SetupType.REVERSE_CREDIT_MEMO)) {
				setup.put("type", "reverse_credit_memo");
			} else if(setupCommand.getSetupType().equals(SetupType.REVERSE_DEBIT_MEMO)) {
				setup.put("type", "reverse_debit_memo");
			} else if(setupCommand.getSetupType().equals(SetupType.FIRMWARE_INFORMATION)) {
				setup.put("type", "firmware_information");
			} else if(setupCommand.getSetupType().equals(SetupType.MEMORY_STATUS)) {
				setup.put("type", "memory_status");
			} else if(setupCommand.getSetupType().equals(SetupType.FIRMWARE_SET_VALUES)) {
				setup.put("type", "firmware_set_values");
				setup.put("firmware_version", setupCommand.getFirmwareVersion());
			}
			// final ProducerRecord record = new ProducerRecord<String, Map<String , Object>>(printerName, "fiscal_printer_setup", setup);
		} catch (Exception e) {
			throw new AdempiereException(e);
		}
	}

	// @Override
	public void messageReceived(PrinterEvent ev) {
		if(ev == null) {
			return;
		}
		//	
		if(ev.getEventType() == PrinterEvent.PRINT_DOCUMENT) {
			FiscalDocumentResult document = ev.getDocument();
			if(document.getDocumentUuid() != null
					&& document.getDocumentNo()!= null) {
				saveDocumentResult(document, ev.getFiscalPrinterResponse());
			}
		}
	}
	
	private void saveDocumentResult(FiscalDocumentResult document, IFiscalPrinterResponse response) {
		Trx.run(transactionName -> {
			if(!Util.isEmpty(document.getDocumentUuid()) 
					&& !document.getIsError()
					&& !Util.isEmpty(document.getDocumentNo())) {
				MInvoice invoice = new Query(Env.getCtx(), I_C_Invoice.Table_Name, I_C_Invoice.COLUMNNAME_UUID + " = ?", transactionName)
						.setParameters(document.getDocumentUuid())
						.setClient_ID()
						.<MInvoice>first();
				if(invoice != null) {
					try {
						invoice.set_ValueOfColumn(FiscalPrinterUtil.COLUMNNAME_FiscalDocumentNo, document.getDocumentNo());
						invoice.set_ValueOfColumn(FiscalPrinterUtil.COLUMNNAME_PrintFiscalDocument, "Y");
						invoice.set_ValueOfColumn(FiscalPrinterUtil.COLUMNNAME_FiscalClosingNo, document.getClosingNo());
						if(document.getPrintDate() != null) {
							invoice.set_ValueOfColumn(FiscalPrinterUtil.COLUMNNAME_FiscalPrintDate, document.getPrintDate());
						}
						invoice.setDocumentNo(document.getDocumentNo());
						invoice.saveEx();
						log.info("Document is Printed: " + response);
					} catch (Exception e) {
						log.severe("Print document error: " + e.getLocalizedMessage());
						//	Save Log
						MFPLog.addToLog(Env.getCtx(), response, e.getLocalizedMessage(), transactionName);
					}
					//	try save document
					try {
						if(invoice.get_ValueAsInt(FiscalPrinterUtil.COLUMNNAME_FiscalPrinter_ID) > 0) {
							String printerSerialNo = document.getFiscalPrinterSerialNo();
							if(Util.isEmpty(printerSerialNo)) {
								MADAppRegistration registeredApplication = MADAppRegistration.getById(Env.getCtx(), invoice.get_ValueAsInt(FiscalPrinterUtil.COLUMNNAME_FiscalPrinter_ID), transactionName);
								printerSerialNo = Optional.ofNullable(registeredApplication.getValue()).orElse("");
							}
							if(printerSerialNo.length() > 4) {
								printerSerialNo = printerSerialNo.substring(printerSerialNo.length() - 4);
							}
							String completefiscalDocumentNo = DataUtils.leftPadding(printerSerialNo, 4, "0") + "-" + document.getDocumentNo();
							invoice.setDocumentNo(completefiscalDocumentNo);
							invoice.saveEx();
						}
					} catch (Exception e) {
						log.severe("Saving document no error: " + e.getLocalizedMessage());
						//	Save Log
						MFPLog.addToLog(Env.getCtx(), document, e.getLocalizedMessage(), transactionName);
					}
				}
			} else if(document.getIsError()) {
				log.severe(getPrettyError(response) + "Printer error: (Printer error flag)");
				MFPLog.addToLog(Env.getCtx(), document, getPrettyError(response) + "Printer error: (Printer error flag)", transactionName);
			} else if(Util.isEmpty(response.getDocumentNo())) {
				log.severe(getPrettyError(response) + "Printer error: (Last Document No not found)");
				MFPLog.addToLog(Env.getCtx(), document, getPrettyError(response) + "Printer error: (Last Document No not found)", transactionName);
			} else {
				log.severe(getPrettyError(response) + "Document UUID Not found");
				MFPLog.addToLog(Env.getCtx(), document, getPrettyError(response) + "Document UUID Not found", transactionName);
			}
		});
	}
	
	/**
	 * Get pretty error message
	 * @param response
	 * @return
	 * @return String
	 */
	private String getPrettyError(IFiscalPrinterResponse response) {
		if(!Optional.ofNullable(response).isPresent()) {
			return "";
		}
		Timestamp printDate = null;
		if(response.getPrintDate() != null) {
			printDate = response.getPrintDate();
		}
		//	
		return "Document Uuid: " + response.getDocumentUuid() + "\n"
			+ "Fiscal Printer No: " + response.getFiscalPrinterSerialNo() + "\n"
			+ "Printer Name: " + response.getPrinterName() + "\n"
			+ "Last Document No: " + response.getDocumentNo() + "\n"
			+ (Optional.ofNullable(printDate).isPresent()
					? "Print Date: " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(printDate) + "\n"
							: "")
			+ "Result: " + response.getErrorMessage() + "\n";
	}
	
	/**
	 * Get response name
	 * @param fiscalPrinter
	 * @return
	 * @return String
	 */
	public String getPrinterResponseName(MADAppRegistration fiscalPrinter) {
		String printerName = getPrinterName(fiscalPrinter);
		String printerResponseName = fiscalPrinter.getParameterValue(PRINTER_RESPONSE_NAME);
		if(printerResponseName == null) {
			printerResponseName = printerName + "-response";
		}
		return Optional.ofNullable(printerResponseName).orElse("").replaceAll("[^-0-9A-Za-z]", "").toLowerCase();
	}
	
	private String getPrinterName(MADAppRegistration fiscalPrinter) {
		MOrg client = MOrg.get(Env.getCtx(), fiscalPrinter.getAD_Org_ID());
		String printerName = client.getValue() + "-" + fiscalPrinter.getValue();
		String queueName = fiscalPrinter.getParameterValue(PRINTER_NAME);
		if(!Util.isEmpty(queueName)) {
			printerName = queueName;
		}
		return Optional.ofNullable(printerName).orElse("").replaceAll("[^-0-9A-Za-z]", "").toLowerCase();
	}

	// public String read(Properties context, String transactionName, boolean allQueues) throws Exception {
	// 	return readFromQueues(context, transactionName, allQueues, Duration.ofMillis(defaultTimeout));
	// }

	// public String readFromQueues(Properties context, String transactionName, boolean allQueues, Duration timeout) throws Exception {
	// 	List<String> queues = new ArrayList<String>();
	// 	if(allQueues) {
	// 		new Query(context, MADAppRegistration.Table_Name, MADAppRegistration.COLUMNNAME_ApplicationType +  "=?", transactionName)
	// 		.setParameters(applicationType)
	// 		.setOrderBy(MADAppRegistration.COLUMNNAME_Value)
	// 		.getIDsAsList().forEach(fiscalPrinterId -> {
	// 			MADAppRegistration fiscalPrinter = MADAppRegistration.getById(context, fiscalPrinterId, transactionName);
	// 			String responseName = getPrinterResponseName(fiscalPrinter);
	// 			queues.add(responseName);
	// 		});
	// 	} else {
	// 		queues.add(printerResponseName);
	// 	}
	// 	String clientName = MClient.get(context).getValue() 
	// 			+ " - " + Adempiere.getVersion() 
	// 			+ " - " + CConnection.get().getDbHost() 
	// 			+ " - " + CConnection.get().getDbName()
	// 			+ " - " + InetAddress.getLocalHost().getHostName(); 
	// 	clientName = clientName.replaceAll("[^a-zA-Z0-9. ]", "-");
	// 	String completeUrl = host + ":" + port;
	// 	String errorMessage = Msg.parseTranslation(Env.getCtx(), "@Error@");
	// 	String errorSummary = Msg.parseTranslation(Env.getCtx(), "@SaveError@");
	// 	long startTime = System.currentTimeMillis();
	// 	AtomicInteger errors = new AtomicInteger();
	// 	AtomicInteger readed = new AtomicInteger();
	// 	Properties config = new Properties();
	// 	config.put(ConsumerConfig.CLIENT_ID_CONFIG, InetAddress.getLocalHost().getHostName());
	// 	config.put(ConsumerConfig.GROUP_ID_CONFIG, clientName);
	// 	config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, completeUrl);
	// 	config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
	// 	config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, MapDeserializer.class.getName());
	// 	config.put(ConsumerConfig.DEFAULT_API_TIMEOUT_MS_CONFIG, 5000);
	// 	config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
	// 	KafkaConsumer<String, Map<String , Object>> consumer = new KafkaConsumer<String, Map<String , Object>>(config);
	// 	consumer.subscribe(queues);
	// 	ConsumerRecords<String, Map<String , Object>> records = consumer.poll(timeout);
	// 	records.forEach(record -> {
	// 		if(record != null) {
	// 			try {
	// 				//	Call event
	// 				if(record.key().equals("fiscal_printer_document")) {
	// 					Map<String, Object> values = record.value();
	// 					FiscalDocumentResult result = FiscalDocumentResult.newInstance()
	// 							.withDocumentUuid((String) values.get("document_uuid"))
	// 							.withDocumentNo((String) values.get("document_no"))
	// 							.withClosingNo((String) values.get("closing_no"))
	// 							.withDocumentType(FiscalDocumentResult.DocumentType.INVOICE);
	// 					String documentType = (String) values.get("document_type");
	// 					if(documentType != null) {
	// 						if(documentType.equals("credit_memo")) {
	// 							result.withDocumentType(FiscalDocumentResult.DocumentType.CREDIT_MEMO);
	// 						} else if(documentType.equals("debit_memo")) {
	// 							result.withDocumentType(FiscalDocumentResult.DocumentType.DEBIT_MEMO);
	// 						} else if(documentType.equals("non_fiscal_document")) {
	// 							result.withDocumentType(FiscalDocumentResult.DocumentType.NON_FISCAL_DOCUMENT);
	// 						}
	// 					}
	// 					//	General Printer Data
	// 					if(values.get("fiscal_printer_serial_no") != null) {
	// 						result.withFiscalPrinterSerialNo((String) values.get("fiscal_printer_serial_no"));
	// 					}
	// 					//	Call action
	// 					// messageReceived(new PrinterEvent(this, result));
	// 					readed.addAndGet(1);
	// 				}
	// 			} catch (Exception e) {
	// 				errors.addAndGet(1);
	// 				addLog(errorMessage, errorSummary, e.getLocalizedMessage(), true);
	// 			}
	// 		}
	// 	});
	// 	consumer.commitSync();
	// 	consumer.unsubscribe();
	// 	consumer.close(Duration.ofSeconds(1));
	// 	long endTime = System.currentTimeMillis();
	// 	SimpleDateFormat format = DisplayType.getDateFormat(DisplayType.DateTime);
	// 	String message = Msg.parseTranslation(Env.getCtx(), 
	// 			"@Records@: " + readed.get() 
	// 			+ " @Errors@: " + errors.get() 
	// 			+ " @StartTime@: " + format.format(new Timestamp(startTime)) 
	// 			+ " @EndTime@: " + format.format(new Timestamp(startTime)) 
	// 			+ " @Duration@" + TimeUtil.formatElapsed(startTime - endTime));
	// 	//	Add to log
	// 	addLog(Msg.getMsg(Env.getCtx(), "Process"), Msg.getMsg(Env.getCtx(), "Summary"), message, false);
	// 	return message;
	// }

	/**
	 * Add log for server
	 * @param importProcessor
	 * @param header
	 * @param summary
	 * @param textMessage
	 * @param isError
	 */
	public void addLog(String header, String summary, String textMessage, boolean isError) {
		// MIMPProcessorLog log = new MIMPProcessorLog(importProcessor, summary);
		// log.setReference(header);
		// log.setTextMsg(textMessage);
		// log.setIsError(isError);
		// log.save();
	}
}
