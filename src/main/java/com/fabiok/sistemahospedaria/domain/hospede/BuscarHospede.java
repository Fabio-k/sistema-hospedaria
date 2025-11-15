package com.fabiok.sistemahospedaria.domain.hospede;

import com.fabiok.sistemahospedaria.DomainException;
import com.fabiok.sistemahospedaria.infra.Idao;

public class BuscarHospede {
    Idao<Hospede> hospedeDao;

    public BuscarHospede(Idao<Hospede> hospedeDao) {
        this.hospedeDao = hospedeDao;
    }

    public Hospede execute(Integer id){
        return hospedeDao.findById(id).orElseThrow(() -> new DomainException("Hóspede não encontrado", 404));
    }
}
