package com.fabiok.sistemahospedaria.domain.hospede;

import java.util.List;

import com.fabiok.sistemahospedaria.DomainException;
import com.fabiok.sistemahospedaria.application.command.EditarHospedeCommand;
import com.fabiok.sistemahospedaria.application.mapper.HospedeMapper;
import com.fabiok.sistemahospedaria.domain.ErroHandler;
import com.fabiok.sistemahospedaria.domain.exceptions.ValidationException;
import com.fabiok.sistemahospedaria.domain.hospede.validacoes.IStrategyValidacaoHospede;
import com.fabiok.sistemahospedaria.domain.hospede.validacoes.ValidarCamposObrigatorios;
import com.fabiok.sistemahospedaria.domain.hospede.validacoes.ValidarCpf;
import com.fabiok.sistemahospedaria.domain.hospede.validacoes.ValidarEmail;
import com.fabiok.sistemahospedaria.infra.HospedeDao;
import com.fabiok.sistemahospedaria.infra.Idao;

public class AtualizarHospede {
	private Idao<Hospede> hospedeDao = new HospedeDao();
	private ErroHandler erroHandler = new ErroHandler();
	private List<IStrategyValidacaoHospede> validacoes = List.of(
		new ValidarCamposObrigatorios(),
		new ValidarCpf(),
		new ValidarEmail()
	);

	public void execute(Integer id, EditarHospedeCommand command){
		validacoes.forEach(v -> v.executar(command, erroHandler));
		if(erroHandler.hasErro()){
			throw new ValidationException(erroHandler.getErros());
		}
		Hospede hospedeEncontrado = hospedeDao.findById(id).orElseThrow(() -> new DomainException("Hóspede não encontrado", 404));
		Hospede hospede = HospedeMapper.update(hospedeEncontrado, command);

		hospedeDao.save(hospede);
	}
}
