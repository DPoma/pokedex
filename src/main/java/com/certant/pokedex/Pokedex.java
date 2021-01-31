package com.certant.pokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.certant.pokedex.handlers.DataAccessObject;
import com.certant.pokedex.handlers.FileHandler;
import com.opencsv.exceptions.CsvValidationException;

@SpringBootApplication
public class Pokedex {
	
	public static void main(String[] args) throws CsvValidationException {
		
		FileHandler.extraerDatos();
		DataAccessObject dao = new DataAccessObject();
		if (dao.baseDatosEstaVacia())		
			dao.persistirDatos();
		//}
		SpringApplication.run(Pokedex.class, args);
	}
}
