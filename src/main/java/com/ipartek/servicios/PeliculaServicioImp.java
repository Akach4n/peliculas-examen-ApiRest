package com.ipartek.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.modelo.Pelicula;
import com.ipartek.repositorio.PeliculaRepositorio;

@Service
public class PeliculaServicioImp implements PeliculaServicio {

	@Autowired
	private PeliculaRepositorio peliRepo;

	@Override
	public List<Pelicula> obtenerPeliculas() {
		try {
			return peliRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Pelicula obtenerPeliculaPorId(Integer id) {
		try {
			return peliRepo.findById(id).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Pelicula insertarPelicula(Pelicula peli) {

		try {
			if (peli.getId() == null) {
				return peliRepo.save(peli);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Pelicula modificarPelicula(Pelicula peli) {
		try {
			if (peli.getId() != null && peliRepo.existsById(peli.getId())) {
				return peliRepo.save(peli);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void borrarPelicula(Integer id) {
		try {
			if (id != null && peliRepo.existsById(id)) {
				peliRepo.deleteById(id);
			}
		} catch (Exception e) {
			
		}
	}

}
