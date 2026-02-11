package com.ipartek.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.modelo.Rol;
import com.ipartek.repositorio.RolRepositorio;

@Service
public class RolServicioImp implements RolServicio{
	
	@Autowired
	private RolRepositorio rolRepo;

	@Override
	public List<Rol> obtenerTodosRoles() {
		try {
			return rolRepo.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Rol obtenerRolPorId(Integer id) {
		try {
			return rolRepo.findById(id).orElse(new Rol());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Rol insertarRol(Rol rol) {
		try {
			if (rol.getId()==0) {
				 return rolRepo.save(rol);
			}
			return new Rol();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Rol modificarRol(Rol rol) {
		try {
			if (rol.getId()>0) {
				 return rolRepo.save(rol);
			}
			return new Rol();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void borrarRol(Integer id) {
		try {
			rolRepo.deleteById(id);
		} catch (Exception e) {
			
		}
	}

}
