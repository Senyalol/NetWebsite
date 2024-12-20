package com.Network.Network.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "devices_id_gen")
    @SequenceGenerator(name = "devices_id_gen", sequenceName = "devices_device_id_seq", allocationSize = 1)
    @Column(name = "device_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "user_id")
    private com.Network.Network.Entites.User user;

    @Size(max = 100)
    @NotNull
    @Column(name = "device_name", nullable = false, length = 100)
    private String deviceName;

    @Size(max = 50)
    @NotNull
    @Column(name = "device_type", nullable = false, length = 50)
    private String deviceType;

    @Size(max = 15)
    @NotNull
    @Column(name = "ip_address", nullable = false, length = 15)
    private String ipAddress;

    @Size(max = 17)
    @NotNull
    @Column(name = "mac_address", nullable = false, length = 17)
    private String macAddress;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

}