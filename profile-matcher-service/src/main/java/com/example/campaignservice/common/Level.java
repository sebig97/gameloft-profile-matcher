package com.example.campaignservice.common;

import com.example.campaignservice.entity.Campaign;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long LevelId;

    private int min;
    private int max;

    @OneToOne
    @JsonIgnore
    Campaign campaign;


}
