package com.fabiok.sistemahospedaria;

public class DomainException extends RuntimeException{
	private Integer status;

	public DomainException(String message, Integer status){
		super(message);
		this.status = status;
	}

	public Integer getStatus(){
		return status;
	}
}
