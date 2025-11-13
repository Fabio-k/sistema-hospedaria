package com.fabiok.sistemahospedaria.domain.hospede.validacoes;

import com.fabiok.sistemahospedaria.domain.Notificacao;
import com.fabiok.sistemahospedaria.domain.hospede.Hospede;

public interface IStrategyValidacaoHospede {
    public void executar(Hospede hospede, Notificacao erroHandler);
}
