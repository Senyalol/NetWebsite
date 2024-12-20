package com.Network.Network.Service;

import com.Network.Network.DTO.UserDTO.CreateUserDTO;
import com.Network.Network.DTO.UserDTO.ShortUserInfoDTO;
import com.Network.Network.DTO.UserDTO.UpdateUserDTO;
import com.Network.Network.Entites.User;
import com.Network.Network.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<ShortUserInfoDTO> getUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> {
                    ShortUserInfoDTO userDTO = new ShortUserInfoDTO();
                    userDTO.setUser_id(user.getId());
                    userDTO.setUsername(user.getUsername());
                    userDTO.setEmail(user.getEmail());
                    userDTO.setCreatedAt(user.getCreatedAt());
                    userDTO.setPassword(user.getPassword());

                    return userDTO;
                }).toList();
    }

    public ShortUserInfoDTO getUserById(int id) {
        User user = userRepository.findById(id);

        ShortUserInfoDTO userDTO = new ShortUserInfoDTO();
        userDTO.setUser_id(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setPassword(user.getPassword());

        return userDTO;
    }

    public ShortUserInfoDTO createUser(CreateUserDTO createUserDTO) {
        User user = new User();
        user.setUsername(createUserDTO.getUsername());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(createUserDTO.getPassword());
        user.setCreatedAt(createUserDTO.getCreatedAt());

        User savedUser = userRepository.save(user);

        // Возвращаем DTO созданного пользователя
        ShortUserInfoDTO userDTO = new ShortUserInfoDTO();
        userDTO.setUser_id(savedUser.getId());
        userDTO.setUsername(savedUser.getUsername());
        userDTO.setEmail(savedUser.getEmail());
        userDTO.setCreatedAt(savedUser.getCreatedAt());

        return userDTO;
    }

    public void updateUser(int id, UpdateUserDTO updateUserDTO) {
        User userToUpdate = userRepository.findById(id);

        if (updateUserDTO.getUsername() != null) {
            userToUpdate.setUsername(updateUserDTO.getUsername());
        }
        if (updateUserDTO.getEmail() != null) {
            userToUpdate.setEmail(updateUserDTO.getEmail());
        }
        if (updateUserDTO.getPassword() != null) {
            userToUpdate.setPassword(updateUserDTO.getPassword());
        }
        if (updateUserDTO.getCreatedAt() != null) {
            userToUpdate.setCreatedAt(updateUserDTO.getCreatedAt());
        }

        userRepository.save(userToUpdate);
    }

    public void deleteUser(int id) {
        User userToDelete = userRepository.findById(id);

        userRepository.delete(userToDelete);
    }
}