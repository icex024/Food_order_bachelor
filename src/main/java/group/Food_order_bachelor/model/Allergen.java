package group.Food_order_bachelor.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Builder
@Entity
@Table(name="allergens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Allergen {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "allergens")
    private Set<Ingredient> ingredients;
}
