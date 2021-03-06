package dto;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Clase que describe un objeto Satelite
 * 
 * @author antonio vargas
 * @email avargas@innovaschools.co
 * @since 24/05/2021
 * @version 1
 */
public class SateliteDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2056154045722672499L;

	/**
	 * nombre del satelite
	 */
	private String name;

	/**
	 * descripcion del satelite
	 */
	private String description;

	/**
	 * mensaje recibido por el satelite
	 */
	private String[] mensaje;

	/**
	 * distancia al emisor del mensaje
	 */
	private Double distancia;

	/**
	 * 
	 */
	private PositionDto position;

	public SateliteDto(String name, String description, Double xPosition, Double yPosition, Double distancia) {
		super();
		this.name = name;
		this.description = description;
		this.position = new PositionDto(xPosition, yPosition);
		this.distancia = distancia;
	}

	public SateliteDto() {
		super();
		this.position = new PositionDto();
		this.distancia = 0D;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getMensaje() {
		return mensaje;
	}

	public void setMensaje(String[] mensaje) {
		this.mensaje = mensaje;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public PositionDto getPosition() {
		return position;
	}

	public void setPosition(PositionDto position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "SateliteDto [name=" + name + ", description=" + description + ", mensaje=" + Arrays.toString(mensaje)
				+ ", distancia=" + distancia + ", position=" + position + "]";
	}

}
