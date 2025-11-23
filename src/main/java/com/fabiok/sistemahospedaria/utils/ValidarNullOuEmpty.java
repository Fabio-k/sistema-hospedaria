package com.fabiok.sistemahospedaria.utils;

import com.fabiok.sistemahospedaria.domain.Notificacao;
import com.fabiok.sistemahospedaria.domain.exceptions.FieldErrorCode;

import java.util.List;

public class ValidarNullOuEmpty {
    public static void validar(Notificacao notification, List<FieldToBeValidated> fields){
        fields.forEach(f -> checkNullOrEmpty(f.field(), f.value(), notification));
    }

    public static void checkNullOrEmpty (String atributeName, Object atribute, Notificacao erroHandler){
        if(atribute == null || (atribute instanceof String && ((String) atribute).isBlank())) erroHandler.addErros(new FieldErrorCode(atributeName, erroMessage(atributeName)));
    }

    public static String erroMessage(String field){
        return field + ".obrigatorio";
    }
}
