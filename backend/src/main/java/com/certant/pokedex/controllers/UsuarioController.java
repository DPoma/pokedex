package com.certant.pokedex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@GetMapping("/login")
	public String login() {
		return "EXITO";
	}
	
	/*
	@PostMapping("/login")
	public Usuario login(@RequestBody Usuario usuarioJSON) {
		Usuario usuario = usuarioService.buscarPorUsername(usuarioJSON.getUsername());
		if(usuario != null) {	
			if (usuario.passwordVerificado(usuarioJSON.getPassword()))
				return usuario;
		}
		return null;
	}
	*/
	
	@PostMapping("/registro")
	public Usuario registro(@RequestBody Usuario usuario) {
		if(usuarioService.existePorUsername(usuario.getUsername()))
			return null;
		usuario.setPassword(usuario.encriptarPassword(usuario.getPassword()));;
		usuarioService.guardar(usuario);
		return usuario;

	}
	
	@GetMapping("/usuarios")
	public List<Usuario> obtenerUsuarios() {
		return usuarioService.obtenerTodos();
	}
}
