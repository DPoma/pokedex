package com.certant.pokedex.handlers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.certant.pokedex.model.Habilidad;
import com.certant.pokedex.model.Pokemon;
import com.certant.pokedex.model.PokemonBase;
import com.certant.pokedex.model.PokemonEvolucion;
import com.certant.pokedex.model.Tipo;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class FileConfig {
	
	private static List<Pokemon> pokemones = new ArrayList<Pokemon>();
	private static List<Habilidad> habilidades = new ArrayList<Habilidad>();
	private static List<Tipo> tipos = new ArrayList<Tipo>();
	
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
				habilidades.add(habilidad);
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
				tipos.add(tipo);
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
				String imagen = linea[3];
				if(nivel == 1)
					pokemon = new PokemonBase(nombre, descripcion, nivel, imagen);
				else
					pokemon = new PokemonEvolucion(nombre, descripcion, nivel, imagen);
				for(int i = 4; i < linea.length; i++)
				{
					String nombreTipo = linea[i];
					Tipo tipo = buscarTipo(nombreTipo);
					pokemon.getTipos().add(tipo);
				}
				pokemones.add(pokemon);
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
				Pokemon pokemonBase = buscarPokemon(nombrePokemonBase);
				for(int i = 1; i < linea.length; i++)
				{
					String nombrePokemonEvolucion = linea[i];
					Pokemon pokemonEvolucion = buscarPokemon(nombrePokemonEvolucion);
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
				Pokemon pokemon = buscarPokemon(nombrePokemon);
				for(int i = 1; i < linea.length; i++)
				{
					String nombreHabilidad = linea[i];
					Habilidad habilidad = buscarHabilidad(nombreHabilidad);
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
	
	public static Pokemon buscarPokemon(String nombre) {
		return pokemones.stream().filter(pokemon -> pokemon.getNombre().equals(nombre)).findFirst().get();
	}
	
	public static Habilidad buscarHabilidad(String nombre) {
		return habilidades.stream().filter(habilidad -> habilidad.getNombre().equals(nombre)).findFirst().get();
	}
	
	public static Tipo buscarTipo(String nombre) {
		return tipos.stream().filter(tipo -> tipo.getNombre().equals(nombre)).findFirst().get();
	}
}
