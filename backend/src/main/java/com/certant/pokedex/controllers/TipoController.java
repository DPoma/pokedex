package com.certant.pokedex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certant.pokedex.model.Pokemon;
import com.certant.pokedex.model.Tipo;
import com.certant.pokedex.service.PokemonService;
import com.certant.pokedex.service.TipoService;

@RestController
@RequestMapping("/api")
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
	public Pokemon agregarHabilidadPost(@PathVariable int pokemonId, @RequestBody Tipo tipoJSON) {
		Pokemon pokemon = pokemonService.buscarPorId(pokemonId);
		Tipo tipo = tipoService.buscarPorId(tipoJSON.getId());
		pokemon.agregarTipo(tipo);
		pokemonService.guardar(pokemon);
		return pokemon;
	}
	
	@GetMapping("/pokemones/{pokemonId}/tipos/eliminar")
	public List<Tipo> eliminarHabilidadGet(@PathVariable int pokemonId) {
		Pokemon pokemon = pokemonService.buscarPorId(pokemonId);
		return pokemon.getTipos();
	}
		
	@PostMapping("/pokemones/{pokemonId}/tipos/eliminar")
	public Pokemon eliminarHabilidadPost(@PathVariable int pokemonId, @RequestBody Tipo tipoJSON) {
		Pokemon pokemon = pokemonService.buscarPorId(pokemonId);
		Tipo tipo = tipoService.buscarPorId(tipoJSON.getId());
		pokemon.eliminarTipo(tipo);
		pokemonService.guardar(pokemon);
		return pokemon;
	}
}
