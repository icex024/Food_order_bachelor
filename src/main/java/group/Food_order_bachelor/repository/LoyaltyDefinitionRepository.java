package group.Food_order_bachelor.repository;

import group.Food_order_bachelor.model.LoyaltyDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoyaltyDefinitionRepository extends JpaRepository<LoyaltyDefinition, UUID> {
    @Modifying(clearAutomatically = true)
    @Query("select l from LoyaltyDefinition l where l.restaurant.id = :restaurantId")
    List<LoyaltyDefinition> getLoyalties(@Param(value = "restaurantId") UUID restaurantId);
}
