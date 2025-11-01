package com.fabiok.sistemahospedaria.application;

import com.fabiok.sistemahospedaria.domain.Hospede;

public class HospedeMapper
{
    public static Hospede from(CadastrarHospedeCommand cmd){
        return new Hospede(null, cmd.nomeCompleto(), cmd.cpf(), cmd.dataNascimento(), cmd.telefone(), cmd.email());
    }
}
