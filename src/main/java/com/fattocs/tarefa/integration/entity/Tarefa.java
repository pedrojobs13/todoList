package com.fattocs.tarefa.integration.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Tarefa {
    @Id
    private Long id;
    private String nome;
    private Double custo;
    private LocalDate dataLimite;
    private Integer orderApresentacao;
}
