package com.fabiok.sistemahospedaria.domain.hospede.validacoes;

import java.util.HashMap;
import java.util.Map;

import com.fabiok.sistemahospedaria.application.command.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.application.command.EnderecoCommand;
import com.fabiok.sistemahospedaria.domain.ErroHandler;

public class ValidarCamposObrigatorios implements IStrategyValidacaoHospede {

    @Override
    public void executar(CadastrarHospedeCommand command, ErroHandler erroHandler) {
		Map<String, String> atributesToValidate = new HashMap<>();
		atributesToValidate.put("Cpf", command.getCpf());
		atributesToValidate.put("E-mail", command.getEmail());
		atributesToValidate.put("Telefone", command.getTelefone());
		atributesToValidate.put("Nome completo", command.getNomeCompleto());
		
		EnderecoCommand endereco = command.getEndereco();
		if(endereco != null){
			atributesToValidate.put("CEP", endereco.cep());
			atributesToValidate.put("Cidade", endereco.cidade());
			atributesToValidate.put("Complemento", endereco.complemento());
			atributesToValidate.put("Bairro", endereco.bairro());
			atributesToValidate.put("Estado", endereco.estado());
			atributesToValidate.put("Numero", endereco.numero());
			atributesToValidate.put("Logradouro", endereco.logradouro());

		}

		atributesToValidate.forEach((k, v) -> checkNullOrEmpty(k, v, erroHandler));

        if(command.getDataNascimento() == null) erroHandler.addErros("Data de nascimento");
        if(endereco == null) erroHandler.addErros(erroMessage("Endereço"));
    }

	private void checkNullOrEmpty (String atributeName, String atribute, ErroHandler erroHandler){
		if(atribute == null || atribute.isBlank()) erroHandler.addErros(erroMessage(atributeName));
	}

    private String erroMessage(String campo){
        return campo + " é obrigatório";
    }
}
