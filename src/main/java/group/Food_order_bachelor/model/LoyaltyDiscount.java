package group.Food_order_bachelor.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="loyaltydiscount")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoyaltyDiscount extends LoyaltyDefinition {
    @Column(name="discountinpercentage")
    private int discountInPercentage;
}
