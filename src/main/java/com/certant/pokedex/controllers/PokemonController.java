package com.certant.pokedex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.certant.pokedex.model.Pokemon;

import com.certant.pokedex.service.PokemonService;

@RestController
public class PokemonController {
	
	@Autowired
	private PokemonService pokemonService;

	@GetMapping("/pokemones")
	public List<Pokemon> obtenerPokemones() {
		return pokemonService.obtenerTodos();
	}
	
	@PostMapping("/pokemones")
	public Pokemon agregarPokemon(@RequestBody Pokemon pokemon) {
		if(pokemon.getImagen() == null || pokemon.getImagen().trim().isEmpty())
			pokemon.setImagen("https://assets.pokemon.com/assets/cms2/img/pokedex/full/201.png");
		pokemonService.guardar(pokemon);
		return pokemon;
	}
	
	@PutMapping("/pokemones")
	public Pokemon editarPost(Pokemon pokemon) {
		pokemonService.guardar(pokemon);
		return pokemon;
	}
	
	@GetMapping("/pokemones/{pokemonId}")
	public Pokemon obtenerPokemon(@PathVariable int pokemonId) {
		Pokemon pokemon = pokemonService.buscarPorId(pokemonId);
		return pokemon;
	}
	
	@GetMapping("/pokemones/evoluciones")
	public List<Pokemon> agregarEvolucionGet() {
		return pokemonService.obtenerEvolucionesDisponibles();
	}
	
	@PostMapping("/pokemones/{pokemonId}/evoluciones")
	public Pokemon agregarEvolucionPost(@PathVariable int pokemonId, @RequestBody Pokemon evolucion) {
		Pokemon pokemon = pokemonService.buscarPorId(pokemonId);
		pokemon.agregarEvolucion(evolucion);	
		pokemonService.guardar(pokemon);
		return pokemon;
	}

}
