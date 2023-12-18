package group.Food_order_bachelor.controller;

import group.Food_order_bachelor.dto.allergen.AllergenDto;
import group.Food_order_bachelor.model.Allergen;
import group.Food_order_bachelor.service.allergenService.AllergenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/allergen")
@RequiredArgsConstructor
public class AllergenController {
    private final AllergenService allergenService;

    @PostMapping("/create-allergen")
    @CrossOrigin("http://localhost:3000")
    public void createAllergen(@RequestParam String name){
        allergenService.createNewAllergen(name);
    }

    @GetMapping("/get-allergens")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<List<AllergenDto>> getAllergens(){
        return ResponseEntity.ok(allergenService.getAllergens());
    }
}
