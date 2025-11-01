package com.fabiok.sistemahospedaria.domain;

import com.fabiok.sistemahospedaria.utils.ValidadorCpf;

public class Cpf {
    private final String valor;

    public  Cpf(String valor){
        if(!ValidadorCpf.validar(valor)) throw new IllegalArgumentException("CPF inváldo");
        this.valor = valor.replaceAll("\\D", "");
    }

    public String getValor() { return valor; }
}
