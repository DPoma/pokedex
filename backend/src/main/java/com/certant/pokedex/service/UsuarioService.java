package com.certant.pokedex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certant.pokedex.dao.UsuarioDAO;
import com.certant.pokedex.model.Usuario;

@Service("userDetailsService")
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	/*
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDAO.findByUsername(username);
		if(usuario == null)
			throw new UsernameNotFoundException(username);
		ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new User(usuario.getUsername(), usuario.getPassword(), roles);
	}
	*/
	
	@Transactional(readOnly = true)
	public List<Usuario> obtenerTodos() {
		return (List<Usuario>)usuarioDAO.findAll();
	}

	@Transactional
	public void guardar(Usuario usuario) {
		usuarioDAO.save(usuario);
		
	}

	@Transactional
	public void eliminar(Usuario usuario) {
		usuarioDAO.delete(usuario);
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorId(int id) {
		return usuarioDAO.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public Usuario buscarPorUsername(String username) {
		return usuarioDAO.findByUsername(username);
	}
}
