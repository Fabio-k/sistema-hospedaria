package com.fabiok.sistemahospedaria.application.command;

public record EnderecoCommand(Integer id, String cep, String logradouro, String cidade, String bairro, String numero, String complemento, String estado) { }
