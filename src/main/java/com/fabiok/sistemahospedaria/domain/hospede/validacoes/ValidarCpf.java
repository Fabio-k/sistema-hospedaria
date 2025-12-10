package com.fabiok.sistemahospedaria.domain.hospede.validacoes;

import com.fabiok.sistemahospedaria.domain.Notificacao;
import com.fabiok.sistemahospedaria.domain.exceptions.FieldErrorCode;
import com.fabiok.sistemahospedaria.domain.hospede.Hospede;
import com.fabiok.sistemahospedaria.infra.HospedeDao;
import com.fabiok.sistemahospedaria.infra.HospedeSqliteDao;
import com.fabiok.sistemahospedaria.infra.Idao;

public class ValidarCpf implements IStrategyValidacaoHospede{
    HospedeDao hospedeDao;

    public ValidarCpf(HospedeDao dao){
        hospedeDao = dao;
    }

    @Override
    public void executar(Hospede hospede, Notificacao erroHandler) {
		if(hospede.getEmail() == null || hospede.getEmail().isEmpty()) return;

		boolean isEmailJaUsado = hospedeDao.existsByEmail(hospede.getEmail(), hospede.getId());

        if(isEmailJaUsado) erroHandler.addErros(new FieldErrorCode("email", "email.jaUsado"));
    }
}
