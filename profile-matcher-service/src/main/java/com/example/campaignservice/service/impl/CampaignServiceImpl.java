package com.example.campaignservice.service.impl;

import com.example.campaignservice.common.DoesNotHave;
import com.example.campaignservice.common.Has;
import com.example.campaignservice.dto.CampaignDto;
import com.example.campaignservice.entity.Campaign;
import com.example.campaignservice.repository.CampaignRepository;
import com.example.campaignservice.service.CampaignService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;

    private final ModelMapper modelMapper;

    @Override
    public CampaignDto saveCampaign(CampaignDto campaignDto) {
        Optional<Campaign> optionalCampaign = campaignRepository.findCampaignByName(campaignDto.getName());

        if (optionalCampaign.isPresent()) {
            throw new RuntimeException("Campaign with name " + campaignDto.getName() + " already exists!");
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

        campaignRepository.save(campaignJpa);

        return modelMapper.map(campaignJpa, CampaignDto.class);
    }

    @Override
    public Campaign findCampaignByName(String name) {
        Campaign campaign = campaignRepository.findCampaignByName(name)
                .orElseThrow(() -> new RuntimeException("Campaign with name " + name + " doesn't exists in database!"));

        return campaign;
    }
}
