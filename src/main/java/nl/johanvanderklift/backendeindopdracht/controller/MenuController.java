package nl.johanvanderklift.backendeindopdracht.controller;

import nl.johanvanderklift.backendeindopdracht.dto.MenuDto;
import nl.johanvanderklift.backendeindopdracht.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<Long> createMenu(@RequestBody MenuDto dto) {
        Long newId = menuService.createMenu(dto);
        return ResponseEntity.ok(newId);
    }

    @GetMapping
    public ResponseEntity<List<MenuDto>> getAllMenus() {
        List<MenuDto> dtos = menuService.getAllMenus();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> getMenu(@PathVariable Long id) {
        MenuDto dto = menuService.getMenu(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuDto> updateMenu(@PathVariable Long id, @RequestBody MenuDto dto) {
        menuService.updateMenu(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
    }
}
