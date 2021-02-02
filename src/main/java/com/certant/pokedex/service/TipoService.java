package com.certant.pokedex.service;

import java.util.List;

import com.certant.pokedex.model.Tipo;

public interface TipoService {
	
	public List<Tipo> tipos();
	
	public void guardar(Tipo tipo);
	
	public void eliminar(Tipo tipo);
	
	public Tipo buscar(Tipo tipo);
}
