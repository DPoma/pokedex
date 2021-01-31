package com.certant.pokedex.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="Ejemplares")
public class Ejemplar implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Pokemon_id")
	private Pokemon pokemon;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Usuario_id")
	private Usuario usuario;
	
	private int nivelActual;
	
	public Ejemplar() {
		
	}
	
	public Ejemplar(Pokemon pokemon, Usuario usuario, int nivelActual) {
		this.pokemon = pokemon;
		this.usuario = usuario;
		this.nivelActual = nivelActual;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pokemon getPokemon() {
		return pokemon;
	}
	
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getNivelActual() {
		return nivelActual;
	}
	
	public void setNivelActual(int nivelActual) {
		this.nivelActual = nivelActual;
	}
}
