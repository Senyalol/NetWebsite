package com.Network.Network.DTO.LogsDTO;

import java.time.Instant;
import lombok.Data;
@Data
public class ShortLogsInfoDTO {
    private Integer logs_id;
    private Integer user_id;
    private String action;
    private Instant timestamp;
    private String description;
}
