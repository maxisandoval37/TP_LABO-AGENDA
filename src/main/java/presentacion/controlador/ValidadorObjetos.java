package presentacion.controlador;

import java.util.List;
import javax.swing.JOptionPane;
import dto.LocalidadDTO;

public class ValidadorObjetos {
	
	public static boolean nuevaLocalidadEsValida(LocalidadDTO nuevaLocalidad,List<LocalidadDTO> localidadesExistentes) {
		boolean bandera = true;

		if (nuevaLocalidad == null) {
			bandera = false;
		}

		else {
			if (nuevaLocalidad.toString().length() == 0) {
				JOptionPane.showMessageDialog(null, "Complete los campos restantes");
				bandera = false;
			}
			else {
				for (LocalidadDTO lit : localidadesExistentes) {
					if (lit.getPais().equalsIgnoreCase(nuevaLocalidad.getPais())&&lit.getIdCodPostal() == nuevaLocalidad.getIdCodPostal()) {
						JOptionPane.showMessageDialog(null, "La localidad ya existe");
						bandera = false;
						break;
					}
				}
			}
		}
		return bandera;
	}

}
