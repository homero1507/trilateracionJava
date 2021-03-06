package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import dto.SateliteDto;
import enums.SatelitesEnum;

public class MensajesCifrado {

	private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

	private SateliteDto kenobi;
	private SateliteDto skywalker;
	private SateliteDto sato;

	private List<String[]> mensajes;

	private static MensajesCifrado instance;

	private MensajesCifrado() {
		super();
		kenobi = null;
		skywalker = null;
		sato = null;

		mensajes = new ArrayList<String[]>();

	}

	public String GetMessage(String... messages) {

		if (mensajes.size() == 3) {
			mensajes = new ArrayList<String[]>();
		}

		mensajes.add(messages);
		System.out.println(mensajes);
		System.out.println(messages);
		if (mensajes.size() > 2) {
			return obtenerMensaje(mensajes);
		}

		return "ERROR";
	}

	/**
	 * Obtiene el mensaje cifrado
	 * 
	 * @param mensajes
	 * @return
	 */
	private String obtenerMensaje(List<String[]> mensajes) {
		String[] listMsj = new String[lengthMaxList(mensajes)];
		String msjCifrado = "";
		for (String[] msj : mensajes) {
			for (int i = 0; i < msj.length; i++) {
				if (!msj[i].equals("")) {
					listMsj[i] = msj[i];
				}
			}
		}
		for (int i = 0; i < listMsj.length; i++) {
			if (Objects.isNull(listMsj[i])) {
				return "ERROR";
			} else {
				msjCifrado += (i > 0 ? " " : "") + listMsj[i];
			}

		}

		return msjCifrado;
	}

	/**
	 * 
	 * @param mensajes
	 * @return
	 */
	private static int lengthMaxList(List<String[]> mensajes) {
		int max = 0;
		System.out.println(mensajes);
		for (String[] msj : mensajes) {
			System.out.println(msj);
			if (msj.length > max) {
				max = msj.length;
			}
		}

		return max;
	}

	public static MensajesCifrado getInstance() {
		if (Objects.isNull(instance)) {
			instance = new MensajesCifrado();
		}
		return instance;
	}

}
