package com.bicho.bet.animais;

import com.bicho.bet.aposta.NumeroAposta;
import com.bicho.bet.core.EntityId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
public class Bicho extends EntityId {

    @Column(name = "nome", nullable = false)
    private String nome;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private BichoNumeros numeros;

    public Bicho(Long id, String nome, BichoNumeros numeros) {
        super(id);
        this.nome = nome;
        this.numeros = numeros;
    }

}
