package com.fattocs.tarefa.business.incluir.usecase;

import java.time.LocalDate;

public interface IncluirTarefaUseCase {
    void execute(String nome, Double custo, LocalDate data);
}
