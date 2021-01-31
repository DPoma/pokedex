package com.certant.pokedex.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.certant.pokedex.model.Pokemon;
import com.certant.pokedex.repositories.RepositorioPokemones;
import com.certant.pokedex.service.PokemonService;

@Controller
public class Controlador {
	
	@GetMapping("/")
	public String inicio() {
		return "index";
	}
	
}
