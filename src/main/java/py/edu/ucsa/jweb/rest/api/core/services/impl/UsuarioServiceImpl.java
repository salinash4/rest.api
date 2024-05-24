package py.edu.ucsa.jweb.rest.api.core.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import jakarta.annotation.Priority;
import py.edu.ucsa.jweb.rest.api.core.services.UsuarioService;
import py.edu.ucsa.jweb.rest.api.web.dto.UsuarioDto;

@Service("usuarioService")
@Priority(0)
public class UsuarioServiceImpl implements UsuarioService {
	private static final AtomicInteger counter = new AtomicInteger();
	private static List<UsuarioDto> usuarios;

	static {
		usuarios = crearUsuariosEnMemoria();
	}

	@Override
	public UsuarioDto getById(Integer id) {
		for (UsuarioDto usu : usuarios) {
			if (usu.getId() == id) {
				return usu;
			}
		}
		return null;
	}

	@Override
	public UsuarioDto getByUsuario(String usuario) {
		if (Objects.nonNull(usuario)) {
			for (UsuarioDto usu : usuarios) {
				if (usuario.equalsIgnoreCase(usu.getUsuario())) {
					return usu;
				}
			}
		}
		return null;
	}

	@Override
	public void crear(UsuarioDto dto) {
		dto.setId(counter.incrementAndGet());
//		dto.setFechaCreacion(LocalDateTime.now());
		usuarios.add(dto);
	}

	@Override
	public void actualizar(UsuarioDto dto) {
		int index = usuarios.indexOf(dto);
		usuarios.set(index, dto);
	}

	@Override
	public void eliminar(Integer id) {
		for (Iterator<UsuarioDto> iterator = usuarios.iterator(); iterator.hasNext();) {
			UsuarioDto u = iterator.next();
			if (u.getId() == id) {
				iterator.remove();
			}
		}
	}

	@Override
	public List<UsuarioDto> listar() {
		return usuarios;
	}

	@Override
	public void eliminarTodos() {
		usuarios.clear();
	}

	@Override
	public boolean isExisteUsuario(UsuarioDto usuario) {
		return Objects.nonNull(getByUsuario(usuario.getUsuario()));
	}

	private static List<UsuarioDto> crearUsuariosEnMemoria() {
		List<UsuarioDto> usuarios = new ArrayList<>();
		usuarios.add(UsuarioDto.builder().id(counter.incrementAndGet()).usuario("jsanchez").clave("123").nombres("JUAN")
				.apellidos("SANCHEZ").email("jsanchez@gmail.com").habilitado(true).fechaCreacion(LocalDateTime.now())
				.build());

		UsuarioDto dto = new UsuarioDto();
		dto.setId(counter.incrementAndGet());
		dto.setUsuario("andyslav");
		dto.setClave("654");
		dto.setNombres("ANDRES");
		dto.setApellidos("SLACHEVSKY");
		dto.setEmail("andyslav@gmail.com");
		dto.setHabilitado(true);
		dto.setFechaCreacion(LocalDateTime.now());
		usuarios.add(dto);

		UsuarioDto otro = new UsuarioDto(counter.incrementAndGet(), "jcubilla", "jcubilla@gmail.com", "josecu", "JOSE",
				"CUBILLA", true, false, LocalDateTime.now());
		usuarios.add(otro);

		return usuarios;
	}
}
