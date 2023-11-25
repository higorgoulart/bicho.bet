package com.bicho.bet.security.token;

import com.bicho.bet.apostador.Apostador;
import lombok.Getter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Entity(name = "refreshtoken")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Apostador apostador;
    @Column(nullable = false, unique = true)
    private String token;
    @Column(nullable = false)
    private Instant expiryDate;

    public void setId(Long id) {
        this.id = id;
    }

    public void setApostador(Apostador apostador) {
        this.apostador = apostador;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }
}






