package group.Food_order_bachelor.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@Entity
@Table(name="deliverer_slots")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DelivererSlots {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @OneToOne(mappedBy = "delivererSlots",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private User user;

    @Column(name="availableslots")
    private int availableSlots;

    @Column(name="maxslots")
    private int maxSlots;
}
