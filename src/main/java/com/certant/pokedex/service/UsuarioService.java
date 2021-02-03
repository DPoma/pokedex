package com.certant.pokedex.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certant.pokedex.dao.UsuarioDAO;
import com.certant.pokedex.model.Usuario;

@Service("userDetailsService")
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
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
	
	@Transactional(readOnly = true)
	public List<Usuario> usuarios() {
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
	public Usuario buscar(Usuario usuario) {
		return usuarioDAO.findById(usuario.getId()).orElse(null);
	}
}
