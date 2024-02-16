package com.example.campaignservice.dto;


import com.example.campaignservice.common.Matcherss;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDto {
    private Long campaignId;
    @NotEmpty(message = "game cannot be empty or null")
    private String game;
    @NotEmpty(message = "name cannot be empty or null")
    private String name;
    private double priority;
    private Matcherss matchers;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private boolean enabled;
    private ZonedDateTime lastUpdated;
}
