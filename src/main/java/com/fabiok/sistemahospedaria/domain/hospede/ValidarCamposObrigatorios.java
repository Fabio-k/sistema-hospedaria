package com.fabiok.sistemahospedaria.domain.hospede;

import com.fabiok.sistemahospedaria.application.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.application.EnderecoCommand;
import com.fabiok.sistemahospedaria.domain.ErroHandler;

public class ValidarCamposObrigatorios implements IStrategyValidacaoHospede {

    @Override
    public void executar(CadastrarHospedeCommand command, ErroHandler erroHandler) {
        if(command.cpf() == null || command.cpf().isBlank()) erroHandler.addErros(erroMessage("Cpf"));
        if(command.email() == null || command.email().isBlank()) erroHandler.addErros(erroMessage("Email"));
        if(command.telefone() == null || command.telefone().isBlank()) erroHandler.addErros(erroMessage("Telefone"));
        if(command.nomeCompleto() == null || command.nomeCompleto().isBlank()) erroHandler.addErros(erroMessage("Nome completo"));
        if(command.dataNascimento() == null) erroHandler.addErros("Data de nascimento");
        EnderecoCommand endereco = command.endereco();
        if(endereco == null) {
           erroHandler.addErros(erroMessage("Endereço"));
           return;
        }
        if(endereco.cep() == null || endereco.cep().isBlank()) erroHandler.addErros(erroMessage("CEP"));
        if(endereco.cidade() == null || endereco.cidade().isBlank()) erroHandler.addErros(erroMessage("Cidade"));
        if(endereco.complemento() == null || endereco.complemento().isBlank()) erroHandler.addErros(erroMessage("Complemento"));
        if(endereco.bairro() == null || endereco.bairro().isBlank()) erroHandler.addErros(erroMessage("Bairro"));
        if(endereco.estado() == null || endereco.estado().isBlank()) erroHandler.addErros(erroMessage("Estado"));
        if(endereco.numero() == null || endereco.numero().isBlank()) erroHandler.addErros(erroMessage("Numero do endereço"));
        if(endereco.logradouro() == null || endereco.logradouro().isBlank()) erroHandler.addErros(erroMessage("Logradouro"));
    }

    private String erroMessage(String campo){
        return campo + " é obrigatório";
    }
}
