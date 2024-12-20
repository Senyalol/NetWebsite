package com.Network.Network.DTO.NetworkDTO;

import java.time.Instant;
import lombok.Data;
@Data
public class UpdateNetworkDTO {
    private Integer network_id;
    private String networkName;
    private String subnet;
    private String gateway;
    private Instant createdAt;
}
