package com.example.playerprofileservice.service.impl;



import com.example.playerprofileservice.common.Clan;
import com.example.playerprofileservice.common.Device;
import com.example.playerprofileservice.dto.APIResponseDto;
import com.example.playerprofileservice.dto.CampaignDto;
import com.example.playerprofileservice.dto.PlayerProfileDto;
import com.example.playerprofileservice.entity.PlayerProfile;
import com.example.playerprofileservice.repository.PlayerProfileRepository;
import com.example.playerprofileservice.service.CampaignAPIClient;
import com.example.playerprofileservice.service.PlayerProfileService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerProfileServiceImpl implements PlayerProfileService {

    private final PlayerProfileRepository playerProfileRepository;

    private final CampaignAPIClient campaignAPIClient;

    private final ModelMapper modelMapper;

    @Override
    public PlayerProfileDto savePlayer(PlayerProfileDto playerProfileDto) {
//        PlayerProfile optionalPlayerProfileEntity =
//                playerProfileRepository.findByCredential(playerProfileDto.getCredential())
//                        .orElseThrow(() -> new RuntimeException("Player already exists!"));
        Optional<PlayerProfile> optionalPlayerProfile = playerProfileRepository.findByCredential(playerProfileDto.getCredential());

        if (optionalPlayerProfile.isPresent()) {
            throw new RuntimeException("Player with credential " + playerProfileDto.getCredential() + " already exists!");
        }

//        PlayerProfile optionalPlayerProfileEntity =
//                playerProfileRepository.findById(playerProfileDto.getPlayerId())
//                        .orElse(playerProfileRepository.save(new PlayerProfile()));


        PlayerProfile playerJpa = modelMapper.map(playerProfileDto, PlayerProfile.class);

        if (playerProfileDto.getClan() != null) {
//            //nr2
//            Clan clan = new Clan();
//            clan.setClan_name("iPhone 15 pro");
//
//            // Set PlayerProfile in Clan
//            clan.setPlayerProfile(playerJpa);

            Clan clan = Clan.builder()
                    .clan_name(playerJpa.getClan().getClan_name())
                    .playerProfile(playerJpa).build();

            // Set Clan in PlayerProfile
            playerJpa.setClan(clan);
        }

        if (!playerProfileDto.getDevices().isEmpty()) {
//            //nr2
//            Clan clan = new Clan();
//            clan.setClan_name("iPhone 15 pro");
//
//            // Set PlayerProfile in Clan
//            clan.setPlayerProfile(playerJpa);

//            Device device = Device.builder()
//                    .model(playerJpa.getDevices().get(0).getModel())
//                    .carrier(playerJpa.getDevices().get(0).getCarrier())
//                    .firmware(playerJpa.getDevices().get(0).getFirmware())
//                    .playerProfile(playerJpa).build();

            List<Device> devices = playerJpa.getDevices().stream()
                    .map(deviceEntity -> Device.builder()
                            .model(deviceEntity.getModel())
                            .carrier(deviceEntity.getCarrier())
                            .firmware(deviceEntity.getFirmware())
//                            .playerProfile(playerJpa)
                            .build())
                    .collect(Collectors.toList());


            // Set Clan in PlayerProfile
            playerJpa.setDevices(devices);
        }

        playerProfileRepository.save(playerJpa);

        return modelMapper.map(playerJpa, PlayerProfileDto.class);

    }

    @Override
    public APIResponseDto findPlayerByUuid(UUID id) {
        Optional<PlayerProfile> optionalPlayerProfile = playerProfileRepository.findById(id);

        if (optionalPlayerProfile.isPresent()) {
            PlayerProfileDto playerProfileDto = modelMapper.map(optionalPlayerProfile.get(), PlayerProfileDto.class);
            CampaignDto campaignDto = campaignAPIClient.getCampaignByName("sebi113");
//            return playerProfileDto;

            APIResponseDto apiResponseDto = APIResponseDto.builder()
                    .playerProfileDto(playerProfileDto)
                    .campaignDto(campaignDto)
                    .build();

            return apiResponseDto;
        } else {
            {
                throw new RuntimeException("Player with id + " + id + " is not registered");
            }
        }

    }
}
