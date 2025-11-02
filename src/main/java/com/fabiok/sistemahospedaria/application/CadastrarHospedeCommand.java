package com.fabiok.sistemahospedaria.application;

import com.fabiok.sistemahospedaria.domain.Endereco;

import java.time.LocalDate;

public record CadastrarHospedeCommand(String nomeCompleto, String cpf, LocalDate dataNascimento, String telefone, String email, EnderecoCommand endereco) {
}
