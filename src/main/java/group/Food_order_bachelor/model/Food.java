package group.Food_order_bachelor.model;

import group.Food_order_bachelor.enums.Food_type;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.jdbc.BinaryJdbcType;
import org.hibernate.type.descriptor.jdbc.VarbinaryJdbcType;
import org.springframework.data.util.QTypeContributor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Entity
@Table(name="foods")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column
    private String name;

    @Column
    private String description;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="foods_ingredients")
    private Set<Ingredient> ingredients;

    @Column(name = "estimatedtimeforpreparationinminutes")
    private int estimatedTimeForPreparationInMinutes;

    @Column(name="foodtype")
    @Enumerated(EnumType.STRING)
    private Food_type foodType;

    @Column(name="meatfree")
    private boolean meatFree;

    @Column
    private double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image imageFood;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id",nullable = true)
    private Menu menu;

    @OneToMany(mappedBy = "freeDrink",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<LoyaltyDefinition> loyaltyFreeDrink = new HashSet<>();

    @ManyToMany(mappedBy = "foods")
    private Set<Order> orders;
}
