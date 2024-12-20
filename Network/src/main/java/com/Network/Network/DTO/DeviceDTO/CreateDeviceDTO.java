package com.Network.Network.DTO.DeviceDTO;

import java.time.Instant;
import lombok.Data;

@Data
public class CreateDeviceDTO {
    private Integer user_id;
    private String deviceName;
    private String deviceType;
    private String ipAddress;
    private String macAddress;
    private Instant createdAt;
}
