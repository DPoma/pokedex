package com.certant.pokedex.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.certant.pokedex.handlers.DataAccessObject;
import com.certant.pokedex.handlers.Sesion;
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

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class Controller {
	
	public ModelAndView homeGet(Request req, Response res){
		Map<String, Object> model = new HashMap<>();
		model.put("nombreUsuario", Sesion.getUsuario().getName());
		return new ModelAndView(model, "Home.hbs");
	}
	
	public ModelAndView loginGet(Request req, Response res){
		DataAccessObject.obtenerUsuarios();
		return new ModelAndView(null, "Login.hbs");
	}
	
	public Void loginPost(Request req, Response res){
		String username = req.queryParams("username");
		String password = req.queryParams("password");		
		if(RepositorioUsuarios.loguear(username, password)) {
			Usuario usuario = RepositorioUsuarios.buscar(username);
			Sesion.iniciarSesion(usuario);
			res.redirect("/home");
		}			
		else
			res.redirect("/loginFailed");
		return null;	
	}

	public ModelAndView signupGet(Request req, Response res) {
		DataAccessObject.obtenerUsuarios();
		return new ModelAndView(null, "Signup.hbs");
	}
	
	public Void signupPost(Request req, Response res) {
		String name = req.queryParams("name");
		String username = req.queryParams("username");
		String password = req.queryParams("password");
		if(RepositorioUsuarios.existe(username))
			res.redirect("/signupFailed");
		else {
			Usuario usuario = new Usuario(name, username, password);
			DataAccessObject.persistirElemento(usuario);
			res.redirect("/login");
		}
		return null;
	}
	
	public ModelAndView loginFailedGet(Request req, Response res) {	
		return new ModelAndView(null, "LoginFailed.hbs");
	}
	
	public ModelAndView signupFailedGet(Request req, Response res) {	
		return new ModelAndView(null, "SignupFailed.hbs");
	}

	public ModelAndView agregarHabilidadGet(Request req, Response res){
		DataAccessObject.obtenerPokemones();
		DataAccessObject.obtenerHabilidades();
		String nombrePokemon = req.params("name");
		Pokemon pokemon = RepositorioPokemones.buscar(nombrePokemon);
		Map<String, Object> model = new HashMap<>();
		model.put("nombrePokemon", nombrePokemon);
		model.put("habilidades", RepositorioHabilidades.disponiblesPara(pokemon));
		return new ModelAndView(model, "AgregarHabilidad.hbs");
	}
	
	public ModelAndView agregarHabilidadPost(Request req, Response res){
		String nombrePokemon = req.params("name");
		Pokemon pokemon = RepositorioPokemones.buscar(nombrePokemon);
		String nombreHabilidad = req.queryParams("nombreHabilidad");
		Habilidad habilidad = RepositorioHabilidades.buscar(nombreHabilidad);
		pokemon.agregarHabilidad(habilidad);
		DataAccessObject.persistirElemento(pokemon);
		return verPokemones(pokemon);
	}
	
	public ModelAndView agregarTipoGet(Request req, Response res){
		DataAccessObject.obtenerPokemones();
		DataAccessObject.obtenerTipos();
		String nombrePokemon = req.params("name");
		Pokemon pokemon = RepositorioPokemones.buscar(nombrePokemon);
		Map<String, Object> model = new HashMap<>();
		model.put("nombrePokemon", nombrePokemon);
		model.put("tipos", RepositorioTipos.disponiblesPara(pokemon));
		return new ModelAndView(model, "AgregarTipo.hbs");
	}
	
	public ModelAndView agregarTipoPost(Request req, Response res){
		String nombrePokemon = req.params("name");
		Pokemon pokemon = RepositorioPokemones.buscar(nombrePokemon);
		String nombreTipo = req.queryParams("nombreTipo");
		Tipo tipo = RepositorioTipos.buscar(nombreTipo);
		pokemon.agregarTipo(tipo);
		DataAccessObject.persistirElemento(pokemon);
		return verPokemones(pokemon);
	}
	
	public ModelAndView agregarPokemonGet(Request req, Response res){
		DataAccessObject.obtenerPokemones();
		return new ModelAndView(null, "AgregarPokemon.hbs");
	}
	
	public Pokemon crearPokemon(Request req, Response res, boolean pokemonEsBase) {
		String nombre = req.queryParams("nombrePokemon");
		String descripcion = req.queryParams("descripcionPokemon");
		int nivel = Integer.parseInt(req.queryParams("nivelRequeridoPokemon"));
		String imagen = req.queryParams("imagenPokemon");
		if(imagen.isEmpty())
			imagen = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/201.png";
		Pokemon pokemon;
		if(pokemonEsBase)
			 pokemon = new PokemonBase(nombre, descripcion, nivel);
		else
			pokemon = new PokemonEvolucion(nombre, descripcion, nivel);
		pokemon.setImagen(imagen);	
		return pokemon;
	}
	
	public ModelAndView agregarPokemonPost(Request req, Response res){
		String nombrePokemon = req.queryParams("nombrePokemon");
		if(RepositorioPokemones.existe(nombrePokemon)) {
			res.redirect("/pokemones/agregarPokemonFailed");
			return null;
		}
		Pokemon pokemon = crearPokemon(req, res, true);
		DataAccessObject.persistirElemento(pokemon);
		DataAccessObject.obtenerPokemones();
		return verPokemones(pokemon);
	}
	
	public ModelAndView agregarEvolucionGet(Request req, Response res){	
		DataAccessObject.obtenerPokemones();
		String nombrePokemon = req.params("name");
		Map<String, Object> model = new HashMap<>();
		model.put("nombrePokemon", nombrePokemon);
		return new ModelAndView(model, "AgregarEvolucion.hbs");
	}
	
	public ModelAndView agregarEvolucionPost(Request req, Response res) {
		String nombrePokemonBase = req.params("name");
		String nombrePokemonEvolucion = req.queryParams("nombrePokemon");
		if(RepositorioPokemones.existe(nombrePokemonEvolucion)) {
			res.redirect("/pokemones/"+nombrePokemonBase+"/agregarEvolucionFailed");
			return null;
		}
		Pokemon pokemonBase = RepositorioPokemones.buscar(nombrePokemonBase);
		Pokemon pokemonEvolucion = crearPokemon(req, res, false);
		pokemonBase.agregarEvolucion(pokemonEvolucion);
		DataAccessObject.persistirElemento(pokemonBase);
		DataAccessObject.obtenerPokemones();
		return verPokemones(pokemonEvolucion);
	}
	
	public ModelAndView agregarEvolucionFailedGet(Request req, Response res) {
		return new ModelAndView(null, "AgregarEvolucionFailed.hbs");
	}
	
	public ModelAndView agregarPokemonFailedGet(Request req, Response res) {
		return new ModelAndView(null, "AgregarPokemonFailed.hbs");
	}
	
	public ModelAndView agregarEjemplarGet(Request req, Response res){
		Map<String, List<Pokemon>> model = new HashMap<>();
		DataAccessObject.obtenerPokemones();
		model.put("pokemones", RepositorioPokemones.getPokemones());
		return new ModelAndView(model, "AgregarEjemplar.hbs");
	}
	
	public ModelAndView modificarDatosGet(Request req, Response res){
		DataAccessObject.obtenerPokemones();
		String nombrePokemon = req.params("name");
		Pokemon pokemon = RepositorioPokemones.buscar(nombrePokemon);
		Map<String, Object> model = new HashMap<>();
		model.put("nombre", pokemon.getNombre());
		model.put("descripcion", pokemon.getDescripcion());
		model.put("nivelRequerido", pokemon.getNivel());
		return new ModelAndView(model, "ModificarDatos.hbs");
	}
	
	public ModelAndView modificarDatosPost(Request req, Response res){
		String nombreAnterior = req.params("name");
		String nombrePokemon = req.queryParams("nombrePokemon");
		if(!nombreAnterior.equals(nombrePokemon) && RepositorioPokemones.existe(nombrePokemon)) {
			res.redirect("/pokemones/"+nombreAnterior+"/modificarDatosFailed");
			return null;
		}
		String descripcionPokemon = req.queryParams("descripcionPokemon");
		String nivelRequeridoPokemon = req.queryParams("nivelRequeridoPokemon");
		Pokemon pokemon = RepositorioPokemones.buscar(nombreAnterior);
		pokemon.modificarDatos(nombrePokemon, descripcionPokemon, nivelRequeridoPokemon);
		DataAccessObject.persistirElemento(pokemon);
		return verPokemones(pokemon);
	}
	
	public ModelAndView modificarDatosFailedGet(Request req, Response res) {
		String nombrePokemon = req.params("name");
		Pokemon pokemon = RepositorioPokemones.buscar(nombrePokemon);
		Map<String, Object> model = new HashMap<>();
		model.put("nombre", pokemon.getNombre());
		model.put("descripcion", pokemon.getDescripcion());
		model.put("nivelRequerido", pokemon.getNivel());
		return new ModelAndView(model, "ModificarDatosFailed.hbs");
	}
	
	public ModelAndView quitarHabilidadGet(Request req, Response res){
		DataAccessObject.obtenerPokemones();
		DataAccessObject.obtenerHabilidades();
		String nombrePokemon = req.params("name");
		Pokemon pokemon = RepositorioPokemones.buscar(nombrePokemon);
		Map<String, Object> model = new HashMap<>();
		model.put("nombrePokemon", nombrePokemon);
		model.put("habilidades", pokemon.getHabilidades());
		return new ModelAndView(model, "QuitarHabilidad.hbs");
	}
		
	public ModelAndView quitarHabilidadPost(Request req, Response res){
		String nombrePokemon = req.params("name");
		Pokemon pokemon = RepositorioPokemones.buscar(nombrePokemon);
		String nombreHabilidad = req.queryParams("nombreHabilidad");
		pokemon.quitarHabilidad(nombreHabilidad);
		DataAccessObject.persistirElemento(pokemon);
		return verPokemones(pokemon);
	}

	public ModelAndView quitarTipoGet(Request req, Response res){
		DataAccessObject.obtenerPokemones();
		DataAccessObject.obtenerTipos();
		String nombrePokemon = req.params("name");
		Pokemon pokemon = RepositorioPokemones.buscar(nombrePokemon);
		Map<String, Object> model = new HashMap<>();
		model.put("nombrePokemon", nombrePokemon);
		model.put("tipos", pokemon.getTipos());
		return new ModelAndView(model, "QuitarTipo.hbs");
	}
	
	public ModelAndView quitarTipoPost(Request req, Response res){
		String nombrePokemon = req.params("name");
		Pokemon pokemon = RepositorioPokemones.buscar(nombrePokemon);
		String nombreTipo = req.queryParams("nombreTipo");
		pokemon.quitarTipo(nombreTipo);
		DataAccessObject.persistirElemento(pokemon);
		return verPokemones(pokemon);
	}
		
	public ModelAndView verEjemplarGet(Request req, Response res){
		return verEjemplares();
	}
	
	public ModelAndView verEjemplarPost(Request req, Response res){
		String nombrePokemon = req.queryParams("nombrePokemon");
		Pokemon pokemon = RepositorioPokemones.buscar(nombrePokemon);
		int nivelActual = Integer.parseInt(req.queryParams("nivelPokemon"));
		Ejemplar ejemplar = new Ejemplar(pokemon, Sesion.getUsuario(), nivelActual);
		DataAccessObject.persistirElemento(ejemplar);
		return verEjemplares();
	}
	
	public ModelAndView verPokemonesGet(Request req, Response res){
		DataAccessObject.obtenerPokemones();
		Map<String, List<Pokemon>> model = new HashMap<>();
		model.put("pokemones", RepositorioPokemones.getPokemones());
		return new ModelAndView(model, "VerPokemones.hbs");
	}
	
	public ModelAndView verPokemonesPost(Request req, Response res) {
		DataAccessObject.obtenerPokemones();
		String nombrePokemon = req.queryParams("nombrePokemon");
		Pokemon pokemon = RepositorioPokemones.buscar(nombrePokemon);
		return verPokemones(pokemon);
	}
	
	public ModelAndView verPokemonGet(Request req, Response res){
		DataAccessObject.obtenerPokemones();
		Map<String, List<Pokemon>> model = new HashMap<>();
		model.put("pokemones", RepositorioPokemones.getPokemones());
		return new ModelAndView(model, "VerPokemon.hbs");
	}
	
	public ModelAndView verPokemones(Pokemon pokemon) {
		Map<String, Object> model = new HashMap<>();		
		model.put("nombre", pokemon.getNombre());
		model.put("descripcion", pokemon.getDescripcion());
		model.put("nivel", pokemon.getNivel());
		model.put("imagen", pokemon.getImagen());
		model.put("habilidades",  pokemon.getHabilidades());
		model.put("tipos",  pokemon.getTipos());
		model.put("evoluciones", RepositorioPokemones.obtenerEvolucionesOrdenadas(pokemon));
		model.put("pokemones", RepositorioPokemones.getPokemones());
		return new ModelAndView(model, "VerPokemon.hbs");
	}
	
	public ModelAndView verEjemplares(){
		DataAccessObject.obtenerEjemplares();
		Map<String, List<Ejemplar>> model = new HashMap<>();
		model.put("ejemplares", RepositorioEjemplares.obtenerDeUsuario(Sesion.getUsuario()));
		return new ModelAndView(model, "VerEjemplar.hbs");
	}
}
