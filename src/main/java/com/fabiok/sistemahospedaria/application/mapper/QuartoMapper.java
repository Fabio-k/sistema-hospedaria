package com.fabiok.sistemahospedaria.application.mapper;

import com.fabiok.sistemahospedaria.application.command.CadastrarQuartoCommand;
import com.fabiok.sistemahospedaria.domain.Quarto;

public class QuartoMapper {
    public static Quarto from(CadastrarQuartoCommand command){
        return new Quarto(command.numero(), command.capacidadeAdultos(), command.capacidadeCriancas(), command.preco(), command.status(), command.tipoQuarto());
    }
}
