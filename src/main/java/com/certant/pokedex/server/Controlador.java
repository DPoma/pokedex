package com.certant.pokedex.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.certant.pokedex.handlers.DataAccessObject;
import com.certant.pokedex.handlers.FileHandler;
import com.certant.pokedex.model.Pokemon;
import com.certant.pokedex.repositories.RepositorioPokemones;
import com.certant.pokedex.service.PokemonService;
import com.opencsv.exceptions.CsvValidationException;

@Controller
public class Controlador {
	
	@Autowired
	private PokemonService pokemonService;
	
	@GetMapping("/")
	public String inicio() throws CsvValidationException {
		//FileHandler.extraerDatos();
		//List<Pokemon> pokemones = RepositorioPokemones.obtenerBases();
		//for(Pokemon pokemon : pokemones)
			//pokemonService.guardar(pokemon);
		return "Home";
	}
	
	@GetMapping("/pokemones")
	public String pokemones(Model model, Pokemon pokemon) {
		List<Pokemon> pokemones = pokemonService.pokemones();
		model.addAttribute("pokemones", pokemones);
		return "Pokemones";
	}
	
	@PostMapping("/pokemones")
	public String pokemones(Pokemon pokemon) {
		pokemon = pokemonService.buscar(pokemon);
		return "redirect:/pokemones/" + pokemon.getId();
	}
	
	@GetMapping("/pokemones/{id}")
	public String pokemon(Pokemon pokemon, Model model) {
		pokemon = pokemonService.buscar(pokemon);
		model.addAttribute("pokemon", pokemon);
		return "Pokemon";
	}
	
	@GetMapping("/ejemplares")
	public String ejemplares() {
		return "Ejemplares";
	}
}
