package com.fabiok.sistemahospedaria.application.mapper;

import com.fabiok.sistemahospedaria.application.command.EnderecoCommand;
import com.fabiok.sistemahospedaria.domain.Endereco;

public class EnderecoMapper {
    public static Endereco to(EnderecoCommand cmd){
        return new Endereco(cmd.id(), cmd.cep(), cmd.logradouro(), cmd.cidade(), cmd.bairro(), cmd.numero(), cmd.complemento(), cmd.estado());
    }
}
