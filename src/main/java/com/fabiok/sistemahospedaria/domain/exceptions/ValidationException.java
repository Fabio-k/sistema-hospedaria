package com.fabiok.sistemahospedaria.domain.exceptions;

import java.util.List;

public class ValidationException extends RuntimeException{
    private final List<FieldErrorCode> erros;

    public ValidationException(List<FieldErrorCode> erros){
        super("Erro de validação");
         this.erros = erros;
    }

    public List<FieldErrorCode> getErros(){
        return erros;
    }
}
