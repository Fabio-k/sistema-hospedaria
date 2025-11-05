package com.fabiok.sistemahospedaria.domain.hospede.validacoes;

import com.fabiok.sistemahospedaria.application.command.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.domain.ErroHandler;

public class ValidarEmail implements IStrategyValidacaoHospede{

	@Override
	public void executar(CadastrarHospedeCommand command, ErroHandler erroHandler) {
		if(command.email() == null || command.email().isEmpty()) return;

		boolean isFormatoValido = command.email().matches("^\\w+@\\w+\\.\\w+$");
		if(!isFormatoValido){
			erroHandler.addErros("E-mail está em um formato inválido");
		}
	}
	
}
