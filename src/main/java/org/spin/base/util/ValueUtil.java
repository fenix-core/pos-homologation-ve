/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it           *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope          *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied        *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                  *
 * See the GNU General Public License for more details.                              *
 * You should have received a copy of the GNU General Public License along           *
 * with this program; if not, write to the Free Software Foundation, Inc.,           *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                            *
 * For the text or an alternative of this public license, you may reach us           *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com                                         *
 *************************************************************************************/
package org.spin.base.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.compiere.util.DisplayType;
import org.compiere.util.Util;
import org.spin.backend.grpc.common.Decimal;
import org.spin.backend.grpc.common.KeyValue;
import org.spin.backend.grpc.common.Value;
import org.spin.backend.grpc.common.Value.ValueType;
import org.spin.service.grpc.util.value.BooleanManager;
import org.spin.service.grpc.util.value.NumberManager;
import org.spin.service.grpc.util.value.StringManager;
import org.spin.service.grpc.util.value.TimeManager;
import org.spin.service.grpc.util.value.ValueManager;

import com.google.protobuf.Struct;

/**
 * Class for handle Values from and to client
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class ValueUtil {

	/**
	 * Value is empty
	 * @param value
	 * @return
	 */
	public static boolean isEmptyValue(Value value) {
		return value == null
			|| value.isInitialized()
			|| value.getValueType() == ValueType.UNKNOWN
			// || value.getValueType() == ValueType.NULL
		;
	}


	/**
	 * Get Value 
	 * @param value
	 * @return
	 */
	public static Value.Builder getValueFromObject(Object value) {
		Value.Builder builderValue = Value.newBuilder();
		if(value == null) {
			return builderValue;
		}
		//	Validate value
		if(value instanceof BigDecimal) {
			return getValueFromDecimal((BigDecimal) value);
		} else if (value instanceof Integer) {
			return getValueFromInteger((Integer)value);
		} else if (value instanceof String) {
			return getValueFromString((String) value);
		} else if (value instanceof Boolean) {
			return getValueFromBoolean((Boolean) value);
		} else if(value instanceof Timestamp) {
			return getValueFromDate((Timestamp) value);
		}
		//	
		return builderValue;
	}
	
	/**
	 * Get value from Integer
	 * @param value
	 * @return
	 */
	public static Value.Builder getValueFromInteger(Integer value) {
		Value.Builder convertedValue = Value.newBuilder()
			.setValueType(ValueType.INTEGER)
		;
		if(value != null) {
			convertedValue.setIntValue((Integer)value);
		}
		//	default
		return convertedValue;
	}
	/**
	 * Get value from Int
	 * @param value
	 * @return
	 */
	public static Value.Builder getValueFromInt(int value) {
		Value.Builder convertedValue = Value.newBuilder()
			.setValueType(ValueType.INTEGER)
		;
		convertedValue.setIntValue(Integer.valueOf(value));
		// default
		return convertedValue;
	}
	
	/**
	 * Get value from a string
	 * @param value
	 * @return
	 */
	public static Value.Builder getValueFromString(String value) {
		return Value.newBuilder()
			.setStringValue(
				StringManager.getValidString(value)
			)
			.setValueType(ValueType.STRING)
		;
	}

	/**
	 * Get value from a boolean value
	 * @param value
	 * @return
	 */
	public static Value.Builder getValueFromBoolean(boolean value) {
		return Value.newBuilder()
			.setBooleanValue(value)
			.setValueType(ValueType.BOOLEAN)
		;
	}
	/**
	 * Get value from a Boolean value
	 * @param value
	 * @return
	 */
	public static Value.Builder getValueFromBoolean(Boolean value) {
		return Value.newBuilder()
			.setBooleanValue(
				value.booleanValue()
			)
			.setValueType(ValueType.BOOLEAN)
		;
	}
	/**
	 * Get value from a String Boolean value
	 * @param value
	 * @return
	 */
	public static Value.Builder getValueFromBoolean(String value) {
		return Value.newBuilder()
			.setBooleanValue(
				BooleanManager.getBooleanFromString(value)
			)
			.setValueType(ValueType.BOOLEAN);
	}

	/**
	 * Get value from a date
	 * @param value
	 * @return
	 */
	public static Value.Builder getValueFromDate(Timestamp value) {
		if (value == null) {
			return Value.newBuilder()
				.setValueType(ValueType.DATE)
			;
		}
		return Value.newBuilder()
			.setLongValue(
				TimeManager.getLongFromTimestamp(value)
			)
			.setValueType(ValueType.DATE)
		;
	}
	
	/**
	 * Get value from big decimal
	 * @param value
	 * @return
	 */
	public static Value.Builder getValueFromDecimal(BigDecimal value) {
		return Value.newBuilder()
			.setValueType(ValueType.DECIMAL)
			.setDecimalValue(
				getDecimalFromBigDecimal(value)
			)
		;
	}
	
	/**
	 * Get decimal from big decimal
	 * @param value
	 * @return
	 */
	public static Decimal.Builder getDecimalFromBigDecimal(BigDecimal value) {
		if (value == null) {
			return Decimal.newBuilder();
		}
		return Decimal.newBuilder()
			.setDecimalValue(value.toPlainString())
			.setScale(value.scale())
		;
	}

	public static Decimal.Builder getDecimalFromInt(int value) {
		return getDecimalFromBigDecimal(
			new BigDecimal(value)
		);
	}


	/**
	 * Get Decimal from Value
	 * @param value
	 * @return
	 */
	public static BigDecimal getDecimalFromValue(Value value) {
		if (Util.isEmpty(value.getDecimalValue().getDecimalValue(), true)) {
			if (value.getValueType() == Value.ValueType.INTEGER) {
				return BigDecimal.valueOf(value.getIntValue());
			}
			if (value.getValueType() == Value.ValueType.STRING) {
				return NumberManager.getBigDecimalFromString(
					value.getStringValue()
				);
			}
			return null;
		}
		// Value.Decimal.DecimalValue
		return getBigDecimalFromDecimal(value.getDecimalValue());
	}
	
	/**
	 * Get BigDecimal object from decimal
	 * @param decimalValue
	 * @return
	 */
	public static BigDecimal getBigDecimalFromDecimal(Decimal decimalValue) {
		if (decimalValue == null || Util.isEmpty(decimalValue.getDecimalValue(), true)) {
			return null;
		}
		return new BigDecimal(decimalValue.getDecimalValue())
			.setScale(decimalValue.getScale())
		;
	}
	
	/**
	 * Get Date from a value
	 * @param value
	 * @return
	 */
	public static Timestamp getDateFromValue(Value value) {
		if(value.getLongValue() > 0) {
			return new Timestamp(value.getLongValue());
		}
		return null;
	}
	
	/**
	 * Get String from a value
	 * @param value
	 * @param uppercase
	 * @return
	 */
	public static String getStringFromValue(Value value, boolean uppercase) {
		String stringValue = value.getStringValue();
		if(Util.isEmpty(stringValue, true)) {
			return null;
		}
		//	To Upper case
		if(uppercase) {
			stringValue = stringValue.toUpperCase();
		}
		return stringValue;
	}
	
	/**
	 * Get String from a value
	 * @param value
	 * @return
	 */
	public static String getStringFromValue(Value value) {
		return getStringFromValue(value, false);
	}
	
	/**
	 * Get integer from a value
	 * @param value
	 * @return
	 */
	public static int getIntegerFromValue(Value value) {
		return value.getIntValue();
	}
	
	/**
	 * Get Boolean from a value
	 * @param value
	 * @return
	 */
	public static boolean getBooleanFromValue(Value value) {
		if (!Util.isEmpty(value.getStringValue(), true)) {
			return BooleanManager.getBooleanFromString(
				value.getStringValue()
			);
		}

		return value.getBooleanValue();
	}
	
	/**
	 * Get Value from reference
	 * @param value
	 * @param referenceId reference of value
	 * @return
	 */
	public static Value.Builder getValueFromReference(Object value, int referenceId) {
		Value.Builder builderValue = Value.newBuilder();
		if(value == null) {
			return builderValue;
		}
		//	Validate values
		if(ValueManager.isLookup(referenceId)
				|| DisplayType.isID(referenceId)) {
			return getValueFromObject(value);
		} else if(DisplayType.Integer == referenceId) {
			Integer integerValue = null;
			if(value instanceof Integer) {
				integerValue = (Integer) value;
			} else if (value instanceof Long) {
				long longValue = (long) value;
				integerValue = Math.toIntExact(longValue);
			} else if(value instanceof BigDecimal) {
				integerValue = ((BigDecimal) value).intValue();
			} else if (value instanceof String) {
				try {
					integerValue = Integer.valueOf((String) value);
				} catch (Exception e) {
					integerValue = null;
				}
			}
			return getValueFromInteger(integerValue);
		} else if(DisplayType.isNumeric(referenceId)) {
			BigDecimal bigDecimalValue = null;
			if (value instanceof Integer) {
				Integer intValue = (Integer) value;
				bigDecimalValue = BigDecimal.valueOf(intValue);
			} else if (value instanceof Long) {
				long longValue = (long) value;
				bigDecimalValue = BigDecimal.valueOf(longValue);
			} else if (value instanceof String) {
				bigDecimalValue = new BigDecimal((String) value);
			} else {
				bigDecimalValue = (BigDecimal) value;
			}
			return getValueFromDecimal(bigDecimalValue);
		} else if(DisplayType.YesNo == referenceId) {
			if (value instanceof String) {
				return getValueFromBoolean((String) value);
			}
			return getValueFromBoolean((Boolean) value);
		} else if(DisplayType.isDate(referenceId)) {
			return getValueFromDate((Timestamp) value);
		} else if(DisplayType.isText(referenceId)) {
			return getValueFromString((String) value);
		} else if (DisplayType.Button == referenceId) {
			if (value instanceof Integer) {
				return getValueFromInteger((Integer) value);
			} else if (value instanceof Long) {
				long longValue = (long) value;
				Integer integerValue = Math.toIntExact(longValue);
				return getValueFromInt(integerValue);
			} else if(value instanceof BigDecimal) {
				return getValueFromInteger(((BigDecimal) value).intValue());
			} else if (value instanceof String) {
				return getValueFromString((String) value);
			}
			return getValueFromObject(value); 
		}
		//
		return builderValue;
	}

	/**
	 * Convert Selection values from gRPC to ADempiere values
	 * @param values
	 * @return
	 */
	public static Map<String, Object> convertValuesToObjects(List<KeyValue> values) {
		Map<String, Object> convertedValues = new HashMap<>();
		if (values == null || values.size() <= 0) {
			return convertedValues;
		}
		for(KeyValue value : values) {
			convertedValues.put(value.getKey(), getObjectFromValue(value.getValue()));
		}
		//	
		return convertedValues;
	}

	/**
	 * Default get value from type
	 * @param valueToConvert
	 * @return
	 */
	public static Object getObjectFromValue(Value valueToConvert) {
		return getObjectFromValue(valueToConvert, false);
	}

	/**
	 * Get value from parameter type
	 * @param value
	 * @return
	 */
	public static Object getObjectFromValue(Value value, boolean uppercase) {
		if(value.getValueType().equals(ValueType.BOOLEAN)) {
			return value.getBooleanValue();
		} else if(value.getValueType().equals(ValueType.DECIMAL)) {
			return getDecimalFromValue(value);
		} else if(value.getValueType().equals(ValueType.INTEGER)) {
			return value.getIntValue();
		} else if(value.getValueType().equals(ValueType.STRING)) {
			return getStringFromValue(value, uppercase);
		} else if(value.getValueType().equals(ValueType.DATE)) {
			return getDateFromValue(value);
		}
		return null;
	}

	/**
	 * Get Object from value based on reference
	 * @param value
	 * @param referenceId
	 * @return
	 */
	public static Object getObjectFromReference(Value value, int referenceId) {
		if(value == null
				|| value.getValueType().equals(ValueType.UNKNOWN)) {
			return null;
		}
		//	Validate values
		if(ValueManager.isLookup(referenceId)
				|| DisplayType.isID(referenceId)) {
			return getObjectFromValue(value);
		} else if(DisplayType.Integer == referenceId) {
			return getIntegerFromValue(value);
		} else if(DisplayType.isNumeric(referenceId)) {
			return getDecimalFromValue(value);
		} else if(DisplayType.YesNo == referenceId) {
			return getBooleanFromValue(value);
		} else if(DisplayType.isDate(referenceId)) {
			return getDateFromValue(value);
		} else if(DisplayType.isText(referenceId)) {
			return getStringFromValue(value);
		}
		//	
		return null;
	}


	/**
	 * Get Value 
	 * @param value
	 * @return
	 */
	public static com.google.protobuf.Value.Builder getProtoValueFromObject(Object value) {
		com.google.protobuf.Value.Builder builder = com.google.protobuf.Value.newBuilder();
		if(value == null) {
			return ValueManager.getValueFromNull();
		}
		//	Validate value
		if(value instanceof BigDecimal) {
			return ValueManager.getValueFromBigDecimal((BigDecimal) value);
		} else if (value instanceof Integer) {
			return ValueManager.getValueFromInteger((Integer)value);
		} else if (value instanceof String) {
			return ValueManager.getValueFromString((String) value);
		} else if (value instanceof Boolean) {
			return ValueManager.getValueFromBoolean((Boolean) value);
		} else if(value instanceof Timestamp) {
			return ValueManager.getValueFromTimestamp((Timestamp) value);
		} else if (value instanceof Map) {
			//  Recursivamente convertir Map a Struct (si es necesario)
			Struct.Builder structBuilder = Struct.newBuilder();
			((Map<?, ?>) value).forEach((k, v) -> {
				String structKey = "";
				if (k instanceof String) {
					structKey = (String) k;
				} else {
					//  Manejar error o lanzar excepción si la clave no es String
					structKey = StringManager.getStringFromObject(k);
				}

				com.google.protobuf.Value.Builder structValue = getProtoValueFromObject(v);
				structBuilder.putFields(
					structKey,
					structValue.build()
				);
			});
			return builder.setStructValue(
				structBuilder.build()
			);
		} else if (value instanceof List) {
			//  Convertir List a ListValue
			com.google.protobuf.ListValue.Builder listBuilder = com.google.protobuf.ListValue.newBuilder();
			((List<?>) value).forEach(v -> {
				listBuilder.addValues(
					getProtoValueFromObject(v)
				);
			});
			return builder.setListValue(
				listBuilder.build()
			);
		} 
		//	
		return builder;
	}

	/**
	 * Convert Selection values from gRPC to ADempiere values
	 * @param values
	 * @return
	 */
	public static com.google.protobuf.Value.Builder convertObjectMapToStruct(Map<String, Object> values) {
		com.google.protobuf.Value.Builder convertedValues = com.google.protobuf.Value.newBuilder();
		Struct.Builder mapValue = Struct.newBuilder();

		if (values != null && values.size() > 0) {
			values.keySet().forEach(keyValue -> {
				Object valueItem = values.get(keyValue);
				com.google.protobuf.Value.Builder valueBuilder = ValueManager.getValueFromObject(
					valueItem
				);
				mapValue.putFields(
					keyValue,
					valueBuilder.build()
				);
			});
		}

		//	
		convertedValues.setStructValue(mapValue);
		return convertedValues;
	}

}
