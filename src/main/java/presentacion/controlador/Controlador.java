package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import presentacion.vista.VentanaAMEtiqueta;
import presentacion.vista.VentanaEtiqueta;
import dto.DomicilioDTO;
import dto.EtiquetaDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;

public class Controlador implements ActionListener {
	private Vista vista;
	private List<PersonaDTO> personasEnTabla;
	//private List<LocalidadDTO> localidadesEnTabla;
	private List<EtiquetaDTO> etiquetasEnTabla;
	private VentanaPersona ventanaPersona;
	private VentanaEtiqueta ventanaEtiqueta;
	private VentanaAMEtiqueta ventanaAMEtiqueta;
	private Agenda agenda;

	public Controlador(Vista vista, Agenda agenda) {
		this.vista = vista;

		this.vista.getBtnAgregar().addActionListener(a -> ventanaAgregarPersona(a));
		this.vista.getBtnBorrar().addActionListener(s -> borrarPersona(s));
		this.vista.getBtnEditar().addActionListener(c -> ventanaEditarPersona(c));
		this.vista.getBtnReporte().addActionListener(r -> mostrarReporte(r));
		
		this.vista.getBtnEtiqueta().addActionListener(b -> ventanaABMEtiqueta(b));
		this.ventanaEtiqueta = VentanaEtiqueta.getInstance();
		this.ventanaAMEtiqueta = VentanaAMEtiqueta.getInstance();
		this.ventanaEtiqueta.getBtnAgregar().addActionListener(h -> ventanaAgregarEtiqueta(h));
		this.ventanaEtiqueta.getBtnEditar().addActionListener(x -> ventanaEditarEtiqueta(x));
		this.ventanaAMEtiqueta.getBtnAgregarEtiqueta().addActionListener(y -> guardarEtiqueta(y));
		this.ventanaAMEtiqueta.getBtnEditarEtiqueta().addActionListener(q -> editarEtiqueta(q));
		this.ventanaEtiqueta.getBtnBorrar().addActionListener(v -> borrarEtiqueta(v));

		this.ventanaPersona = VentanaPersona.getInstance();
		this.ventanaPersona.getBtnAgregarPersona().addActionListener(w -> guardarPersona(w));
		this.ventanaPersona.getBtnEditarPersona().addActionListener(p -> editarPersona(p));
		this.agenda = agenda;
		agregarEtiquetasGenericas();
		ventanaPersona.agregarEtiquetasComboBox(obtenerEtiquetas());
		ventanaPersona.agregarLocalidadesComboBox(obtenerLocalidades());
	}
	
	private void ventanaABMEtiqueta(ActionEvent a) {
		this.refrescarTablaEtiquetas();
		ventanaEtiqueta.mostrarVentana();
	}

	private void ventanaAgregarPersona(ActionEvent a) {
		this.ventanaPersona.resetearVista();
		this.ventanaPersona.getBtnEditarPersona().setVisible(false);
		this.ventanaPersona.getBtnAgregarPersona().setVisible(true);
		this.ventanaPersona.mostrarVentana();
	}
	
	private void ventanaAgregarEtiqueta(ActionEvent a) {
		this.ventanaAMEtiqueta.resetearVista();
		this.ventanaAMEtiqueta.getBtnEditarEtiqueta().setVisible(false);
		this.ventanaAMEtiqueta.getBtnAgregarEtiqueta().setVisible(true);
		this.ventanaAMEtiqueta.mostrarVentana();
	}

	private void ventanaEditarPersona(ActionEvent a) {
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();

		this.ventanaPersona.getBtnAgregarPersona().setVisible(false);
		this.ventanaPersona.getBtnEditarPersona().setVisible(true);
		this.ventanaPersona.mostrarVentana();

		for (int fila : filasSeleccionadas) {
			ventanaPersona.getTxtNombre().setText(personasEnTabla.get(fila).getNombre());
			ventanaPersona.getTxtTelefono().setText(personasEnTabla.get(fila).getTelefono());
			ventanaPersona.getTxtAltura().setText(String.valueOf(personasEnTabla.get(fila).getDomicilio().getAltura()));
			ventanaPersona.getTxtCalle().setText(personasEnTabla.get(fila).getDomicilio().getCalle());
			ventanaPersona.getTxtDepartamento().setText(String.valueOf(personasEnTabla.get(fila).getDomicilio().getDepto()));
			ventanaPersona.getTxtEmail().setText(personasEnTabla.get(fila).getEmail());
			ventanaPersona.getTxtPiso().setText(String.valueOf(personasEnTabla.get(fila).getDomicilio().getPiso()));
			ventanaPersona.getTxtFechaCumple().setText(personasEnTabla.get(fila).getFechaCumple().toString());
		}
	}
	
