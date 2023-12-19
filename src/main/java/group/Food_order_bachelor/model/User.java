package group.Food_order_bachelor.model;

import group.Food_order_bachelor.enums.User_role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Builder
@Entity
@Table(name="registered_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name="firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    @Column
    private String city;

    @Column(name="streetname")
    private String streetName;

    @Column(name="streetnumber")
    private String streetNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private User_role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Order> orders= new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deliverer_slots_id", referencedColumnName = "id")
    private DelivererSlots delivererSlots;

    @OneToMany(mappedBy = "deliverer",fetch =  FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Order> ordersForDeliverer = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
