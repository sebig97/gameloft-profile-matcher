package com.example.campaignservice.controller;

import com.example.campaignservice.dto.CampaignDto;
import com.example.campaignservice.entity.Campaign;
import com.example.campaignservice.service.CampaignService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping("/save")
    public ResponseEntity<?> saveCampaign(@RequestBody CampaignDto campaignDto) {
        CampaignDto savedCampaignDto = campaignService.saveCampaign(campaignDto);
        return new ResponseEntity<>(campaignDto, HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getCampaignByName(@PathVariable String name) {
        Campaign campaign = campaignService.findCampaignByName(name);
        return new ResponseEntity<>(campaign, HttpStatus.OK);
    }
}
