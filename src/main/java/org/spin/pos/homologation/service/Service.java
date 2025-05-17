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

import org.compiere.model.MOrder;
import org.compiere.model.MPOS;
import org.spin.base.Version;
import org.spin.base.util.ConvertUtil;
import org.spin.pos.service.order.OrderManagement;
import org.spin.pos.service.order.OrderUtil;
import org.spin.proto.pos.homologation.Order;
import org.spin.proto.pos.homologation.ProcessWithoutPrintRequest;
import org.spin.proto.pos.homologation.SystemInfo;
import org.spin.service.grpc.util.value.StringManager;
import org.spin.service.grpc.util.value.TimeManager;
import org.spin.service.grpc.util.value.ValueManager;
import org.spin.service.pos.POS;

public class Service {

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

		Order.Builder builder = ConvertUtil.convertOrder(order);
		return builder;
	}

}
