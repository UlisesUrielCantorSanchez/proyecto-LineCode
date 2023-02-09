package com.lineCode.usuario;

import java.util.HashSet;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	
	@Column(length = 10, nullable = false)
	private String password;
	
	@ManyToMany//(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol",joinColumns = @JoinColumn(name="usuario_id"),
	inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	public void añadirRol(Rol rol) {
		this.roles.add(rol);
	}
	
	public void eliminarRol(Rol rol) {
		this.roles.remove(rol);
	}

	public Usuario() {
		super();
	}
	
	public Usuario(Integer id) {
		super();
		this.id = id;
	}

	public Usuario(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Usuario(String email, String password, Set<Rol> roles) {
		super();
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public Usuario(Integer id, String email, String password, Set<Rol> roles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", password=" + password + ", roles=" + roles + "]";
	}
	
	

}
