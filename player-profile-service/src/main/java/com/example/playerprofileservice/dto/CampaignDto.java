package com.example.playerprofileservice.dto;

import com.example.campaignservice.common.Matcherss;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDto {
    private Long campaignId;
    private String game;
    private String name;
    private double priority;
//    private Map<String, Integer> levelMatchers;
//    private DoesNotHave doesNotHave;
//    private Has has;
    private Matcherss matchers;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private boolean enabled;
    private ZonedDateTime lastUpdated;
}
