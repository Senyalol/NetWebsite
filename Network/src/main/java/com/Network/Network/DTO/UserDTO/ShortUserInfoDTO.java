package com.Network.Network.DTO.UserDTO;

import java.time.Instant;
import lombok.Data;

@Data
public class ShortUserInfoDTO {
    private Integer user_id;
    private String username;
    private String email;
    private String password;
    private Instant createdAt;
}
