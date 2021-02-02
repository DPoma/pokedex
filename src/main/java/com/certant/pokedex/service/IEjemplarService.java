package com.certant.pokedex.service;

import java.util.List;

import com.certant.pokedex.model.Ejemplar;

public interface IEjemplarService {
	
	public List<Ejemplar> obtener();
	
	public void guardar(Ejemplar ejemplar);
	
	public void eliminar(Ejemplar ejemplar);
	
	public Ejemplar buscar(Ejemplar ejemplar);
}
