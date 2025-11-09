package com.fabiok.sistemahospedaria.application.command;

import java.time.LocalDate;

public class EditarHospedeCommand extends CadastrarHospedeCommand{
	public EditarHospedeCommand(String nomeCompleto, String cpf, LocalDate dataNascimento, String telefone,
			String email, EnderecoCommand endereco) {
		super(nomeCompleto, cpf, dataNascimento, telefone, email, endereco);
	}
}
