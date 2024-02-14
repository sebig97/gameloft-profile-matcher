package com.example.playerprofileservice.dto;

import com.example.playerprofileservice.common.Clan;
import com.example.playerprofileservice.common.Device;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerProfileDto {
    private UUID playerId;
//    private Long playerId;
    private String credential;
    private ZonedDateTime created;
    private ZonedDateTime modified;
    private ZonedDateTime lastSession;
    private double totalSpent;
    private double totalRefund;
    private int totalTransactions;
    private ZonedDateTime lastPurchase;
    private List<String> activeCampaigns;
    private List<Device> devices;
    private int level;
    private int xp;
    private int totalPlaytime;
    private String country;
    private String language;
    private ZonedDateTime birthdate;
    private String gender;
    private Map<String, Integer> inventory;
    private ClanDto clan;
    private String customField;
}
