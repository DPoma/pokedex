package com.certant.pokedex.service;

import java.util.List;

import com.certant.pokedex.model.Pokemon;

public interface IPokemonService {
	
	public List<Pokemon> obtener();
	
	public void guardar(Pokemon pokemon);
	
	public void eliminar(Pokemon pokemon);
	
	public Pokemon buscar(Pokemon pokemon);
}
