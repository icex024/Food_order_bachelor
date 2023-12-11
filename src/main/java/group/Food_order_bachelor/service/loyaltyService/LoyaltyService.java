package group.Food_order_bachelor.service.loyaltyService;

import group.Food_order_bachelor.model.Loyalty;
import group.Food_order_bachelor.model.Restaurant;
import group.Food_order_bachelor.model.User;
import group.Food_order_bachelor.repository.LoyaltyV2Repository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoyaltyService implements LoyaltyServiceInterface {
    private final LoyaltyV2Repository loyaltyRepository;
    @Override//ovo je lose implementirano, napravi da dodaje nove loyaltije i sve provere itd.
    public Set<Loyalty> findLoyaltiesForUser(User user, Restaurant restaurant) {
        var loyalties =  loyaltyRepository.selectLoyaltiesForUser(user.getId(),restaurant.getId());
        if(loyalties.isEmpty()){
            return createNewLoyalties(user, restaurant);
        }else{
            return loyalties;
        }
    }

    @Override
    public void incrementLoyalties(User user, Restaurant restaurant) {
        var loyalties =  loyaltyRepository.selectLoyaltiesForUser(user.getId(),restaurant.getId());
        for(var loyalty: loyalties){
            if(loyalty.getLoyaltyDefinition().getThreshold() == loyalty.getNumberOfOrders()){
                if(loyalty.getLoyaltyDefinition().isReset()){
                    loyalty.setNumberOfOrders(0);
                }else{
                    loyalty.setNumberOfOrders(loyalty.getNumberOfOrders()+1);
                }
                loyaltyRepository.saveAndFlush(loyalty);
                continue;
            }
            loyalty.setNumberOfOrders(loyalty.getNumberOfOrders()+1);
            loyaltyRepository.saveAndFlush(loyalty);
        }
    }

    private Set<Loyalty> createNewLoyalties(User user,Restaurant restaurant){
        Set<Loyalty> loyalties = new HashSet<>();
        for(var loyaltyDefinition:restaurant.getLoyaltyDefinitions()){
            loyalties.add(Loyalty.builder().id(UUID.randomUUID()).user(user).restaurant(restaurant)
                    .loyaltyDefinition(loyaltyDefinition).numberOfOrders(0).build());
        }
        loyaltyRepository.saveAll(loyalties);
        return loyalties;
    }
}
