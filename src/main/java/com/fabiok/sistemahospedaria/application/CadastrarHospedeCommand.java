package com.fabiok.sistemahospedaria.application;

import java.time.LocalDate;

public record CadastrarHospedeCommand(String nomeCompleto,String cpf,LocalDate dataNascimento, String telefone, String email) {
}
