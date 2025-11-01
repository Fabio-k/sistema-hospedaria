package com.fabiok.sistemahospedaria.utils;

public class ValidadorCpf {
	public static boolean validar(String cpf){
		String cpfNormalized = cpf.replaceAll("\\D", "");
		if(cpfNormalized.length() != 11){
			return false;
		}
		Integer sum = 0;
		Integer sum2 = 0;
		int digitoValidador1 = cpfNormalized.charAt(cpfNormalized.length() - 2) - '0';
		int digitoValidador2 = cpfNormalized.charAt(cpfNormalized.length() - 1) - '0';
		
		for(int i = 10; i > 1; i--){
			sum += i * (cpfNormalized.charAt(10 - i) - '0');
		}
		for(int i = 11; i > 1; i--){
			sum2 += i * (cpfNormalized.charAt(11 - i) - '0');
		}
		Integer firstValidation =  (sum * 10) % 11;
		Integer secondValidation = (sum2 * 10) % 11;

		return digitoValidador1 == firstValidation && digitoValidador2 == secondValidation;
	}
}
