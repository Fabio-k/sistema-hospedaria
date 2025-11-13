package com.fabiok.sistemahospedaria.domain;

import com.fabiok.sistemahospedaria.utils.ValidadorCpf;

public class Cpf {
    private final String valor;

    public  Cpf(String valor){
        this.valor = valor.replaceAll("\\D", "");
    }

    public void validar(Notificacao notification){
        if(!ValidadorCpf.validar(valor)) notification.addErros("CPF inválido");
    }

    public String getValor() { return valor; }
}
