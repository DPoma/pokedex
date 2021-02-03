package com.certant.pokedex.service;

import java.util.List;

import com.certant.pokedex.model.Habilidad;

public interface IHabilidadService {
	
	public List<Habilidad> obtener();
	
	public void guardar(Habilidad habilidad);
	
	public void eliminar(Habilidad habilidad);
	
	public Habilidad buscar(Habilidad habilidad);
}
