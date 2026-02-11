package com.ipartek.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.modelo.Pelicula;
import com.ipartek.servicios.PeliculaServicio;

@RestController
@RequestMapping("/api/v1/peliculas/")
public class PeliculaControladorREST {

	@Autowired
	private PeliculaServicio peliServ;

	@GetMapping
	public ResponseEntity<?> obtenerPeliculas() {

		try {
			List<Pelicula> listaPeliculas = peliServ.obtenerPeliculas();

			if (listaPeliculas.isEmpty()) {
				return ResponseEntity.noContent().build();
			}

			return ResponseEntity.ok(listaPeliculas);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("Error al obtener todas las peliculas");
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<?> obtenerPeliPorId(@PathVariable Integer id) {

		try {
			if (id == null) {
				return ResponseEntity.badRequest().body("Parámetro incorrecto");
			}
			Pelicula peli = peliServ.obtenerPeliculaPorId(id);
			if (peli == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encotrado pelicula con el ID: " + id);
			}

			return ResponseEntity.ok(peli);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error al obtener la pelicula con ID: " + id);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> insertarPelicula(@RequestBody Pelicula peli) {
	    try {
	    	
	    	if (peli.getId() != null) {
	    		return ResponseEntity.badRequest().body("No se pudo insertar. El ID debe ser nulo.");	    		
	    	}
	        Pelicula nueva = peliServ.insertarPelicula(peli);
	        
	        if (nueva != null) {
	            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
	        } else {
	            return ResponseEntity.badRequest().body("No se pudo insertar. Parámetros incorrectos");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.internalServerError().body("Error al insertar la película");
	    }
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> borrarPelicula(@PathVariable Integer id) {
	    try {
	        Pelicula peli = peliServ.obtenerPeliculaPorId(id);
	        
	        if (peli != null) {
	            peliServ.borrarPelicula(id);
	            return ResponseEntity.ok("Película eliminada correctamente");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe la película con ID: " + id);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.internalServerError().body("Error al borrar la película");
	    }
	}
	
	@PutMapping
	public ResponseEntity<?> modificarPelicula(@RequestBody Pelicula peli) {
	    try {
	        if (peli.getId() == null || peli.getId() == 0) {
	            return ResponseEntity.badRequest().body("Es necesario un ID para modificar la película");
	        }

	        Pelicula modificada = peliServ.modificarPelicula(peli);

	        if (modificada != null) {
	            return ResponseEntity.ok(modificada);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("No se encontró la película con ID: " + peli.getId());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.internalServerError().body("Error al modificar la película");
	    }
	}

}
