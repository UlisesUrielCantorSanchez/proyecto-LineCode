package com.lineCode.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServicio {
	
	@Autowired
	private ProductoRepository productoRepositorio;
	
	public List<Producto> listAll(String palabraClave){
		if(palabraClave != null) {
			return productoRepositorio.finAll(palabraClave);
		}
		return productoRepositorio.findAll();
	}

}
