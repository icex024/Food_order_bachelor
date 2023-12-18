package group.Food_order_bachelor.repository;

import group.Food_order_bachelor.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MenuRepository extends JpaRepository<Menu, UUID> {
    @Modifying(clearAutomatically = true)
    @Query("delete from Menu m where m.id = :id")
    void deleteMenuByIdCustom(@Param(value="id") UUID id);
}
