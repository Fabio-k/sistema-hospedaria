package com.fabiok.sistemahospedaria.application.mapper;

import com.fabiok.sistemahospedaria.application.command.EnderecoCommand;
import com.fabiok.sistemahospedaria.domain.Endereco;

public class EnderecoMapper {
    public static Endereco to(EnderecoCommand cmd){
        return new Endereco(cmd.id(), cmd.cep(), cmd.logradouro(), cmd.cidade(), cmd.bairro(), cmd.numero(), cmd.complemento(), cmd.estado());
    }

	public static Endereco update(Endereco endereco, EnderecoCommand cmd) {
        return new Endereco(
            endereco.getId(),
            updatedValue(endereco.getCep(), cmd.cep()),
            updatedValue(endereco.getLogradouro(), cmd.logradouro()),
            updatedValue(endereco.getCidade(), cmd.cidade()),
            updatedValue(endereco.getBairro(), cmd.bairro()),
            updatedValue(endereco.getNumero(), cmd.numero()),
            updatedValue(endereco.getComplemento(), cmd.complemento()),
            updatedValue(endereco.getEstado(), cmd.estado())
        );
    }

    private static <T> T updatedValue(T oldValue, T newValue) {
        return newValue != null ? newValue : oldValue;
    }
}
