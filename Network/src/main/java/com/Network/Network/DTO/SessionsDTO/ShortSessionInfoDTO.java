package com.Network.Network.DTO.SessionsDTO;

import java.time.Instant;
import lombok.Data;
@Data
public class ShortSessionInfoDTO {
    private Integer session_id;
    private Integer user_id;
    private Integer device_id;
    private Instant startTime;
    private Instant endTime;
}
