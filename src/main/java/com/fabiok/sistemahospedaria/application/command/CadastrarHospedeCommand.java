package com.fabiok.sistemahospedaria.application.command;

import java.time.LocalDate;

public class CadastrarHospedeCommand extends HospedeCommand{
	public CadastrarHospedeCommand() {
	}

	public CadastrarHospedeCommand(String nomeCompleto, String cpf, LocalDate dataNascimento, String telefone, String email, EnderecoCommand endereco) {
		super(nomeCompleto, cpf, dataNascimento, telefone, email, endereco);
	}
}
