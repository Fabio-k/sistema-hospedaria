package com.fabiok.sistemahospedaria;

import java.math.BigDecimal;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Quarto quarto = new Quarto("123", 2, 2, BigDecimal.valueOf(100), "ATIVO", "SINGLE");
		QuartoIdao quartoIdao = new QuartoIdao();
		quartoIdao.save(quarto);
    }
}
