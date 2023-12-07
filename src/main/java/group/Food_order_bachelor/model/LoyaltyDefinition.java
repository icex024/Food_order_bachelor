package group.Food_order_bachelor.model;

import group.Food_order_bachelor.enums.Loyalty_type;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name="loyalties")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="loyaltyType",
        discriminatorType = DiscriminatorType.STRING)
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
}
