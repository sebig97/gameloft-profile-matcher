package com.example.campaignservice.entity;

import com.example.campaignservice.common.DoesNotHave;
import com.example.campaignservice.common.Has;
import com.example.campaignservice.common.Matcherss;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonIgnore
    private Long campaign_id;

    private String game;
    private String name;
    private double priority;

//    @ElementCollection
//    @CollectionTable(name = "level_matchers", joinColumns = @JoinColumn(name = "campaign_id"))
//    @MapKeyColumn(name = "level_key")
//    @Column(name = "level_value")
//    private Map<String, Integer> levelMatchers;
//
//    @PrimaryKeyJoinColumn(name = "DoesNotHaveId")
//    @OneToOne(mappedBy = "campaign", cascade = CascadeType.ALL)
//    private DoesNotHave doesNotHave;
//
//    @PrimaryKeyJoinColumn(name = "HasId")
//    @OneToOne(mappedBy = "campaign", cascade = CascadeType.ALL)
//    private Has has;

    @Embedded
    private Matcherss matchers;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime endDate;
    private boolean enabled;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime lastUpdated;


}
