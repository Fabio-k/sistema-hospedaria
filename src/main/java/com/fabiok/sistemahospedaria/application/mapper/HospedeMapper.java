package com.fabiok.sistemahospedaria.application.mapper;

import com.fabiok.sistemahospedaria.application.command.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.application.command.EditarHospedeCommand;
import com.fabiok.sistemahospedaria.domain.hospede.Hospede;

public class HospedeMapper
{
    public static Hospede from(CadastrarHospedeCommand cmd){
        return new Hospede(null, cmd.getNomeCompleto(), cmd.getCpf(), cmd.getDataNascimento(), cmd.getTelefone(), cmd.getEmail(), EnderecoMapper.to(cmd.getEndereco()));
    }

	public static Hospede update(Hospede hospede, EditarHospedeCommand cmd) {
    return new Hospede(
        hospede.getId(),
        updatedValue(hospede.getNomeCompleto(), cmd.getNomeCompleto()),
        updatedValue(hospede.getCpf(), cmd.getCpf()),
        updatedValue(hospede.getDataNascimento(), cmd.getDataNascimento()),
        updatedValue(hospede.getTelefone(), cmd.getTelefone()),
        updatedValue(hospede.getEmail(), cmd.getEmail()),
        EnderecoMapper.update(hospede.getEndereco(), cmd.getEndereco())
    );
}

	public static <T> T updatedValue(T old, T newValue){
		return newValue == null ? old : newValue;
	}
}
