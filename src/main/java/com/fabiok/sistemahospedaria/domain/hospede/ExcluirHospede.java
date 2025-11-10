package com.fabiok.sistemahospedaria.domain.hospede;

import com.fabiok.sistemahospedaria.DomainException;
import com.fabiok.sistemahospedaria.infra.HospedeDao;
import com.fabiok.sistemahospedaria.infra.Idao;

public class ExcluirHospede {
    private Idao<Hospede> hospedeDao = new HospedeDao();

    public void execute(Integer id){
        Hospede hospede = hospedeDao.findById(id).orElseThrow(() -> new DomainException("Hóspede não encontrado", 404));
        hospedeDao.delete(hospede.getId());
    }
}
