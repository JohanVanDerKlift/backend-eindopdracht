package nl.johanvanderklift.backendeindopdracht.controller;

import jakarta.validation.Valid;
import nl.johanvanderklift.backendeindopdracht.dto.DishDto;
import nl.johanvanderklift.backendeindopdracht.service.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    public ResponseEntity<Object> createDish(@Valid @RequestBody DishDto dto, BindingResult br) {
        if (br.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(getBindingResult(br));
        } else {
            Long newId = dishService.createDish(dto);
            return ResponseEntity.ok(newId);
        }
    }

    @GetMapping
    public ResponseEntity<List<DishDto>> getAllDishes() {
        return ResponseEntity.ok(dishService.getAllDishes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDto> getDish(@PathVariable Long id) {
        return ResponseEntity.ok(dishService.getDish(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDish(@PathVariable Long id, @RequestBody DishDto dto, BindingResult br) {
        if (br.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(getBindingResult(br));
        } else {
            dishService.updateDish(id, dto);
            return ResponseEntity.ok("Dish was updated");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
    }

    private String getBindingResult(BindingResult br) {
        StringBuilder sb = new StringBuilder();
        for (FieldError fe : br.getFieldErrors()) {
            sb.append(fe.getField()).append(": ").append(fe.getDefaultMessage()).append("\n");
        }
        return sb.toString();
    }
}
