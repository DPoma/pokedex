package com.certant.pokedex.repositories;

import java.util.ArrayList;
import java.util.List;

import com.certant.pokedex.handlers.ListHandler;
import com.certant.pokedex.model.Usuario;

public class RepositorioUsuarios {

	private static List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public static List<Usuario> getUsuarios() {
		return usuarios;
	}

	public static void setUsuarios(List<Usuario> lista) {
		usuarios = lista;
	}
			
	public static Usuario buscar(String username) {
		return ListHandler.buscarElemento(usuarios, usuario -> usuario.getUsername().equals(username));
	}
	
	public static boolean existe(String username) {
		return ListHandler.cumpleCondicionElemento(usuarios, usuario -> usuario.getUsername().equals(username));
	}
}
