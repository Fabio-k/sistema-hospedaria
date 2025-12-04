CREATE EXTENSION pg_trgm;

CREATE TABLE quarto (
	qua_id SMALLSERIAL PRIMARY KEY,
	qua_numero VARCHAR(6),
	qua_tipo_quarto VARCHAR(20),
	qua_capacidade_adultos INTEGER,
	qua_capacidade_criancas INTEGER,
	qua_status VARCHAR(20),
	qua_preco_base DECIMAL(10, 2)
);

CREATE TABLE usuario(
	usr_id SMALLSERIAL PRIMARY KEY,
	usr_nome VARCHAR(20),
	usr_senha VARCHAR(40)
);

CREATE TABLE hospede (
	hos_id SMALLSERIAL PRIMARY KEY,
	hos_nome_completo VARCHAR(50) NOT NULL,
	hos_cpf VARCHAR(20) NOT NULL,
	hos_data_nascimento DATE NOT NULL,
	hos_telefone VARCHAR(20) NOT NULL,
	hos_email VARCHAR(30) NOT NULL,
	hos_status VARCHAR(20) NOT NULL
);

CREATE TABLE politica_cancelamento(
	poc_id SMALLSERIAL PRIMARY KEY,
	poc_descricao VARCHAR(20)
);

CREATE TABLE reserva(
	res_id SMALLSERIAL PRIMARY KEY,
	res_codigo VARCHAR(30),
	res_hos_id INTEGER,
	res_qua_id INTEGER,
	res_poc_id INTEGER,
	res_data_entrada TIMESTAMP,
	res_data_saida TIMESTAMP,
	res_qtd_adultos INTEGER,
	res_qtd_criancas INTEGER,
	res_qtd_crianca_isenta INTEGER,
	res_status VARCHAR(20),
	res_valor_total DECIMAL(10, 2),
	res_no_show BOOLEAN,
	FOREIGN KEY (res_hos_id) REFERENCES hospede(hos_id),
	FOREIGN KEY (res_qua_id) REFERENCES quarto(qua_id),
	FOREIGN KEY (res_poc_id) REFERENCES politica_cancelamento(poc_id)
);

CREATE TABLE pagamento(
	pag_id SMALLSERIAL PRIMARY KEY,
	pag_res_id INTEGER NOT NULL,
	pag_forma_pagamento VARCHAR(20),
	pag_valor DECIMAL(10, 2),
	pag_data_operacao DATE,
	pag_status VARCHAR(20),
	FOREIGN KEY (pag_res_id) REFERENCES reserva(res_id)
);

CREATE TABLE promocao (
	pro_id SMALLSERIAL PRIMARY KEY,
	pro_codigo VARCHAR(30),
	pro_desconto INTEGER,
	pro_is_comulativa BOOLEAN DEFAULT false
);

CREATE TABLE pagamento_promocao(
	pap_id SMALLSERIAL PRIMARY KEY,
	pap_pro_id INTEGER NOT NULL,
	pap_pag_id INTEGER NOT NULL,
	FOREIGN KEY (pap_pro_id) REFERENCES promocao(pro_id),
	FOREIGN KEY (pap_pag_id) REFERENCES pagamento(pag_id)
);



CREATE TABLE endereco (
	end_id SMALLSERIAL PRIMARY KEY,
	end_logradouro VARCHAR(30) NOT NULL,
	end_numero VARCHAR(30) NOT NULL,
	end_cep VARCHAR(30) NOT NULL,
	end_bairro VARCHAR(30) NOT NULL,
	end_complemento VARCHAR(30) NOT NULL,
	end_cidade VARCHAR(30) NOT NULL,
	end_estado VARCHAR(30) NOT NULL,
	end_hos_id INTEGER NOT NULL,
    FOREIGN KEY (end_hos_id) REFERENCES hospede(hos_id) ON DELETE CASCADE
);

CREATE TABLE hotel (
	hot_id SMALLSERIAL PRIMARY KEY,
	hot_end_id INTEGER NOT NULL,
	hot_nome VARCHAR(40),
	FOREIGN KEY (hot_end_id) REFERENCES endereco(end_id)
);