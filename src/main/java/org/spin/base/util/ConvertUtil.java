package org.spin.base.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.adempiere.core.domains.models.I_AD_Ref_List;
import org.adempiere.core.domains.models.I_AD_User;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCity;
import org.compiere.model.MConversionRate;
import org.compiere.model.MCountry;
import org.compiere.model.MCurrency;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPOS;
import org.compiere.model.MPayment;
import org.compiere.model.MPriceList;
import org.compiere.model.MRefList;
import org.compiere.model.MRegion;
import org.compiere.model.MTable;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.spin.backend.grpc.common.Currency;
import org.spin.backend.grpc.common.DocumentStatus;
import org.spin.backend.grpc.common.DocumentType;
import org.spin.backend.grpc.common.PriceList;
import org.spin.backend.grpc.common.SalesRepresentative;
import org.spin.backend.grpc.common.Warehouse;
import org.spin.pos.service.order.OrderUtil;
import org.spin.pos.util.POSConvertUtil;
import org.spin.proto.pos.homologation.Address;
import org.spin.proto.pos.homologation.City;
import org.spin.proto.pos.homologation.Customer;
import org.spin.proto.pos.homologation.Order;
import org.spin.proto.pos.homologation.Region;
import org.spin.service.grpc.util.value.StringManager;
import org.spin.service.grpc.util.value.TimeManager;
import org.spin.service.grpc.util.value.ValueManager;
import org.spin.store.util.VueStoreFrontUtil;

public class ConvertUtil {

	/**
	 * Convert Document Status
	 * @param value
	 * @param name
	 * @param description
	 * @return
	 */
	public static DocumentStatus.Builder convertDocumentStatus(String value, String name, String description) {
		return DocumentStatus.newBuilder()
			.setValue(
				StringManager.getValidString(value)
			)
			.setName(
				StringManager.getValidString(name)
			)
			.setDescription(
				StringManager.getValidString(description)
			)
		;
	}

	/**
	 * Convert Document Type
	 * @param documentType
	 * @return
	 */
	public static DocumentType.Builder convertDocumentType(MDocType documentType) {
		if (documentType == null) {
			return DocumentType.newBuilder();
		}

		return DocumentType.newBuilder()
			.setUuid(
				StringManager.getValidString(
					documentType.getUUID()
				)
			)
			.setId(
				documentType.getC_DocType_ID()
			)
			.setName(
				StringManager.getValidString(
					documentType.getName()
				)
			)
			.setDescription(
				StringManager.getValidString(
					documentType.getDescription()
				)
			)
			.setPrintName(
				StringManager.getValidString(
					documentType.getPrintName()
				)
			)
		;
	}

	/**
	 * Convert Currency
	 * @param currency
	 * @return
	 */
	public static Currency.Builder convertCurrency(MCurrency currency) {
		Currency.Builder builder = Currency.newBuilder();
		if(currency == null) {
			return builder;
		}
		//	Set values
		return builder.setUuid(
				StringManager.getValidString(
					currency.getUUID()
				)
			)
			.setId(
				currency.getC_Currency_ID()
			)
			.setIsoCode(
				StringManager.getValidString(
					currency.getISO_Code()
				)
			)
			.setCurSymbol(
				StringManager.getValidString(
					currency.getCurSymbol()
				)
			)
			.setDescription(
				StringManager.getValidString(
					currency.getDescription()
				)
			)
			.setStandardPrecision(
				currency.getStdPrecision()
			)
			.setCostingPrecision(
				currency.getCostingPrecision()
			)
		;
	}

