package com.certant.pokedex.dao;

import org.springframework.data.repository.CrudRepository;

import com.certant.pokedex.model.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {
	
	Usuario findByUsername(String username);
}
