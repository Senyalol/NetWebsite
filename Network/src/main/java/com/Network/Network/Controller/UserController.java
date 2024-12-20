package com.Network.Network.Controller;

import com.Network.Network.Service.UserService;
import com.Network.Network.DTO.UserDTO.CreateUserDTO;
import com.Network.Network.DTO.UserDTO.ShortUserInfoDTO;
import com.Network.Network.DTO.UserDTO.UpdateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<ShortUserInfoDTO> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ShortUserInfoDTO getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<ShortUserInfoDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
        // Создаем пользователя через сервис
        ShortUserInfoDTO createdUser = userService.createUser(createUserDTO);

        // Возвращаем созданного пользователя с статусом CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") int id, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        try {
            userService.updateUser(id, updateUserDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}