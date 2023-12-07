package group.Food_order_bachelor.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="loyaltyfreedrink")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoyaltyFreeDrink extends LoyaltyDefinition  {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="freedrink_id")
    private Food freeDrink;
    @Builder
    public LoyaltyFreeDrink(UUID id,Restaurant restaurant,int threshold,boolean reset,Food freeDrink){
        super(id,restaurant,threshold,reset);
        this.freeDrink= freeDrink;
    }
}
