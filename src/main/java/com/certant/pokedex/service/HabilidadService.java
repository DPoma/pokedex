package com.certant.pokedex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certant.pokedex.dao.HabilidadDAO;
import com.certant.pokedex.model.Habilidad;

@Service
public class HabilidadService implements IHabilidadService {

	@Autowired
	private HabilidadDAO habilidadDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Habilidad> obtener() {
		return (List<Habilidad>)habilidadDAO.findAll();
	}

	@Override
	@Transactional
	public void guardar(Habilidad habilidad) {
		habilidadDAO.save(habilidad);
		
	}

	@Override
	@Transactional
	public void eliminar(Habilidad habilidad) {
		habilidadDAO.delete(habilidad);
	}

	@Override
	@Transactional(readOnly = true)
	public Habilidad buscar(Habilidad habilidad) {
		return habilidadDAO.findById(habilidad.getId()).orElse(null);
	}
}
