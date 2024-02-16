package com.example.playerprofileservice.service;


import com.example.playerprofileservice.dto.CampaignDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name = "CAMPAIGN-SERVICE")
@FeignClient(name = "CAMPAIGN-SERVICE")
public interface CampaignAPIClient {

    @GetMapping("/campaigns/{name}")
    CampaignDto getCampaignByName(@PathVariable String name);

    @GetMapping("/campaigns/all")
    List<CampaignDto> findAllCampaigns();
}
