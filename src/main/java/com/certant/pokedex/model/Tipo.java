package com.certant.pokedex.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Tipos")
public class Tipo implements Serializable {
	
    private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	
	private String nombre;
	
	@ManyToMany(mappedBy="tipos")
	private List<Pokemon> pokemones;
	
	public Tipo() {
		
	}

	public Tipo(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Pokemon> getPokemones() {
		return pokemones;
	}

	public void setPokemones(List<Pokemon> pokemones) {
		this.pokemones = pokemones;
	}	
}
