package py.edu.ucsa.jweb.rest.api.web.controllers;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.util.UriComponentsBuilder;

import py.edu.ucsa.jweb.rest.api.core.services.UsuarioService;
import py.edu.ucsa.jweb.rest.api.web.dto.ErrorDto;
import py.edu.ucsa.jweb.rest.api.web.dto.UsuarioDto;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	public static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuService;

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		UsuarioDto dto = usuService.getById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(usuService.listar());
	}

	@PostMapping
	public ResponseEntity<?> crear(@RequestBody UsuarioDto usuario, 
									UriComponentsBuilder uriBuilder) {
		logger.info("Creando el usuario : {}", usuario);
		if (usuService.isExisteUsuario(usuario)) {
			logger.error("Inserción fallida. Ya existe un registro con el usuario {}", 
					usuario.getUsuario());
			return new ResponseEntity<ErrorDto>(
					new ErrorDto("Inserción fallida. Ya existe un registro con el usuario " 
			+ usuario.getUsuario()),
					HttpStatus.CONFLICT);
		}
		usuService.crear(usuario);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/usuarios/{id}")
				.buildAndExpand(usuario.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") Integer id, 
										@RequestBody UsuarioDto usuario) {
		logger.info("Actualizando el usuario con id: {}", id);
		UsuarioDto usuarioBD = usuService.getById(id);
		if (Objects.isNull(usuarioBD)) {
			logger.error("Actualización fallida. No existe el usuario con id {}", 
					id);
			return new ResponseEntity<ErrorDto>(
					new ErrorDto("Actualización fallida. No existe el usuario con id " 
			+ id),
					HttpStatus.NOT_FOUND);
		}
		usuarioBD.setApellidos(usuario.getApellidos());
		usuarioBD.setNombres(usuario.getNombres());
		usuarioBD.setHabilitado(usuario.isHabilitado());
		usuarioBD.setCuentaBloqueada(usuario.isCuentaBloqueada());
		usuarioBD.setEmail(usuario.getEmail());
		usuService.actualizar(usuarioBD);
		return new ResponseEntity<UsuarioDto>(usuarioBD, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) {
		logger.info("Obteniendo y eliminando el usuario con id {}", id);
		UsuarioDto usuario = usuService.getById(id);
		if (usuario == null) {
			logger.error("Eliminación fallida. No existe el usuario con el id {}", id);
			return new ResponseEntity<ErrorDto>(
					new ErrorDto("No existe el usuario con el id " + id),
				HttpStatus.NOT_FOUND);
		}
		usuService.eliminar(id);
		return new ResponseEntity<String>("Usuario eliminado exitosamente", 
				HttpStatus.OK);
	}

}
