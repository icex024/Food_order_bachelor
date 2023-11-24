package group.Food_order_bachelor.controller;

import group.Food_order_bachelor.dto.ingredient.CreateIngredientDto;
import group.Food_order_bachelor.service.ingredientService.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ingredient")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @PostMapping("/create-ingredient")
    @CrossOrigin("http://localhost:3000")
    public HttpStatus  createIngredient(@RequestBody CreateIngredientDto dto){
        ingredientService.CreateNewIngredient(dto);
        try{
            return HttpStatus.ACCEPTED;
        }catch (Exception e){
            return HttpStatus.BAD_REQUEST;//ovo namestati sutra
        }
    }
}
