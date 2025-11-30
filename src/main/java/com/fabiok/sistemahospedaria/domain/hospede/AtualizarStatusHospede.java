package com.fabiok.sistemahospedaria.domain.hospede;

import java.util.List;

import com.fabiok.sistemahospedaria.DomainException; 
import com.fabiok.sistemahospedaria.infra.Idao;

public class AtualizarStatusHospede {
	Idao<Hospede> hospedeDao;

	public AtualizarStatusHospede(Idao<Hospede> hospedeDao) {
		this.hospedeDao = hospedeDao;
	}

	public void executar(Integer id, String status){
		List<String> validStatus = List.of("ativar", "inativar");
		if(status == null || !validStatus.contains(status)) throw new DomainException("Status inválido", 400);
		HospedeStatus hospedeStatus = status.equals("ativar") ? HospedeStatus.ATIVO : HospedeStatus.INATIVO;
		Hospede hospedeEncontrado = hospedeDao.findById(id).orElseThrow(() -> new DomainException("Hóspede não encontrado", 404));
		hospedeEncontrado.definirStatus(hospedeStatus);
		hospedeDao.update(hospedeEncontrado);
	}
}
