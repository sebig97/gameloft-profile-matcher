package com.example.campaignservice.service;

import com.example.campaignservice.dto.CampaignDto;
import com.example.campaignservice.entity.Campaign;

public interface CampaignService {

    CampaignDto saveCampaign(CampaignDto campaignDto);

    Campaign findCampaignByName(String name);
}
