package com.certant.pokedex.dao;

import org.springframework.data.repository.CrudRepository;

import com.certant.pokedex.model.Ejemplar;

public interface EjemplarDAO extends CrudRepository<Ejemplar, Integer> {

}
