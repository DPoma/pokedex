package com.certant.pokedex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.certant.pokedex.model.Ejemplar;
import com.certant.pokedex.model.Pokemon;
import com.certant.pokedex.model.Usuario;
import com.certant.pokedex.service.EjemplarService;
import com.certant.pokedex.service.PokemonService;
import com.certant.pokedex.service.UsuarioService;

@RestController
public class EjemplarController {
	
	@Autowired
	private EjemplarService ejemplarService;
	@Autowired
	private PokemonService pokemonService;	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/ejemplares")
	public List<Ejemplar> ejemplares(Model model) {
		//String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = usuarioService.buscarPorUsername("user");
		return ejemplarService.obtenerDeUsuario(usuario);
	}
	
	@GetMapping("/ejemplares/agregar")
	public List<Pokemon> agregarEjemplarGet() {
		return pokemonService.obtenerTodos();
	}
		
	@PostMapping("/ejemplares/agregar")
	public Ejemplar agregarEjemplar(@RequestParam int pokemonId, @RequestBody Ejemplar ejemplar) {
		Pokemon pokemon = pokemonService.buscarPorId(pokemonId);
		Usuario usuario = usuarioService.buscarPorUsername("user");
		ejemplar.setPokemon(pokemon);
		ejemplar.setUsuario(usuario);
		return ejemplar;
	}
	
}
