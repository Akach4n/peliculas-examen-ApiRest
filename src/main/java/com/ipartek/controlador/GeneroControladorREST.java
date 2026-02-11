package com.ipartek.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.modelo.Genero;
import com.ipartek.servicios.GeneroServicio;


@RestController
@RequestMapping("/api/v1/generos/")
@CrossOrigin(origins = "*")
public class GeneroControladorREST {

	@Autowired
	private GeneroServicio generoServ;
	
	@GetMapping
	public ResponseEntity<?> obtenerGeneros() {
		
		try {
			List<Genero> listaGeneros = generoServ.obtenerGeneros();
			
			if (listaGeneros.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			
			return ResponseEntity.ok(listaGeneros);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error al obtener todas los géneros");
		}
	}
	
	
	
}
