package com.example.campaignservice.service;

import com.example.campaignservice.dto.CampaignDto;
import com.example.campaignservice.entity.Campaign;

import java.util.List;

public interface CampaignService {

    CampaignDto saveCampaign(CampaignDto campaignDto);

    CampaignDto findCampaignByName(String name);

    List<CampaignDto> findAllCampaigns();
}
