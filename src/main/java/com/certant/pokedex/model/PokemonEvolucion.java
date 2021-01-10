package com.certant.pokedex.model;

import javax.persistence.*;

import com.certant.pokedex.handlers.ListHandler;

@Entity
@DiscriminatorValue("NO")
public class PokemonEvolucion extends Pokemon {

	@ManyToOne(cascade = CascadeType.ALL)
	private PokemonBase pokemonBase;

	public PokemonEvolucion() {
		pokemonBase = new PokemonBase();
	}
 	
	public PokemonEvolucion(String nombre, String descripcion, int nivel) {
		super(nombre, descripcion, nivel);
		pokemonBase = new PokemonBase();
	}

	public PokemonBase getPokemonBase() {
		return pokemonBase;
	}

	public void setPokemonBase(PokemonBase pokemonBase) {
		this.pokemonBase = pokemonBase;
	}
	
	@Override
	public boolean estaDisponible() {
		return this.pokemonBase.getNombre() == null;
	}
	
	@Override
	public void agregarEvolucion(Pokemon pokemon) {
		((PokemonEvolucion)pokemon).setPokemonBase(this.pokemonBase);
		this.pokemonBase.getEvoluciones().add((PokemonEvolucion)pokemon);
	}
	
	@Override
	public boolean tieneEsaEvolucion(String nombrePokemon) {
		return ListHandler.cumpleCondicionElemento(pokemonBase.getEvoluciones(), 
				evolucion -> evolucion.getNombre().equals(nombrePokemon));
	}
	
	@Override
	public Pokemon buscarEvolucion(String nombrePokemon) {
		return ListHandler.buscarElemento(pokemonBase.getEvoluciones(), 
				evolucion -> evolucion.getNombre().equals(nombrePokemon));
	}
}
