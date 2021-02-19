package com.certant.pokedex.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("SI")
public class PokemonBase extends Pokemon implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
	@OneToMany(mappedBy = "pokemonBase", cascade = CascadeType.ALL)
	private List<PokemonEvolucion> evoluciones = new ArrayList<PokemonEvolucion>();
	
	public PokemonBase() {
		
	}
	
	public PokemonBase(String nombre, String descripcion, int nivel, String imagen) {
		super(nombre, descripcion, nivel, imagen);		
	}

	public List<PokemonEvolucion> getEvoluciones() {
		return evoluciones;
	}
	
	public void setEvoluciones(List<PokemonEvolucion> evoluciones) {
		this.evoluciones = evoluciones;
	}
	
	@Override
	public List<PokemonEvolucion> obtenerEvoluciones() {
		return evoluciones;
	}
	
	@Override
	public void agregarEvolucion(Pokemon pokemon) {
		PokemonEvolucion evolucion = (PokemonEvolucion)pokemon;
		evolucion.setPokemonBase(this);
		evoluciones.add(evolucion);
	}
	
	public Pokemon obtenerDetalles() {
		this.agregarEvolucionBase();
		return this;
	}
	
	public void agregarEvolucionBase() {
		PokemonEvolucion evolucion = new PokemonEvolucion(this.getNombre(), this.getDescripcion(), this.getNivelRequerido(), this.getImagen()); 	
		evoluciones.add(evolucion);
		Collections.sort(evoluciones, Comparator.comparing(Pokemon::getNivelRequerido));
	}
}
