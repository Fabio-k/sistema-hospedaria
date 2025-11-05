package com.fabiok.sistemahospedaria.domain.hospede.validacoes;

import com.fabiok.sistemahospedaria.application.command.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.domain.ErroHandler;

public interface IStrategyValidacaoHospede {
    public void executar(CadastrarHospedeCommand command, ErroHandler erroHandler);
}
