package nl.johanvanderklift.backendeindopdracht.controller;

import jakarta.validation.Valid;
import nl.johanvanderklift.backendeindopdracht.dto.DishDto;
import nl.johanvanderklift.backendeindopdracht.service.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping
    public ResponseEntity<Long> createDish(@Valid @RequestBody DishDto dto) {
        Long newId = dishService.createDish(dto);
        return ResponseEntity.ok(newId);
    }

    @GetMapping
    public ResponseEntity<List<DishDto>> getAllDishes() {
        List<DishDto> dishDtoList = dishService.getAllDishes();
        return ResponseEntity.ok(dishDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDto> getDish(@PathVariable Long id) {
        DishDto dto = dishService.getDish(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public void updateDish(@PathVariable Long id, @RequestBody DishDto dto) {
        dishService.updateDish(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
    }
}
