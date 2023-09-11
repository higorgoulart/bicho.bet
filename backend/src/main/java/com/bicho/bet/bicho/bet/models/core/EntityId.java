package com.bicho.bet.bicho.bet.models.core;

import javax.persistence.*;

@MappedSuperclass
public class EntityId {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }
}
