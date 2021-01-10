package com.certant.pokedex.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.certant.pokedex.handlers.ListHandler;
import com.certant.pokedex.model.Pokemon;
import com.certant.pokedex.model.PokemonBase;
import com.certant.pokedex.model.PokemonEvolucion;

public class RepositorioPokemones {
	
	private static List<Pokemon> pokemones = new ArrayList<Pokemon>();
	
	public static List<Pokemon> getPokemones() {
		return pokemones;
	}

	public static void setPokemones(List<Pokemon> lista) {
		pokemones = lista;
	}
	
	public static void agregar(Pokemon pokemon) {
		pokemones.add(pokemon);
	}
	
	public void limpiar() {
		pokemones.clear();
	}
	
	public static Pokemon buscar(String nombre) {
		return ListHandler.buscarElemento(pokemones, pokemon -> pokemon.getNombre().equals(nombre));
	}
	
	public static boolean existe(String nombre) {
		return ListHandler.cumpleCondicionElemento(pokemones, pokemon -> pokemon.getNombre().equals(nombre));
	}
		
	public static List<Pokemon> obtenerBases() {
		return ListHandler.filtrarElementos(pokemones, pokemon -> pokemon instanceof PokemonBase);
	}
	
	public static List<Pokemon> obtenerEvoluciones() {
		return ListHandler.filtrarElementos(pokemones, pokemon -> pokemon instanceof PokemonEvolucion);
	}
	
	public static List<Pokemon> obtenerEvolucionesDisponiblesPara(Pokemon pokemon) {
		return ListHandler.filtrarElementos(obtenerEvoluciones(), 
				evolucion -> evolucion.estaDisponible()
				&& !evolucion.getNombre().equals(pokemon.getNombre()));			
	}
	
	@SuppressWarnings("unchecked")
	public static List<Pokemon> obtenerEvolucionesOrdenadas(Pokemon pokemon) {
		List<Pokemon> pokemones = new ArrayList<Pokemon>();
		List<Pokemon> evoluciones = ((List<Pokemon>)pokemon.obtenerEvoluciones());
		if(pokemon instanceof PokemonBase)
			pokemones.add(pokemon);
		else
			pokemones.add(((PokemonEvolucion)pokemon).getPokemonBase());
		for(Pokemon evolucion : evoluciones)
			pokemones.add(evolucion);
		Collections.sort(pokemones, Comparator.comparing(Pokemon::getNivel));
		return pokemones;
	}
 }
