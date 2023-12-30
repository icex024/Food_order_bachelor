package group.Food_order_bachelor.repository;

import group.Food_order_bachelor.model.MessageForAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;
@Repository
public interface MessageForAdminRepository extends JpaRepository<MessageForAdmin, UUID> {
    @Modifying(clearAutomatically = true)
    @Query("select m from MessageForAdmin m where m.messageForAdminStatus = 'NOT_REVIEWED'")
    Set<MessageForAdmin> getMessageForAdminThatAreNotReviewed();
}
