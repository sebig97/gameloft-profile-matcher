package com.example.playerprofileservice.service;


import com.example.playerprofileservice.dto.PlayerProfileDto;
import com.example.playerprofileservice.entity.PlayerProfile;

import java.util.UUID;

public interface PlayerProfileService {

    PlayerProfileDto savePlayer(PlayerProfileDto playerProfileDto);

//    PlayerProfileDto findPlayerByUuid(Long uuid);
    PlayerProfileDto findPlayerByUuid(UUID uuid);
}
