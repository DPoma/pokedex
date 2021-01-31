package com.certant.pokedex.service;

import java.util.List;

import com.certant.pokedex.model.Pokemon;

public interface PokemonService {
	
	public List<Pokemon> pokemones();
	
	public void guardar(Pokemon pokemon);
	
	public void eliminar(Pokemon pokemon);
	
	public Pokemon buscar(Pokemon pokemon);
}
