package com.certant.pokedex.handlers;

import java.util.List;

import javax.persistence.EntityManager;

import com.certant.pokedex.model.*;

import com.certant.pokedex.repositories.RepositorioPokemones;

import com.certant.pokedex.service.IPokemonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DataAccessObject {
	
	@Autowired
	private IPokemonService pokemonService;
		
	public void persistirDatos() {
		List<Pokemon> pokemones = RepositorioPokemones.obtenerBases();
		for(Pokemon pokemon : pokemones)
			pokemonService.guardar(pokemon);
	}
			
	public boolean baseDatosEstaVacia() {
		pokemonService.pokemones();
		return RepositorioPokemones.getPokemones().isEmpty();
	}
	
	public static void persistirDatosTest() {
		List<Pokemon> pokemones = RepositorioPokemones.obtenerBases();
		for(Pokemon pokemon : pokemones)
			persistirElementoTest(pokemon);
	}
	
	public static void persistirElementoTest(Object objeto) { 
		EntityManager entityManager = (EntityManager)new Object();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(objeto);
			entityManager.getTransaction().commit();
		}
		catch(Exception ex) {
			entityManager.getTransaction().rollback();
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
	}
}
