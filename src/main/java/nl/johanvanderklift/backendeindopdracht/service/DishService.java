package nl.johanvanderklift.backendeindopdracht.service;

import nl.johanvanderklift.backendeindopdracht.dto.DishDto;
import nl.johanvanderklift.backendeindopdracht.exception.RecordNotFoundException;
import nl.johanvanderklift.backendeindopdracht.model.Dish;
import nl.johanvanderklift.backendeindopdracht.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Long createDish(DishDto dto) {
        Dish dish = new Dish();
        dishRepository.save(transferDtoToDish(dto, dish));
        return dish.getId();
    }

    public List<DishDto> getAllDishes() {
        List<DishDto> dtos = new ArrayList<>();
        Iterable<Dish> dishes = dishRepository.findAll();
        for (Dish dish : dishes) {
            dtos.add(transferDishToDto(dish));
        }
        return dtos;
    }

    public DishDto getDish(Long id) {
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id: " + id + " not found"));
        return transferDishToDto(dish);
    }

    public void updateDish(Long id, DishDto dto) {
        Dish oldDish = dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id: " + id + " not found"));
        dishRepository.save(transferDtoToDish(dto, oldDish));
    }

    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }

    public Boolean setAvailabilityDish(Long id) {
        Optional<Dish> dish = dishRepository.findById(id);
        if (dish.isPresent()) {
            Dish currentDish = dish.get();
            currentDish.setAvailable(!currentDish.getAvailable());
            dishRepository.save(currentDish);
            return currentDish.getAvailable();
        } else {
            throw new RecordNotFoundException("Record with id: " + id + " not found");
        }
    }

    public Double setDishPrice(Long id, double price) {
        Optional<Dish> dish = dishRepository.findById(id);
        if (dish.isPresent()) {
            Dish currentDish = dish.get();
            currentDish.setPrice(price);
            dishRepository.save(currentDish);
            return currentDish.getPrice();
        } else {
            throw new RecordNotFoundException("Record with id: " + id + " not found");
        }
    }

    private DishDto transferDishToDto(Dish dish) {
        DishDto dto = new DishDto();
        dto.id = dish.getId();
        dto.name = dish.getName();
        dto.category = dish.getCategory();
        dto.price = dish.getPrice();
        dto.tax = dish.getTax();
        dto.available = dish.getAvailable();
        return dto;
    }

    private Dish transferDtoToDish(DishDto dto, Dish dish) {
        dish.setName(dto.name);
        dish.setCategory(dto.category);
        dish.setPrice(dto.price);
        dish.setTax(dto.tax);
        dish.setAvailable(dto.available);
        return dish;
    }
}
