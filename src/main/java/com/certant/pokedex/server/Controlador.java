package com.certant.pokedex.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.certant.pokedex.model.Habilidad;
import com.certant.pokedex.model.Pokemon;
import com.certant.pokedex.model.PokemonBase;
import com.certant.pokedex.model.PokemonEvolucion;
import com.certant.pokedex.model.Tipo;
import com.certant.pokedex.repositories.RepositorioHabilidades;
import com.certant.pokedex.repositories.RepositorioPokemones;
import com.certant.pokedex.repositories.RepositorioTipos;
import com.certant.pokedex.service.IEjemplarService;
import com.certant.pokedex.service.IHabilidadService;
import com.certant.pokedex.service.IPokemonService;
import com.certant.pokedex.service.ITipoService;
import com.opencsv.exceptions.CsvValidationException;

@Controller
public class Controlador {
	
	@Autowired
	private IPokemonService pokemonService;
	@Autowired
	private IHabilidadService habilidadService;
	@Autowired
	private ITipoService tipoService;
	@Autowired
	private IEjemplarService ejemplarService;
	
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
		model.addAttribute("evoluciones", RepositorioPokemones.obtenerEvolucionesOrdenadas(pokemon));
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
	public String agregarHabilidadPost(Pokemon pokemon, int habilidadId) {
		pokemon = pokemonService.buscar(pokemon);
		Habilidad habilidad = new Habilidad(habilidadId);
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
	public String eliminarHabilidadPost(Pokemon pokemon, int habilidadId) {
		pokemon = pokemonService.buscar(pokemon);
		Habilidad habilidad = new Habilidad(habilidadId);
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
	public String agregarTipoPost(Pokemon pokemon, int tipoId) {
		pokemon = pokemonService.buscar(pokemon);
		Tipo tipo = new Tipo(tipoId);
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
	public String eliminarTipoPost(Pokemon pokemon, int tipoId) {
		pokemon = pokemonService.buscar(pokemon);
		Tipo tipo = new Tipo(tipoId);
		tipo = tipoService.buscar(tipo);
		pokemon.quitarTipo(tipo);
		pokemonService.guardar(pokemon);
		return "redirect:/pokemones/{id}";
	}
	
	@GetMapping("/pokemones/{id}/agregarEvolucion")
	public String agregarEvolucionGet(Pokemon pokemon, PokemonEvolucion evolucion, Model model) {
		model.addAttribute("pokemon", pokemon);
		model.addAttribute("evolucion", evolucion);
		return "EvolucionAgregar";
	}
	
	@PostMapping("/pokemones/{id}/agregarEvolucion")
	public String agregarEvolucionPost(Pokemon pokemon, PokemonEvolucion evolucion) {
		pokemon = pokemonService.buscar(pokemon);
		pokemon.agregarEvolucion(evolucion);	
		pokemonService.guardar(pokemon);
		return "redirect:/pokemones/{id}";
	}
	
	@GetMapping("/pokemones/agregar")
	public String agregarPokemonGet(PokemonBase pokemon, Model model) {
		model.addAttribute("pokemon", pokemon);
		return "PokemonAgregar";
	}
	
	@PostMapping("/pokemones/agregar")
	public String agregarPokemonPost(PokemonBase pokemon) {
		pokemonService.guardar(pokemon);
		return "redirect:/pokemones";
	}
	
	@GetMapping("/ejemplares")
	public String ejemplaresGet() {
		return "Ejemplares";
	}
	
	@GetMapping("/pokemones/{id}/editar")
	public String editarGet(Pokemon pokemon, Model model) {
		pokemon = pokemonService.buscar(pokemon);
		model.addAttribute("pokemon", pokemon);
		return "PokemonEditar";
	}
	
	@PostMapping("/pokemones/{id}/editar")
	public String editarPost(Pokemon pokemon) {
		String nombre = pokemon.getNombre();
		String descripcion = pokemon.getDescripcion();
		int nivelRequerido = pokemon.getNivelRequerido();
		pokemon = pokemonService.buscar(pokemon);
		pokemon.editarDatos(nombre, descripcion, nivelRequerido);
		pokemonService.guardar(pokemon);
		return "redirect:/pokemones/{id}";
	}
}
