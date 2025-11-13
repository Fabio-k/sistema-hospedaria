package com.fabiok.sistemahospedaria.application.builder;

import java.time.LocalDate;

import com.fabiok.sistemahospedaria.domain.Endereco;
import com.fabiok.sistemahospedaria.domain.hospede.Hospede;
import com.fabiok.sistemahospedaria.domain.hospede.HospedeStatus;

public class HospedeBuilder {
	private Integer id;
	private String nomeCompleto;
	private String cpf;
	private LocalDate dataNascimento;
	private String telefone;
	private String email;
	private Endereco endereco;
	private HospedeStatus status;

	public Hospede criar(){
		return new Hospede(id, nomeCompleto, cpf, dataNascimento, telefone, email, endereco, status);
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public void setStatus(HospedeStatus status) {
		this.status = status;
	} 

	
	
}
