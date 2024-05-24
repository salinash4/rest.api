package py.edu.ucsa.jweb.rest.api.core.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.Priority;
import py.edu.ucsa.jweb.rest.api.core.services.UsuarioService;
import py.edu.ucsa.jweb.rest.api.web.dto.UsuarioDto;

@Service("usuService")
@Priority(0)
public class UsuServiceImpl implements UsuarioService {

	@Override
	public UsuarioDto getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioDto getByUsuario(String usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crear(UsuarioDto dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(UsuarioDto dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UsuarioDto> listar() {
		List<UsuarioDto> usuarios = new ArrayList<>();
		usuarios.add(UsuarioDto.builder().id(4).usuario("jsanchez").clave("123").nombres("JUAN")
				.apellidos("SANCHEZ").email("jsanchez@gmail.com").habilitado(true).fechaCreacion(LocalDateTime.now())
				.build());

		return usuarios;
	}

	@Override
	public void eliminarTodos() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isExisteUsuario(UsuarioDto usuario) {
		// TODO Auto-generated method stub
		return false;
	}

}
