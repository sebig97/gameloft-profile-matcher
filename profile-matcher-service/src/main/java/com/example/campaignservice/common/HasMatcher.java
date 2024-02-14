//package com.example.campaignservice.common;
//
//import com.example.campaignservice.entity.Campaign;
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//public class HasMatcher {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "campaign_id")
//    private Campaign campaign;
//
//    @ElementCollection
//    @CollectionTable(name = "has_country_matcher", joinColumns = @JoinColumn(name = "has_matcher_id"))
//    @Column(name = "country")
//    private List<String> countries;
//
//    @ElementCollection
//    @CollectionTable(name = "has_item_matcher", joinColumns = @JoinColumn(name = "has_matcher_id"))
//    @Column(name = "item")
//    private List<String> items;
//
//}
