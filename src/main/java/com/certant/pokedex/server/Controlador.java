package com.certant.pokedex.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
