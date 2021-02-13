package com.certant.pokedex.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Pokemones")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="BASE", discriminatorType = DiscriminatorType.STRING)
public class Pokemon implements Serializable{
	
    private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nombre;
	
	private String descripcion;
	
	private int nivelRequerido;
	
	private String imagen;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Pokemones_Habilidades", 
	joinColumns = {@JoinColumn(name = "Pokemon_Id")}, 
	inverseJoinColumns = {@JoinColumn(name = "Habilidad_Id")})
	private List<Habilidad> habilidades = new ArrayList<Habilidad>();
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Pokemones_Tipos", 
	joinColumns = {@JoinColumn(name = "Pokemon_Id")}, 
	inverseJoinColumns = {@JoinColumn(name = "Tipo_Id")})
	private List<Tipo> tipos = new ArrayList<Tipo>();
	
	public Pokemon() {
		
	}
	
	public Pokemon(String nombre, String descripcion, int nivelRequerido, String imagen) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nivelRequerido = nivelRequerido;
		this.imagen = imagen;
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

	public int getNivelRequerido() {
		return nivelRequerido;
	}
	public void setNivelRequerido(int nivel) {
		this.nivelRequerido = nivel;
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<Tipo> getTipos() {
		return tipos;
	}

	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}

	public List<Habilidad> getHabilidades() {
		return habilidades;
	}
	
	public void setHabilidades(List<Habilidad> habilidades) {
		this.habilidades = habilidades;
	}
	
	public void agregarHabilidad(Habilidad habilidad) {
		habilidades.add(habilidad);
	}
	
	public void eliminarHabilidad(Habilidad habilidad) {
		habilidades.remove(habilidad);
	}
	
	public void agregarTipo(Tipo tipo) {
		tipos.add(tipo);
	}
	
	public void eliminarTipo(Tipo tipo) {
		tipos.remove(tipo);
	}
	
	public boolean tieneHabilidad(Habilidad habilidad) {
		return habilidades.contains(habilidad);
	}
	
	public boolean esTipo(Tipo tipo) {
		return tipos.contains(tipo);
	}
	
	public List<?> obtenerEvoluciones() {
		return null;
	}
	
	public void agregarEvolucion(Pokemon pokemon) {
		
	}
		
	public boolean esEvolucionDisponible() {
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + id;
		result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		result = prime * result + nivelRequerido;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Pokemon other = (Pokemon) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id != other.id)
			return false;
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (nivelRequerido != other.nivelRequerido)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
}
