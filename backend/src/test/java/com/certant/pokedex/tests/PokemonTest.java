package com.certant.pokedex.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.certant.pokedex.handlers.FileConfig;
import com.certant.pokedex.model.Habilidad;
import com.certant.pokedex.model.Pokemon;
import com.certant.pokedex.model.Tipo;

import com.opencsv.exceptions.CsvValidationException;

public class PokemonTest {

	/*
	@BeforeAll
	public static void cargarDatos() throws CsvValidationException {
		FileHandler.extraerDatos();
		DataAccessObject.persistirDatosTest();
		DataAccessObject.obtenerPokemones();
	}
	
	@Test
	public void todosLosPokemonesDebenSerObtenidosTest() {
		assertEquals(21, RepositorioPokemones.getPokemones().size());
	}
	
	@Test
	public void datosDelPokemonDebenSerObtenidosTest() {
		Pokemon pidgey = RepositorioPokemones.buscar("Pidgey");
		assertEquals("Pidgey", pidgey.getNombre());
		assertEquals(1, pidgey.getNivelRequerido());
	}
	
	@Test
	public void tiposDelPokemonDebenSerObtenidosTest() {
		Pokemon pidgey = RepositorioPokemones.buscar("Pidgey");
		Tipo normal = RepositorioTipos.buscar("Normal");
		Tipo volador = RepositorioTipos.buscar("Volador");
		assertTrue(pidgey.esTipo(normal));
		assertTrue(pidgey.esTipo(volador));
	}
	
	@Test
	public void habilidadesDelPokemonDebenSerObtenidasTest() {
		Pokemon pidgey = RepositorioPokemones.buscar("Pidgey");
		Habilidad vistaLince = RepositorioHabilidades.buscar("VistaLince");
		Habilidad tumbos = RepositorioHabilidades.buscar("Tumbos");
		assertTrue(pidgey.tieneHabilidad(vistaLince));
		assertTrue(pidgey.tieneHabilidad(tumbos));
	}
	
	@Test
	public void evolucionesDelPokemonDebenSerObtenidasTest() {
		Pokemon pidgey = RepositorioPokemones.buscar("Pidgey");
		assertTrue(pidgey.tieneEsaEvolucion("Pidgeotto"));
		assertTrue(pidgey.tieneEsaEvolucion("Pidgeot"));
	}
	
	@Test
	public void datosDeEvolucionesDelPokemonDebenSerObtenidosTest() {
		Pokemon pidgey = RepositorioPokemones.buscar("Pidgey");
		Pokemon pidgeotto = pidgey.buscarEvolucion("Pidgeotto");
		Pokemon pidgeot = pidgey.buscarEvolucion("Pidgeot");
		assertEquals("Pidgeotto", pidgeotto.getNombre());
		assertEquals(18, pidgeotto.getNivelRequerido());
		assertEquals("Pidgeot", pidgeot.getNombre());
		assertEquals(36, pidgeot.getNivelRequerido());
	}
	
	@Test
	public void tiposDeEvolucionesDelPokemonDebenSerObtenidosTest() {
		Pokemon pidgey = RepositorioPokemones.buscar("Pidgey");
		Pokemon pidgeotto = pidgey.buscarEvolucion("Pidgeotto");
		Pokemon pidgeot = pidgey.buscarEvolucion("Pidgeot");
		Tipo normal = RepositorioTipos.buscar("Normal");
		Tipo volador = RepositorioTipos.buscar("Volador");
		assertTrue(pidgeotto.esTipo(normal));
		assertTrue(pidgeotto.esTipo(volador));
		assertTrue(pidgeot.esTipo(normal));
		assertTrue(pidgeot.esTipo(volador));
	}
	
	@Test
	public void nuevoPokemonDebeSerPersistidoYObtenidoTest() {
		Pokemon pikachu = new Pokemon("Pikachu", "Es amarillo", 1);
		DataAccessObject.persistirElementoTest(pikachu);
		DataAccessObject.obtenerPokemones();
		assertTrue(RepositorioPokemones.existe("Pikachu"));
	}
	
	@Test
	public void datosDelPokemonDebenSerActualizadosTest() {
		Pokemon geodude = RepositorioPokemones.buscar("Geodude");
		Habilidad vistaLince = RepositorioHabilidades.buscar("VistaLince");
		Tipo veneno = RepositorioTipos.buscar("Veneno");
		geodude.setNombre("GeodudeVenenoso");
		geodude.setNivelRequerido(20);
		geodude.agregarTipo(veneno);
		geodude.agregarHabilidad(vistaLince);
		DataAccessObject.persistirElementoTest(geodude);
		DataAccessObject.obtenerPokemones();
		Pokemon geodudeVenenoso = RepositorioPokemones.buscar("GeodudeVenenoso");
		assertEquals("GeodudeVenenoso", geodudeVenenoso.getNombre());
		assertEquals(20, geodudeVenenoso.getNivelRequerido());
		assertTrue(geodudeVenenoso.tieneHabilidad(vistaLince));
		assertTrue(geodudeVenenoso.esTipo(veneno));
	}
	*/
}
