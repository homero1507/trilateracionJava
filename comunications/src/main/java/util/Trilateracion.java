package util;

import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import dto.PositionDto;
import dto.ResponseDto;
import dto.SateliteDto;
import enums.SatelitesEnum;

/**
 * Clase que aplica el algoritmo de trilateracion para calcular la posicion de
 * un objeto
 * 
 * @author antonio vargas
 * @email inganthonyvargas@gmail.com
 * @since 24/05/2021
 * @version 1
 *
 */
public class Trilateracion {

	private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

	private static Trilateracion instance;

	private SateliteDto kenobi;
	private SateliteDto skywalker;
	private SateliteDto sato;

	private final static String SUCESS_CODE = "200";
	private final static String ERROR_CODE = "404";

	private void Trilateracion() {
		kenobi = null;
		skywalker = null;
		sato = null;
	}

	/**
	 * Metodo que calcula la posicion de un objeto en el plano XY, basado en la
	 * distancia de este a 3 puntos datos, por definicion se recomienda minimo 3
	 * puntos conocidos, para exactitud. Se inician los satelites en la media que se
	 * obtiene la informacion de las distancias
	 * 
	 * @param distancias
	 * @return
	 */
	public ResponseDto getLocation(double distancia) {

		asignarInfoSatelite(distancia);

		ResponseDto responseDto = new ResponseDto();

		validarInfoSatelites(responseDto);

		if ("200".equals(responseDto.getCode())) {
			double positionY = calcularYPoint(kenobi, skywalker, sato);
			double positionX = calcularXPoint(kenobi, skywalker, positionY);
			responseDto.setPosition(new PositionDto(positionX, positionY));
			log.log(Level.INFO, "Obtiene posicion de emisor", responseDto);
			responseDto.setCode(SUCESS_CODE);
		}

		return responseDto;
	}

	/**
	 * basado en el libro 'Programming with Java: A Multimedia Approach' capitulo
	 * 3.13.1 2D Trilateracion, pag 78
	 * 
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	private double calcularYPoint(SateliteDto s1, SateliteDto s2, SateliteDto s3) {

		PositionDto p1 = s1.getPosition();
		PositionDto p2 = s2.getPosition();
		PositionDto p3 = s3.getPosition();

		double py = calculateYpointFunction(p1.getxPosition(), p1.getyPosition(), s1.getDistancia(), p2.getxPosition(),
				p2.getyPosition(), s2.getDistancia(), p3.getxPosition(), p3.getyPosition(), s3.getDistancia());
		return py;
	}

	/**
	 * basado en el libro 'Programming with Java: A Multimedia Approach' capitulo
	 * 3.13.1 2D Trilateracion, pag 78
	 * 
	 * @param s1 satelite uno
	 * @param s2 satelite dos
	 * @param y  punto en el eje Y
	 * @return
	 */
	private double calcularXPoint(SateliteDto s1, SateliteDto s2, double y) {

		PositionDto p1 = s1.getPosition();
		PositionDto p2 = s2.getPosition();

		double px = calculateXpointFunction(p1.getxPosition(), p1.getyPosition(), s1.getDistancia(), p2.getxPosition(),
				p2.getyPosition(), s2.getDistancia(), y);
		return px;
	}

	/**
	 * ejecuta la funcion para obtener el punto X
	 * 
	 * @param a1 punto x para el satelite 1
	 * @param b1 punto y para el satelite 1
	 * @param r1 distancia desde satelite 1 a emisor
	 * @param a2 punto x para el satelite 2
	 * @param b2 punto y para el satelite 2
	 * @param r2 distancia desde satelite 2 a emisor
	 * @param a3 punto x para el satelite 3
	 * @param b3 punto y para el satelite 3
	 * @param r3 distancia desde satelite 3 a emisor
	 * @return
	 */
	private double calculateYpointFunction(double a1, double b1, double r1, double a2, double b2, double r2, double a3,
			double b3, double r3) {

		double y = 0D;
		double yNumerador = 0D;
		double yDenominador = 0D;

		yNumerador = ((a2 - a1) * (exp2(a3) + exp2(b3) - exp2(r3))) + ((a1 - a3) * (exp2(a2) + exp2(b2) - exp2(r2)))
				+ ((a3 - a1) * (exp2(a1) + exp2(b1) - exp2(r1)));

		yDenominador = 2 * ((b3 * (a2 - a1)) + (b2 * (a1 - a3)) + (b1 * (a3 - a2)));

		y = yNumerador / yDenominador;
		return y;
	}

	/**
	 * ejecuta la funcion para obtener el punto X
	 * 
	 * @param a1 punto x para el satelite 1
	 * @param b1 punto y para el satelite 1
	 * @param r1 distancia desde satelite 1 a emisor
	 * @param a2 punto x para el satelite 2
	 * @param b2 punto y para el satelite 2
	 * @param r2 distancia desde satelite 2 a emisor
	 * @param y  posicion y del emisor, ya calculada
	 * @return
	 */
	private double calculateXpointFunction(double a1, double b1, double r1, double a2, double b2, double r2, double y) {
		double x = 0D;
		double xNumerador = 0D;
		double xDenominador = 0D;

		xNumerador = exp2(r2) + exp2(a1) + exp2(b1) - exp2(r1) - exp2(a2) - exp2(b2) - (2 * (b1 - b2) * y);
		xDenominador = 2 * (a1 - a2);

		x = xNumerador / xDenominador;

		return x;
	}

