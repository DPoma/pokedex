package com.certant.pokedex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certant.pokedex.dao.PokemonDAO;
import com.certant.pokedex.model.Pokemon;

@Service
public class PokemonService implements IPokemonService {

	@Autowired
	private PokemonDAO pokemonDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pokemon> pokemones() {
		return (List<Pokemon>)pokemonDAO.findAll();
	}

	@Override
	@Transactional
	public void guardar(Pokemon pokemon) {
		pokemonDAO.save(pokemon);
		
	}

	@Override
	@Transactional
	public void eliminar(Pokemon pokemon) {
		pokemonDAO.delete(pokemon);
	}

	@Override
	@Transactional(readOnly = true)
	public Pokemon buscar(Pokemon pokemon) {
		return pokemonDAO.findById(pokemon.getId()).orElse(null);
	}
}
