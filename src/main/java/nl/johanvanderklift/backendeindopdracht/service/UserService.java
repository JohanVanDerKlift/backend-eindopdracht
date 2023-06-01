package nl.johanvanderklift.backendeindopdracht.service;

import nl.johanvanderklift.backendeindopdracht.dto.UserDto;
import nl.johanvanderklift.backendeindopdracht.exception.RecordNotFoundException;
import nl.johanvanderklift.backendeindopdracht.model.User;
import nl.johanvanderklift.backendeindopdracht.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createUser(UserDto dto) {
        User user = new User();
        userRepository.save(transferDtoToUser(dto, user));
        return user.getId();
    }

    public List<UserDto> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        List<UserDto> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(transferUserToDto(user));
        }
        return dtos;
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id: " + id + " not found"));
        return transferUserToDto(user);
    }

    public void updateUser(Long id, UserDto dto) {
        User oldUser = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id: " + id + " not found"));
        if (oldUser != null) {
            userRepository.save(transferDtoToUser(dto, oldUser));
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private User transferDtoToUser(UserDto dto, User user) {
        user.setFirstName(dto.firstName);
        user.setLastName(dto.lastName);
        user.setAdres(dto.adres);
        user.setZipCode(dto.zipCode);
        user.setCompanyName(dto.companyName);
        user.setPhoneNumber(dto.phoneNumber);
        user.setEmail(dto.email);
        user.setPassword(dto.password);
        return user;
    }

    private UserDto transferUserToDto(User user) {
        UserDto dto = new UserDto();
        dto.id = user.getId();
        dto.firstName = user.getFirstName();
        dto.lastName = user.getLastName();
        dto.adres = user.getAdres();
        dto.companyName = user.getCompanyName();
        dto.phoneNumber = user.getPhoneNumber();
        dto.zipCode = user.getZipCode();
        dto.email = user.getEmail();
        dto.password = user.getPassword();
        return dto;
    }
}
