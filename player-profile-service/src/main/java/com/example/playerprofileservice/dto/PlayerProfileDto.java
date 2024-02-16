package com.example.playerprofileservice.dto;

import com.example.playerprofileservice.common.Clan;
import com.example.playerprofileservice.common.Device;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotEmpty(message = "credential cannot be empty or null")
    private String credential;
    private ZonedDateTime created;
    private ZonedDateTime modified;
    private ZonedDateTime lastSession;
    @NotNull
    private double totalSpent;
    @NotNull
    private double totalRefund;
    @NotNull
    private int totalTransactions;
    private ZonedDateTime lastPurchase;
    private List<String> activeCampaigns;
    private List<Device> devices;
    @NotNull
    private int level;
    private int xp;
    @Min(value=1, message = "totalPlaytime must be greater than or equal to 1")
    private int totalPlaytime;
    @NotEmpty(message = "country cannot be empty or null")
    private String country;
    @NotEmpty(message = "language cannot be empty or null")
    private String language;
    private ZonedDateTime birthdate;
    @NotEmpty(message = "gender cannot be empty or null")
    private String gender;
    private Map<String, Integer> inventory;
    private ClanDto clan;
    private String customField;
}
