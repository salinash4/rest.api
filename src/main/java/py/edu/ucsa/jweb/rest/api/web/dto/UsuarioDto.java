package py.edu.ucsa.jweb.rest.api.web.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

	private Integer id;
	private String usuario;
	private String email;
	private String clave;
	private String nombres;
	private String apellidos;
	private boolean habilitado;
	private boolean cuentaBloqueada;
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaCreacion;
	
}
