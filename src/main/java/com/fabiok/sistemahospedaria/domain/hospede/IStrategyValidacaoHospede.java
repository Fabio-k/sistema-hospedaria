package com.fabiok.sistemahospedaria.domain.hospede;

import com.fabiok.sistemahospedaria.application.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.domain.ErroHandler;

public interface IStrategyValidacaoHospede {
    public void executar(CadastrarHospedeCommand command, ErroHandler erroHandler);
}
