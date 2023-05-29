package nl.johanvanderklift.backendeindopdracht.repository;

import nl.johanvanderklift.backendeindopdracht.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
