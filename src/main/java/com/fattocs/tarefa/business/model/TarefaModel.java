package com.fattocs.tarefa.business.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TarefaModel {
    private Long id;
    private String nome;
    private Double custo;
    private LocalDate dataLimite;
    private Integer orderApresentacao;
    private Boolean custoAlto;


    public Boolean getCustoAlto() {
        return this.custo > 1000;
    }
}