	private void ventanaEditarEtiqueta(ActionEvent et) {
		int[] filasSeleccionadas = this.ventanaEtiqueta.getTablaEtiquetas().getSelectedRows();

		this.ventanaAMEtiqueta.getBtnAgregarEtiqueta().setVisible(false);
		this.ventanaAMEtiqueta.getBtnEditarEtiqueta().setVisible(true);
		this.ventanaAMEtiqueta.mostrarVentana();
		
		for (int fila : filasSeleccionadas) {
			ventanaAMEtiqueta.getTxtTipoEtiqueta().setText(etiquetasEnTabla.get(fila).getTipoEtiqueta());
		}
	}

	private void guardarPersona(ActionEvent p) {
		try {
			String nombre = this.ventanaPersona.getTxtNombre().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
			
			String calle = ventanaPersona.getTxtCalle().getText();
			int altura = Integer.parseInt(ventanaPersona.getTxtAltura().getText());
			int piso =  Integer.parseInt(ventanaPersona.getTxtPiso().getText());
			int departamento =  Integer.parseInt(ventanaPersona.getTxtDepartamento().getText());
			LocalidadDTO localidad = ventanaPersona.getLocalidadSeleccionada();
			System.out.println(localidad.getNombreLocalidad());
			
			String email = ventanaPersona.getTxtEmail().getText();
			String fechaCumple = ventanaPersona.getTxtFechaCumple().getText();
			LocalDate auxFecha = LocalDate.parse(fechaCumple);
				
			int IdDom = this.agenda.obtenerUltimoIdDomicilio()+1;
			DomicilioDTO domicilio = new DomicilioDTO(IdDom,calle, altura, piso, departamento, localidad);
			this.agenda.agregarDomicilio(domicilio);
			PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel, domicilio, email, ventanaPersona.getEtiquetaSeleccionada(), auxFecha);

			this.agenda.agregarPersona(nuevaPersona);
			this.refrescarTablaPersonas();
			this.ventanaPersona.resetearVista();
		}

