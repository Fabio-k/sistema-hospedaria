package com.fabiok.sistemahospedaria;

import java.time.LocalDate;

public record CadastrarHospedeCommand(String nomeCompleto,String cpf,LocalDate dataNascimento, String telefone, String email) {
}
