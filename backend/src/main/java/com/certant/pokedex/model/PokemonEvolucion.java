package com.certant.pokedex.model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("NO")
public class PokemonEvolucion extends Pokemon implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private PokemonBase pokemonBase = new PokemonBase();;

	public PokemonEvolucion() {
		
	}
 	
	public PokemonEvolucion(String nombre, String descripcion, int nivel, String imagen) {
		super(nombre, descripcion, nivel, imagen);
	}

	public PokemonBase getPokemonBase() {
		return pokemonBase;
	}

	public void setPokemonBase(PokemonBase pokemonBase) {
		this.pokemonBase = pokemonBase;
	}
	
	@Override
	public List<PokemonEvolucion> obtenerEvoluciones() {
		return pokemonBase.getEvoluciones();
	}
	
	@Override
	public boolean esEvolucionDisponible() {
		return this.pokemonBase.getNombre() == null;
	}
	
	@Override
	public void agregarEvolucion(Pokemon pokemon) {
		((PokemonEvolucion)pokemon).setPokemonBase(this.pokemonBase);
		this.pokemonBase.getEvoluciones().add((PokemonEvolucion)pokemon);
	}
	
	public Pokemon obtenerDetalles() {
		PokemonBase pokemon = new PokemonBase(this.getNombre(), this.getDescripcion(), this.getNivelRequerido(), this.getImagen());
		pokemon.setId(this.getId());
		pokemon.setHabilidades(this.getHabilidades());
		pokemon.setTipos(this.getTipos());
		this.getPokemonBase().agregarEvolucionBase();
		pokemon.setEvoluciones(this.getPokemonBase().getEvoluciones());
		return pokemon;
	}
}
