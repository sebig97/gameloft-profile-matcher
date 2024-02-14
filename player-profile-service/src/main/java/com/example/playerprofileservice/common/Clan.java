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
//@Embeddable
public class Clan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clan_id;
    private String clan_name;

    @OneToOne
    @JsonIgnore
    PlayerProfile playerProfile;
}
