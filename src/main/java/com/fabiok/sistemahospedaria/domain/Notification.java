package com.fabiok.sistemahospedaria.domain;

import java.util.ArrayList;
import java.util.List;

public class Notification {
    private final List<String> erros = new ArrayList<>();

    public void addErros(String mensagemErro){
        erros.add(mensagemErro);
    }

    public List<String> getErros(){
        return List.copyOf(erros);
    }

    public boolean hasErro(){
        return !erros.isEmpty();
    }

}
