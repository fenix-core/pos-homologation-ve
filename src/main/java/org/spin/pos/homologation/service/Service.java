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

import java.util.Arrays;
import java.util.List;

import org.adempiere.core.domains.models.X_AD_Table;
import org.spin.base.Version;
import org.spin.proto.pos.homologation.SystemInfo;
import org.spin.service.grpc.util.value.StringManager;
import org.spin.service.grpc.util.value.TimeManager;
import org.spin.service.grpc.util.value.ValueManager;

public class Service {
	
	/** Table Allows Records with Zero Identifier */
	public static List<String> ALLOW_ZERO_ID = Arrays.asList(
		X_AD_Table.ACCESSLEVEL_All,
		X_AD_Table.ACCESSLEVEL_SystemPlusClient,
		X_AD_Table.ACCESSLEVEL_ClientPlusOrganization
	);


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

}