	/**
	 * Convert Price List
	 * @param priceList
	 * @return
	 */
	public static PriceList.Builder convertPriceList(MPriceList priceList) {
		PriceList.Builder builder = PriceList.newBuilder();
		if(priceList == null) {
			return builder;
		}
		//	
		return builder.setUuid(
				StringManager.getValidString(
					priceList.getUUID()
				)
			)
			.setId(
				priceList.getM_PriceList_ID()
			)
			.setName(
				StringManager.getValidString(
					priceList.getName()
				)
			)
			.setDescription(
				StringManager.getValidString(
					priceList.getDescription()
				)
			)
			.setCurrency(
				convertCurrency(
					MCurrency.get(
						priceList.getCtx(),
						priceList.getC_Currency_ID()
					)
				)
			)
			.setIsDefault(
				priceList.isDefault()
			)
			.setIsTaxIncluded(
				priceList.isTaxIncluded()
			)
			.setIsEnforcePriceLimit(
				priceList.isEnforcePriceLimit()
			)
			.setIsNetPrice(
				priceList.isNetPrice()
			)
			.setPricePrecision(
				priceList.getPricePrecision()
			)
		;
	}

	/**
	 * Convert Order from entity
	 * @param order
	 * @return
	 */
	public static Order.Builder convertOrder(MOrder order) {
		Order.Builder builder = Order.newBuilder();
		if(order == null) {
			return builder;
		}
		MPOS pos = new MPOS(Env.getCtx(), order.getC_POS_ID(), order.get_TrxName());
		int defaultDiscountChargeId = pos.get_ValueAsInt("DefaultDiscountCharge_ID");
		MRefList reference = MRefList.get(Env.getCtx(), MOrder.DOCSTATUS_AD_REFERENCE_ID, order.getDocStatus(), null);
		MPriceList priceList = MPriceList.get(Env.getCtx(), order.getM_PriceList_ID(), order.get_TrxName());
		List<MOrderLine> orderLines = Arrays.asList(order.getLines());
		BigDecimal totalLines = orderLines.stream()
				.filter(orderLine -> orderLine.getC_Charge_ID() != defaultDiscountChargeId || defaultDiscountChargeId == 0)
				.map(orderLine -> Optional.ofNullable(orderLine.getLineNetAmt()).orElse(Env.ZERO)).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal discountAmount = orderLines.stream()
				.filter(orderLine -> orderLine.getC_Charge_ID() > 0 && orderLine.getC_Charge_ID() == defaultDiscountChargeId)
				.map(orderLine -> Optional.ofNullable(orderLine.getLineNetAmt()).orElse(Env.ZERO)).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal lineDiscountAmount = orderLines.stream()
				.filter(orderLine -> orderLine.getC_Charge_ID() != defaultDiscountChargeId || defaultDiscountChargeId == 0)
				.map(orderLine -> {
					BigDecimal priceActualAmount = Optional.ofNullable(orderLine.getPriceActual()).orElse(Env.ZERO);
					BigDecimal priceListAmount = Optional.ofNullable(orderLine.getPriceList()).orElse(Env.ZERO);
					BigDecimal discountLine = priceListAmount.subtract(priceActualAmount)
						.multiply(Optional.ofNullable(orderLine.getQtyOrdered()).orElse(Env.ZERO));
					return discountLine;
				})
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		//	
		BigDecimal totalDiscountAmount = discountAmount.add(lineDiscountAmount);
		
		//	
		Optional<BigDecimal> paidAmount = MPayment.getOfOrder(order).stream().map(payment -> {
			BigDecimal paymentAmount = payment.getPayAmt();
			if(paymentAmount.compareTo(Env.ZERO) == 0
					&& payment.getTenderType().equals(MPayment.TENDERTYPE_CreditMemo)) {
				MInvoice creditMemo = new Query(payment.getCtx(), MInvoice.Table_Name, "C_Payment_ID = ?", payment.get_TrxName()).setParameters(payment.getC_Payment_ID()).first();
				if(creditMemo != null) {
					paymentAmount = creditMemo.getGrandTotal();
				}
			}
			if(!payment.isReceipt()) {
				paymentAmount = payment.getPayAmt().negate();
			}
			return getConvetedAmount(order, payment, paymentAmount);
		}).collect(Collectors.reducing(BigDecimal::add));

		BigDecimal grandTotal = order.getGrandTotal();
		BigDecimal paymentAmount = Env.ZERO;
		if(paidAmount.isPresent()) {
			paymentAmount = paidAmount.get();
		}

		BigDecimal creditAmt = OrderUtil.getCreditAmount(order);
		BigDecimal chargeAmt = OrderUtil.getChargeAmount(order);
		BigDecimal totalPaymentAmount = OrderUtil.getTotalPaymentAmount(order);

		BigDecimal openAmount = (grandTotal.subtract(totalPaymentAmount).compareTo(Env.ZERO) < 0? Env.ZERO: grandTotal.subtract(totalPaymentAmount));
		BigDecimal refundAmount = (grandTotal.subtract(totalPaymentAmount).compareTo(Env.ZERO) > 0? Env.ZERO: grandTotal.subtract(totalPaymentAmount).negate());
		BigDecimal displayCurrencyRate = getDisplayConversionRateFromOrder(order);
		//	Convert
		return builder
			.setUuid(
				StringManager.getValidString(
					order.getUUID()
				)
			)
			.setId(
				order.getC_Order_ID()
			)
			.setDocumentType(
				ConvertUtil.convertDocumentType(
					MDocType.get(
						Env.getCtx(),
						order.getC_DocTypeTarget_ID()
					)
				)
			)
			.setDocumentNo(
				StringManager.getValidString(
					order.getDocumentNo()
				)
			)
			.setSalesRepresentative(
				convertSalesRepresentative(
					MUser.get(
						Env.getCtx(),
						order.getSalesRep_ID()
					)
				)
			)
			.setDescription(
				StringManager.getValidString(
					order.getDescription()
				)
			)
			.setOrderReference(
				StringManager.getValidString(
					order.getPOReference()
				)
			)
			.setDocumentStatus(
				ConvertUtil.convertDocumentStatus(
					StringManager.getValidString(order.getDocStatus()),
					StringManager.getValidString(ValueManager.getTranslation(reference, I_AD_Ref_List.COLUMNNAME_Name)),
					StringManager.getValidString(ValueManager.getTranslation(reference, I_AD_Ref_List.COLUMNNAME_Description))
				)
			)
			.setPriceList(
				ConvertUtil.convertPriceList(
					MPriceList.get(
						Env.getCtx(),
						order.getM_PriceList_ID(),
						order.get_TrxName()
					)
				)
			)
			.setWarehouse(
				convertWarehouse(
					order.getM_Warehouse_ID()
				)
			)
			.setIsDelivered(
				order.isDelivered()
			)
			.setDiscountAmount(
				ValueUtil.getDecimalFromBigDecimal(
					Optional.ofNullable(totalDiscountAmount)
						.orElse(Env.ZERO)
						.setScale(priceList.getStandardPrecision(), RoundingMode.HALF_UP)
				)
			)
			.setTaxAmount(
				ValueUtil.getDecimalFromBigDecimal(
					grandTotal.subtract(totalLines.add(discountAmount)).setScale(priceList.getStandardPrecision(), RoundingMode.HALF_UP)
				)
			)
			.setTotalLines(
				ValueUtil.getDecimalFromBigDecimal(
					totalLines.add(totalDiscountAmount).setScale(priceList.getStandardPrecision(), RoundingMode.HALF_UP)
				)
			)
			.setGrandTotal(
				ValueUtil.getDecimalFromBigDecimal(
					grandTotal.setScale(priceList.getStandardPrecision(), RoundingMode.HALF_UP)
				)
			)
			.setDisplayCurrencyRate(
				ValueUtil.getDecimalFromBigDecimal(
					displayCurrencyRate.setScale(priceList.getStandardPrecision(), RoundingMode.HALF_UP)
				)
			)
			.setPaymentAmount(
				ValueUtil.getDecimalFromBigDecimal(
					paymentAmount.setScale(priceList.getStandardPrecision(), RoundingMode.HALF_UP)
				)
			)
			.setOpenAmount(
				ValueUtil.getDecimalFromBigDecimal(
					openAmount.setScale(priceList.getStandardPrecision(), RoundingMode.HALF_UP)
				)
			)
			.setRefundAmount(
				ValueUtil.getDecimalFromBigDecimal(
					refundAmount.setScale(priceList.getStandardPrecision(), RoundingMode.HALF_UP)
				)
			)
			.setDateOrdered(
				TimeManager.getTimestampToString(
					order.getDateOrdered()
				)
			)
			.setCustomer(
				convertCustomer(
					(MBPartner) order.getC_BPartner()
				)
			)
			.setCampaign(
				POSConvertUtil.convertCampaign(
					order.getC_Campaign_ID()
				)
			)
			.setChargeAmount(
				ValueUtil.getDecimalFromBigDecimal(chargeAmt)
			)
			.setCreditAmount(
				ValueUtil.getDecimalFromBigDecimal(creditAmt)
			)
			.setSourceRmaId(
				order.get_ValueAsInt("ECA14_Source_RMA_ID")
			)
			.setIsRma(
				order.isReturnOrder()
			)
			.setIsOrder(
				!order.isReturnOrder()
			)
			.setIsBindingOffer(
				OrderUtil.isBindingOffer(order)
			)
		;
	}

