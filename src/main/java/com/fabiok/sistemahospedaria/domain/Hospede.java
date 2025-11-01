package com.fabiok.sistemahospedaria.domain;

import java.time.LocalDate;

public class Hospede {
	private String id;
	private String nomeCompleto;
	private Cpf cpf;
	private LocalDate dataNascimento;
	private String telefone;
	private String email;

	public Hospede(String id, String nomeCompleto, String cpf, LocalDate dataNascimento, String telefone, String email) {
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.cpf = new Cpf(cpf);
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void definirCpf(String cpf){
		this.cpf = new Cpf(cpf);
	}

	public String getCpf() {
		return cpf.getValor();
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
}
