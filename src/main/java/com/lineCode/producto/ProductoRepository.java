package com.lineCode.producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	@Query("SELECT p FROM Producto p WHERE"
			+ " CONCAT(p.id, p.nombre, p.precio)"
			+ " LIKE %?1%")
	public List<Producto> finAll(String palabraClave);
	
	

}
