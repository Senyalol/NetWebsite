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
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logs_id_gen")
    @SequenceGenerator(name = "logs_id_gen", sequenceName = "logs_log_id_seq", allocationSize = 1)
    @Column(name = "log_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "user_id")
    private com.Network.Network.Entites.User user;

    @Size(max = 255)
    @NotNull
    @Column(name = "action", nullable = false)
    private String action;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "\"timestamp\"")
    private Instant timestamp;

    @Column(name = "description")
    private String description;

}