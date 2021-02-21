package com.certant.pokedex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certant.pokedex.model.Usuario;
import com.certant.pokedex.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/login" )
	public boolean loginUsuario() {
		return true;
	}
	
	@PostMapping("/registro")
	public Usuario registrarUsuario(@RequestBody Usuario usuario) {
		if(usuarioService.existePorUsername(usuario.getUsername()))
			return null;
		usuario.setPassword(usuario.encriptarPassword(usuario.getPassword()));;
		usuarioService.guardar(usuario);
		return usuario;
	}
}
