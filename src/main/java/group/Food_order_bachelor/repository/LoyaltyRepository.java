package group.Food_order_bachelor.repository;

import group.Food_order_bachelor.model.LoyaltyDefinition;
import group.Food_order_bachelor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoyaltyRepository extends JpaRepository<LoyaltyDefinition, UUID> {
}
