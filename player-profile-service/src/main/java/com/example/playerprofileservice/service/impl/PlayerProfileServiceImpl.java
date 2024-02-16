package com.example.playerprofileservice.service.impl;


import com.example.playerprofileservice.common.Clan;
import com.example.playerprofileservice.common.Device;
import com.example.playerprofileservice.dto.CampaignDto;
import com.example.playerprofileservice.dto.PlayerProfileDto;
import com.example.playerprofileservice.entity.PlayerProfile;
import com.example.playerprofileservice.exception.ResourceAlreadyExistsException;
import com.example.playerprofileservice.exception.ResourceNotFoundException;
import com.example.playerprofileservice.repository.PlayerProfileRepository;
import com.example.playerprofileservice.service.CampaignAPIClient;
import com.example.playerprofileservice.service.PlayerProfileService;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerProfileServiceImpl implements PlayerProfileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerProfileServiceImpl.class);
    private final PlayerProfileRepository playerProfileRepository;
    private final CampaignAPIClient campaignAPIClient;
    private final ProfileMatcherService profileMatcherService;
    private final ModelMapper modelMapper;

    @Override
    public PlayerProfileDto savePlayer(PlayerProfileDto playerProfileDto) {
        // Check if a player with the same credential already exists
        Optional<PlayerProfile> optionalPlayerProfile = playerProfileRepository.findByCredential(playerProfileDto.getCredential());

        if (optionalPlayerProfile.isPresent()) {
            // Throw exception if player with the same credential already exists
            throw new ResourceAlreadyExistsException("Player with credential " + playerProfileDto.getCredential() + " already exists!");
        }

        // Map PlayerProfileDto to PlayerProfile entity
        PlayerProfile playerJpa = modelMapper.map(playerProfileDto, PlayerProfile.class);

        // Map nested objects (Clan and Devices) if they are present in PlayerProfileDto
        if (playerProfileDto.getClan() != null) {
            Clan clan = Clan.builder()
                    .clan_name(playerJpa.getClan().getClan_name())
                    .playerProfile(playerJpa).build();

            // Set Clan in PlayerProfile
            playerJpa.setClan(clan);
        }

        if (!playerProfileDto.getDevices().isEmpty()) {
            List<Device> devices = playerJpa.getDevices().stream()
                    .map(deviceEntity -> Device.builder()
                            .model(deviceEntity.getModel())
                            .carrier(deviceEntity.getCarrier())
                            .firmware(deviceEntity.getFirmware())
                            .build())
                    .collect(Collectors.toList());


            // Set Clan in PlayerProfile
            playerJpa.setDevices(devices);
        }

        // Save the player profile in the database
        playerProfileRepository.save(playerJpa);

        // Map the saved PlayerProfile entity back to PlayerProfileDto and return it
        return modelMapper.map(playerJpa, PlayerProfileDto.class);

    }

    @Override
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultCampaign")
    public PlayerProfileDto findPlayerByUuid(UUID id) throws ResourceNotFoundException {
        LOGGER.info("inside findPlayerByUuid method");

        // Find the player profile in the database by its UUID
        Optional<PlayerProfile> optionalPlayerProfile = playerProfileRepository.findById(id);

        if (optionalPlayerProfile.isPresent()) {
            PlayerProfileDto playerProfileDto = modelMapper.map(optionalPlayerProfile.get(), PlayerProfileDto.class);

            // Retrieve all campaigns from external API
            List<CampaignDto> allCampaignsDto = campaignAPIClient.findAllCampaigns();

            // Check if player profile matches any of the current campaigns
            for (CampaignDto campaignDto : allCampaignsDto) {
                if (profileMatcherService.isProfileMatchingCampaign(playerProfileDto, campaignDto)) {
                    // Update player profile with active campaign
                    updatePlayerProfileWithActiveCampaign(playerProfileDto, campaignDto);

                    // Fetch updated player profile after updating
                    optionalPlayerProfile = playerProfileRepository.findById(id);
                    playerProfileDto = modelMapper.map(optionalPlayerProfile.get(), PlayerProfileDto.class);
                }
            }

            return playerProfileDto;
        } else {
            {
                throw new ResourceNotFoundException("Player with id + " + id + " is not registered");
            }
        }
    }

    // Fallback method to provide default campaigns when the main method fails
    public PlayerProfileDto getDefaultCampaign(UUID id, Exception exception) throws ResourceNotFoundException {
        LOGGER.info("inside getDefaultCampaign method");

        Optional<PlayerProfile> optionalPlayerProfile = playerProfileRepository.findById(id);

        if (optionalPlayerProfile.isPresent()) {
            PlayerProfileDto playerProfileDto = modelMapper.map(optionalPlayerProfile.get(), PlayerProfileDto.class);

            playerProfileDto.setActiveCampaigns(Arrays.asList("Default_Campaign_1", "Default_Campaign_2"));

            return playerProfileDto;
        } else {
            {
                throw new ResourceNotFoundException("Player with id " + id + " is not registered");
            }
        }
    }

    private void updatePlayerProfileWithActiveCampaign(PlayerProfileDto playerProfileDto, CampaignDto campaignDto) {
        // Check if the campaign is not already in the player's active campaigns list
        if (!playerProfileDto.getActiveCampaigns().contains(campaignDto.getName())) {
            // Add the campaign name to the player's active campaigns
            playerProfileDto.getActiveCampaigns().add(campaignDto.getName());
            // Update the player profile in the database
            updatePlayerProfile(playerProfileDto);
        }
    }

    @Transactional
    public void updatePlayerProfile(PlayerProfileDto playerProfileDto) {

        PlayerProfile playerProfileJpa = modelMapper.map(playerProfileDto, PlayerProfile.class);
        // Update the player profile in the database
        playerProfileRepository.save(playerProfileJpa);
    }
}
