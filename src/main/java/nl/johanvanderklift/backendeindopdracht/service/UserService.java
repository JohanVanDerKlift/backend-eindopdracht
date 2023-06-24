package nl.johanvanderklift.backendeindopdracht.service;

import nl.johanvanderklift.backendeindopdracht.dto.UserInputDto;
import nl.johanvanderklift.backendeindopdracht.dto.UserOutputDto;
import nl.johanvanderklift.backendeindopdracht.exception.RecordNotFoundException;
import nl.johanvanderklift.backendeindopdracht.model.User;
import nl.johanvanderklift.backendeindopdracht.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(UserInputDto dto) {
        User user = new User();
        userRepository.save(transferDtoToUser(dto, user));
        return user.getEmail();
    }

    public List<UserOutputDto> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        List<UserOutputDto> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(transferUserToDto(user));
        }
        return dtos;
    }

    public UserOutputDto getUserByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            User currentUser = user.get();
            return transferUserToDto(currentUser);
        } else {
            throw new RecordNotFoundException("User with email " + email + "  was not found");
        }
    }

    public void updateUser(String email, UserInputDto dto) {
        Optional<User> oldUser = userRepository.findUserByEmail(email);
        if (oldUser.isPresent()) {
            User newUser = oldUser.get();
            userRepository.save(transferDtoToUser(dto, newUser));
        } else {
            throw new RecordNotFoundException("User with email " + email + "  was not found");
        }
    }

    public void deleteUser(String email) {
        userRepository.deleteUserByEmail(email);
    }

    public Boolean toggleUserHasCredit(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            User currentUser = user.get();
            currentUser.setHasCredit(!currentUser.isHasCredit());
            userRepository.save(currentUser);
            return currentUser.isHasCredit();
        } else {
            throw new RecordNotFoundException("User with email " + email + " was not found");
        }
    }

    private User transferDtoToUser(UserInputDto dto, User user) {
        user.setFirstName(dto.firstName);
        user.setLastName(dto.lastName);
        user.setAdres(dto.adres);
        user.setZipCode(dto.zipCode);
        user.setCompanyName(dto.companyName);
        user.setPhoneNumber(dto.phoneNumber);
        user.setEmail(dto.email);
        user.setPassword(dto.password);
        user.setHasCredit(false);
        return user;
    }

    private UserOutputDto transferUserToDto(User user) {
        UserOutputDto dto = new UserOutputDto();
        dto.firstName = user.getFirstName();
        dto.lastName = user.getLastName();
        dto.adres = user.getAdres();
        dto.companyName = user.getCompanyName();
        dto.phoneNumber = user.getPhoneNumber();
        dto.zipCode = user.getZipCode();
        dto.email = user.getEmail();
        dto.password = user.getPassword();
        dto.hasCredit = user.isHasCredit();
        return dto;
    }
}
