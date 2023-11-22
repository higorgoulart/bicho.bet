package com.bicho.bet.security;


import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Getter
@Entity
@Table( name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Size(max = 20)
    private String username;


    @NotBlank
    @Size(max = 50)
    @Email
    private String email;


    @NotBlank
    @Size(max = 120)
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User() {
    }


    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
