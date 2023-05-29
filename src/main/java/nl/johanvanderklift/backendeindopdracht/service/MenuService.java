package nl.johanvanderklift.backendeindopdracht.service;

import nl.johanvanderklift.backendeindopdracht.dto.MenuDto;
import nl.johanvanderklift.backendeindopdracht.model.Menu;
import nl.johanvanderklift.backendeindopdracht.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Long createMenu(MenuDto dto) {
        Menu menu = new Menu();
        menuRepository.save(transferDtoToMenu(dto, menu));
        return menu.getId();
    }

    public List<MenuDto> getAllMenus() {
        Iterable<Menu> menus = menuRepository.findAll();
        List<MenuDto> dtos = new ArrayList<>();
        for (Menu menu : menus) {
            dtos.add(transferMenuToDto(menu));
        }
        return dtos;
    }

    public MenuDto getMenu(Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow();
        return transferMenuToDto(menu);
    }

    public void updateMenu(Long id, MenuDto dto) {
        Menu oldMenu = menuRepository.findById(id).orElseThrow();
        menuRepository.save(transferDtoToMenu(dto, oldMenu));
    }

    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    private Menu transferDtoToMenu(MenuDto dto, Menu menu) {
        menu.setType(dto.type);
        return menu;
    }

    private MenuDto transferMenuToDto(Menu menu) {
        MenuDto dto = new MenuDto();
        dto.id = menu.getId();
        dto.type = menu.getType();
        return dto;
    }
}
