package com.example.campaignservice.common;

import com.example.campaignservice.entity.Campaign;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Has {

    @ElementCollection
    @CollectionTable(name = "has_country", joinColumns = @JoinColumn(name = "campaign_id"))
    @Column(name = "country")
    private List<String> country;

    @ElementCollection
    @CollectionTable(name = "has_items", joinColumns = @JoinColumn(name = "campaign_id"))
    @Column(name = "item")
    private List<String> items;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long HasId;

    @OneToOne
    @JsonIgnore
    Campaign campaign;
}
