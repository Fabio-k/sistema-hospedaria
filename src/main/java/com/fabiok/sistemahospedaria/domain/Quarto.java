package com.fabiok.sistemahospedaria.domain;
import com.fabiok.sistemahospedaria.DomainException;

import java.math.BigDecimal;

public class Quarto {
	private String numero;
	private Integer capacidadeAdultos;
	private Integer capacidadeCriancas;
	private BigDecimal preco;
	private String status;
	private String tipoQuarto;

	public Quarto(String numero, Integer capacidadeAdultos,Integer capacidadeCriancas,BigDecimal preco, String status, String tipoQuarto){
		this.numero = numero;
		this.capacidadeAdultos = capacidadeAdultos;
		this.capacidadeCriancas = capacidadeCriancas;
		this.preco = preco;
		this.status = status;
		this.tipoQuarto = tipoQuarto;
	}

	public Boolean isDentroCapacidade(Integer qtdAdultos, Integer qtdCriancas){
		if(capacidadeAdultos < qtdAdultos) return false;
		Integer capAdultoDisponivel = capacidadeAdultos - qtdAdultos;
		return (capAdultoDisponivel + capacidadeCriancas) >= qtdCriancas;
	}

	public void definirCapacidadeAdultos(Integer capacidadeAdultos){
		if(capacidadeAdultos < 1){
			throw new DomainException(numero);
		}
		this.capacidadeAdultos = capacidadeAdultos;
	}

	public String getNumero(){
		return numero;
	}

	public Integer getCapacidadeAdultos(){
		return capacidadeAdultos;
	}

	public Integer getCapacidadeCriancas(){
		return capacidadeCriancas;
	}

	public BigDecimal getPreco(){
		return preco;
	}

	public String getStatus(){
		return status;
	}

	public String getTipoQuarto(){
		return tipoQuarto;
	}
}