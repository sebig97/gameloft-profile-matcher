package com.example.campaignservice.common;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Matcherss {
    @PrimaryKeyJoinColumn(name = "LevelId")
    @OneToOne(mappedBy = "campaign", cascade = CascadeType.ALL)
    private Level level;

    @PrimaryKeyJoinColumn(name = "DoesNotHaveId")
    @OneToOne(mappedBy = "campaign", cascade = CascadeType.ALL)
    private DoesNotHave doesNotHave;

    @PrimaryKeyJoinColumn(name = "HasId")
    @OneToOne(mappedBy = "campaign", cascade = CascadeType.ALL)
    private Has has;
}
