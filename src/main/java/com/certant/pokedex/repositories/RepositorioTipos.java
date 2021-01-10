package com.certant.pokedex.repositories;

import java.util.ArrayList;
import java.util.List;

import com.certant.pokedex.handlers.ListHandler;
import com.certant.pokedex.model.Pokemon;
import com.certant.pokedex.model.Tipo;

public class RepositorioTipos {
	
	private static List<Tipo> tipos = new ArrayList<Tipo>();
	
	public List<Tipo> getTipos() {
		return tipos;
	}

	public static void setTipos(List<Tipo> lista) {
		tipos = lista;
	}
	
	public static void agregar(Tipo tipo) {
		tipos.add(tipo);
	}
	
	public void limpiar() {
		tipos.clear();
	}
	
	public static Tipo buscar(String nombre) {
		return ListHandler.buscarElemento(tipos, tipo -> tipo.getNombre().equals(nombre));
	}
	
	public static List<Tipo> disponiblesPara(Pokemon pokemon) {
		return ListHandler.filtrarElementos(tipos, tipo -> !pokemon.esTipo(tipo));
	}
}
