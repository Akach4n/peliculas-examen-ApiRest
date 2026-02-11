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

import com.ipartek.auxiliar.Auxiliar;
import com.ipartek.modelo.Usuario;
import com.ipartek.servicios.UsuarioServicio;

@RestController
@RequestMapping("/api/v1/usuarios/")
public class UsuarioControladorREST {

	@Autowired
	private UsuarioServicio usuarioServ;

	@PostMapping("validar/")
	public ResponseEntity<?> validarUsuario(@RequestBody Usuario obj_usuario) {
		Usuario usrTemp = usuarioServ.obtenerUsuarioPorNombre(obj_usuario.getUser());

		if (usrTemp != null && usrTemp.getId() > 0) {
			if ((usrTemp.getRol().getNombre()).equalsIgnoreCase("ADMIN")
					|| (usrTemp.getRol().getNombre()).equalsIgnoreCase("USUARIO")) {

				String passParaVerificarTemp = obj_usuario.getPass() + usrTemp.getSalt();
				String passParaVerificar = Auxiliar.hashear(passParaVerificarTemp);
				
				String resultado=usuarioServ.validarUsuario(obj_usuario.getUser(), passParaVerificar);
				if (!resultado.isEmpty()) {
					return ResponseEntity.status(HttpStatus.OK).body(resultado);
				}
				else {
					return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Usuario());
				}
			}else {
				System.out.println("UNAUTORIZED");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ErrorUsuario1");
			}		
		} else if (usrTemp != null && usrTemp.getId() == 0){
			System.out.println("BAD REQUEST");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Usuario());
		} else {
			System.out.println("ESTA VACIO");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Usuario());
		}
		

	}
	
	
	@PutMapping("bloquear/")
	public ResponseEntity<?> bloquearUsuario(@RequestBody Usuario usu) {
		
		String nombrePosibleBloqueado=usu.getUser();
		
		Usuario usuarioTemp = usuarioServ.obtenerUsuarioPorNombre(nombrePosibleBloqueado);

		try {
			if (usuarioTemp.getId()>0) {
				Usuario usuBloqueado=usuarioServ.bloquearUsuario(usuarioTemp);
				
				return  ResponseEntity.ok(usuBloqueado);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuarioTemp);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ErrorUsuario3");
		}
	}
	
	
	@PostMapping("")
	public ResponseEntity<?> guardarUsuario(@RequestBody Usuario obj_usuario) {
		
		try {
			Usuario usuTemp=usuarioServ.insertarUsuario(obj_usuario);
			if (usuTemp.getId()>0) {
				return  ResponseEntity.ok(usuTemp);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ErrorUsuario4");
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ErrorUsuario5");
		}

	}
	
	@GetMapping("")
	public ResponseEntity<?> obtenerTodosUsuarios() {
		
		try {
			List<Usuario> lista = usuarioServ.obtenerTodosUsuarios();
			
			if (lista.size()>0) {
				return  ResponseEntity.ok(lista);
			}else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(lista);
			}	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ErrorUsuario6");
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> obtenerUsuarioPorID(@PathVariable Integer id) {
		
		try {
			Usuario usu = usuarioServ.obtenerUsuarioPorId(id);
			
			if ( usu!=null && usu.getId()>0) {
				return  ResponseEntity.ok(usu);
			}else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Usuario());
			}	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ErrorUsuario7");
		}
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> borrarUsuario(@PathVariable Integer id) {
		
		try {
			 usuarioServ.borrarUsuario(id);

			 return  ResponseEntity.ok().body(new Usuario());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ErrorUsuario8");
		}
	}
	

	@PutMapping("")
	public ResponseEntity<?> modificarUsuario(@RequestBody Usuario obj_usuario) {
		
		try {
			 Usuario temp=usuarioServ.modificarUsuario(obj_usuario);

			 return  ResponseEntity.ok().body(temp);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ErrorUsuario3");
		}
	}
	

}
