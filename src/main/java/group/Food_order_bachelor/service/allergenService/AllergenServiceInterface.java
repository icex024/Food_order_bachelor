package group.Food_order_bachelor.service.allergenService;

import group.Food_order_bachelor.dto.allergen.AllergenDto;
import group.Food_order_bachelor.model.Allergen;

import java.util.List;
import java.util.Set;

public interface AllergenServiceInterface {
    void createNewAllergen(String name);
    List<AllergenDto> getAllergens();
    Set<Allergen> getAllergensByIds(List<String> ids);
}
