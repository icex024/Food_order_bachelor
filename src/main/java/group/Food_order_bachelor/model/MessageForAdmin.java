package group.Food_order_bachelor.model;

import group.Food_order_bachelor.enums.Message_for_admin_status;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="messages_for_admin")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageForAdmin {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manager_id")
    private User manager;

    @Column(name = "messageforadminstatus")
    @Enumerated(EnumType.STRING)
    private Message_for_admin_status messageForAdminStatus;
}
