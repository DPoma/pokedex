package com.certant.pokedex.model;

import javax.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String username;
	
	private String password;
	
	private String name;
	
	public Usuario()
	{

	}
	
	public Usuario(String name, String user, String password)
	{
		this.username = user;
		this.password = password;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean loginExitoso(String username, String password)
	{
		return this.username.equals(username) && this.password.equals(password);
	}
}
