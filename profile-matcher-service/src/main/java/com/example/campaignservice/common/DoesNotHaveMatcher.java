//package com.example.campaignservice.common;
//
//
//import com.example.campaignservice.entity.Campaign;
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//public class DoesNotHaveMatcher {
////    @ElementCollection
////    @CollectionTable(name = "does_not_have_items", joinColumns = @JoinColumn(name = "campaign_id"))
////    @Column(name = "item")
////    private List<String> items;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "campaign_id")
//    private Campaign campaign;
//
//    @ElementCollection
//    @CollectionTable(name = "does_not_have_item_matcher", joinColumns = @JoinColumn(name = "does_not_have_matcher_id"))
//    @Column(name = "item")
//    private List<String> items;
//}
