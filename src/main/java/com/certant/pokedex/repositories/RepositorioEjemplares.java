package com.certant.pokedex.repositories;

import java.util.ArrayList;
import java.util.List;

import com.certant.pokedex.handlers.ListHandler;
import com.certant.pokedex.model.Ejemplar;
import com.certant.pokedex.model.Usuario;

public class RepositorioEjemplares {
	
	private static List<Ejemplar> ejemplares = new ArrayList<Ejemplar>();

	public static List<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public static void setEjemplares(List<Ejemplar> lista) {
		ejemplares = lista;
	}
	
	public void agregar(Ejemplar ejemplar) {
		ejemplares.add(ejemplar);
	}
			
	public void limpiar() {
		ejemplares.clear();
	}
	
	public Ejemplar buscar(String nombre) {
		return ListHandler.buscarElemento(ejemplares, ejemplar -> ejemplar.getPokemon().getNombre().equals(nombre));
	}
		
	public static List<Ejemplar> obtenerDeUsuario(Usuario usuario) {
		return ListHandler.filtrarElementos(ejemplares, ejemplar -> 
		ejemplar.getUsuario().getUsername()
		.equals(usuario.getUsername()));
	}
}
