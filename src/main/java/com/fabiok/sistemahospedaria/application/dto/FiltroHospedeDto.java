package com.fabiok.sistemahospedaria.application.dto;

import java.time.LocalDate;

public record FiltroHospedeDto(String nomeCompleto, String cpf, LocalDate dataNascimento) {
}
