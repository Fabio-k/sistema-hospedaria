package com.fabiok.sistemahospedaria.application.command;

import java.time.LocalDate;

public class HospedeCommand {
    private String nomeCompleto;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;
    private EnderecoCommand endereco;

    public HospedeCommand() {
    }

    public HospedeCommand(String nomeCompleto, String cpf, LocalDate dataNascimento, String telefone, String email, EnderecoCommand endereco) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public EnderecoCommand getEndereco() {
        return endereco;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereco(EnderecoCommand endereco) {
        this.endereco = endereco;
    }
}
