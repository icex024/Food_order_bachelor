package group.Food_order_bachelor.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name="menus")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column
    private String name;

    @OneToMany(mappedBy = "menu",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Food> foods =new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;
}
