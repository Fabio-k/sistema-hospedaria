package com.fabiok.sistemahospedaria;

import java.time.LocalDate;

import com.fabiok.sistemahospedaria.utils.ValidadorCpf;

public class Hospede {
	private String id;
	private String nomeCompleto;
	private String cpf;
	private LocalDate dataNascimento;
	private String telefone;
	private String email;

	public Hospede(String id, String nomeCompleto, String cpf, LocalDate dataNascimento, String telefone, String email) {
		if(!ValidadorCpf.validar(cpf)){
			throw new IllegalArgumentException("Cpf inválido");
		}
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
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
		if(!ValidadorCpf.validar(cpf)){
			throw new IllegalArgumentException("Cpf inválido");
		}
		this.cpf = cpf;
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
}
