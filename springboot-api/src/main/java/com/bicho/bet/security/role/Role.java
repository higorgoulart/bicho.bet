package com.bicho.bet.security.role;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(length=20)
    private ERole name;

    public Role() {
    }



    public Role(ERole name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public ERole getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
