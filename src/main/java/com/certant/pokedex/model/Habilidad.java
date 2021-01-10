package com.certant.pokedex.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Habilidades")
public class Habilidad {

	@Id
	@GeneratedValue
	private int id;
	
	private String nombre;
	
	private String descripcion;
	
	@ManyToMany(mappedBy="habilidades")
	private List<Pokemon> pokemones;
	
	public Habilidad() {
		
	}
	
	public Habilidad(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Pokemon> getPokemones() {
		return pokemones;
	}

	public void setPokemones(List<Pokemon> pokemones) {
		this.pokemones = pokemones;
	}	
}
