package group.Food_order_bachelor.model;

import group.Food_order_bachelor.enums.Loyalty_type;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name="loyalty_definitions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoyaltyDefinition {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column
    private int threshold;

    @Column
    private boolean reset;

    @Column(name="discountinpercentage")
    private int discountInPercentage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="freedrink_id",nullable = true)
    private Food freeDrink;

    @Column(name = "loyalty_type")
    @Enumerated(EnumType.STRING)
    private Loyalty_type loyaltyType;
}
