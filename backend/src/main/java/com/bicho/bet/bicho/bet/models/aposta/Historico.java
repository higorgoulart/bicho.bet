package com.bicho.bet.bicho.bet.models.aposta;

import com.bicho.bet.bicho.bet.models.core.EntityId;
import com.bicho.bet.bicho.bet.models.resultado.NumeroResultado;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Historico extends EntityId {
    private Long idAposta;
    private Double idResultado;
    private LocalDateTime data;
    private List<NumeroResultado> numeros = new ArrayList<>();

    public Long getIdAposta() { return idAposta;}

    public Double getIdResultado() { return idResultado;}

    public LocalDateTime getData() { return data;}

    public List<NumeroResultado> getNumeros() { return numeros;}
}
