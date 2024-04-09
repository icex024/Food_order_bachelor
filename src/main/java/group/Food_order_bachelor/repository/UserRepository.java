package group.Food_order_bachelor.repository;

import group.Food_order_bachelor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByUsername(String username);

    @Modifying(clearAutomatically = true)
    @Query("select u from User u where u.role = 'MANAGER' or u.role = 'DELIVERER'")
    List<User> findManagersAndDeliverers();
}
