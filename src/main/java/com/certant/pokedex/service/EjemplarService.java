package com.certant.pokedex.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certant.pokedex.dao.EjemplarDAO;
import com.certant.pokedex.model.Ejemplar;
import com.certant.pokedex.model.Usuario;

@Service
public class EjemplarService {

	@Autowired
	private EjemplarDAO ejemplarDAO;
	
	@Transactional(readOnly = true)
	public List<Ejemplar> obtenerTodos() {
		return (List<Ejemplar>)ejemplarDAO.findAll();
	}
	
	public List<Ejemplar> obtenerDeUsuario(Usuario usuario) {
		return obtenerTodos().stream().filter(ejemplar -> ejemplar.getUsuario().equals(usuario)).collect(Collectors.toList());
	}

	@Transactional
	public void guardar(Ejemplar ejemplar) {
		ejemplarDAO.save(ejemplar);	
	}

	@Transactional
	public void eliminar(Ejemplar ejemplar) {
		ejemplarDAO.delete(ejemplar);
	}

	@Transactional(readOnly = true)
	public Ejemplar buscarPorId(int id) {
		return ejemplarDAO.findById(id).orElse(null);
	}
}