		catch (Exception e) {
			if (e.getMessage().equals("For input string: \"\""))
				JOptionPane.showMessageDialog(null, "Complete los campos vac�os");
			else
				JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void editarPersona(ActionEvent p) {
		try {
			int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			for (int fila : filasSeleccionadas) {

				this.personasEnTabla.get(fila).setNombre(this.ventanaPersona.getTxtNombre().getText());
				this.personasEnTabla.get(fila).setTelefono(this.ventanaPersona.getTxtTelefono().getText());

				String calle = ventanaPersona.getTxtCalle().getText();
				int altura = Integer.parseInt(ventanaPersona.getTxtAltura().getText());
				int piso = Integer.parseInt(ventanaPersona.getTxtPiso().getText());
				int departamento = Integer.parseInt(ventanaPersona.getTxtDepartamento().getText());
				LocalidadDTO localidad = ventanaPersona.getLocalidadSeleccionada();
				
				//this.personasEnTabla.get(fila).getDomicilio().setId(id);
				int idDom = this.personasEnTabla.get(fila).getDomicilio().getId();
				DomicilioDTO domicilio = new DomicilioDTO(idDom,calle, altura, piso, departamento, localidad);
				this.personasEnTabla.get(fila).setDomicilio(domicilio);
				this.agenda.editarDomicilio(idDom, domicilio);
				//this.EditarDomicilio(idDom,domicilio) //pasar el this.personasEnTabla.get(fila).getDomicilio()
				
				this.personasEnTabla.get(fila).setEmail(ventanaPersona.getTxtEmail().getText());
				this.personasEnTabla.get(fila).setEtiqueta(ventanaPersona.getEtiquetaSeleccionada());
				String fechaCumple = ventanaPersona.getTxtFechaCumple().getText();
				LocalDate auxFecha = LocalDate.parse(fechaCumple);
				this.personasEnTabla.get(fila).setFechaCumple(auxFecha);

				int idPersonaClick = this.personasEnTabla.get(fila).getIdPersona();
				this.agenda.editarPersona(idPersonaClick, this.personasEnTabla.get(fila));

				this.refrescarTablaPersonas();
				this.ventanaPersona.resetearVista();
				break;
			}
		}

		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void mostrarReporte(ActionEvent r) {
		ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
		reporte.mostrar();
	}

	private void borrarPersona(ActionEvent s) {
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		for (int fila : filasSeleccionadas) {
			this.agenda.borrarPersona(this.personasEnTabla.get(fila));
		}
		this.refrescarTablaPersonas();
	}
	
	private List<LocalidadDTO> obtenerLocalidades() {
		return this.agenda.obtenerLocalidades();
	}

	
	private void guardarEtiqueta(ActionEvent e) {
		String tipoEtiqueta = this.ventanaAMEtiqueta.getTxtTipoEtiqueta().getText();

		if (nuevaEtiquetaEsValida(tipoEtiqueta)) {
			EtiquetaDTO nuevaEtiqueta = new EtiquetaDTO(0, tipoEtiqueta);
			this.agenda.agregarEtiqueta(nuevaEtiqueta);
			this.refrescarTablaEtiquetas();
			ventanaPersona.agregarEtiquetasComboBox(obtenerEtiquetas());
			this.ventanaAMEtiqueta.resetearVista();
		}
	}
	
	private boolean nuevaEtiquetaEsValida(String tipoEtiqueta) {
		boolean bandera = true;

		if (tipoEtiqueta.equals("")) {
			JOptionPane.showMessageDialog(null, "Complete el tipo de Etiqueta");
			bandera = false;
		}

		else {
			for (EtiquetaDTO eit : obtenerEtiquetas()) {
				if (eit.getTipoEtiqueta().equalsIgnoreCase(tipoEtiqueta)) {
					JOptionPane.showMessageDialog(null, "La etiqueta ya existe");
					bandera = false;
					break;
				}
			}
		}
		return bandera;
	}
	
	private void editarEtiqueta(ActionEvent e) {
		String tipoEtiqueta = this.ventanaAMEtiqueta.getTxtTipoEtiqueta().getText();
		
		if (nuevaEtiquetaEsValida(tipoEtiqueta)) {
			
			int[] filasSeleccionadas = this.ventanaEtiqueta.getTablaEtiquetas().getSelectedRows();
			for (int fila : filasSeleccionadas) {

				this.etiquetasEnTabla.get(fila).setTipoEtiqueta(tipoEtiqueta);
			
				int idEtiquetaClick = this.etiquetasEnTabla.get(fila).getId();
				this.agenda.editarEtiqueta(idEtiquetaClick, this.etiquetasEnTabla.get(fila));

				this.refrescarTablaEtiquetas();
				this.refrescarTablaPersonas();
				this.ventanaAMEtiqueta.resetearVista();
				break;
			}
		}
	}
	
	private void borrarEtiqueta(ActionEvent e) {
		int[] filasSeleccionadas = this.ventanaEtiqueta.getTablaEtiquetas().getSelectedRows();
		for (int fila : filasSeleccionadas) {
			this.agenda.borrarEtiqueta(this.etiquetasEnTabla.get(fila));
		}
		this.refrescarTablaEtiquetas();
		this.refrescarTablaPersonas();
	}

	private List<EtiquetaDTO> obtenerEtiquetas() {
		return this.agenda.obtenerEtiquetas();
	}

	public EtiquetaDTO obtenerEtiquetaPorID(int id) {
		for (EtiquetaDTO e : this.agenda.obtenerEtiquetas()) {
			if (e.getId() == id)
				return e;
		}
		return null;
	}

	private void agregarEtiquetasGenericas() {
		this.agenda.insertarEtiquetasGenericas();
	}

	public void inicializar() {
		this.refrescarTablaPersonas();
		this.vista.show();
	}

	private void refrescarTablaPersonas() {
		this.personasEnTabla = agenda.obtenerPersonas();
		this.vista.llenarTabla(this.personasEnTabla);
	}
	
	private void refrescarTablaEtiquetas() {
		this.etiquetasEnTabla = agenda.obtenerEtiquetas();
		ventanaEtiqueta.llenarTabla(this.etiquetasEnTabla);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}