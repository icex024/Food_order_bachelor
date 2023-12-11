package group.Food_order_bachelor.service.loyaltyService;

import group.Food_order_bachelor.model.Loyalty;
import group.Food_order_bachelor.model.Restaurant;
import group.Food_order_bachelor.model.User;

import java.util.Set;

public interface LoyaltyServiceInterface {
    Set<Loyalty> findLoyaltiesForUser(User user, Restaurant restaurant);
    void incrementLoyalties(User user,Restaurant restaurant);
}
