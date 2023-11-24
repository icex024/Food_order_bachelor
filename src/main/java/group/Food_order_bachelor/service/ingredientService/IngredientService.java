package group.Food_order_bachelor.service.ingredientService;

import group.Food_order_bachelor.dto.ingredient.CreateIngredientDto;
import group.Food_order_bachelor.dto.ingredient.IngredientAdapter;
import group.Food_order_bachelor.repository.IngredientRepository;
import group.Food_order_bachelor.service.allergenService.AllergenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientService implements IngredientServiceInterface {
    private final AllergenService allergenService;
    private final IngredientAdapter ingredientAdapter = new IngredientAdapter();
    private final IngredientRepository ingredientRepository;
    @Override
    public void CreateNewIngredient(CreateIngredientDto dto) {
        ingredientRepository.save(ingredientAdapter
                .ingredientDtoToIngredient(dto,allergenService.getAllergensByIds(dto.getAllergenIds())));
    }

}
