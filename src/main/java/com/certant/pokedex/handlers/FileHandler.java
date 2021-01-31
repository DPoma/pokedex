package com.certant.pokedex.handlers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.certant.pokedex.model.*;
import com.certant.pokedex.repositories.RepositorioHabilidades;
import com.certant.pokedex.repositories.RepositorioPokemones;
import com.certant.pokedex.repositories.RepositorioTipos;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class FileHandler {

	public static void extraerDatos() throws CsvValidationException {
		leerHabilidades("./src/main/resources/static/Habilidades.csv");
		leerTipos("./src/main/resources/static/Tipos.csv");
		leerPokemones("./src/main/resources/static/Pokemones.csv");
		leerHabilidadesDePokemon("./src/main/resources/static/PokemonesHabilidades.csv");
		leerEvolucionesDePokemon("./src/main/resources/static/PokemonesEvoluciones.csv");
	}
	
	public static void leerHabilidades(String path) throws CsvValidationException {
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
	
	public static void leerTipos(String path) throws CsvValidationException {
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
	
	public static void leerPokemones(String path) throws CsvValidationException {
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
	
	public static void leerEvolucionesDePokemon(String path) throws CsvValidationException {	
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
	
	public static void leerHabilidadesDePokemon(String path) throws CsvValidationException {
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
		
	public static CSVReader crearReader(String path) throws FileNotFoundException {
		FileReader fileReader = new FileReader(path);
		CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
		return new CSVReaderBuilder(fileReader).withCSVParser(csvParser).build();
	}
}
