package com.certant.pokedex.service;

import java.util.List;

import com.certant.pokedex.model.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> obtener();
	
	public void guardar(Usuario usuario);
	
	public void eliminar(Usuario usuario);
	
	public Usuario buscar(Usuario usuario);
}
