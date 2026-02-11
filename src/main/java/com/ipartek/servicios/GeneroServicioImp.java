package com.ipartek.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.modelo.Genero;
import com.ipartek.repositorio.GeneroRepositorio;

@Service
public class GeneroServicioImp implements GeneroServicio {

	@Autowired
	private GeneroRepositorio generoRepo;
	
	@Override
	public List<Genero> obtenerGeneros() {
		try {
			return generoRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
