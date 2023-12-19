package group.Food_order_bachelor.repository;

import group.Food_order_bachelor.model.Restaurant;
import group.Food_order_bachelor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
}
