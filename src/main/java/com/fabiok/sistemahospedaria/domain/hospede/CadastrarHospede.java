package com.fabiok.sistemahospedaria.domain.hospede;

import com.fabiok.sistemahospedaria.application.command.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.application.mapper.HospedeMapper;
import com.fabiok.sistemahospedaria.domain.Notificacao;
import com.fabiok.sistemahospedaria.domain.exceptions.ValidationException;
import com.fabiok.sistemahospedaria.domain.hospede.validacoes.IStrategyValidacaoHospede;
import com.fabiok.sistemahospedaria.infra.Idao;

import java.util.List;

public class CadastrarHospede {
    private Idao<Hospede> hospedeDao;
    private Notificacao notificacao;
    private List<IStrategyValidacaoHospede> validacoes;

    public CadastrarHospede(Idao<Hospede> hospedeDao, List<IStrategyValidacaoHospede> validacoes, Notificacao notificacao) {
		this.hospedeDao = hospedeDao;
		this.validacoes = validacoes;
		this.notificacao = notificacao;
	}

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
