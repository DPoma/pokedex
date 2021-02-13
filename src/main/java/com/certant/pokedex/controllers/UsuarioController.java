package com.certant.pokedex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certant.pokedex.model.Usuario;
import com.certant.pokedex.service.UsuarioService;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<Usuario> obtenerUsuarios() {
		return usuarioService.obtenerTodos();
	}
}
