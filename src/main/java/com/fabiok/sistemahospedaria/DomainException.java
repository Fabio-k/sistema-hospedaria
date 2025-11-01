package com.fabiok.sistemahospedaria;

public class DomainException extends RuntimeException{
	String status;
	public DomainException(String message){
		super(message);
	}
}
