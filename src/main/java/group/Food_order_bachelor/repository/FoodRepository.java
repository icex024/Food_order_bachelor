package group.Food_order_bachelor.repository;

import group.Food_order_bachelor.model.Food;
import group.Food_order_bachelor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface FoodRepository extends JpaRepository<Food, UUID> {
    @Modifying(clearAutomatically = true)
    @Query("update Food as food set food.price = :price where food.id = :id")
    void updatePrice(@Param(value="id") UUID id,@Param(value="price") double price);

    @Modifying(clearAutomatically = true)
    @Query("select f From Food f where f.menu.id = :menu_id")
    Set<Food> selectFoodsByMenuId(@Param(value="menu_id") UUID menu_id);

    @Modifying(clearAutomatically = true)
    @Query("update Food as food set food.menu.id = null where food.menu.id = :menu_id")
    void updateMenuStatus(@Param(value="menu_id") UUID menu_id);
}
