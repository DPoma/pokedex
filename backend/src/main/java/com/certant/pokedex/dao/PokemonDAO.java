package com.certant.pokedex.dao;

import org.springframework.data.repository.CrudRepository;

import com.certant.pokedex.model.Pokemon;

public interface PokemonDAO extends CrudRepository<Pokemon, Integer> {
	
	public abstract Pokemon findByNombre(String nombre);
}
