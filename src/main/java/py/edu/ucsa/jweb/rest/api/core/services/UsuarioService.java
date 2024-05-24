package py.edu.ucsa.jweb.rest.api.core.services;

import java.util.List;

import py.edu.ucsa.jweb.rest.api.web.dto.UsuarioDto;

public interface UsuarioService {
	UsuarioDto getById(Integer id);
	UsuarioDto getByUsuario(String usuario);
	void crear(UsuarioDto dto);
	void actualizar(UsuarioDto dto);
	void eliminar(Integer id);
	List<UsuarioDto> listar();
	void eliminarTodos();
	boolean isExisteUsuario(UsuarioDto usuario);
}
