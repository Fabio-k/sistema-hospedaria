package com.fabiok.sistemahospedaria.utils;

import com.fabiok.sistemahospedaria.domain.Notification;

import java.util.List;

public class ValidarNullOuEmpty {
    public static void validar(Notification notification, List<FieldToBeValidated> fields){
        fields.forEach(f -> checkNullOrEmpty(f.fieldName(), f.value(), notification));
    }

    public static void checkNullOrEmpty (String atributeName, Object atribute, Notification erroHandler){
        if(atribute == null || (atribute instanceof String && ((String) atribute).isBlank())) erroHandler.addErros(erroMessage(atributeName));
    }

    public static String erroMessage(String campo){
        return campo + " é obrigatório";
    }
}
