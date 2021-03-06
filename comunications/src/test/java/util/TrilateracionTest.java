package util;

import static org.junit.Assert.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dto.PositionDto;
import dto.ResponseDto;
import enums.SatelitesEnum;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TrilateracionTest {

	ResponseDto res;

	Logger log = Logger.getLogger(this.getClass().getCanonicalName());

	// @Before
	public void moverSatelites() {
		SatelitesEnum.KENOBI.getSatelite().setPosition(new PositionDto(4D, 8D));
		SatelitesEnum.SKYWALKER.getSatelite().setPosition(new PositionDto(11D, 7D));
		SatelitesEnum.SATO.getSatelite().setPosition(new PositionDto(16D, 17D));
	}

	@Before
	public void initResponse() {
		res = null;
	}

	@After
	public void consoleLog() {
		log.log(Level.INFO, res.toString());
	}

	@Test
	@Ignore
	public void primerMensajeUbicacion() {
		// res = Trilateracion.getInstance().getLocation(5D);
		res = Trilateracion.getInstance().getLocation(100D);
	}

	@Test
	@Ignore
	public void segundoMensajeUbicacion() {
		// res = Trilateracion.getInstance().getLocation(5D);
		res = Trilateracion.getInstance().getLocation(115.5D);
	}

	@Test
	@Ignore
	public void tercerMensajeUbicacion() {
		// res = Trilateracion.getInstance().getLocation(10D);
		res = Trilateracion.getInstance().getLocation(142.7D);
	}

}
