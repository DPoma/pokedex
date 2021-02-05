package com.certant.pokedex.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.certant.pokedex.handlers.FileHandler;
import com.certant.pokedex.model.Ejemplar;
import com.certant.pokedex.model.Habilidad;
import com.certant.pokedex.model.Pokemon;
import com.certant.pokedex.model.PokemonBase;
import com.certant.pokedex.model.PokemonEvolucion;
import com.certant.pokedex.model.Tipo;
import com.certant.pokedex.model.Usuario;
import com.certant.pokedex.repositories.RepositorioEjemplares;
import com.certant.pokedex.repositories.RepositorioHabilidades;
import com.certant.pokedex.repositories.RepositorioPokemones;
import com.certant.pokedex.repositories.RepositorioTipos;
import com.certant.pokedex.repositories.RepositorioUsuarios;
import com.certant.pokedex.service.EjemplarService;
import com.certant.pokedex.service.HabilidadService;
import com.certant.pokedex.service.PokemonService;
import com.certant.pokedex.service.TipoService;
import com.certant.pokedex.service.UsuarioService;
import com.opencsv.exceptions.CsvValidationException;

@Controller
public class Controlador {
	
	@Autowired
	private PokemonService pokemonService;
	@Autowired
	private HabilidadService habilidadService;
	@Autowired
	private TipoService tipoService;
	@Autowired
	private EjemplarService ejemplarService;
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/login")
	public String login() throws CsvValidationException {
		if(tipoService.obtener().isEmpty()) {
			FileHandler.extraerDatos();
			List<Pokemon> pokemones = RepositorioPokemones.obtenerBases();
			for(Pokemon pokemon : pokemones)
				pokemonService.guardar(pokemon);	
		}
		return "Login";
	}
	
	@GetMapping("/registro")
	public String registroGet(Usuario usuario, Model model) {
		model.addAttribute("usuario", usuario);
		return "Registro";
	}
	
	@PostMapping("/registro")
	public String registroPost(Usuario usuario) {
		usuarioService.guardar(usuario);
		return "redirect:/login";
	}
	
	@GetMapping("/")
	public String inicio(Model model) {
		String nombreUsuario = SecurityContextHolder.getContext().getAuthentication().getName();	
		model.addAttribute("username", nombreUsuario);
		return "Home";
	}
	
