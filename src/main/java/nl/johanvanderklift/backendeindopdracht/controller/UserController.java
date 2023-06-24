package nl.johanvanderklift.backendeindopdracht.controller;

import jakarta.validation.Valid;
import nl.johanvanderklift.backendeindopdracht.dto.UserInputDto;
import nl.johanvanderklift.backendeindopdracht.dto.UserOutputDto;
import nl.johanvanderklift.backendeindopdracht.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.plaf.SpinnerUI;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserInputDto dto, BindingResult br) {
        if (br.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(getBindingResult(br));
        } else {
            String email = userService.createUser(dto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + email).toUriString());
            return ResponseEntity.created(uri).body(email);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserOutputDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{email}")
    public ResponseEntity<Object> updateUser(@PathVariable String email, @Valid @RequestBody UserInputDto dto, BindingResult br) {
        if (br.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(getBindingResult(br));
        } else {
            userService.updateUser(email, dto);
            return ResponseEntity.ok("User was updated");
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/has_credit/{email}")
    public ResponseEntity<Boolean> setHasCredit(@PathVariable String email) {
        Boolean result = userService.toggleUserHasCredit(email);
        return ResponseEntity.ok().body(result);
    }

    private String getBindingResult(BindingResult br) {
        StringBuilder sb = new StringBuilder();
        for (FieldError fe : br.getFieldErrors()) {
            sb.append(fe.getField()).append(": ").append(fe.getDefaultMessage()).append("\n");
        }
        return sb.toString();
    }
}
