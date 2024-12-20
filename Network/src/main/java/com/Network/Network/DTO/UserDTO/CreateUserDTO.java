package com.Network.Network.DTO.UserDTO;
import lombok.Data;

import java.time.Instant;

@Data
public class CreateUserDTO {

    private Integer user_id;
    private String username;
    private String email;
    private String password;
    private Instant createdAt;

}
