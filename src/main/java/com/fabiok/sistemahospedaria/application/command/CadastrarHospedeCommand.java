package com.fabiok.sistemahospedaria.application.command;

import java.time.LocalDate;

public class CadastrarHospedeCommand{
	private String nomeCompleto; 
	private String cpf; 
	private LocalDate dataNascimento; 
	private String telefone; 
	private String email; 
	private EnderecoCommand endereco;
	
	public CadastrarHospedeCommand(String nomeCompleto, String cpf, LocalDate dataNascimento, String telefone,
			String email, EnderecoCommand endereco) {
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public EnderecoCommand getEndereco() {
		return endereco;
	}

	
}
