package com.certant.pokedex;

import com.certant.pokedex.handlers.DataAccessObject;
import com.certant.pokedex.handlers.FileHandler;
import com.certant.pokedex.handlers.Server;

public class Pokedex {
	
	public static void main(String[] args) {
		
		if (DataAccessObject.baseDatosEstaVacia()) {
			FileHandler.extraerDatos();
			DataAccessObject.persistirDatos();
		}
		Server.iniciarServer();
	}
}
