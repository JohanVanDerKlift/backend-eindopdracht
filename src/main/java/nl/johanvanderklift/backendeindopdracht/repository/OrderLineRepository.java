package nl.johanvanderklift.backendeindopdracht.repository;

import nl.johanvanderklift.backendeindopdracht.model.OrderLine;
import org.springframework.data.repository.CrudRepository;

public interface OrderLineRepository extends CrudRepository<OrderLine, Long> {

}
