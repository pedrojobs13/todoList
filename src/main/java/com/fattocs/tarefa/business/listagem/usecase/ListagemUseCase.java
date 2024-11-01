package com.fattocs.tarefa.business.listagem.usecase;

import com.fattocs.tarefa.business.model.TarefaModel;

import java.util.Collection;

public interface ListagemUseCase {
    Collection<TarefaModel> execute();
}
