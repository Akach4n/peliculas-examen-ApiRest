package com.ipartek.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.modelo.Pelicula;

@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula, Integer> {

}
