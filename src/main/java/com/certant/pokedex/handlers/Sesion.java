package com.certant.pokedex.handlers;

import com.certant.pokedex.model.Usuario;

public class Sesion {

	private static Usuario usuario;

	public static Usuario getUsuario() {
		return usuario;
	}

	public static void iniciarSesion(Usuario user) {
		usuario = user;
	}
	
	public static boolean sinIniciar() {
		return usuario == null;
	}
}
