package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.google.gson.JsonElement;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.Domicilio;
import dto.PersonaDTO;

public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personasEnTabla;
		private VentanaPersona ventanaPersona; 
		private Agenda agenda;
		
		public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnEditar().addActionListener(c->ventanaEditarPersona(c));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
			this.ventanaPersona = VentanaPersona.getInstance();
			this.ventanaPersona.getBtnAgregarPersona().addActionListener(w->guardarPersona(w));
			this.ventanaPersona.getBtnEditarPersona().addActionListener(p->editarPersona(p));
			this.agenda = agenda;
		}
		
		private void ventanaAgregarPersona(ActionEvent a) {
			this.ventanaPersona.mostrarVentana();
		}
		
		private void ventanaEditarPersona(ActionEvent a) {
			this.ventanaPersona.mostrarVentana();
		}

		private void guardarPersona(ActionEvent p) {
			String nombre = this.ventanaPersona.getTxtNombre().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
			String localidad = ventanaPersona.getTxtLocalidad().getText();
			String calle = ventanaPersona.getTxtCalle().getText();
			String altura = ventanaPersona.getTxtAltura().getText();
			String piso = ventanaPersona.getTxtPiso().getText();
			String departamento = ventanaPersona.getTxtDepartamento().getText();
			String email = ventanaPersona.getTxtEmail().getText();
			String fechaCumple = ventanaPersona.getTxtFechaCumple().getText();
			String tipoContacto = ventanaPersona.getTxtTipoContacto().getText();
			
			//LocalDate auxFecha = LocalDate.parse(fechaCumple, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			
			Domicilio domicilio = new Domicilio (calle,Integer.parseInt(altura),Integer.parseInt(piso),Integer.parseInt(departamento),localidad);
			
			PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel,domicilio,email,tipoContacto);
			this.agenda.agregarPersona(nuevaPersona);
			this.refrescarTabla();
			this.ventanaPersona.cerrar();
		}
		
		private void editarPersona(ActionEvent p) {
			int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			for (int fila : filasSeleccionadas) {

				int idPersonaClick = this.personasEnTabla.get(fila).getIdPersona();

				for (PersonaDTO per : personasEnTabla) {

					if (per.getIdPersona() == idPersonaClick) {

						//this.ventanaPersona.setTxtNombre(per.getNombre());
						per.setNombre(this.ventanaPersona.getTxtNombre().getText());
						per.setTelefono(this.ventanaPersona.getTxtTelefono().getText());
						
						String localidad = ventanaPersona.getTxtLocalidad().getText();
						String calle = ventanaPersona.getTxtCalle().getText();
						int altura = Integer.parseInt(ventanaPersona.getTxtAltura().getText());
						int piso = Integer.parseInt(ventanaPersona.getTxtPiso().getText());
						int departamento = Integer.parseInt(ventanaPersona.getTxtDepartamento().getText());
						Domicilio daux = new Domicilio(calle,altura,piso,departamento,localidad);
						
						per.setDomicilio(daux);
						
						per.setEmail(ventanaPersona.getTxtEmail().getText());
						per.setEtiqueta(ventanaPersona.getTxtTipoContacto().getText());
					
						//String fechaCumple = ventanaPersona.getTxtFechaCumple().getText(); hacer set de fecha

						this.agenda.editarPersona(idPersonaClick, per);
						this.refrescarTabla();

						break;
					}
				}
			}

			this.ventanaPersona.cerrar();
		}
		
		private void mostrarReporte(ActionEvent r) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();	
		}

		public void borrarPersona(ActionEvent s)
		{
			int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			for (int fila : filasSeleccionadas)
			{
				this.agenda.borrarPersona(this.personasEnTabla.get(fila));
			}
			
			this.refrescarTabla();
		}
		
		public void inicializar()
		{
			this.refrescarTabla();
			this.vista.show();
		}
		
		private void refrescarTabla()
		{
			this.personasEnTabla = agenda.obtenerPersonas();
			this.vista.llenarTabla(this.personasEnTabla);
		}

		@Override
		public void actionPerformed(ActionEvent e) { }
		
}