	/**
	 * Get Converted Amount based on Order currency
	 * @param order
	 * @param payment
	 * @return
	 * @return BigDecimal
	 */
	private static BigDecimal getConvetedAmount(MOrder order, MPayment payment, BigDecimal amount) {
		if(payment.getC_Currency_ID() == order.getC_Currency_ID()
				|| amount == null
				|| amount.compareTo(Env.ZERO) == 0) {
			return amount;
		}
		BigDecimal convertedAmount = MConversionRate.convert(
			payment.getCtx(),
			amount,
			payment.getC_Currency_ID(),
			order.getC_Currency_ID(),
			payment.getDateAcct(),
			payment.getC_ConversionType_ID(),
			payment.getAD_Client_ID(),
			payment.getAD_Org_ID()
		);
		//	
		return Optional.ofNullable(convertedAmount).orElse(Env.ZERO);
	}

	/**
	 * Get Display Currency rate from Sales Order
	 * @param order
	 * @return
	 * @return BigDecimal
	 */
	private static BigDecimal getDisplayConversionRateFromOrder(MOrder order) {
		MPOS pos = MPOS.get(order.getCtx(), order.getC_POS_ID());
		if(order.getC_Currency_ID() == pos.get_ValueAsInt("DisplayCurrency_ID")
				|| pos.get_ValueAsInt("DisplayCurrency_ID") <= 0) {
			return Env.ONE;
		}
		BigDecimal conversionRate = MConversionRate.getRate(
			order.getC_Currency_ID(),
			pos.get_ValueAsInt("DisplayCurrency_ID"),
			order.getDateAcct(),
			order.getC_ConversionType_ID(),
			order.getAD_Client_ID(),
			order.getAD_Org_ID()
		);
		//	
		return Optional.ofNullable(conversionRate).orElse(Env.ZERO);
	}

