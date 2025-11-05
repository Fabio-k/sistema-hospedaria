package com.fabiok.sistemahospedaria.domain.hospede;

import com.fabiok.sistemahospedaria.application.command.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.application.mapper.HospedeMapper;
import com.fabiok.sistemahospedaria.domain.ErroHandler;
import com.fabiok.sistemahospedaria.domain.exceptions.ValidationException;
import com.fabiok.sistemahospedaria.domain.hospede.validacoes.IStrategyValidacaoHospede;
import com.fabiok.sistemahospedaria.domain.hospede.validacoes.ValidarCamposObrigatorios;
import com.fabiok.sistemahospedaria.domain.hospede.validacoes.ValidarCpf;
import com.fabiok.sistemahospedaria.domain.hospede.validacoes.ValidarEmail;
import com.fabiok.sistemahospedaria.infra.HospedeDao;
import com.fabiok.sistemahospedaria.infra.Idao;

import java.util.List;

public class CadastrarHospede {
    private Idao<Hospede> hospedeDao = new HospedeDao();
    private ErroHandler erroHandler = new ErroHandler();
    private List<IStrategyValidacaoHospede> validacoes = List.of(
		new ValidarCamposObrigatorios(),
		new ValidarCpf(),
		new ValidarEmail()
	);

    public void execute(CadastrarHospedeCommand command){
        validacoes.forEach(v -> v.executar(command, erroHandler));
        if(erroHandler.hasErro()){
            throw new ValidationException(erroHandler.getErros());
        }
        Hospede hospede = HospedeMapper.from(command);

        hospedeDao.save(hospede);
    }
}
