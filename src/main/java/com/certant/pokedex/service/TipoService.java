package com.certant.pokedex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certant.pokedex.dao.TipoDAO;
import com.certant.pokedex.model.Tipo;

@Service
public class TipoService implements ITipoService {

	@Autowired
	private TipoDAO tipoDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Tipo> obtener() {
		return (List<Tipo>)tipoDAO.findAll();
	}

	@Override
	@Transactional
	public void guardar(Tipo tipo) {
		tipoDAO.save(tipo);
		
	}

	@Override
	@Transactional
	public void eliminar(Tipo tipo) {
		tipoDAO.delete(tipo);
	}

	@Override
	@Transactional(readOnly = true)
	public Tipo buscar(Tipo tipo) {
		return tipoDAO.findById(tipo.getId()).orElse(null);
	}
}
