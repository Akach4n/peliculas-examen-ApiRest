package com.ipartek.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.modelo.Rol;
import com.ipartek.servicios.RolServicio;

@RestController
@RequestMapping("/api/v1/roles/")
@CrossOrigin(origins = "*")
public class RolControladorREST {

	@Autowired
	private RolServicio rolServ;

	@GetMapping("")
	public ResponseEntity<?> ontenerTodosRoles() {
		try {
			List<Rol> listaRoles = rolServ.obtenerTodosRoles();

			if (listaRoles != null && listaRoles.size() > 0) {
				return ResponseEntity.ok(listaRoles);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Rol());
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ErrorPaises1");
		}
	}
	
	

}
