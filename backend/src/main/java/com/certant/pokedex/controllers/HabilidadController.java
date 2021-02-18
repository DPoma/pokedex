package com.certant.pokedex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certant.pokedex.model.Habilidad;
import com.certant.pokedex.model.Pokemon;
import com.certant.pokedex.service.HabilidadService;
import com.certant.pokedex.service.PokemonService;

@RestController
@RequestMapping("/api")
public class HabilidadController {
	
	@Autowired
	private HabilidadService habilidadService;
	
	@Autowired
	private PokemonService pokemonService;
	
	@GetMapping("/pokemones/{pokemonId}/habilidades/agregar")
	public List<Habilidad> agregarHabilidadGet(@PathVariable int pokemonId) {
		Pokemon pokemon = pokemonService.buscarPorId(pokemonId);
		return habilidadService.disponiblesPara(pokemon);
	}
	
	@PostMapping("/pokemones/{pokemonId}/habilidades/agregar")
	public Pokemon agregarHabilidadPost(@PathVariable int pokemonId, @RequestBody Habilidad habilidadJSON) {
		Pokemon pokemon = pokemonService.buscarPorId(pokemonId);
		Habilidad habilidad = habilidadService.buscarPorId(habilidadJSON.getId());
		pokemon.agregarHabilidad(habilidad);
		pokemonService.guardar(pokemon);
		return pokemon;
	}
	
	@GetMapping("/pokemones/{pokemonId}/habilidades/eliminar")
	public List<Habilidad> eliminarHabilidadGet(@PathVariable int pokemonId) {
		Pokemon pokemon = pokemonService.buscarPorId(pokemonId);
		return pokemon.getHabilidades();
	}
		
	@PostMapping("/pokemones/{pokemonId}/habilidades/eliminar")
	public Pokemon eliminarHabilidadPost(@PathVariable int pokemonId, @RequestBody Habilidad habilidadJSON) {
		Pokemon pokemon = pokemonService.buscarPorId(pokemonId);
		Habilidad habilidad = habilidadService.buscarPorId(habilidadJSON.getId());
		pokemon.eliminarHabilidad(habilidad);
		pokemonService.guardar(pokemon);
		return pokemon;
	}
}
