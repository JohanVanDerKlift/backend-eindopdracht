package nl.johanvanderklift.backendeindopdracht.repository;

import nl.johanvanderklift.backendeindopdracht.model.Dish;
import org.springframework.data.repository.CrudRepository;

public interface DishRepository extends CrudRepository<Dish, Long> {
}
