package com.fabiok.sistemahospedaria.application.command;

import com.fabiok.sistemahospedaria.domain.hospede.HospedeStatus;

import java.time.LocalDate;

public class AtualizarHospedeCommand extends HospedeCommand{
	private HospedeStatus status;

	public AtualizarHospedeCommand() {
	}

	public AtualizarHospedeCommand(String nomeCompleto, String cpf, LocalDate dataNascimento, String telefone, String email, EnderecoCommand endereco, HospedeStatus status) {
		super(nomeCompleto, cpf, dataNascimento, telefone, email, endereco);
		this.status = status;
	}

	public HospedeStatus getStatus() {
		return status;
	}

	public void setStatus(HospedeStatus status) {
		this.status = status;
	}
}
