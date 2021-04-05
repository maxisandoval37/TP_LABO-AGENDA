package presentacion.reportes;

import java.util.Comparator;

import dto.PersonaDTO;

public class ComparadorCodigoPostal implements Comparator<PersonaDTO> {
	
	@Override
	public int compare(PersonaDTO persona1, PersonaDTO persona2) {
		
		
		if (persona1.getDomicilio() != null && persona2.getDomicilio() != null) {
			
		
			if (persona1.getDomicilio().getLocalidad().getIdCodPostal() == persona2.getDomicilio().getLocalidad().getIdCodPostal()) {
				return 0;
			}	
		}
		
		if (persona1.getDomicilio() != null && persona2.getDomicilio() != null) {
	
			if (persona1.getDomicilio().getLocalidad().getIdCodPostal() > persona2.getDomicilio().getLocalidad().getIdCodPostal()) {
				return 1;
			}
			
		}
		
		return -1;

	}
}
