package nl.johanvanderklift.backendeindopdracht.repository;

import nl.johanvanderklift.backendeindopdracht.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
