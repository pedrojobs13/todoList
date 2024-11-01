package com.fattocs.tarefa.business.editar.usecase;

import java.time.LocalDate;

public interface EditarTarefaUseCase {
    void execute(Long id, String nome, Double custo, LocalDate dataLimite);
}
