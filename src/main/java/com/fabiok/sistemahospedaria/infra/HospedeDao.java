package com.fabiok.sistemahospedaria.infra;

import com.fabiok.sistemahospedaria.domain.hospede.Hospede;

public interface HospedeDao extends Idao<Hospede> {
    Boolean existsByCpf(String cpf, Integer id);
}
