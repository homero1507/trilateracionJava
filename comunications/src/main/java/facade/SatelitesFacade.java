package facade;

import dto.ResponseDto;

public interface SatelitesFacade {

	public String GetMessage(String... messages);

	public ResponseDto getLocation(double distancia);

}
