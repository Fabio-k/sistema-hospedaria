package com.fabiok.sistemahospedaria.utils;

import com.fabiok.sistemahospedaria.domain.Notificacao;

import java.util.List;

public class ValidarNullOuEmpty {
    public static void validar(Notificacao notification, List<FieldToBeValidated> fields){
        fields.forEach(f -> checkNullOrEmpty(f.fieldName(), f.value(), notification));
    }

    public static void checkNullOrEmpty (String atributeName, Object atribute, Notificacao erroHandler){
        if(atribute == null || (atribute instanceof String && ((String) atribute).isBlank())) erroHandler.addErros(erroMessage(atributeName));
    }

    public static String erroMessage(String campo){
        return campo + " é obrigatório";
    }
}
