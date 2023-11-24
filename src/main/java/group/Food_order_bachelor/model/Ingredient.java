package group.Food_order_bachelor.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name="ingredients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column(unique = true)
    private String name;

    @Column(name="meatfree")
    private boolean meatFree;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="ingredients_allergens")
    private Set<Allergen> allergens;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Food> foods;
}
