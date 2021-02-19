package com.certant.pokedex.model;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="Ejemplares")
public class Ejemplar implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + nivelActual;
		result = prime * result + ((pokemon == null) ? 0 : pokemon.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ejemplar other = (Ejemplar) obj;
		if (id != other.id)
			return false;
		if (nivelActual != other.nivelActual)
			return false;
		if (pokemon == null) {
			if (other.pokemon != null)
				return false;
		} else if (!pokemon.equals(other.pokemon))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
}
