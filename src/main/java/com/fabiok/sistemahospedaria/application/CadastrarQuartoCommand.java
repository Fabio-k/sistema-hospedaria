package com.fabiok.sistemahospedaria.application;

import java.math.BigDecimal;

public record CadastrarQuartoCommand(String numero, Integer capacidadeAdultos, Integer capacidadeCriancas,
                                     BigDecimal preco, String status, String tipoQuarto) {}