package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dto.PersonaDTO;
import javax.swing.JButton;
import persistencia.conexion.Conexion;

public class Vista {
	private JFrame frame;
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private JButton btnReporte;
	private JButton btnLocalidades;
	private JButton btnEtiquetas;
	private JButton btnManual;
	private DefaultTableModel modelPersonas;
	private String[] nombreColumnas = { "Nombre y apellido", "Telefono", "Localidad","Cod Postal", "Provincia", "Pais", "Calle", "Altura", "Piso","Departamento", "Email", "Nacimiento", "Tipo de contacto", "Signo Zodiaco" };

	public Vista() {
		super();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, frame.getWidth() - 25, 720);
		frame.getContentPane().add(panel);
		frame.setResizable(false);
		panel.setLayout(null);

		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 11, panel.getWidth() - 10, 540);
		panel.add(spPersonas);

		modelPersonas = new DefaultTableModel(null, nombreColumnas);
		tablaPersonas = new JTable(modelPersonas);

		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);

		spPersonas.setViewportView(tablaPersonas);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 600, 89, 23);
		panel.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(btnAgregar.getX()+100, 600, 89, 23);
		panel.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(btnEditar.getX()+100, 600, 89, 23);
		panel.add(btnBorrar);

		btnReporte = new JButton("Reporte");
		btnReporte.setBounds(btnBorrar.getX()+100, 600, 89, 23);
		panel.add(btnReporte);
		
		btnLocalidades = new JButton("Localidades");
		btnLocalidades.setBounds(btnReporte.getX()+100, 600, 105, 23);
		panel.add(btnLocalidades);

		btnEtiquetas = new JButton("Etiquetas");
		btnEtiquetas.setBounds(btnLocalidades.getX()+115, 600, 89, 23);
		panel.add(btnEtiquetas);
		
		btnManual = new JButton("Manual");
		btnManual.setBounds(btnEtiquetas.getX()+100, 600, 89, 23);
		panel.add(btnManual);
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que quieres salir de la Agenda?",
						"Confirmaci??n", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					Conexion.getConexion().cerrarConexion();
					System.exit(0);
				}
			}
		});
		this.frame.setVisible(true);
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	public JButton getBtnReporte() {
		return btnReporte;
	}
	
	public JButton getBtnLocalidad() {
		return this.btnLocalidades;
	}

	public JButton getBtnEtiqueta() {
		return this.btnEtiquetas;
	}
	
	public JButton getBtnManual() {
		return this.btnManual;
	}

	public DefaultTableModel getModelPersonas() {
		return modelPersonas;
	}

	public JTable getTablaPersonas() {
		return tablaPersonas;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void llenarTabla(List<PersonaDTO> personasEnTabla) {
		this.getModelPersonas().setRowCount(0);
		this.getModelPersonas().setColumnCount(0);
		this.getModelPersonas().setColumnIdentifiers(this.getNombreColumnas());

		for (PersonaDTO p : personasEnTabla) {
			String nombre = p.getNombre();
			String tel = p.getTelefono();
			String email = p.getEmail();
			String fechaNac = "";
			String etiq = "";
			String localidad = "";
			String codPostal = "";
			String provincia = "";
			String pais = "";
			String calle = "";
			String altura = "";
			String piso = "";
			String depa = "";
			String signo = "";
			try {
				fechaNac = p.getFechaNac().toString();
			}
			catch(Exception e) {}
			try {
				etiq = p.getEtiqueta().getTipoEtiqueta();
			}
			catch(Exception e) {}
			try {				
				localidad = p.getDomicilio().getLocalidad().getNombreLocalidad();
				codPostal = p.getDomicilio().getLocalidad().getIdCodPostal()+"";
				provincia = p.getDomicilio().getLocalidad().getProvincia();
				pais = p.getDomicilio().getLocalidad().getPais();
				calle = p.getDomicilio().getCalle()+"";
				altura = p.getDomicilio().getAltura()+"";
				piso = p.getDomicilio().getPiso()+"";
				depa = p.getDomicilio().getDepto()+"";
			}
			catch(Exception e) {}
			try {
				signo = p.getSignoZodiaco().getSigno();
			}
			catch(Exception e) {}
			
			Object[] fila = {nombre,tel,localidad,codPostal,provincia,pais,calle,altura,piso,depa,email,fechaNac,etiq,signo};
			this.getModelPersonas().addRow(fila);
		}
	}
}
