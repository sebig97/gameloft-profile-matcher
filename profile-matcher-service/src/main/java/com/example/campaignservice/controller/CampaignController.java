package com.example.campaignservice.controller;

import com.example.campaignservice.dto.CampaignDto;
import com.example.campaignservice.entity.Campaign;
import com.example.campaignservice.service.CampaignService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping("/save")
    public ResponseEntity<?> saveCampaign(@RequestBody CampaignDto campaignDto) {
        CampaignDto savedCampaignDto = campaignService.saveCampaign(campaignDto);
        return new ResponseEntity<>(savedCampaignDto, HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getCampaignByName(@PathVariable String name) {
        CampaignDto campaignDto = campaignService.findCampaignByName(name);
        return new ResponseEntity<>(campaignDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllCampaigns() {
        List<CampaignDto> allCampaignsDto = campaignService.findAllCampaigns();
        return new ResponseEntity<>(allCampaignsDto, HttpStatus.OK);
    }
}
