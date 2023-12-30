package group.Food_order_bachelor.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Entity
@Table(name="restaurants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "restaurant",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Menu> menus = new HashSet<>();

    @Column(name = "streetname")
    private String streetName;

    @Column(name = "streetnumber")
    private String streetNumber;

    @Column
    private String city;

    @Column
    private String country;

    @OneToMany(mappedBy = "restaurant",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Set<User> managers;

    @OneToMany(mappedBy = "restaurant",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Set<User> drivers;

    @Column(name = "worktimestart")
    private String workTimeStart;

    @Column(name = "worktimeend")
    private String workTimeEnd;

    @OneToMany(mappedBy = "restaurant",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<LoyaltyDefinition> loyaltyDefinitions = new HashSet<>();

    @Column(name="numberofslotsformakingfood")
    private int numberOfSlotsForMakingFood;

    @Column(name="freeslots")
    private int freeSlots;

    @OneToMany(mappedBy = "restaurant",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    @Column
    private double latitude;

    @Column
    private double longitude;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image imageRestaurant;
}
