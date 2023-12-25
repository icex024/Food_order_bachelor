package group.Food_order_bachelor.repository;

import group.Food_order_bachelor.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Modifying(clearAutomatically = true)
    @Query("select o From Order o where o.user.id = :customerId and o.restaurant.id = :restaurantId " +
            "and (o.status = 'PROCESS' OR o.status = 'READY' OR o.status = 'TAKEN_BY_DELIVERER' OR o.status = 'IN_DELIVERY')")
    Set<Order> getOrdersByCustomerAndRestaurantInitialState(@Param(value = "customerId") UUID customerId
            ,@Param("restaurantId") UUID restaurantId);

    @Modifying(clearAutomatically = true)
    @Query("select o from Order o where o.user.id = :customerId and o.restaurant.id = :restaurantId " +
            "and (o.status = 'CANCELLED' OR o.status = 'DELIVERED')")
    Set<Order> getOrdersByCustomerAndRestaurantHistory(@Param(value = "customerId") UUID customerId
            ,@Param("restaurantId") UUID restaurantId);

    @Modifying(clearAutomatically = true)
    @Query("select o From Order o where o.restaurant.id = :restaurantId " +
            "and (o.status = 'PROCESS' or o.status = 'READY')")
    Set<Order> getOrdersForDriverInitial(@Param(value = "restaurantId") UUID restaurantId);

    @Modifying(clearAutomatically = true)
    @Query("select o From Order o where o.deliverer.id = :delivererId " +
            "and (o.status = 'TAKEN_BY_DELIVERER')")
    Set<Order> getOrdersForDelivererTaken(@Param(value = "delivererId") UUID delivererId);

    @Modifying(clearAutomatically = true)
    @Query("select o From Order o where o.deliverer.id = :delivererId " +
            "and (o.status = 'IN_DELIVERY')")
    Set<Order> getOrdersForDelivererInDelivery(@Param(value = "delivererId") UUID delivererId);

    @Modifying(clearAutomatically = true)
    @Query("select o From Order o where o.deliverer.id = :delivererId " +
            "and (o.status = 'DELIVERED')")
    Set<Order> getOrdersByForDelivererHistory(@Param(value = "delivererId") UUID delivererId);

    @Modifying(clearAutomatically = true)
    @Query("select o From Order o where o.restaurant.id = :restaurantId " +
            "and (o.status = 'DELIVERED' and o.status = 'CANCELLED')")
    Set<Order> getOrdersForRestaurantHistory(@Param(value = "restaurantId") UUID restaurantId);
}
