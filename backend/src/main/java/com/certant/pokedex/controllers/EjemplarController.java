package com.certant.pokedex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certant.pokedex.model.Ejemplar;
import com.certant.pokedex.model.Pokemon;
import com.certant.pokedex.model.Usuario;
import com.certant.pokedex.service.EjemplarService;
import com.certant.pokedex.service.PokemonService;
import com.certant.pokedex.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class EjemplarController {
	
	@Autowired
	private EjemplarService ejemplarService;
	@Autowired
	private PokemonService pokemonService;	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/ejemplares")
	public List<Ejemplar> obtenerEjemplaresPost() {
		Usuario usuario = usuarioService.obtenerUsuario();
		return ejemplarService.obtenerDeUsuario(usuario);
	}
	
	@GetMapping("/ejemplares/agregar")
	public List<Pokemon> agregarEjemplarGet() {
		return pokemonService.obtenerTodos();
	}
		
	@PostMapping("/ejemplares/agregar")
	public Ejemplar agregarEjemplarPost(@RequestBody Ejemplar ejemplar) {
		Pokemon pokemon = pokemonService.buscarPorId(ejemplar.getPokemon().getId());
		Usuario usuario = usuarioService.obtenerUsuario();
		ejemplar.setPokemon(pokemon);
		ejemplar.setUsuario(usuario);
		ejemplarService.guardar(ejemplar);
		return ejemplar;
	}
}
