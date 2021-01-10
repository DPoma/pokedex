package com.certant.pokedex.repositories;

import java.util.ArrayList;
import java.util.List;

import com.certant.pokedex.handlers.ListHandler;
import com.certant.pokedex.model.Habilidad;
import com.certant.pokedex.model.Pokemon;

public class RepositorioHabilidades {
	
	private static List<Habilidad> habilidades = new ArrayList<Habilidad>();

	public List<Habilidad> getHabilidades() {
		return habilidades;
	}

	public static void setHabilidades(List<Habilidad> lista) {
		habilidades = lista;
	}
	
	public static void agregar(Habilidad habilidad) {
		habilidades.add(habilidad);
	}
	
	public void limpiar() {
		habilidades.clear();
	}
		
	public static Habilidad buscar(String nombre) {
		return ListHandler.buscarElemento(habilidades, habilidad -> habilidad.getNombre().equals(nombre));
	}
	
	public static List<Habilidad> disponiblesPara(Pokemon pokemon) {
		return ListHandler.filtrarElementos(habilidades, habilidad -> !pokemon.tieneEsaHabilidad(habilidad));
	}
}
