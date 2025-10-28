package com.fabiok.sistemahospedaria;

public class QuartoMapper {
    public static Quarto from(CadastrarQuartoCommand command){
        return new Quarto(command.numero(), command.capacidadeAdultos(), command.capacidadeCriancas(), command.preco(), command.status(), command.tipoQuarto());
    }
}
