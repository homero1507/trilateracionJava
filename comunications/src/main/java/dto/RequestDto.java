package dto;

import java.util.List;
import java.io.Serializable;

public class RequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7700628660924364981L;
	private List<SateliteRequestDto> satellites;

	public List<SateliteRequestDto> getSatellites() {
		return satellites;
	}

	public void setSatellites(List<SateliteRequestDto> satellites) {
		this.satellites = satellites;
	}

	@Override
	public String toString() {
		return "RequestDto [satellites=" + satellites + "]";
	}

}
