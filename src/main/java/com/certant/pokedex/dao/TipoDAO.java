package com.certant.pokedex.dao;

import org.springframework.data.repository.CrudRepository;

import com.certant.pokedex.model.Tipo;

public interface TipoDAO extends CrudRepository<Tipo, Integer> {

}