	/**
	 * Convert Sales Representative
	 * @param salesRepresentative
	 * @return
	 */
	public static SalesRepresentative.Builder convertSalesRepresentative(MUser salesRepresentative) {
		if (salesRepresentative == null) {
			return SalesRepresentative.newBuilder();
		}
		return SalesRepresentative.newBuilder()
			.setUuid(
				StringManager.getValidString(
					salesRepresentative.getUUID()
				)
			)
			.setId(
				salesRepresentative.getAD_User_ID()
			)
			.setName(
				StringManager.getValidString(
					salesRepresentative.getName()
				)
			)
			.setDescription(
				StringManager.getValidString(
					salesRepresentative.getDescription()
				)
			)
		;
	}

	/**
	 * Convert customer
	 * @param businessPartner
	 * @return
	 */
	public static Customer.Builder convertCustomer(MBPartner businessPartner) {
		if(businessPartner == null) {
			return Customer.newBuilder();
		}
		Customer.Builder customer = Customer.newBuilder()
			.setUuid(
				StringManager.getValidString(
					businessPartner.getUUID()
				)
			)
			.setId(
				businessPartner.getC_BPartner_ID()
			)
			.setValue(
				StringManager.getValidString(
					businessPartner.getValue()
				)
			)
			.setTaxId(
				StringManager.getValidString(
					businessPartner.getTaxID()
				)
			)
			.setDuns(
				StringManager.getValidString(
					businessPartner.getDUNS()
				)
			)
			.setNaics(
				StringManager.getValidString(
					businessPartner.getNAICS()
				)
			)
			.setName(
				StringManager.getValidString(
					businessPartner.getName()
				)
			)
			.setLastName(
				StringManager.getValidString(
					businessPartner.getName2()
				)
			)
			.setDescription(
				StringManager.getValidString(
					businessPartner.getDescription()
				)
			)
		;
		//	Additional Attributes
		MTable.get(Env.getCtx(), businessPartner.get_Table_ID()).getColumnsAsList()
			.stream()
			.map(column -> {
				return column.getColumnName();
			})
			.filter(columnName -> {
				return !columnName.equals(MBPartner.COLUMNNAME_UUID)
					&& !columnName.equals(MBPartner.COLUMNNAME_Value)
					&& !columnName.equals(MBPartner.COLUMNNAME_TaxID)
					&& !columnName.equals(MBPartner.COLUMNNAME_DUNS)
					&& !columnName.equals(MBPartner.COLUMNNAME_NAICS)
					&& !columnName.equals(MBPartner.COLUMNNAME_Name)
					&& !columnName.equals(MBPartner.COLUMNNAME_Name2)
					&& !columnName.equals(MBPartner.COLUMNNAME_Description)
				;
			})
			.forEach(columnName -> {
				customer.putAdditionalAttributes(
					columnName,
					ValueUtil.getValueFromObject(
						businessPartner.get_Value(columnName)
					).build()
				);
			})
		;
		//	Add Address
		Arrays.asList(businessPartner.getLocations(true))
			.stream()
			.filter(customerLocation -> {
				return customerLocation.isActive();
			})
			.forEach(address -> {
				customer.addAddresses(convertCustomerAddress(address));
			})
		;
		return customer;
	}

