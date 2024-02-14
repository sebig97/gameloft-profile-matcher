package com.example.playerprofileservice.entity;

import com.example.playerprofileservice.common.Clan;
import com.example.playerprofileservice.common.Device;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "player_profiles")
public class PlayerProfile {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "player_id", updatable = false, nullable = false)
    private UUID playerId;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long playerId;
    @Column(name = "credential", nullable = false)
    private String credential;
    @Column(name = "created", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime created;
    @Column(name = "modified")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime modified;
    @Column(name = "last_session")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime lastSession;
    @Column(name = "total_spent", nullable = false)
    private double totalSpent;
    @Column(name = "total_refund", nullable = false)
    private double totalRefund;
    @Column(name = "total_transactions", nullable = false)
    private int totalTransactions;
    @Column(name = "last_purchase")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime lastPurchase;

//    The activeCampaigns field is annotated with @ElementCollection since it's a collection of basic types
    @ElementCollection
    @Column(name = "active_campaigns")
    private List<String> activeCampaigns;
//    In this case, Device is marked with @Embeddable, and it's used within the PlayerProfile entity as a collection using the @ElementCollection annotation. This is appropriate when Device is considered as a component or value type that is embedded directly into the entity.
    @Column(name = "devices")
    @OneToMany(mappedBy = "playerProfile", cascade = CascadeType.ALL)
    private List<Device> devices;
    @Column(name = "level", nullable = false)
    private int level;
    @Column(name = "xp", nullable = false)
    private int xp;
    @Column(name = "total_playtime", nullable = false)
    private int totalPlaytime;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "language", nullable = false)
    private String language;
    @Column(name = "birthdate", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime birthdate;
    @Column(name = "gender", nullable = false)
    private String gender;

//    Inventory and Clan are marked with @Embeddable, and they are used within the PlayerProfile entity with the @Embedded annotation
//    @Column(name = "inventory", nullable = false)
//    @Embedded

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "cash", column = @Column(name = "inventory_cash")),
//            @AttributeOverride(name = "coins", column = @Column(name = "inventory_coins")),
//            @AttributeOverride(name = "items", column = @Column(name = "inventory_items"))
//    })
//    private Inventory inventory;

    @ElementCollection
    private Map<String, Integer> inventory;


    @PrimaryKeyJoinColumn(name = "clan_id")
    @OneToOne(mappedBy = "playerProfile", cascade = CascadeType.ALL)
    private Clan clan;
//    @OneToOne(mappedBy = "playerProfile")
//    @PrimaryKeyJoinColumn(name = "clan_id")



    @Column(name = "customField", nullable = false)
    private String customField;



//    nullable = true este by default, nu il mai trec
//    din cate am observat, player id este un uuid si am optat fata de id simplu


//    Key points:
//
//@ElementCollection is used for collections of basic types (List<String> and List<Device>).
//@Embedded is used for complex types (Inventory and Clan).
}
