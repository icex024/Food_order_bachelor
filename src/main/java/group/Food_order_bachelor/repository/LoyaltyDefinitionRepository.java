package group.Food_order_bachelor.repository;

import group.Food_order_bachelor.model.LoyaltyDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoyaltyDefinitionRepository extends JpaRepository<LoyaltyDefinition, UUID> {
}
