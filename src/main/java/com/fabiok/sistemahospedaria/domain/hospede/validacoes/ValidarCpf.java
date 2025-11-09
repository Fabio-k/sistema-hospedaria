package com.fabiok.sistemahospedaria.domain.hospede.validacoes;

import com.fabiok.sistemahospedaria.application.command.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.domain.ErroHandler;
import com.fabiok.sistemahospedaria.infra.HospedeDao;
import com.fabiok.sistemahospedaria.utils.ValidadorCpf;

public class ValidarCpf implements IStrategyValidacaoHospede{
    HospedeDao hospedeDao = new HospedeDao();
    @Override
    public void executar(CadastrarHospedeCommand command, ErroHandler erroHandler) {
		if(command.getCpf() == null || command.getCpf().isEmpty()) return;
		boolean isFormatoCpfValido = ValidadorCpf.validar(command.getCpf());
		boolean isCpfJaUsado = hospedeDao.existsByCpf(command.getCpf());

        if(!isFormatoCpfValido) erroHandler.addErros("CPF inválido");
        if(isCpfJaUsado) erroHandler.addErros("CPF já está em uso");
    }
}
