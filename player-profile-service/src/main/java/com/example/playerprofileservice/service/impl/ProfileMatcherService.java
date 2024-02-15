package com.example.playerprofileservice.service.impl;

import com.example.playerprofileservice.dto.CampaignDto;
import com.example.playerprofileservice.dto.PlayerProfileDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProfileMatcherService {

    public boolean isProfileMatchingCampaign(PlayerProfileDto playerProfileDto, CampaignDto campaignDto) {
        return isLevelMatching(playerProfileDto, campaignDto) ||
                isHasMatching(playerProfileDto, campaignDto) ||
                isDoesNotHaveMatching(playerProfileDto, campaignDto);
    }

    public boolean isLevelMatching(PlayerProfileDto playerProfileDto, CampaignDto campaignDto) {
        int playerLevel = playerProfileDto.getLevel();
        int minLevel = campaignDto.getMatchers().getLevel().getMin();
        int maxLevel = campaignDto.getMatchers().getLevel().getMax();

        return playerLevel >= minLevel && playerLevel <= maxLevel;
    }

    public boolean isHasMatching(PlayerProfileDto playerProfileDto, CampaignDto campaignDto) {
        List<String> campaignCountries = campaignDto.getMatchers().getHas().getCountry();
        List<String> playerCountries = List.of(playerProfileDto.getCountry());
        List<String> campaignItems = campaignDto.getMatchers().getHas().getItems();
        Map<String, Integer> playerInventory = playerProfileDto.getInventory();

        //all match or anyMatch? to be discussed
        return campaignCountries.stream().anyMatch(playerCountries::contains) &&
                campaignItems.stream().anyMatch(playerInventory::containsKey);
    }

    private boolean isDoesNotHaveMatching(PlayerProfileDto playerProfileDto, CampaignDto campaignDto) {
        List<String> campaignItems = campaignDto.getMatchers().getDoesNotHave().getItems();
        Map<String, Integer> playerInventory = playerProfileDto.getInventory();

        return campaignItems.stream().noneMatch(playerInventory::containsKey);
    }
}
