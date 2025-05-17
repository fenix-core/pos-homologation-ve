package org.spin.pos.util;

import org.compiere.model.MCampaign;
import org.compiere.util.Env;
import org.spin.base.util.ValueUtil;
import org.spin.proto.pos.homologation.Campaign;

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
		builder.setId(campaign.getC_Campaign_ID())
			.setUuid(
				ValueUtil.validateNull(
					campaign.getUUID()
				)
			)
			.setName(
				ValueUtil.validateNull(
					campaign.getName()
				)
			)
			.setDescription(
				ValueUtil.validateNull(
					campaign.getDescription()
				)
			)
			.setStartDate(
				ValueUtil.getLongFromTimestamp(
					campaign.getStartDate()
				)
			)
			.setEndDate(
				ValueUtil.getLongFromTimestamp(
					campaign.getEndDate()
				)
			)
		;
		return builder;
	}

}
