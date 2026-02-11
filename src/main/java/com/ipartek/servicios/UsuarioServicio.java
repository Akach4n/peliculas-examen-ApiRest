package com.ipartek.servicios;

import java.util.List;

import com.ipartek.modelo.Usuario;

public interface UsuarioServicio {

	public List<Usuario> obtenerTodosUsuarios();
	public Usuario obtenerUsuarioPorId(Integer id);
	public Usuario insertarUsuario(Usuario usu);
	public Usuario modificarUsuario(Usuario usu);
	public void borrarUsuario(Integer id);
	
	public Usuario obtenerUsuarioPorNombre(String usu);
	public String validarUsuario(String usu, String contra);//devuelve Token
	public Usuario bloquearUsuario(Usuario usuarioTemp);
}
