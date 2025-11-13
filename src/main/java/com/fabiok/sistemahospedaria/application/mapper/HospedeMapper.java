package com.fabiok.sistemahospedaria.application.mapper;

import com.fabiok.sistemahospedaria.application.builder.HospedeBuilder;
import com.fabiok.sistemahospedaria.application.command.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.application.command.EditarHospedeCommand;
import com.fabiok.sistemahospedaria.domain.hospede.Hospede;

public class HospedeMapper
{
	
    public static Hospede from(CadastrarHospedeCommand cmd){
		HospedeBuilder hospedeBuilder = new HospedeBuilder();
		hospedeBuilder.setNomeCompleto(cmd.getNomeCompleto());
		hospedeBuilder.setCpf(cmd.getCpf());
    	hospedeBuilder.setDataNascimento(cmd.getDataNascimento());
		hospedeBuilder.setTelefone(cmd.getTelefone());
		hospedeBuilder.setEmail(cmd.getEmail());
		hospedeBuilder.setEndereco(EnderecoMapper.to(cmd.getEndereco()));

		return hospedeBuilder.criar();
    }

	public static Hospede update(Hospede hospede, EditarHospedeCommand cmd) {
		HospedeBuilder hospedeBuilder = new HospedeBuilder();
		hospedeBuilder.setId(hospede.getId());
		hospedeBuilder.setNomeCompleto(updatedValue(hospede.getNomeCompleto(), cmd.getNomeCompleto()));
		hospedeBuilder.setCpf(updatedValue(hospede.getCpf(), cmd.getCpf()));
    	hospedeBuilder.setDataNascimento(updatedValue(hospede.getDataNascimento(), cmd.getDataNascimento()));
		hospedeBuilder.setTelefone(updatedValue(hospede.getTelefone(), cmd.getTelefone()));
		hospedeBuilder.setEmail(updatedValue(hospede.getEmail(), cmd.getEmail()));
		hospedeBuilder.setEndereco(EnderecoMapper.update(hospede.getEndereco(), cmd.getEndereco()));

		return hospedeBuilder.criar();
}

	public static <T> T updatedValue(T old, T newValue){
		return newValue == null ? old : newValue;
	}
}
