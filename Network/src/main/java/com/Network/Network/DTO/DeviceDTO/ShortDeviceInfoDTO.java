package com.Network.Network.DTO.DeviceDTO;

import java.time.Instant;
import lombok.Data;
@Data
public class ShortDeviceInfoDTO {
    private Integer device_id;
    private Integer user_id;
    private String deviceName;
    private String deviceType;
    private String ipAddress;
    private String macAddress;
    private Instant createdAt;
}
