package group.Food_order_bachelor.controller;

import group.Food_order_bachelor.dto.ingredient.CreateIngredientDto;
import group.Food_order_bachelor.dto.ingredient.IngredientToShowDto;
import group.Food_order_bachelor.service.ingredientService.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredient")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @PostMapping("/create-ingredient")
    @CrossOrigin("http://localhost:3000")
    public void  createIngredient(@RequestBody CreateIngredientDto dto){
        ingredientService.CreateNewIngredient(dto);
    }

    @GetMapping("/get-all-ingredients")
    @CrossOrigin("http://localhost:3000")
    public List<IngredientToShowDto> getAllIngredients(){
        return ingredientService.getAllIngredients();
    }

    @DeleteMapping("/delete-ingredient")
    @CrossOrigin("http://localhost:3000")
    public void deleteIngredient(@RequestParam String ingredientId){
        ingredientService.deleteIngredient(ingredientId);
    }
}
