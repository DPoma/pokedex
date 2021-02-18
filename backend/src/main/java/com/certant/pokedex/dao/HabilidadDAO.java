package com.certant.pokedex.dao;

import org.springframework.data.repository.CrudRepository;

import com.certant.pokedex.model.Habilidad;

public interface HabilidadDAO extends CrudRepository<Habilidad, Integer> {

	public abstract Habilidad findByNombre(String nombre);
}
