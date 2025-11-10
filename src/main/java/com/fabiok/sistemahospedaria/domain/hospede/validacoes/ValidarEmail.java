package com.fabiok.sistemahospedaria.domain.hospede.validacoes;

import com.fabiok.sistemahospedaria.application.command.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.domain.Notification;
import com.fabiok.sistemahospedaria.domain.hospede.Hospede;

public class ValidarEmail implements IStrategyValidacaoHospede{

	@Override
	public void executar(Hospede hospede, Notification erroHandler) {
		if(hospede.getEmail() == null || hospede.getEmail().isEmpty()) return;

		boolean isFormatoValido = hospede.getEmail().matches("^\\w+@\\w+\\.\\w+$");
		if(!isFormatoValido){
			erroHandler.addErros("E-mail está em um formato inválido");
		}
	}
	
}
