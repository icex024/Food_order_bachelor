package group.Food_order_bachelor.service.allergenService;

import group.Food_order_bachelor.dto.allergen.AllergenDto;
import group.Food_order_bachelor.model.Allergen;
import group.Food_order_bachelor.repository.AllergenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AllergenService implements AllergenServiceInterface{
    private final AllergenRepository allergenRepository;
    @Override
    public void createNewAllergen(String name) {
        var allergen = Allergen.builder().name(name).build();
        allergenRepository.save(allergen);
    }

    @Override
    public List<AllergenDto> getAllergens() {
        List<AllergenDto> allergensDto = new ArrayList<>();
        for (var allergen:
                allergenRepository.findAll()) {
            allergensDto.add(new AllergenDto().builder().id(allergen.getId().toString()).name(allergen.getName()).build());
        }
        return allergensDto;
    }

    @Override
    public Set<Allergen> getAllergensByIds(List<String> ids) {
        List<UUID> idsUUID = new ArrayList<>();
        for (var id:
             ids) {
            idsUUID.add(UUID.fromString(id));
        }
        return new HashSet<>(allergenRepository.findAllById(idsUUID));
    }
}
