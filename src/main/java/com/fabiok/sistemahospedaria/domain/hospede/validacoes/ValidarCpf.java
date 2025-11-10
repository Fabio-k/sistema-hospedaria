package com.fabiok.sistemahospedaria.domain.hospede.validacoes;

import com.fabiok.sistemahospedaria.domain.Notification;
import com.fabiok.sistemahospedaria.domain.hospede.Hospede;
import com.fabiok.sistemahospedaria.infra.HospedeDao;

public class ValidarCpf implements IStrategyValidacaoHospede{
    HospedeDao hospedeDao = new HospedeDao();
    @Override
    public void executar(Hospede hospede, Notification erroHandler) {
		if(hospede.getCpf() == null || hospede.getCpf().isEmpty()) return;

		boolean isCpfJaUsado = hospedeDao.existsByCpf(hospede.getCpf(), hospede.getId());

        if(isCpfJaUsado) erroHandler.addErros("CPF já está em uso");
    }
}
