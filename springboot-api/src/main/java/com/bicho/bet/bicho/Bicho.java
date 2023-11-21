package com.bicho.bet.bicho;

import com.bicho.bet.core.BetNumber;
import com.bicho.bet.core.EntityId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Bicho extends EntityId {
    @Column(name = "nome", nullable = false)
    private String nome;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<BetNumber> numeros;
}
