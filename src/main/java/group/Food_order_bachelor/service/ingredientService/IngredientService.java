package group.Food_order_bachelor.service.ingredientService;

import group.Food_order_bachelor.dto.ingredient.CreateIngredientDto;
import group.Food_order_bachelor.dto.ingredient.IngredientAdapter;
import group.Food_order_bachelor.dto.ingredient.IngredientToShowDto;
import group.Food_order_bachelor.model.Ingredient;
import group.Food_order_bachelor.repository.IngredientRepository;
import group.Food_order_bachelor.service.allergenService.AllergenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public List<IngredientToShowDto> getAllIngredients() {
        List<IngredientToShowDto> dtos = new ArrayList<>();
        for (var ingredient:
        ingredientRepository.findAll()) {
            dtos.add(ingredientAdapter.ingredientToIngredientToShowDto(ingredient));
        }
        return dtos;
    }

    @Override
    public Set<Ingredient> getIngredientsByIds(List<String> ids) {
        List<UUID> uuids = new ArrayList<>();
        for (var id:
             ids) {
            uuids.add(UUID.fromString(id));
        }
        return new HashSet<>(ingredientRepository.findAllById(uuids));
    }
}
