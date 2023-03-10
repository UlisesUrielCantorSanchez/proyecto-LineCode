package com.lineCode.categoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 45, nullable=false,unique=true)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "marca_id")
	private Categoria marca;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Categoria getMarca() {
		return marca;
	}

	public void setMarca(Categoria marca) {
		this.marca = marca;
	}

	public Categoria() {
		super();
	}
	
	public Categoria(Integer id) {
		super();
		this.id = id;
	}

	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Categoria(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Categoria(Integer id, String nombre, Categoria marca) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.marca = marca;
	}

	public Categoria(String nombre, Categoria marca) {
		super();
		this.nombre = nombre;
		this.marca = marca;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	

}