	/**
	 * Convert Address
	 * @param businessPartnerLocation
	 * @return
	 * @return Address.Builder
	 */
	public static Address.Builder convertCustomerAddress(MBPartnerLocation businessPartnerLocation) {
		if(businessPartnerLocation == null) {
			return Address.newBuilder();
		}
		MLocation location = businessPartnerLocation.getLocation(true);
		Address.Builder builder =  Address.newBuilder()
			.setUuid(
				StringManager.getValidString(
					businessPartnerLocation.getUUID()
				)
			)
			.setId(
				businessPartnerLocation.getC_BPartner_Location_ID()
			)
			.setPostalCode(
				StringManager.getValidString(
					location.getPostal()
				)
			)
			.setAddress1(
				StringManager.getValidString(
					location.getAddress1()
				)
			)
			.setAddress2(
				StringManager.getValidString(
					location.getAddress2()
				)
			)
			.setAddress3(
				StringManager.getValidString(
					location.getAddress3()
				)
			)
			.setAddress4(
				StringManager.getValidString(
					location.getAddress4()
				)
			)
			.setPostalCode(
				StringManager.getValidString(
					location.getPostal()
				)
			)
			// .setDescription(StringManager.getValidString(businessPartnerLocation.get_ValueAsString("Description")))
			// .setFirstName(StringManager.getValidString(businessPartnerLocation.getName()))
			// .setLastName(StringManager.getValidString(businessPartnerLocation.get_ValueAsString("Name2")))
			// .setContactName(StringManager.getValidString(businessPartnerLocation.get_ValueAsString("ContactName")))
			.setEmail(
				StringManager.getValidString(
					businessPartnerLocation.getEMail()
				)
			)
			.setPhone(
				StringManager.getValidString(
					businessPartnerLocation.getPhone()
				)
			)
			.setIsDefaultShipping(
				businessPartnerLocation.get_ValueAsBoolean(VueStoreFrontUtil.COLUMNNAME_IsDefaultShipping)
			)
			.setIsDefaultBilling(
				businessPartnerLocation.get_ValueAsBoolean(VueStoreFrontUtil.COLUMNNAME_IsDefaultBilling)
			)
		;
		//	Get user from location
		MUser user = new Query(
			Env.getCtx(),
			I_AD_User.Table_Name,
			I_AD_User.COLUMNNAME_C_BPartner_Location_ID + " = ?",
			businessPartnerLocation.get_TrxName()
		)
			.setParameters(businessPartnerLocation.getC_BPartner_Location_ID())
			.setOnlyActiveRecords(true)
			.first()
		;
		String phone = null;
		if(user != null && user.getAD_User_ID() > 0) {
			if(!Util.isEmpty(user.getPhone())) {
				phone = user.getPhone();
			}
			if(!Util.isEmpty(user.getName())
					&& Util.isEmpty(builder.getContactName())) {
				builder.setContactName(user.getName());
			}
		}
		//	
		builder.setPhone(
			StringManager.getValidString(
				Optional.ofNullable(businessPartnerLocation.getPhone()).orElse(Optional.ofNullable(phone).orElse(""))
			)
		);
		MCountry country = MCountry.get(Env.getCtx(), location.getC_Country_ID());
		builder.setCountryCode(
				StringManager.getValidString(
					country.getCountryCode()
				)
			)
			.setCountryUuid(
				StringManager.getValidString(
					country.getUUID()
				)
			)
			.setCountryId(
				country.getC_Country_ID()
			)
		;
		//	City
		if(location.getC_City_ID() > 0) {
			MCity city = MCity.get(Env.getCtx(), location.getC_City_ID());
			builder.setCity(
				City.newBuilder()
					.setId(
						city.getC_City_ID()
					)
					.setUuid(
						StringManager.getValidString(
							city.getUUID()
						)
					)
					.setName(
						StringManager.getValidString(
							city.getName()
						)
					)
			);
		} else {
			builder.setCity(
				City.newBuilder()
					.setName(
						StringManager.getValidString(
							location.getCity()
						)
					)
			);
		}
		//	Region
		if(location.getC_Region_ID() > 0) {
			MRegion region = MRegion.get(Env.getCtx(), location.getC_Region_ID());
			builder.setRegion(
				Region.newBuilder()
					.setId(
						region.getC_Region_ID()
					)
					.setUuid(
						StringManager.getValidString(
							region.getUUID()
						)
					)
					.setName(
						StringManager.getValidString(
							region.getName()
						)
					)
			);
		}
		//	Additional Attributes
		MTable.get(Env.getCtx(), businessPartnerLocation.get_Table_ID()).getColumnsAsList()
			.stream()
			.map(column -> {
				return column.getColumnName();
			})
			.filter(columnName -> {
				return !columnName.equals(MBPartnerLocation.COLUMNNAME_UUID)
					&& !columnName.equals(MBPartnerLocation.COLUMNNAME_Phone)
					&& !columnName.equals(MBPartnerLocation.COLUMNNAME_Name);
			})
			.forEach(columnName -> {
				builder.putAdditionalAttributes(columnName, ValueUtil.getValueFromObject(businessPartnerLocation.get_Value(columnName)).build());
			})
		;
		//	
		return builder;
	}

	/**
	 * convert warehouse from id
	 * @param warehouseId
	 * @return
	 */
	public static Warehouse.Builder convertWarehouse(int warehouseId) {
		Warehouse.Builder builder = Warehouse.newBuilder();
		if(warehouseId <= 0) {
			return builder;
		}
		return ConvertUtil.convertWarehouse(MWarehouse.get(Env.getCtx(), warehouseId));
	}
	/**
	 * Convert warehouse
	 * @param warehouse
	 * @return
	 */
	public static Warehouse.Builder convertWarehouse(MWarehouse warehouse) {
		if (warehouse == null) {
			return Warehouse.newBuilder();
		}
		return Warehouse.newBuilder()
			.setUuid(
				StringManager.getValidString(
					warehouse.getUUID()
				)
			)
			.setId(
				warehouse.getM_Warehouse_ID()
			)
			.setName(
				StringManager.getValidString(
					warehouse.getName()
				)
			)
			.setDescription(
				StringManager.getValidString(
					warehouse.getDescription()
				)
			)
		;
	}

}
