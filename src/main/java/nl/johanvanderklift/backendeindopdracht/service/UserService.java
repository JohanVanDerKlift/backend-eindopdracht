package nl.johanvanderklift.backendeindopdracht.service;

import nl.johanvanderklift.backendeindopdracht.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
