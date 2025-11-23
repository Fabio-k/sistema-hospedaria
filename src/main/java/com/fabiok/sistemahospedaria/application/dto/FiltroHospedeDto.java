package com.fabiok.sistemahospedaria.application.dto;

public record FiltroHospedeDto(String termo, Integer minIdade, Integer maxIdade, String status, Integer page, Integer size) {
}
