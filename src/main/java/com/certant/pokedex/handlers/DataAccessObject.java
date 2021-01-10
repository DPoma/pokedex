package com.certant.pokedex.handlers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.certant.pokedex.model.*;
import com.certant.pokedex.repositories.RepositorioEjemplares;
import com.certant.pokedex.repositories.RepositorioHabilidades;
import com.certant.pokedex.repositories.RepositorioPokemones;
import com.certant.pokedex.repositories.RepositorioTipos;
import com.certant.pokedex.repositories.RepositorioUsuarios;
import org.hibernate.cfg.Configuration;

public class DataAccessObject {
	
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db");

	@SuppressWarnings("deprecation")
	private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Tipo.class)
			.addAnnotatedClass(Habilidad.class)
			.addAnnotatedClass(Pokemon.class)
			.addAnnotatedClass(PokemonBase.class)
			.addAnnotatedClass(PokemonEvolucion.class)
			.addAnnotatedClass(Ejemplar.class)
			.addAnnotatedClass(Usuario.class)
			.buildSessionFactory();
	
	public static void persistirDatos() {
		List<Pokemon> pokemones = RepositorioPokemones.obtenerBases();
		for(Pokemon pokemon : pokemones)
			persistirElemento(pokemon);
	}
	
	public static void persistirElemento(Object objeto) { 
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(objeto);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
	}
	
	public static List<?> obtenerElementos(String query) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<?> elementos = (List<?>)entityManager.createQuery(query).getResultList();
		entityManager.close();
		return elementos;
	}
	
	@SuppressWarnings("unchecked")
	public static void obtenerPokemones() {		  
		RepositorioPokemones.setPokemones((List<Pokemon>)obtenerElementos("FROM Pokemon"));
	}
	
	@SuppressWarnings("unchecked")
	public static void obtenerHabilidades() {		  
		RepositorioHabilidades.setHabilidades((List<Habilidad>)obtenerElementos("FROM Habilidad"));
	}
	
	@SuppressWarnings("unchecked")
	public static void obtenerTipos() {		  
		RepositorioTipos.setTipos((List<Tipo>)obtenerElementos("FROM Tipo"));
	}
	
	@SuppressWarnings("unchecked")
	public static void obtenerEjemplares() {		  
		RepositorioEjemplares.setEjemplares((List<Ejemplar>)obtenerElementos("FROM Ejemplar"));
	}
	
	@SuppressWarnings("unchecked")
	public static void obtenerUsuarios() {		  
		RepositorioUsuarios.setUsuarios((List<Usuario>)obtenerElementos("FROM Usuario"));
	}
	
	public static boolean baseDatosEstaVacia() {
		obtenerPokemones();
		return RepositorioPokemones.getPokemones().isEmpty();
	}
	
	public static void persistirDatosTest() {
		List<Pokemon> pokemones = RepositorioPokemones.obtenerBases();
		for(Pokemon pokemon : pokemones)
			persistirElementoTest(pokemon);
	}
	
	public static void persistirElementoTest(Object objeto) { 
		EntityManager entityManager = entityManagerFactory.createEntityManager();
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
