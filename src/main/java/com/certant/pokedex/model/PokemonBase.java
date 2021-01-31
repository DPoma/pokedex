package com.certant.pokedex.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.certant.pokedex.handlers.ListHandler;

@Entity
@DiscriminatorValue("SI")
public class PokemonBase extends Pokemon implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
	@OneToMany(mappedBy = "pokemonBase", cascade = CascadeType.ALL)
	private List<PokemonEvolucion> evoluciones;
	
	public PokemonBase() {
		this.evoluciones = new ArrayList<PokemonEvolucion>();
	}
	
	public PokemonBase(String nombre, String descripcion, int nivel) {
		super(nombre, descripcion, nivel);
		this.evoluciones = new ArrayList<PokemonEvolucion>();
	}

	public List<PokemonEvolucion> getEvoluciones() {
		return evoluciones;
	}
	
	public void setEvoluciones(List<PokemonEvolucion> evoluciones) {
		this.evoluciones = evoluciones;
	}
	
	@Override
	public void agregarEvolucion(Pokemon pokemon) {
		((PokemonEvolucion)pokemon).setPokemonBase(this);
		evoluciones.add((PokemonEvolucion)pokemon);
	}
	
	@Override
	public boolean tieneEsaEvolucion(String nombrePokemon) {
		return ListHandler.cumpleCondicionElemento(evoluciones, 
				evolucion -> evolucion.getNombre().equals(nombrePokemon));
	}
	
	@Override
	public Pokemon buscarEvolucion(String nombrePokemon) {
		return ListHandler.buscarElemento(evoluciones, 
				evolucion -> evolucion.getNombre().equals(nombrePokemon));
	}
}
