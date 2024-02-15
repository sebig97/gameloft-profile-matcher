package com.example.playerprofileservice.service;


import com.example.playerprofileservice.dto.CampaignDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name = "CAMPAIGN-SERVICE")
@FeignClient(url = "http://localhost:8081", value = "CAMPAIGN-SERVICE")
public interface CampaignAPIClient {

    @GetMapping("/campaigns/{name}")
    CampaignDto getCampaignByName(@PathVariable String name);

    @GetMapping("/all")
    List<CampaignDto> findAllCampaigns();
}
