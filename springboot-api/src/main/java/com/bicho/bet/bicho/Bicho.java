package com.bicho.bet.bicho;

import com.bicho.bet.core.BetNumber;
import com.bicho.bet.core.EntityId;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import java.util.ArrayList;
import java.util.List;

@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SequenceGenerator(name = EntityId.SEQUENCE_GENERATOR, sequenceName = "tb_bicho_sequence")
public class Bicho extends EntityId {
    @Column(name = "nome", nullable = false)
    private String nome;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<BetNumber> numeros = new ArrayList<>();
}
