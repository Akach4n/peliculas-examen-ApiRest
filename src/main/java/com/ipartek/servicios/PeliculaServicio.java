package com.ipartek.servicios;

import java.util.List;

import com.ipartek.modelo.Pelicula;

public interface PeliculaServicio {

	List<Pelicula> obtenerPeliculas();

	Pelicula obtenerPeliculaPorId(Integer id);

	Pelicula insertarPelicula(Pelicula peli);

	Pelicula modificarPelicula(Pelicula peli);

	void borrarPelicula(Integer id);

}
