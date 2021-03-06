package util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MensajesCifradoTest {

	private String mensaje;

	@After	
	public void printConsole() {
		System.out.println(mensaje);
	}

	@Test
	@Ignore
	public void primerTest() {
		mensaje = MensajesCifrado.getInstance().GetMessage(new String[] { "este", "", "un", "mensaje", "" });
	}

	@Test
	@Ignore
	public void segundoTest() {
		mensaje = MensajesCifrado.getInstance().GetMessage(new String[] { "", "es", "un", "", "" });
	}

	@Test
	@Ignore
	public void tercerTest() {
		mensaje = MensajesCifrado.getInstance().GetMessage(new String[] { "este", "", "", "", "secreto" });
	}

}