	/**
	 * eleva un numeor al cuadrado
	 * 
	 * @param d
	 * @return
	 */
	private double exp2(double d) {
		return Math.pow(d, 2);
	}

	/**
	 * asigna la info recibida segun el orden de los satelites
	 * 
	 * @param distancia
	 */
	private void asignarInfoSatelite(double distancia) {

		if (Objects.isNull(kenobi)) {
			// el toma kenobi como el primer satelite
			kenobi = SatelitesEnum.KENOBI.getSatelite();
			kenobi.setDistancia(distancia);
			log.log(Level.INFO, "Crear KENOBI {0}", kenobi);
		} else if (Objects.isNull(skywalker)) {
			// se toma skywalker como el segundo
			skywalker = SatelitesEnum.SKYWALKER.getSatelite();
			skywalker.setDistancia(distancia);
			log.log(Level.INFO, "Crear SKYWALKER {0}", skywalker);
		} else if (Objects.isNull(sato)) {
			// se toma sato como el tercero
			sato = SatelitesEnum.SATO.getSatelite();
			sato.setDistancia(distancia);
			log.log(Level.INFO, "Crear SATO {0}", sato);
		} else {
			// si los tres tienen datos, se reinician
			kenobi = SatelitesEnum.KENOBI.getSatelite();
			kenobi.setDistancia(distancia);
			skywalker = null;
			sato = null;
			log.log(Level.INFO, "Reinicia KENOBI {0}", kenobi);
		}
	}

	/**
	 * validamos que la informacion sumistrada es suficiente
	 * 
	 * @param responseDto
	 */
	private void validarInfoSatelites(ResponseDto responseDto) {
		if (Objects.isNull(kenobi) || Objects.isNull(skywalker) || Objects.isNull(sato)) {
			errorCalculoPosition(responseDto);
			log.log(Level.SEVERE, "Uno o varios satelites nulos");
			return;
		}

		if (!validarInterseccion(kenobi, skywalker)) {
			errorCalculoPosition(responseDto);
			log.log(Level.SEVERE, "kenobi y skaywalker no se interceptan");
			return;
		}

		if (!validarInterseccion(skywalker, sato)) {
			errorCalculoPosition(responseDto);
			log.log(Level.SEVERE, "skaywalker y sato no se interceptan");
			return;
		}

		if (!validarInterseccion(kenobi, sato)) {
			errorCalculoPosition(responseDto);
			log.log(Level.SEVERE, "kenobi y sato no se interceptan");
			return;
		}

		responseDto.setCode(SUCESS_CODE);
	}

	/***
	 * crea objeto respuesta cunado no encuientra la posicion
	 * 
	 * @param responseDto
	 */
	private void errorCalculoPosition(ResponseDto responseDto) {
		responseDto.setCode(ERROR_CODE);
		responseDto.setPosition(null);
	}

	/**
	 * Calcula la distancia entre dos posiciones en un plano XY
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	private double calcularDistancia(PositionDto p1, PositionDto p2) {

		double dx = p2.getxPosition() - p1.getxPosition();
		double dy = p2.getyPosition() - p1.getyPosition();

		double d = Math.sqrt(Math.pow(dx, 2D) + Math.pow(dy, 2D));

		return d;
	}

	/**
	 * Valida que la circunferencias de dos satelites, con radio distancia al
	 * emisor, se puedan intersertar, en caso contrario no se puede determinar la
	 * ubicacion del emisor
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	private boolean validarInterseccion(SateliteDto s1, SateliteDto s2) {

		double d = calcularDistancia(s1.getPosition(), s2.getPosition());

		// si la distancia entre los dos satelites es mayor que las sumas de las
		// distancias hasta le punto emisor, quiere decir que no existen puntos de
		// interseccion posibles
		if (d > (s1.getDistancia() + s2.getDistancia())) {
			return false;
		}

		// si la distancia es menor a la diferencias de distancias al emisor, quiere
		// decir que una cisrcunferencia estan dentro de la otra, por lo tando hay
		// puntos de intersecicon
		if (d < Math.abs((s1.getDistancia() - s2.getDistancia()))) {
			return false;
		}

		// si la distancia es cero, estan en el mismo punto, peor la distancia al emisor
		// es distinta, no tienen puntos de interseccion
		if (d == 0 && (s1.getDistancia() != s2.getDistancia())) {
			return false;
		}

		return true;
	}

	public static Trilateracion getInstance() {
		if (Objects.isNull(instance)) {
			instance = new Trilateracion();
		}
		return instance;
	}

}
