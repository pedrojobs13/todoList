package com.fattocs.tarefa.business.busca.usecase;

import com.fattocs.tarefa.business.model.TarefaModel;
import com.fattocs.tarefa.exception.BusinessException;

public interface GetTarefa {
    TarefaModel execute(Long id) throws BusinessException;
}
