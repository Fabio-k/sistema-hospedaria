package com.fabiok.sistemahospedaria.domain.hospede;

import com.fabiok.sistemahospedaria.application.command.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.application.mapper.HospedeMapper;
import com.fabiok.sistemahospedaria.domain.Notification;
import com.fabiok.sistemahospedaria.domain.exceptions.ValidationException;
import com.fabiok.sistemahospedaria.domain.hospede.validacoes.IStrategyValidacaoHospede;
import com.fabiok.sistemahospedaria.domain.hospede.validacoes.ValidarCpf;
import com.fabiok.sistemahospedaria.domain.hospede.validacoes.ValidarEmail;
import com.fabiok.sistemahospedaria.infra.HospedeDao;
import com.fabiok.sistemahospedaria.infra.Idao;

import java.util.List;

public class CadastrarHospede {
    private Idao<Hospede> hospedeDao = new HospedeDao();
    private Notification notificacao = new Notification();
    private List<IStrategyValidacaoHospede> validacoes = List.of(
		new ValidarCpf(),
		new ValidarEmail()
	);

    public void execute(CadastrarHospedeCommand command){
        Hospede hospede = HospedeMapper.from(command);
        validacoes.forEach(v -> v.executar(hospede, notificacao));
        hospede.validar(notificacao);

        if(notificacao.hasErro()){
            throw new ValidationException(notificacao.getErros());
        }

        hospedeDao.save(hospede);
    }
}
