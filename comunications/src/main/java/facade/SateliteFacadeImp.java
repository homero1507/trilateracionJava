package facade;

import dto.ResponseDto;
import util.MensajesCifrado;
import util.Trilateracion;

public class SateliteFacadeImp implements SatelitesFacade {

	public String GetMessage(String... messages) {
		return MensajesCifrado.getInstance().GetMessage(messages);
	}

	public ResponseDto getLocation(double distancia) {
		return Trilateracion.getInstance().getLocation(distancia);
	}

}
