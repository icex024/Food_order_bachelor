package group.Food_order_bachelor.repository;

import group.Food_order_bachelor.model.Allergen;
import group.Food_order_bachelor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AllergenRepository extends JpaRepository<Allergen, UUID> {
}
