package com.certant.pokedex.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.certant.pokedex.handlers.ListHandler;

@Entity
@Table(name = "Pokemones")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="BASE", discriminatorType = DiscriminatorType.STRING)
public class Pokemon implements Serializable{
	
    private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	
	private String nombre;
	
	private String descripcion;
	
	private int nivelRequerido;
	
	private String imagen;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Pokemones_Tipos", 
	joinColumns = {@JoinColumn(name = "Pokemon_Id")}, 
	inverseJoinColumns = {@JoinColumn(name = "Tipo_Id")})
	private List<Tipo> tipos;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Pokemones_Habilidades", 
	joinColumns = {@JoinColumn(name = "Pokemon_Id")}, 
	inverseJoinColumns = {@JoinColumn(name = "Habilidad_Id")})
	private List<Habilidad> habilidades;
	
	public Pokemon() {
		
	}
	
	public Pokemon(String nombre, String descripcion, int nivelRequerido) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nivelRequerido = nivelRequerido;
		this.tipos = new ArrayList<Tipo>();
		this.habilidades = new ArrayList<Habilidad>();
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

	public int getNivel() {
		return nivelRequerido;
	}
	public void setNivel(int nivel) {
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
	
	public boolean tieneEsaHabilidad(Habilidad habilidad) {
		return ListHandler.cumpleCondicionElemento(habilidades, habilidadDelPokemon-> 
		habilidadDelPokemon.getNombre()
		.equals(habilidad.getNombre()));
	}
	
	public boolean esTipo(Tipo tipo) {
		return ListHandler.cumpleCondicionElemento(tipos, tipoDelPokemon -> 
		tipoDelPokemon.getNombre()
		.equals(tipo.getNombre()));
	}
	
	public List<?> obtenerEvoluciones() {
		if(this instanceof PokemonBase)
			return ((PokemonBase)this).getEvoluciones();	
		else
			return ((PokemonEvolucion)this).getPokemonBase().getEvoluciones();
	}
	
	public Habilidad buscarHabilidad(String nombre) {
		return ListHandler.buscarElemento(habilidades, habilidad -> habilidad.getNombre().equals(nombre));
	}
	
	public Tipo buscarTipo(String nombre) {
		return ListHandler.buscarElemento(tipos, tipo -> tipo.getNombre().equals(nombre));
	}
	
	public void agregarHabilidad(Habilidad habilidad) {
		this.habilidades.add(habilidad);
	}
	
	public void quitarHabilidad(String nombreHabilidad) {
		this.habilidades.remove(this.buscarHabilidad(nombreHabilidad));
	}
	
	public void agregarTipo(Tipo tipo) {
		this.tipos.add(tipo);
	}
	
	public void quitarTipo(String nombreTipo) {
		this.tipos.remove(this.buscarTipo(nombreTipo));
	}
	
	public void modificarDatos(String nombre, String descripcion, String nivel) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nivelRequerido = Integer.parseInt(nivel);
	}
	
	public boolean estaDisponible() {
		return false;
	}

	public void agregarEvolucion(Pokemon pokemon) {
		
	}

	public boolean tieneEsaEvolucion(String nombre) {
		return false;
	}
	
	public Pokemon buscarEvolucion(String nombre) {
		return null;
	}
}
