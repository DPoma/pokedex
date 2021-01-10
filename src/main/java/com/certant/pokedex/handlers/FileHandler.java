package com.certant.pokedex.handlers;

import java.io.FileReader;
import java.io.IOException;

import com.certant.pokedex.model.*;
import com.certant.pokedex.repositories.RepositorioHabilidades;
import com.certant.pokedex.repositories.RepositorioPokemones;
import com.certant.pokedex.repositories.RepositorioTipos;
import com.opencsv.CSVReader;

public class FileHandler {

	public static void extraerDatos() {
		leerHabilidades("./src/main/resources/data/Habilidades.csv");
		leerTipos("./src/main/resources/data/Tipos.csv");
		leerPokemones("./src/main/resources/data/Pokemones.csv");
		leerHabilidadesDePokemon("./src/main/resources/data/PokemonesHabilidades.csv");
		leerEvolucionesDePokemon("./src/main/resources/data/PokemonesEvoluciones.csv");
	}
	
	public static void leerHabilidades(String path) {
		try {
			CSVReader reader = crearReader(path);
			String [] linea;
			while ((linea = reader.readNext()) != null) {
				String nombre = linea[0];
				String descripcion = linea[1];
				Habilidad habilidad = new Habilidad(nombre, descripcion);
				RepositorioHabilidades.agregar(habilidad);
			}
			reader.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static void leerTipos(String path) {
		try {
			CSVReader reader = crearReader(path);
			String [] linea;
			while ((linea = reader.readNext()) != null) {
				String nombre = linea[0];
				Tipo tipo = new Tipo(nombre);
				RepositorioTipos.agregar(tipo);
			}
			reader.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static void leerPokemones(String path) {
		try {
			CSVReader reader = crearReader(path);
			String [] linea;
			while ((linea = reader.readNext()) != null) {
				Pokemon pokemon;
				String nombre = linea[0];
				String descripcion = linea[1];
				int nivel = Integer.parseInt(linea[2]);
				String imagenUrl = linea[3];
				if(nivel == 1) {
					pokemon = new PokemonBase(nombre, descripcion, nivel);
					pokemon.setImagen(imagenUrl);
				}

				else {
					pokemon = new PokemonEvolucion(nombre, descripcion, nivel);
					pokemon.setImagen(imagenUrl);
				}

				for(int i = 4; i < linea.length; i++)
				{
					String nombreTipo = linea[i];
					Tipo tipo = RepositorioTipos.buscar(nombreTipo);
					pokemon.getTipos().add(tipo);
				}
				RepositorioPokemones.agregar(pokemon);
			}
			reader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void leerEvolucionesDePokemon(String path) {	
		try {
			CSVReader reader = crearReader(path);
			String [] linea;
			while ((linea = reader.readNext()) != null) {
				String nombrePokemonBase = linea[0];
				Pokemon pokemonBase = RepositorioPokemones.buscar(nombrePokemonBase);
				for(int i = 1; i < linea.length; i++)
				{
					String nombrePokemonEvolucion = linea[i];
					Pokemon pokemonEvolucion = RepositorioPokemones.buscar(nombrePokemonEvolucion);
					((PokemonBase)pokemonBase).getEvoluciones().add((PokemonEvolucion)pokemonEvolucion);
					((PokemonEvolucion)pokemonEvolucion).setPokemonBase((PokemonBase)pokemonBase);
				}
			}
			reader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void leerHabilidadesDePokemon(String path) {
		try {
			CSVReader reader = crearReader(path);
			String [] linea;
			while ((linea = reader.readNext()) != null) {
				String nombrePokemon = linea[0];
				Pokemon pokemon = RepositorioPokemones.buscar(nombrePokemon);
				for(int i = 1; i < linea.length; i++)
				{
					String nombreHabilidad = linea[i];
					Habilidad habilidad = RepositorioHabilidades.buscar(nombreHabilidad);
					pokemon.getHabilidades().add(habilidad);
				}
			}
			reader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public static CSVReader crearReader(String path) throws IOException {
		return new CSVReader(new FileReader(path), ';');
	}
}
