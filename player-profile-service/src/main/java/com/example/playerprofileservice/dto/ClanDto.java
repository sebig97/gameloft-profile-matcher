package com.example.playerprofileservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClanDto {
    private Long clan_id;
    private String clan_name;

    @JsonIgnore
    private PlayerProfileDto playerProfile;

}
