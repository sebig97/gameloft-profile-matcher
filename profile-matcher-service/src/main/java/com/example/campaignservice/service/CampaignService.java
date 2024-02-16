package com.example.campaignservice.service;

import com.example.campaignservice.dto.CampaignDto;
import com.example.campaignservice.exception.ResourceAlreadyExistsException;
import com.example.campaignservice.exception.ResourceNotFoundException;

import java.util.List;

public interface CampaignService {

    CampaignDto saveCampaign(CampaignDto campaignDto) throws ResourceAlreadyExistsException;

    CampaignDto findCampaignByName(String name) throws ResourceNotFoundException;

    List<CampaignDto> findAllCampaigns();
}
