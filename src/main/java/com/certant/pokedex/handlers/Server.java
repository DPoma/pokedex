package com.certant.pokedex.handlers;

import com.certant.pokedex.server.Router;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	
	public static void iniciarServer() {
		Spark.port(9000);
		DebugScreen.enableDebugScreen();
		Router.configure();
	}
}
