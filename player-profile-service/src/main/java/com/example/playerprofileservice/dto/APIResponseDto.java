package com.example.playerprofileservice.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIResponseDto {
    private PlayerProfileDto playerProfileDto;
    private CampaignDto campaignDto;
}
