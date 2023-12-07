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

    @Builder
    public LoyaltyDiscount(UUID id,Restaurant restaurant,int threshold,boolean reset,int discountInPercentage){
        super(id,restaurant,threshold,reset);
        this.discountInPercentage= discountInPercentage;
    }
}
