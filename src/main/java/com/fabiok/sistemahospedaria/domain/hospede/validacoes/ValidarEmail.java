package com.fabiok.sistemahospedaria.domain.hospede.validacoes;

import com.fabiok.sistemahospedaria.application.command.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.domain.Notificacao;
import com.fabiok.sistemahospedaria.domain.exceptions.FieldErrorCode;
import com.fabiok.sistemahospedaria.domain.hospede.Hospede;

public class ValidarEmail implements IStrategyValidacaoHospede{

	@Override
	public void executar(Hospede hospede, Notificacao erroHandler) {
		if(hospede.getEmail() == null || hospede.getEmail().isEmpty()) return;

		boolean isFormatoValido = hospede.getEmail().matches("^\\w+@\\w+\\.\\w+$");
		if(!isFormatoValido){
		erroHandler.addErros(new FieldErrorCode("email", "email.formatoInvalido"));

		}
	}
	
}
