package com.fabiok.sistemahospedaria;

import com.fabiok.sistemahospedaria.application.AuthenticatorHandler;
import com.fabiok.sistemahospedaria.application.HospedeController;
import com.fabiok.sistemahospedaria.application.QuartoController;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
		server.createContext("/quarto", new QuartoController());
		server.createContext("/hospede",new AuthenticatorHandler(new HospedeController()));
		server.start();
		System.out.println("ready to receive connections!");

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("Shutting down HTTP server...");
			server.stop(0);
		}));
    }
}
