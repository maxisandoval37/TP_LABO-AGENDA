package presentacion.controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import dto.EtiquetaDTO;
import dto.LocalidadDTO;

public class ValidadorObjetos {
	
	public static void aplicarKeyListener(JTextField jtf, String regex, int limiteDeCaracteres) {
		jtf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				Pattern pattern2 = Pattern.compile(regex);
				if (!pattern2.matcher(""+caracter).matches()) {
					e.consume();
				}
				else {
					if (jtf.getText().length()>=limiteDeCaracteres)
						e.consume();
				}
			}
		});
	}
	
	private static boolean formatoTelefonoValido(String cad) {
		Pattern pattern = Pattern.compile("[^a-zA-Z]{3,20}");
		Matcher matcher = pattern.matcher(cad);
		
		return matcher.matches();
	}
	
	private static boolean formatoMailValido(String cad) {
		Pattern pattern2 = Pattern.compile("(\\.|\\w)+@\\w+(\\.\\w+)+");
		Matcher matcher = pattern2.matcher(cad);
		
		return matcher.matches();
	}
	
	private static boolean formatoFechaValido(String cad) {
		Pattern pattern2 = Pattern.compile("\\d{4}\\-\\d{2}\\-\\d{2}");
		Matcher matcher = pattern2.matcher(cad);
		
		return matcher.matches();
	}
	
	public static boolean nuevaPersonaEsValida(String tel, String email, String fecha) {
		boolean retEmail = false;
		boolean retTel = false;
		boolean retFecha = true;
		
		if (formatoTelefonoValido(tel))
			retTel = true;
		else
			JOptionPane.showMessageDialog(null, "El formato del Telefono no es valido");
		
		if (formatoMailValido(email))
			retEmail = true;
		else 
			JOptionPane.showMessageDialog(null, "El formato del E-Mail no es valido");
		
		if (!formatoFechaValido(fecha) && !fecha.isEmpty()) {
			retFecha = false;
			JOptionPane.showMessageDialog(null, "El formato de la fecha no es valido");
		}

		return retEmail && retTel && retFecha;
	}
	
	public static boolean datosPrincipalesCompletos(String nombre, String tel, String email) {
		boolean ret = true;
		return ret && !nombre.isEmpty() && !tel.isEmpty() && !email.isEmpty();
	}
	
	public static boolean nuevaLocalidadEsValida(LocalidadDTO nuevaLocalidad,List<LocalidadDTO> localidadesExistentes, String accion) {
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
					if (lit.getIdCodPostal().equals(nuevaLocalidad.getIdCodPostal()) && accion.equals("agregar")) {
						JOptionPane.showMessageDialog(null, "La localidad ya existe");
						bandera = false;
						break;
					}
				}
			}
		}
		return bandera;
	}

	
	public static boolean nuevaEtiquetaEsValida(String tipoEtiquetaNuevo, List<EtiquetaDTO> etiquetasExistentes) {
		boolean bandera = true;

		if (tipoEtiquetaNuevo.equals("")) {
			JOptionPane.showMessageDialog(null, "Complete el tipo de Etiqueta");
			bandera = false;
		}

		else {
			for (EtiquetaDTO eit : etiquetasExistentes) {
				if (eit.getTipoEtiqueta().equalsIgnoreCase(tipoEtiquetaNuevo)) {
					JOptionPane.showMessageDialog(null, "La etiqueta ya existe");
					bandera = false;
					break;
				}
			}
		}
		return bandera;
	}
}
