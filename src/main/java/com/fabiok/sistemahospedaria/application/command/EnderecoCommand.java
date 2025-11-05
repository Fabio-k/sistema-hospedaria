package com.fabiok.sistemahospedaria.application.command;

public record EnderecoCommand(String id, String cep, String logradouro, String cidade, String bairro, String numero, String complemento, String estado) { }
