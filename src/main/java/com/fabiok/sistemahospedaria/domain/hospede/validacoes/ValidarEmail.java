package com.fabiok.sistemahospedaria.domain.hospede.validacoes;

import com.fabiok.sistemahospedaria.application.command.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.domain.ErroHandler;

public class ValidarEmail implements IStrategyValidacaoHospede{

	@Override
	public void executar(CadastrarHospedeCommand command, ErroHandler erroHandler) {
		if(command.getEmail() == null || command.getEmail().isEmpty()) return;

		boolean isFormatoValido = command.getEmail().matches("^\\w+@\\w+\\.\\w+$");
		if(!isFormatoValido){
			erroHandler.addErros("E-mail está em um formato inválido");
		}
	}
	
}
