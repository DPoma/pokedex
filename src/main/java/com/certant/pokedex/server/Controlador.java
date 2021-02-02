package com.certant.pokedex.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.certant.pokedex.model.Habilidad;
import com.certant.pokedex.model.Pokemon;
import com.certant.pokedex.model.Tipo;
import com.certant.pokedex.repositories.RepositorioHabilidades;
import com.certant.pokedex.repositories.RepositorioTipos;
import com.certant.pokedex.service.HabilidadService;
import com.certant.pokedex.service.PokemonService;
import com.certant.pokedex.service.TipoService;
import com.opencsv.exceptions.CsvValidationException;

@Controller
public class Controlador {
	
	@Autowired
	private PokemonService pokemonService;
	@Autowired
	private HabilidadService habilidadService;
	@Autowired
	private TipoService tipoService;
	
	@GetMapping("/")
	public String inicio() throws CsvValidationException {
		//FileHandler.extraerDatos();
		//List<Pokemon> pokemones = RepositorioPokemones.obtenerBases();
		//for(Pokemon pokemon : pokemones)
			//pokemonService.guardar(pokemon);
		return "Home";
	}
	
	@GetMapping("/pokemones")
	public String pokemonesGet(Model model, Pokemon pokemon) {
		List<Pokemon> pokemones = pokemonService.pokemones();
		model.addAttribute("pokemones", pokemones);
		return "Pokemones";
	}
	
	@GetMapping("/pokemones/{id}")
	public String pokemonGet(Pokemon pokemon, Model model) {
		pokemon = pokemonService.buscar(pokemon);
		model.addAttribute("pokemon", pokemon);
		return "Pokemon";
	}
	
	@GetMapping("/pokemones/{id}/agregarHabilidad")
	public String agregarHabilidadGet(Pokemon pokemon, Model model) {
		pokemon = pokemonService.buscar(pokemon);
		RepositorioHabilidades.setHabilidades(habilidadService.habilidades());
		model.addAttribute("pokemon", pokemon);
		model.addAttribute("habilidades", RepositorioHabilidades.disponiblesPara(pokemon));
		return "HabilidadAgregar";
	}
	
	@PostMapping("/pokemones/{id}/agregarHabilidad")
	public String agregarHabilidadPost(Pokemon pokemon, Habilidad habilidad) {
		pokemon = pokemonService.buscar(pokemon);
		habilidad = habilidadService.buscar(habilidad);
		pokemon.agregarHabilidad(habilidad);
		pokemonService.guardar(pokemon);
		return "redirect:/pokemones/{id}";
	}
	
	@GetMapping("/pokemones/{id}/eliminarHabilidad")
	public String eliminarHabilidadGet(Pokemon pokemon, Model model) {
		pokemon = pokemonService.buscar(pokemon);
		model.addAttribute("pokemon", pokemon);
		return "HabilidadEliminar";
	}
	
	@PostMapping("/pokemones/{id}/eliminarHabilidad")
	public String eliminarHabilidadPost(Pokemon pokemon, Habilidad habilidad) {
		pokemon = pokemonService.buscar(pokemon);
		habilidad = habilidadService.buscar(habilidad);
		pokemon.quitarHabilidad(habilidad);
		pokemonService.guardar(pokemon);
		return "redirect:/pokemones/{id}";
	}
	
	@GetMapping("/pokemones/{id}/agregarTipo")
	public String agregarTipoGet(Pokemon pokemon, Model model) {
		pokemon = pokemonService.buscar(pokemon);
		RepositorioTipos.setTipos(tipoService.tipos());
		model.addAttribute("pokemon", pokemon);
		model.addAttribute("tipos", RepositorioTipos.disponiblesPara(pokemon));
		return "TipoAgregar";
	}
	
	@PostMapping("/pokemones/{id}/agregarTipo")
	public String agregarTipoPost(Pokemon pokemon, Tipo tipo) {
		pokemon = pokemonService.buscar(pokemon);
		tipo = tipoService.buscar(tipo);
		pokemon.agregarTipo(tipo);
		pokemonService.guardar(pokemon);
		return "redirect:/pokemones/{id}";
	}
	
	@GetMapping("/pokemones/{id}/eliminarTipo")
	public String eliminarTipoGet(Pokemon pokemon, Model model) {
		pokemon = pokemonService.buscar(pokemon);
		model.addAttribute("pokemon", pokemon);
		return "TipoEliminar";
	}
	
	@PostMapping("/pokemones/{id}/eliminarTipo")
	public String eliminarTipoPost(Pokemon pokemon, Tipo tipo) {
		pokemon = pokemonService.buscar(pokemon);
		tipo = tipoService.buscar(tipo);
		pokemon.quitarTipo(tipo);
		pokemonService.guardar(pokemon);
		return "redirect:/pokemones/{id}";
	}
	
	@GetMapping("/pokemones/{id}/agregarEvolucion")
	public String agregarEvolucionGet(Pokemon pokemon, Model model) {
		pokemon = pokemonService.buscar(pokemon);
		model.addAttribute("pokemon", pokemon);
		return "EvolucionAgregar";
	}
	
	@PostMapping("/pokemones/{id}/agregarEvolucion")
	public String agregarEvolucionPost(Pokemon pokemon, Pokemon evolucion) {
		pokemon = pokemonService.buscar(pokemon);
		evolucion = pokemonService.buscar(evolucion);
		pokemon.agregarEvolucion(evolucion);
		pokemonService.guardar(pokemon);
		return "redirect:/pokemones/{id}";
	}
	
	@GetMapping("/ejemplares")
	public String ejemplares() {
		return "Ejemplares";
	}
}