	@GetMapping("/pokemones")
	public String pokemonesGet(Model model, Pokemon pokemon) {
		List<Pokemon> pokemones = pokemonService.obtener();
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
	
	@GetMapping("/pokemones/agregar")
	public String agregarPokemonGet(PokemonBase pokemon, Model model) {
		model.addAttribute("pokemon", pokemon);
		return "PokemonAgregar";
	}
	
	@PostMapping("/pokemones/agregar")
	public String agregarPokemonPost(PokemonBase pokemon) {
		String imagen = pokemon.getImagen();
		if(imagen == null || imagen.trim().isEmpty())
			pokemon.setImagen("https://assets.pokemon.com/assets/cms2/img/pokedex/full/201.png");
		pokemonService.guardar(pokemon);
		return "redirect:/pokemones";
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
	
	@GetMapping("/pokemones/{id}/habilidades/agregar")
	public String agregarHabilidadGet(Pokemon pokemon, Model model) {
		pokemon = pokemonService.buscar(pokemon);
		RepositorioHabilidades.setHabilidades(habilidadService.obtener());
		model.addAttribute("pokemon", pokemon);
		model.addAttribute("habilidades", RepositorioHabilidades.disponiblesPara(pokemon));
		return "HabilidadAgregar";
	}
	
	@PostMapping("/pokemones/{id}/habilidades/agregar")
	public String agregarHabilidadPost(Pokemon pokemon, int habilidadId) {
		pokemon = pokemonService.buscar(pokemon);
		Habilidad habilidad = new Habilidad(habilidadId);
		habilidad = habilidadService.buscar(habilidad);
		pokemon.agregarHabilidad(habilidad);
		pokemonService.guardar(pokemon);
		return "redirect:/pokemones/{id}";
	}
	
	@GetMapping("/pokemones/{id}/tipos/agregar")
	public String agregarTipoGet(Pokemon pokemon, Model model) {
		pokemon = pokemonService.buscar(pokemon);
		RepositorioTipos.setTipos(tipoService.obtener());
		model.addAttribute("pokemon", pokemon);
		model.addAttribute("tipos", RepositorioTipos.disponiblesPara(pokemon));
		return "TipoAgregar";
	}
	
	@PostMapping("/pokemones/{id}/tipos/agregar")
	public String agregarTipoPost(Pokemon pokemon, int tipoId) {
		pokemon = pokemonService.buscar(pokemon);
		Tipo tipo = new Tipo(tipoId);
		tipo = tipoService.buscar(tipo);
		pokemon.agregarTipo(tipo);
		pokemonService.guardar(pokemon);
		return "redirect:/pokemones/{id}";
	}
	
	@GetMapping("/pokemones/{id}/evoluciones/agregar")
	public String agregarEvolucionGet(Pokemon pokemon, PokemonEvolucion evolucion, Model model) {
		model.addAttribute("pokemon", pokemon);
		model.addAttribute("evolucion", evolucion);
		return "EvolucionAgregar";
	}
	
	@PostMapping("/pokemones/{id}/evoluciones/agregar")
	public String agregarEvolucionPost(Pokemon pokemon, PokemonEvolucion evolucion) {
		pokemon = pokemonService.buscar(pokemon);
		pokemon.agregarEvolucion(evolucion);	
		pokemonService.guardar(pokemon);
		return "redirect:/pokemones/{id}";
	}
		
	@GetMapping("/pokemones/{id}/habilidades/eliminar")
	public String eliminarHabilidadGet(Pokemon pokemon, Model model) {
		pokemon = pokemonService.buscar(pokemon);
		model.addAttribute("pokemon", pokemon);
		return "HabilidadEliminar";
	}
	
	@PostMapping("/pokemones/{id}/habilidades/eliminar")
	public String eliminarHabilidadPost(Pokemon pokemon, int habilidadId) {
		pokemon = pokemonService.buscar(pokemon);
		Habilidad habilidad = new Habilidad(habilidadId);
		habilidad = habilidadService.buscar(habilidad);
		pokemon.quitarHabilidad(habilidad);
		pokemonService.guardar(pokemon);
		return "redirect:/pokemones/{id}";
	}
	
	@GetMapping("/pokemones/{id}/tipos/eliminar")
	public String eliminarTipoGet(Pokemon pokemon, Model model) {
		pokemon = pokemonService.buscar(pokemon);
		model.addAttribute("pokemon", pokemon);
		return "TipoEliminar";
	}
	
	@PostMapping("/pokemones/{id}/tipos/eliminar")
	public String eliminarTipoPost(Pokemon pokemon, int tipoId) {
		pokemon = pokemonService.buscar(pokemon);
		Tipo tipo = new Tipo(tipoId);
		tipo = tipoService.buscar(tipo);
		pokemon.quitarTipo(tipo);
		pokemonService.guardar(pokemon);
		return "redirect:/pokemones/{id}";
	}

	@GetMapping("/ejemplares")
	public String ejemplaresGet(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();	
		RepositorioEjemplares.setEjemplares(ejemplarService.obtener());
		model.addAttribute("usuario", username);
		model.addAttribute("ejemplares", RepositorioEjemplares.obtenerDeUsuario(username));
		return "Ejemplares";
	}
	
	@GetMapping("/ejemplares/agregar")
	public String ejemplarAgregarGet(Pokemon pokemon, Model model) {
		model.addAttribute("pokemon", pokemon);
		model.addAttribute("pokemones", pokemonService.obtener());
		return "EjemplarAgregar";
	}
	
	@PostMapping("/ejemplares/agregar")
	public String ejemplarAgregarPost(Pokemon pokemon) {
		int nivelActual = pokemon.getNivelRequerido();
		pokemon = pokemonService.buscar(pokemon);
		RepositorioUsuarios.setUsuarios(usuarioService.obtener());
		String nombreUsuario = SecurityContextHolder.getContext().getAuthentication().getName();	
		Usuario usuario = RepositorioUsuarios.buscar(nombreUsuario);
		Ejemplar ejemplar = new Ejemplar(pokemon, usuario, nivelActual);
		ejemplarService.guardar(ejemplar);
		return "redirect:/ejemplares";
	}
}
