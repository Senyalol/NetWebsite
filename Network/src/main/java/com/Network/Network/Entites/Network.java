package com.Network.Network.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "networks")
public class Network {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "networks_id_gen")
    @SequenceGenerator(name = "networks_id_gen", sequenceName = "networks_network_id_seq", allocationSize = 1)
    @Column(name = "network_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "network_name", nullable = false, length = 100)
    private String networkName;

    @Size(max = 18)
    @NotNull
    @Column(name = "subnet", nullable = false, length = 18)
    private String subnet;

    @Size(max = 15)
    @NotNull
    @Column(name = "gateway", nullable = false, length = 15)
    private String gateway;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

}