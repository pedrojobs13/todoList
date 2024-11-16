package com.fattocs.tarefa.business.incluir.usecase;

import com.fattocs.tarefa.exception.BusinessException;

import java.time.LocalDate;

public interface IncluirTarefaUseCase {
    void execute(String nome, Double custo, LocalDate data) throws BusinessException;
}
