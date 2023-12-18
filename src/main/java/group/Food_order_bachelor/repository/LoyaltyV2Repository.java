package group.Food_order_bachelor.repository;

import group.Food_order_bachelor.model.Loyalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface LoyaltyV2Repository extends JpaRepository<Loyalty, UUID> {
    @Modifying(clearAutomatically = true)
    @Query(value = "select bzvz from Loyalty bzvz where bzvz.user.id = :user_id and bzvz.restaurant.id = :restaurant_id")
    Set<Loyalty> selectLoyaltiesForUser(@Param(value = "user_id")UUID user_id
            ,@Param(value="restaurant_id") UUID restaurant_id);
}
