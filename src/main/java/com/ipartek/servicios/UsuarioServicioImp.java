package com.ipartek.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.componente.JwtUtil;
import com.ipartek.modelo.Rol;
import com.ipartek.modelo.Usuario;
import com.ipartek.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioImp implements UsuarioServicio{
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UsuarioRepositorio usuarioRepo;

	@Override
	public List<Usuario> obtenerTodosUsuarios() {
		try {
			return usuarioRepo.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Usuario obtenerUsuarioPorId(Integer id) {
		try {
			return usuarioRepo.findById(id).orElse(new Usuario());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Usuario insertarUsuario(Usuario usu) {
		try {
			if (usu.getId()==0) {
				 return usuarioRepo.save(usu);
			}
			return new Usuario();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Usuario modificarUsuario(Usuario usu) {
		try {
			if (usu.getId()>0) {
				 return usuarioRepo.save(usu);
			}
			return new Usuario();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void borrarUsuario(Integer id) {
		try {
			usuarioRepo.deleteById(id);
		} catch (Exception e) {
			
		}
	}

	@Override
	public Usuario obtenerUsuarioPorNombre(String usu) {
		try {
			return usuarioRepo.findByUser(usu).orElse(new Usuario());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String validarUsuario(String usu, String contra) {
		try {
			Boolean resultado = usuarioRepo.existsByUserAndPass(usu, contra);

			if (resultado) {
				Usuario usuTemp=usuarioRepo.findByUser(usu).orElse(new Usuario());
				return jwtUtil.generateToken(usu, usuTemp.getRol().getNombre());
			} else {
				return "";
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Usuario bloquearUsuario(Usuario usuarioTemp) {
		try {
			Rol rol= new Rol(3, "BLOQUEADO");
			usuarioTemp.setRol(rol);
			return modificarUsuario(usuarioTemp);
		} catch (Exception e) {
			return null;
		}
	}

}
