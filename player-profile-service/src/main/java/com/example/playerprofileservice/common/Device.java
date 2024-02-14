package com.example.playerprofileservice.common;

import com.example.playerprofileservice.entity.PlayerProfile;
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
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    private String carrier;
    private String firmware;

    @ManyToOne
    @JsonIgnore
    private PlayerProfile playerProfile;
}
