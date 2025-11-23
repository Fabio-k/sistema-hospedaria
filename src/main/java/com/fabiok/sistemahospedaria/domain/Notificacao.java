package com.fabiok.sistemahospedaria.domain;

import com.fabiok.sistemahospedaria.domain.exceptions.FieldErrorCode;

import java.util.ArrayList;
import java.util.List;

public class Notificacao {
    private final List<FieldErrorCode> erros = new ArrayList<>();

    public void addErros(FieldErrorCode fieldErrorCode){
        erros.add(fieldErrorCode);
    }

    public List<FieldErrorCode> getErros(){
        return List.copyOf(erros);
    }

    public boolean hasErro(){
        return !erros.isEmpty();
    }

}
