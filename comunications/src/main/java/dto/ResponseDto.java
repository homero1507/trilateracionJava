package dto;

import java.io.Serializable;

/**
 * Clase para el envio de la respusta a las peticiones
 * 
 * @author antonio vargas
 * @email inganthonyvargas@gmail.com
 * @since 24/05/2021
 * @version 1
 *
 */
public class ResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -177538914806616410L;

	/**
	 * mensaje recibido
	 */
	private String mensaje;

	/**
	 * codigo de respuesta
	 */
	private String code;

	private PositionDto position;

	public ResponseDto(String mensaje, String code, Double xPosition, Double yPosition) {
		super();
		this.mensaje = mensaje;
		this.code = code;
		this.position = new PositionDto(xPosition, yPosition);
	}

	public ResponseDto() {
		super();
		this.position = new PositionDto();
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public PositionDto getPosition() {
		return position;
	}

	public void setPosition(PositionDto position) {
		this.position = position;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ResponseDto [mensaje=" + mensaje + ", code=" + code + ", position=" + position + "]";
	}

}
