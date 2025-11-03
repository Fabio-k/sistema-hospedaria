package com.fabiok.sistemahospedaria.domain.hospede;

import com.fabiok.sistemahospedaria.application.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.domain.ErroHandler;
import com.fabiok.sistemahospedaria.infra.HospedeDao;
import com.fabiok.sistemahospedaria.utils.ValidadorCpf;

public class ValidarCpf implements IStrategyValidacaoHospede{
    HospedeDao hospedeDao = new HospedeDao();
    @Override
    public void executar(CadastrarHospedeCommand command, ErroHandler erroHandler) {
        if(!ValidadorCpf.validar(command.cpf())) erroHandler.addErros("CPF inválido");
        if(hospedeDao.existsByCpf(command.cpf())) erroHandler.addErros("CPF já está em uso");
    }
}
