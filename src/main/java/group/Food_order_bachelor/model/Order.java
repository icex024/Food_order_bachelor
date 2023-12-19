package group.Food_order_bachelor.model;

import group.Food_order_bachelor.enums.Payment_type;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Builder
@Entity
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orders_foods")
    private List<Food> foods;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column
    private double price;

    @Column
    private String note;

    @Column(name = "paymenttype")
    @Enumerated(EnumType.STRING)
    private Payment_type paymentType;

    @Column(name="timeofmakingorder")
    private LocalDateTime timeOfMakingOrder;

    @Column(name="estimatedtime")
    private int estimatedTime;

    @Column
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="deliverer_id")
    private User deliverer;
}
