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
}
