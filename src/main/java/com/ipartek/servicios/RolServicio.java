package com.ipartek.servicios;

import java.util.List;

import com.ipartek.modelo.Rol;

public interface RolServicio {

	public List<Rol> obtenerTodosRoles();
	public Rol obtenerRolPorId(Integer id);
	public Rol insertarRol(Rol rol);
	public Rol modificarRol(Rol rol);
	public void borrarRol(Integer id);
	
}
