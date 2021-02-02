package com.certant.pokedex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certant.pokedex.dao.EjemplarDAO;
import com.certant.pokedex.model.Ejemplar;

@Service
public class EjemplarService implements IEjemplarService {

	@Autowired
	private EjemplarDAO ejemplarDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Ejemplar> obtener() {
		return (List<Ejemplar>)ejemplarDAO.findAll();
	}

	@Override
	@Transactional
	public void guardar(Ejemplar ejemplar) {
		ejemplarDAO.save(ejemplar);
		
	}

	@Override
	@Transactional
	public void eliminar(Ejemplar ejemplar) {
		ejemplarDAO.delete(ejemplar);
	}

	@Override
	@Transactional(readOnly = true)
	public Ejemplar buscar(Ejemplar ejemplar) {
		return ejemplarDAO.findById(ejemplar.getId()).orElse(null);
	}
}
