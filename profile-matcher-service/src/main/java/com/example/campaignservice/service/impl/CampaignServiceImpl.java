package com.example.campaignservice.service.impl;

import com.example.campaignservice.common.DoesNotHave;
import com.example.campaignservice.common.Has;
import com.example.campaignservice.common.Level;
import com.example.campaignservice.dto.CampaignDto;
import com.example.campaignservice.entity.Campaign;
import com.example.campaignservice.exception.ResourceAlreadyExistsException;
import com.example.campaignservice.exception.ResourceNotFoundException;
import com.example.campaignservice.repository.CampaignRepository;
import com.example.campaignservice.service.CampaignService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class CampaignServiceImpl implements CampaignService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignServiceImpl.class);

    private final CampaignRepository campaignRepository;

    private final ModelMapper modelMapper;

    @Override
    public CampaignDto saveCampaign(CampaignDto campaignDto) throws ResourceAlreadyExistsException {
        Optional<Campaign> optionalCampaign = campaignRepository.findCampaignByName(campaignDto.getName());

        if (optionalCampaign.isPresent()) {
            throw new ResourceAlreadyExistsException("Campaign with name " + campaignDto.getName() + " already exists!");
        }

        Campaign campaignJpa = modelMapper.map(campaignDto, Campaign.class);

        if (campaignDto.getMatchers().getDoesNotHave() != null) {
            DoesNotHave doesNotHave = DoesNotHave.builder()
                    .items(campaignJpa.getMatchers().getDoesNotHave().getItems())
                    .campaign(campaignJpa)
                    .build();

            campaignJpa.getMatchers().setDoesNotHave(doesNotHave);
        }

        if (campaignDto.getMatchers().getHas() != null) {
            Has has = Has.builder()
                    .items(campaignJpa.getMatchers().getHas().getItems())
                    .country(campaignJpa.getMatchers().getHas().getCountry())
                    .campaign(campaignJpa)
                    .build();

            campaignJpa.getMatchers().setHas(has);
        }

        if (campaignDto.getMatchers().getLevel() != null) {
            Level level = Level.builder()
                    .min(campaignJpa.getMatchers().getLevel().getMin())
                    .max(campaignJpa.getMatchers().getLevel().getMax())
                    .campaign(campaignJpa)
                    .build();

            campaignJpa.getMatchers().setLevel(level);
        }

        campaignRepository.save(campaignJpa);

        return modelMapper.map(campaignJpa, CampaignDto.class);
    }

    @Override
    public CampaignDto findCampaignByName(String name) throws ResourceNotFoundException {
        LOGGER.info("inside findCampaignByName method");

        Optional<Campaign> optionalCampaign = campaignRepository.findCampaignByName(name);

        if (optionalCampaign.isPresent()) {
            CampaignDto campaignDto = modelMapper.map(optionalCampaign.get(), CampaignDto.class);
            return campaignDto;
        } else {
            throw new ResourceNotFoundException("Campaign with name " + name + " doesn't exists in database!");
        }

    }

    @Override
    public List<CampaignDto> findAllCampaigns() {
        LOGGER.info("inside findAllCampaigns method");

        List<Campaign> allCampaigns = campaignRepository.findAll();
        Function<Campaign, CampaignDto> JpaToDto = campaign -> modelMapper.map(campaign, CampaignDto.class);

        if (allCampaigns.size() > 0) {
            List<CampaignDto> allCampaignsDto = allCampaigns.stream()
                    .map(JpaToDto).toList();

            return allCampaignsDto;
        }

        return new ArrayList<>();
    }
}
