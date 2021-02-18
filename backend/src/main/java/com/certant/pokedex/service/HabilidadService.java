package com.certant.pokedex.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certant.pokedex.dao.HabilidadDAO;
import com.certant.pokedex.model.Habilidad;
import com.certant.pokedex.model.Pokemon;

@Service
public class HabilidadService {

	@Autowired
	private HabilidadDAO habilidadDAO;
	
	@Transactional(readOnly = true)
	public List<Habilidad> obtenerTodos() {
		return (List<Habilidad>)habilidadDAO.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Habilidad> disponiblesPara(Pokemon pokemon) {
		return obtenerTodos().stream().filter(habilidad -> !pokemon.tieneHabilidad(habilidad)).collect(Collectors.toList());
	}

	@Transactional
	public void guardar(Habilidad habilidad) {
		habilidadDAO.save(habilidad);		
	}

	@Transactional
	public void eliminar(Habilidad habilidad) {
		habilidadDAO.delete(habilidad);
	}

	@Transactional(readOnly = true)
	public Habilidad buscarPorId(int id) {
		return habilidadDAO.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public  Habilidad buscarPorNombre(String nombre) {
		return habilidadDAO.findByNombre(nombre);
	}
}
