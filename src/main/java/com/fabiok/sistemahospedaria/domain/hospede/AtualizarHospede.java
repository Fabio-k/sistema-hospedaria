package com.fabiok.sistemahospedaria.domain.hospede;

import com.fabiok.sistemahospedaria.DomainException;
import com.fabiok.sistemahospedaria.application.command.EditarHospedeCommand;
import com.fabiok.sistemahospedaria.application.mapper.HospedeMapper;
import com.fabiok.sistemahospedaria.domain.Notificacao;
import com.fabiok.sistemahospedaria.domain.exceptions.ValidationException;
import com.fabiok.sistemahospedaria.domain.hospede.validacoes.IStrategyValidacaoHospede;
import com.fabiok.sistemahospedaria.infra.HospedeDao;
import com.fabiok.sistemahospedaria.infra.Idao;

import java.util.List;

public class AtualizarHospede {
	private Idao<Hospede> hospedeDao;
	private Notificacao notificacao;
	private List<IStrategyValidacaoHospede> validacoes;

	public AtualizarHospede(Idao<Hospede> hospedeDao, List<IStrategyValidacaoHospede> validacoes, Notificacao notificacao) {
		this.hospedeDao = hospedeDao;
		this.validacoes = validacoes;
		this.notificacao = notificacao;
	}

	public void execute(Integer id, EditarHospedeCommand command){
		Hospede hospedeEncontrado = hospedeDao.findById(id).orElseThrow(() -> new DomainException("Hóspede não encontrado", 404));
		Hospede hospede = HospedeMapper.update(hospedeEncontrado, command);
		validacoes.forEach(v -> v.executar(hospede, notificacao));

		hospede.validar(notificacao);
		if(notificacao.hasErro()){
			throw new ValidationException(notificacao.getErros());
		}

		hospedeDao.update(hospede);
	}
}
