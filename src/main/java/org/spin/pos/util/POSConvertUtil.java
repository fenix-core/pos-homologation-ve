package org.spin.pos.util;

import org.compiere.model.MCampaign;
import org.compiere.util.Env;
import org.spin.proto.pos.homologation.Campaign;
import org.spin.service.grpc.util.value.StringManager;
import org.spin.service.grpc.util.value.TimeManager;

public class POSConvertUtil {

	public static Campaign.Builder convertCampaign(int campaignId) {
		Campaign.Builder builder = Campaign.newBuilder();
		if (campaignId <= 0) {
			return builder;
		}
		MCampaign campaign = MCampaign.getById(Env.getCtx(), campaignId, null);
		return convertCampaign(campaign);
	}

	public static Campaign.Builder convertCampaign(MCampaign campaign) {
		Campaign.Builder builder = Campaign.newBuilder();
		if (campaign == null || campaign.getC_Campaign_ID() <= 0) {
			return builder;
		}
		builder.setId(
				campaign.getC_Campaign_ID()
			)
			.setUuid(
				StringManager.getValidString(
					campaign.getUUID()
				)
			)
			.setName(
				StringManager.getValidString(
					campaign.getName()
				)
			)
			.setDescription(
				StringManager.getValidString(
					campaign.getDescription()
				)
			)
			.setStartDate(
				TimeManager.getLongFromTimestamp(
					campaign.getStartDate()
				)
			)
			.setEndDate(
				TimeManager.getLongFromTimestamp(
					campaign.getEndDate()
				)
			)
		;
		return builder;
	}

}
