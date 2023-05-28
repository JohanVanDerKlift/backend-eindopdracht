package nl.johanvanderklift.backendeindopdracht.service;

import nl.johanvanderklift.backendeindopdracht.dto.MenuDto;
import nl.johanvanderklift.backendeindopdracht.model.Menu;
import nl.johanvanderklift.backendeindopdracht.repository.MenuRepository;
import org.springframework.stereotype.Service;

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

    private Menu transferDtoToMenu(MenuDto dto, Menu menu) {
        menu.setType(dto.type);
    }
}
