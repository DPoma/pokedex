package com.certant.pokedex.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certant.pokedex.dao.TipoDAO;
import com.certant.pokedex.model.Pokemon;
import com.certant.pokedex.model.Tipo;

@Service
public class TipoService {

	@Autowired
	private TipoDAO tipoDAO;
	
	@Transactional(readOnly = true)
	public List<Tipo> obtenerTodos() {
		return (List<Tipo>)tipoDAO.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Tipo> disponiblesPara(Pokemon pokemon) {
		return obtenerTodos().stream().filter(tipo -> !pokemon.esTipo(tipo)).collect(Collectors.toList());
	}
	
	@Transactional
	public void guardar(Tipo tipo) {
		tipoDAO.save(tipo);
		
	}

	@Transactional
	public void eliminar(Tipo tipo) {
		tipoDAO.delete(tipo);
	}

	@Transactional(readOnly = true)
	public Tipo buscarPorId(int id) {
		return tipoDAO.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public Tipo buscarPorNombre(String nombre) {
		return tipoDAO.findByNombre(nombre);
	}
}
