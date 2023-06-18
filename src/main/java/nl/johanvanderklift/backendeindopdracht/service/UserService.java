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

    public Long createUser(UserInputDto dto) {
        User user = new User();
        userRepository.save(transferDtoToUser(dto, user));
        return user.getId();
    }

    public List<UserOutputDto> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        List<UserOutputDto> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(transferUserToDto(user));
        }
        return dtos;
    }

    public UserOutputDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id: " + id + " not found"));
        return transferUserToDto(user);
    }

    public void updateUser(Long id, UserInputDto dto) {
        User oldUser = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id: " + id + " not found"));
        if (oldUser != null) {
            userRepository.save(transferDtoToUser(dto, oldUser));
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Boolean toggleUserHasCredit(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User currentUser = user.get();
            currentUser.setHasCredit(!currentUser.getHasCredit());
            userRepository.save(currentUser);
            return currentUser.getHasCredit();
        } else {
            throw new RecordNotFoundException("Record with id: " + id + " not found");
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
