package dto;

/**
 * Clase que describe una ubicacion en el espacio en un plano XY
 * 
 * @author antonio vargas
 * @email inganthonyvargas@gmail.com
 * @since 24/05/2021
 * @version 1
 *
 */
public class PositionDto {

	/**
	 * posicion en el eje X
	 */
	private Double xPosition;

	/**
	 * posicion en el eje Y
	 */
	private Double yPosition;

	public PositionDto() {
		super();
		this.xPosition = 0D;
		this.yPosition = 0D;
		// TODO Auto-generated constructor stub
	}

	public PositionDto(Double xPosition, Double yPosition) {
		super();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	public Double getxPosition() {
		return xPosition;
	}

	public void setxPosition(Double xPosition) {
		this.xPosition = xPosition;
	}

	public Double getyPosition() {
		return yPosition;
	}

	public void setyPosition(Double yPosition) {
		this.yPosition = yPosition;
	}

	@Override
	public String toString() {
		return "PositionDto [xPosition=" + xPosition + ", yPosition=" + yPosition + "]";
	}

}
