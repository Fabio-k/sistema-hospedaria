package com.fabiok.sistemahospedaria;

public record CadastrarQuartoCommand(String numero, private Integer capacidadeAdultos, Integer capacidadeCriancas,
 BigDecimal preco, String status, String tipoQuarto) {}