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
import presentacion.vista.VistaEtiqueta;
import dto.DomicilioDTO;
import dto.EtiquetaDTO;
import dto.PersonaDTO;

public class Controlador implements ActionListener {
	private Vista vista;
	private VistaEtiqueta vistaEtiqueta;
	private List<PersonaDTO> personasEnTabla;
	private List<EtiquetaDTO> etiquetasEnTabla;
	private VentanaPersona ventanaPersona;
	private Agenda agenda;

	public Controlador(Vista vista,VistaEtiqueta vistaEtiqueta, Agenda agenda) {
		this.vista = vista;
		this.vistaEtiqueta = vistaEtiqueta;

		this.vista.getBtnAgregar().addActionListener(a -> ventanaAgregarPersona(a));
		this.vista.getBtnBorrar().addActionListener(s -> borrarPersona(s));
		this.vista.getBtnEditar().addActionListener(c -> ventanaEditarPersona(c));
		this.vista.getBtnReporte().addActionListener(r -> mostrarReporte(r));
		
		this.vista.getBtnEtiqueta().addActionListener(b -> ventanaABMEtiqueta(b));
		this.vistaEtiqueta.getBtnBorrar().addActionListener(v -> borrarEtiqueta(v));
		//hacer el getInstance de etiqueta
		
		this.ventanaPersona = VentanaPersona.getInstance();
		this.ventanaPersona.getBtnAgregarPersona().addActionListener(w -> guardarPersona(w));
		this.ventanaPersona.getBtnEditarPersona().addActionListener(p -> editarPersona(p));

		this.agenda = agenda;
		agregarEtiquetasGenericas();
		ventanaPersona.agregarEtiquetasComboBox(obtenerEtiquetas());
	}
	
	private void ventanaABMEtiqueta(ActionEvent a) {
		VistaEtiqueta ve = new VistaEtiqueta();
	
		this.etiquetasEnTabla = agenda.obtenerEtiquetas();
		ve.llenarTabla(this.etiquetasEnTabla);
		
		ve.show();
	}

	private void ventanaAgregarPersona(ActionEvent a) {
		this.ventanaPersona.getBtnEditarPersona().setVisible(false);
		this.ventanaPersona.getBtnAgregarPersona().setVisible(true);
		this.ventanaPersona.mostrarVentana();
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

	private void guardarPersona(ActionEvent p) {

		try {
			String nombre = this.ventanaPersona.getTxtNombre().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
			String localidad = ventanaPersona.getTxtLocalidad();
			String calle = ventanaPersona.getTxtCalle().getText();
			String altura = ventanaPersona.getTxtAltura().getText();
			String piso = ventanaPersona.getTxtPiso().getText();
			String departamento = ventanaPersona.getTxtDepartamento().getText();
			String email = ventanaPersona.getTxtEmail().getText();
			String fechaCumple = ventanaPersona.getTxtFechaCumple().getText();
			LocalDate auxFecha = LocalDate.parse(fechaCumple);

			DomicilioDTO domicilio = new DomicilioDTO(calle, Integer.parseInt(altura), Integer.parseInt(piso),
					Integer.parseInt(departamento), localidad);

			PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel, domicilio, email, ventanaPersona.getNombreEtiquetaSeleccionada(), auxFecha);
			this.agenda.agregarPersona(nuevaPersona);
			this.refrescarTabla();
			this.ventanaPersona.cerrar();
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

				String localidad = ventanaPersona.getTxtLocalidad();
				String calle = ventanaPersona.getTxtCalle().getText();
				int altura = Integer.parseInt(ventanaPersona.getTxtAltura().getText());
				int piso = Integer.parseInt(ventanaPersona.getTxtPiso().getText());
				int departamento = Integer.parseInt(ventanaPersona.getTxtDepartamento().getText());
				DomicilioDTO daux = new DomicilioDTO(calle, altura, piso, departamento, localidad);

				this.personasEnTabla.get(fila).setDomicilio(daux);
				this.personasEnTabla.get(fila).setEmail(ventanaPersona.getTxtEmail().getText());
				this.personasEnTabla.get(fila).setEtiqueta(ventanaPersona.getNombreEtiquetaSeleccionada());

				String fechaCumple = ventanaPersona.getTxtFechaCumple().getText();
				LocalDate auxFecha = LocalDate.parse(fechaCumple);
				this.personasEnTabla.get(fila).setFechaCumple(auxFecha);

				int idPersonaClick = this.personasEnTabla.get(fila).getIdPersona();
				this.agenda.editarPersona(idPersonaClick, this.personasEnTabla.get(fila));

				this.refrescarTabla();
				this.ventanaPersona.cerrar();
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

	public void borrarPersona(ActionEvent s) {
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		for (int fila : filasSeleccionadas) {
			this.agenda.borrarPersona(this.personasEnTabla.get(fila));
		}

		this.refrescarTabla();
	}
	
	public void borrarEtiqueta(ActionEvent e) {
		int[] filasSeleccionadas = this.vistaEtiqueta.getTablaEtiquetas().getSelectedRows();
		for (int fila : filasSeleccionadas) {
			this.agenda.borrarEtiqueta(this.etiquetasEnTabla.get(fila));
		}

		this.refrescarTabla();
	}

	public List<EtiquetaDTO> obtenerEtiquetas() {
		return this.agenda.obtenerEtiquetas();
	}

	public EtiquetaDTO obtenerEtiquetaPorID(int id) {
		for (EtiquetaDTO e : this.agenda.obtenerEtiquetas()) {
			if (e.getId() == id)
				return e;
		}
		return null;
	}

	public void agregarEtiquetasGenericas() {
		this.agenda.insertarEtiquetasGenericas();
	}

	public void inicializar() {
		this.refrescarTabla();
		this.vista.show();
	}

	private void refrescarTabla() {
		this.personasEnTabla = agenda.obtenerPersonas();
		this.vista.llenarTabla(this.personasEnTabla);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}