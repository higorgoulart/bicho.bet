package com.bicho.bet.bicho.bet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bicho")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bicho {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bicho")
    @SequenceGenerator(name = "seq_bicho", sequenceName = "seq_bicho")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "nome")
    private String nome;
}
