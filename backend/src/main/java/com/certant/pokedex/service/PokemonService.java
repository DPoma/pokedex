package com.certant.pokedex.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certant.pokedex.dao.PokemonDAO;

import com.certant.pokedex.model.Pokemon;
import com.certant.pokedex.model.PokemonBase;
import com.certant.pokedex.model.PokemonEvolucion;

@Service
public class PokemonService {

	@Autowired
	private PokemonDAO pokemonDAO;
	
	@Transactional(readOnly = true)
	public List<Pokemon> obtenerTodos() {
		return (List<Pokemon>)pokemonDAO.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Pokemon> obtenerBases() {
		return obtenerTodos().stream().filter(pokemon -> pokemon instanceof PokemonBase).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<Pokemon> obtenerEvoluciones() {
		return obtenerTodos().stream().filter(pokemon -> pokemon instanceof PokemonEvolucion).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<Pokemon> obtenerEvolucionesDisponibles() {
		return obtenerEvoluciones().stream().filter(evolucion -> evolucion.esEvolucionDisponible()).collect(Collectors.toList());		
	}
	
	@Transactional(readOnly = true)
	public Pokemon obtenerDetalles(int pokemonId) {
		Pokemon pokemon = this.buscarPorId(pokemonId);
		return pokemon.obtenerDetalles();
		
	}

	@Transactional(readOnly = true)
	public Pokemon buscarPorId(int id) {
		return pokemonDAO.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public Pokemon buscarPorNombre(String nombre) {
		return pokemonDAO.findByNombre(nombre);
	}
	
	@Transactional
	public void guardar(Pokemon pokemon) {
		pokemonDAO.save(pokemon);		
	}

	@Transactional
	public void eliminar(Pokemon pokemon) {
		pokemonDAO.delete(pokemon);
	}
	
}
