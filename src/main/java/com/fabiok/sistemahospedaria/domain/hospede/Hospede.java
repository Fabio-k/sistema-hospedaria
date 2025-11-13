package com.fabiok.sistemahospedaria.domain.hospede;

import com.fabiok.sistemahospedaria.domain.Cpf;
import com.fabiok.sistemahospedaria.domain.Endereco;
import com.fabiok.sistemahospedaria.domain.Notification;
import com.fabiok.sistemahospedaria.utils.FieldToBeValidated;
import com.fabiok.sistemahospedaria.utils.ValidarNullOuEmpty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hospede {
	private Integer id;
	private String nomeCompleto;
	private Cpf cpf;
	private LocalDate dataNascimento;
	private String telefone;
	private String email;
	private Endereco endereco;

	public Hospede(Integer id, String nomeCompleto, String cpf, LocalDate dataNascimento, String telefone, String email, Endereco endereco) {
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.cpf = new Cpf(cpf);
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}

	public void validar(Notification notificacao){
		List<FieldToBeValidated> fieldsToValidate = new ArrayList<>();
		String cpfValor = null;
		if(cpf != null){
			cpfValor = cpf.getValor();
			cpf.validar(notificacao);
		}
		fieldsToValidate.add(new FieldToBeValidated("cpf", "CPF", cpfValor));
		fieldsToValidate.add(new FieldToBeValidated("email", "E-mail", email));
		fieldsToValidate.add(new FieldToBeValidated("telefone", "Telefone", telefone));
		fieldsToValidate.add(new FieldToBeValidated("nomeCompleto", "Nome completo", nomeCompleto));

		if (endereco != null) {
			fieldsToValidate.add(new FieldToBeValidated("cep", "CEP", endereco.getCep()));
			fieldsToValidate.add(new FieldToBeValidated("cidade", "Cidade", endereco.getCidade()));
			fieldsToValidate.add(new FieldToBeValidated("complemento", "Complemento", endereco.getComplemento()));
			fieldsToValidate.add(new FieldToBeValidated("bairro", "Bairro", endereco.getBairro()));
			fieldsToValidate.add(new FieldToBeValidated("estado", "Estado", endereco.getEstado()));
			fieldsToValidate.add(new FieldToBeValidated("numero", "Número", endereco.getNumero()));
			fieldsToValidate.add(new FieldToBeValidated("logradouro", "Logradouro", endereco.getLogradouro()));
		}

		ValidarNullOuEmpty.validar(notificacao, fieldsToValidate);

		if(dataNascimento == null) notificacao.addErros("Data de nascimento");
		if(endereco == null) notificacao.addErros(ValidarNullOuEmpty.erroMessage("Endereço"));
	}

	public Integer getId() {
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

	public Endereco getEndereco(){
		return endereco;
	}

	public void setId(Integer id){
		this.id = id;
	}
}
