package com.certant.pokedex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certant.pokedex.model.Pokemon;
import com.certant.pokedex.model.PokemonEvolucion;
import com.certant.pokedex.service.PokemonService;

@RestController
@RequestMapping("/api")
public class PokemonController {
	
	@Autowired
	private PokemonService pokemonService;

	@GetMapping("/pokemones")
	public List<Pokemon> obtenerPokemones() {
		return pokemonService.obtenerTodos();
	}
	
	@PostMapping("/pokemones/agregar")
	public Pokemon agregarPokemon(@RequestBody Pokemon pokemon) {
		if(pokemon.getImagen() == null || pokemon.getImagen().trim().isEmpty())
			pokemon.setImagen("https://assets.pokemon.com/assets/cms2/img/pokedex/full/201.png");
		pokemonService.guardar(pokemon);
		return pokemon;
	}
	
	@GetMapping("/pokemones/{pokemonId}")
	public Pokemon obtenerPokemon(@PathVariable int pokemonId) {
		Pokemon pokemon = pokemonService.obtenerDetalles(pokemonId);
		return pokemon;
	}
	
	@GetMapping("/pokemones/{pokemonId}/editar")
	public Pokemon editarPokemonGet(@PathVariable int pokemonId) {
		Pokemon pokemon = pokemonService.buscarPorId(pokemonId);
		pokemonService.guardar(pokemon);
		return pokemon;
	}
	
	@PutMapping("/pokemones/{pokemonId}/editar")
	public Pokemon editarPokemonPut(@PathVariable int pokemonId, @RequestBody Pokemon pokemonJSON) {
		Pokemon pokemon = pokemonService.buscarPorId(pokemonId);
		pokemon.setNombre(pokemonJSON.getNombre());
		pokemon.setDescripcion(pokemonJSON.getDescripcion());
		pokemon.setNivelRequerido(pokemonJSON.getNivelRequerido());
		pokemonService.guardar(pokemon);
		return pokemon;
	}
	
	@GetMapping("/pokemones/{pokemonId}/evoluciones/agregar")
	public void agregarEvolucionGet() {
		
	}
	
	@PostMapping("/pokemones/{pokemonId}/evoluciones/agregar")
	public Pokemon agregarEvolucionPost(@PathVariable int pokemonId, @RequestBody PokemonEvolucion evolucion) {
		Pokemon pokemon = pokemonService.buscarPorId(pokemonId);
		if(evolucion.getImagen() == null || evolucion.getImagen().trim().isEmpty())
			evolucion.setImagen("https://assets.pokemon.com/assets/cms2/img/pokedex/full/201.png");
		pokemon.agregarEvolucion(evolucion);	
		pokemonService.guardar(pokemon);
		return pokemon;
	}
}
