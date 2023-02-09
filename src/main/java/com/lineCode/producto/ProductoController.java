package com.lineCode.producto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lineCode.categoria.Categoria;
import com.lineCode.categoria.CategoriaRepository;

@Controller
public class ProductoController {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired 
	private CategoriaRepository categoriaRepository;
	
	@Autowired 
	private ProductoServicio productoServicio;
	
	@RequestMapping("/")
	public String verPaginaInicio(Model model, @Param("palabraClave") String palabraClave) {
		List<Producto> listaProductos = productoServicio.listAll(palabraClave);
		model.addAttribute("listaProductos", listaProductos);
		model.addAttribute("palabraClave", palabraClave);
		return "productos";
	}
	
	@GetMapping("productos/nuevo")
	public String mostrarFormularioDeNuevoProducto(Model model) {
		List<Categoria> listaCategorias = categoriaRepository.findAll();
		
		model.addAttribute("producto", new Producto());
		model.addAttribute("listaCategorias", listaCategorias);
		return "producto_formulario";
	}
	
	@PostMapping("/productos/guardar")
	public String guardarProducto(@Validated Producto producto, BindingResult bindingResult,RedirectAttributes redirect, HttpServletRequest request, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("producto", producto);
			return "producto_formulario";
		}
		String[] detallesIDs = request.getParameterValues("detallesID");
		String[] detallesNombres = request.getParameterValues("detallesNombre");
		String[] detallesValores = request.getParameterValues("detallesValor");
		
		for(int i = 0; i<detallesNombres.length; i++) {
			if(detallesIDs != null && detallesIDs.length > 0) {
				producto.setDetalles(Integer.valueOf(detallesIDs[i]), detallesNombres[i], detallesValores[i]);
			}else {
				producto.añadirDetalles(detallesNombres[i], detallesValores[i]);
			}
		}
		
		productoRepository.save(producto);
		redirect.addFlashAttribute("msgExito", "El contacto ha sido agregado con exito");
		return "redirect:/";
	}
	
	@GetMapping("/productos")
	public String listarProductos(Model model) {
		List<Producto> listaProductos = productoRepository.findAll();
		model.addAttribute("listaProductos", listaProductos);
		return"productos";		
	}
	
	@GetMapping("/productos/editar/{id}")
	public String mostrarFormuladioDeEditarProducto(@PathVariable("id") Integer id, Model model) {
		Producto producto = productoRepository.findById(id).get();
		model.addAttribute("producto", producto);
		
		List<Categoria> listaCategorias = categoriaRepository.findAll();
		model.addAttribute("listaCategorias", listaCategorias);	
		return "producto_formulario";
	}
	
	@GetMapping("productos/eliminar/{id}")
	public String eliminarProducto(@PathVariable("id") Integer id, Model model, RedirectAttributes redirect) {
		productoRepository.deleteById(id);
		redirect.addFlashAttribute("msgExito", "El contacto ha sido eliminado correctamente");
		return "redirect:/productos";
		
	}
	
}
