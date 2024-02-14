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
public class DoesNotHave {
    @ElementCollection
    @CollectionTable(name = "does_not_have_items", joinColumns = @JoinColumn(name = "campaign_id"))
    @Column(name = "item")
    private List<String> items;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long DoesNotHaveId;

    @OneToOne
    @JsonIgnore
    Campaign campaign;
}
