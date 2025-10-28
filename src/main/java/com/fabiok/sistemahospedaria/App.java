package com.fabiok.sistemahospedaria;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetSocketAddress;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
		server.createContext("/quarto", new QuartoHttpHandler());
		server.start();
    }
}
