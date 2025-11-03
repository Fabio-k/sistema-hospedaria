package com.fabiok.sistemahospedaria.domain.exceptions;

import java.util.List;

public class ValidationException extends RuntimeException{
    private final List<String> erros;

    public ValidationException(List<String> erros){
        super("Erro de validação");
         this.erros = erros;
    }

    public List<String> getErros(){
        return erros;
    }
}
