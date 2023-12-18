package group.Food_order_bachelor.service.loyaltyService;

import group.Food_order_bachelor.model.Loyalty;
import group.Food_order_bachelor.model.LoyaltyDefinition;
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
        return createNewLoyaltiesIfNeeded(
                loyaltyRepository.selectLoyaltiesForUser(user.getId(),restaurant.getId()),user,restaurant);
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

    private Set<Loyalty> createNewLoyaltiesIfNeeded(Set<Loyalty> loyalties, User user,Restaurant restaurant){
        for(var loyaltyDefinition:restaurant.getLoyaltyDefinitions()){
            var loyaltyExist = false;
            for(var loyalty:loyalties){
                if(loyalty.getLoyaltyDefinition().getId().equals(loyaltyDefinition.getId())){
                    loyaltyExist = true;
                    break;
                }
            }
            if(!loyaltyExist){
                var loyalty = Loyalty.builder().id(UUID.randomUUID()).user(user).restaurant(restaurant)
                        .loyaltyDefinition(loyaltyDefinition).numberOfOrders(0).build();
                loyalties.add(loyalty);
                loyaltyRepository.saveAndFlush(loyalty);
            }
        }

        return loyalties;
    }
}
