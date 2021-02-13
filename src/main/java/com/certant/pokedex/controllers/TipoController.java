package com.certant.pokedex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.certant.pokedex.model.Pokemon;
import com.certant.pokedex.model.Tipo;
import com.certant.pokedex.service.PokemonService;
import com.certant.pokedex.service.TipoService;

@RestController
public class TipoController {
	
	@Autowired
	private TipoService tipoService;
	
	@Autowired
	private PokemonService pokemonService;
	
	@GetMapping("/pokemones/{pokemonId}/tipos/agregar")
	public List<Tipo> agregarTipo(@PathVariable int pokemonId) {
		Pokemon pokemon = pokemonService.buscarPorId(pokemonId);
		return tipoService.disponiblesPara(pokemon);
	}
	
	@PostMapping("/pokemones/{pokemonId}/tipos/agregar")
	public Pokemon agregarHabilidadPost(@PathVariable int pokemonId, @RequestParam int tipoId) {
		Pokemon pokemon = pokemonService.buscarPorId(pokemonId);
		Tipo tipo = tipoService.buscarPorId(tipoId);
		pokemon.agregarTipo(tipo);
		pokemonService.guardar(pokemon);
		return pokemon;
	}
	
	@GetMapping("/pokemones/{pokemonId}/tipos/eliminar")
	public List<Tipo> eliminarHabilidadGet(@PathVariable int pokemonId) {
		Pokemon pokemon = pokemonService.buscarPorId(pokemonId);
		return pokemon.getTipos();
	}
		
	@DeleteMapping("/pokemones/{pokemonId}/tipos/eliminar")
	public Pokemon eliminarHabilidadPost(@PathVariable int pokemonId, @RequestParam int tipoId) {
		Pokemon pokemon = pokemonService.buscarPorId(pokemonId);
		Tipo tipo = tipoService.buscarPorId(tipoId);
		pokemon.eliminarTipo(tipo);
		pokemonService.guardar(pokemon);
		return pokemon;
	}
}
