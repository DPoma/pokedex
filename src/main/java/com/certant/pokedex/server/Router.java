package com.certant.pokedex.server;

import java.util.HashSet;
import java.util.Set;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
	
	static Set<String> publicRoutes = new HashSet<String>();

	public static Boolean isPublic(String route){
		return publicRoutes.contains(route);
	}
	
	private static void setPublicRoutes(Set<String> publicRoutes) {
		publicRoutes.add("/login");
		publicRoutes.add("/loginFailed");
		publicRoutes.add("/signup");
		publicRoutes.add("/signupFailed");
	}
	
	public static void configure() {
		
		HandlebarsTemplateEngine engine = HandlebarsBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();

		Spark.staticFiles.location("/public");
		setPublicRoutes(publicRoutes);
		Spark.before(SessionHandler.allowed());
		
 		Controller pokemonController = new Controller();

 		Spark.get("/home", pokemonController::homeGet, engine);
 		Spark.get("/login", pokemonController::loginGet, engine);
		Spark.get("/loginFailed", pokemonController::loginFailedGet, engine);
 		Spark.get("/signup", pokemonController::signupGet, engine);
 		Spark.get("/signupFailed", pokemonController::signupFailedGet, engine);
 		Spark.get("/pokemones/agregarEjemplar", pokemonController::agregarEjemplarGet,engine);
 		Spark.get("/pokemones/agregarPokemon", pokemonController::agregarPokemonGet,engine);
 		Spark.get("/pokemones/agregarPokemonFailed", pokemonController::agregarPokemonFailedGet,engine);
		Spark.get("/pokemones/:name/agregarEvolucion", pokemonController::agregarEvolucionGet,engine);
		Spark.get("/pokemones/:name/agregarEvolucionFailed", pokemonController::agregarEvolucionFailedGet,engine);
		Spark.get("/pokemones/:name/agregarHabilidad", pokemonController::agregarHabilidadGet,engine);
		Spark.get("/pokemones/:name/agregarTipo", pokemonController::agregarTipoGet,engine);
		Spark.get("/pokemones/:name/modificarDatos", pokemonController::modificarDatosGet,engine);
		Spark.get("/pokemones/:name/modificarDatosFailed", pokemonController::modificarDatosFailedGet,engine);
		Spark.get("/pokemones/:name/quitarHabilidad", pokemonController::quitarHabilidadGet,engine);
		Spark.get("/pokemones/:name/quitarTipo", pokemonController::quitarTipoGet,engine);
		Spark.get("/pokemones", pokemonController::verPokemonesGet,engine);
		Spark.get("/pokemones/verPokemon", pokemonController::verPokemonGet,engine);
		Spark.get("/pokemones/verEjemplar", pokemonController::verEjemplarGet,engine);

  		Spark.post("/login", pokemonController::loginPost);
 		Spark.post("/signup", pokemonController::signupPost);
 		Spark.post("/pokemones/agregarPokemon", pokemonController::agregarPokemonPost,engine);
		Spark.post("/pokemones/:name/agregarEvolucion", pokemonController::agregarEvolucionPost,engine);
		Spark.post("/pokemones/:name/agregarHabilidad", pokemonController::agregarHabilidadPost,engine);
		Spark.post("/pokemones/:name/agregarTipo", pokemonController::agregarTipoPost,engine);
		Spark.post("/pokemones/:name/modificarDatos", pokemonController::modificarDatosPost,engine);
		Spark.post("/pokemones/:name/quitarHabilidad", pokemonController::quitarHabilidadPost,engine);
		Spark.post("/pokemones/:name/quitarTipo", pokemonController::quitarTipoPost,engine);
		Spark.post("/pokemones", pokemonController::verPokemonesPost,engine);
		Spark.post("/pokemones/verEjemplar", pokemonController::verEjemplarPost,engine);
	}
}