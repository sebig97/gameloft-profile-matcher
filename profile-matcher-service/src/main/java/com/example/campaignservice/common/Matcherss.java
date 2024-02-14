package com.example.campaignservice.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Matcherss {
    @ElementCollection
    @CollectionTable(name = "level_matchers", joinColumns = @JoinColumn(name = "campaign_id"))
    @MapKeyColumn(name = "level_key")
    @Column(name = "level_value")
    private Map<String, Integer> levelMatchers;

    @PrimaryKeyJoinColumn(name = "DoesNotHaveId")
    @OneToOne(mappedBy = "campaign", cascade = CascadeType.ALL)
    private DoesNotHave doesNotHave;

    @PrimaryKeyJoinColumn(name = "HasId")
    @OneToOne(mappedBy = "campaign", cascade = CascadeType.ALL)
    private Has has;
}
