package com.fabiok.sistemahospedaria;

public class DomainException extends RuntimeException{
	String status;
	DomainException(String message){
		super(message);
	}
}
