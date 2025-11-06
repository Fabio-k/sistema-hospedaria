package com.fabiok.sistemahospedaria.domain;

public class Endereco {
    private Integer id;
    private String cep;
    private String logradouro;
    private String cidade;
    private String bairro;
    private String numero;
    private String complemento;
    private String estado;

    public Endereco(Integer id, String cep, String logradouro, String cidade, String bairro, String numero, String complemento, String estado) {
        this.id = id;
        this.cep = cep;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento(){
        return complemento;
    }

    public String getEstado(){
        return estado;
    }
}
