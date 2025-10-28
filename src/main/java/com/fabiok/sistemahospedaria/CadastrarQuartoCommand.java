package com.fabiok.sistemahospedaria;

import java.math.BigDecimal;

public record CadastrarQuartoCommand(String numero, Integer capacidadeAdultos, Integer capacidadeCriancas,
                                     BigDecimal preco, String status, String tipoQuarto) {}