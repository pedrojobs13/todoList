package com.fattocs.tarefa.presentation.validation;

import com.fattocs.tarefa.presentation.dto.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApiValidation {
    public static ResponseEntity<String> validateApi(Request request) {
        if (request.getNome() == null || request.getNome().isEmpty()) {
            return ResponseEntity.badRequest().body("Nome da tarefa não pode ser vazio");
        }
        if (request.getNome().length() > 50) {
            return ResponseEntity.badRequest().body("Nome da tarefa não pode ter mais de 50 caracteres");
        }
        if (request.getCusto() == null || request.getCusto().isNaN()) {
            return ResponseEntity.badRequest().body("Custo da tarefa não pode ser vazio");
        }
        if (request.getDataLimite() == null) {
            return ResponseEntity.badRequest().body("Data limite da tarefa não pode ser vazio");
        }
        return null;
    }

}
