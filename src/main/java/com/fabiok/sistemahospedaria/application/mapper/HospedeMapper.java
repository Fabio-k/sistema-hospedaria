package com.fabiok.sistemahospedaria.application.mapper;

import com.fabiok.sistemahospedaria.application.command.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.domain.hospede.Hospede;

public class HospedeMapper
{
    public static Hospede from(CadastrarHospedeCommand cmd){
        return new Hospede(null, cmd.nomeCompleto(), cmd.cpf(), cmd.dataNascimento(), cmd.telefone(), cmd.email(), EnderecoMapper.to(cmd.endereco()));
    }
}
