package com.fabiok.sistemahospedaria.domain.hospede;

import com.fabiok.sistemahospedaria.application.command.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.domain.ErroHandler;
import com.fabiok.sistemahospedaria.infra.HospedeDao;
import com.fabiok.sistemahospedaria.utils.ValidadorCpf;

public class ValidarCpf implements IStrategyValidacaoHospede{
    HospedeDao hospedeDao = new HospedeDao();
    @Override
    public void executar(CadastrarHospedeCommand command, ErroHandler erroHandler) {
		if(command.cpf() == null || command.cpf().isEmpty()) return;
		boolean isFormatoCpfValido = ValidadorCpf.validar(command.cpf());
		boolean isCpfJaUsado = hospedeDao.existsByCpf(command.cpf());

        if(!isFormatoCpfValido) erroHandler.addErros("CPF inválido");
        if(isCpfJaUsado) erroHandler.addErros("CPF já está em uso");
    }
}
