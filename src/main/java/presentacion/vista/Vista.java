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
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que quieres salir de la Agenda?",
						"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
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
			String localidad = p.getDomicilio().getLocalidad().getNombreLocalidad();
			int codPostal = p.getDomicilio().getLocalidad().getIdCodPostal();
			String provincia = p.getDomicilio().getLocalidad().getProvincia();
			String pais = p.getDomicilio().getLocalidad().getPais();
			String calle = p.getDomicilio().getCalle();
			int altura = p.getDomicilio().getAltura();
			int piso = p.getDomicilio().getPiso();
			int depa = p.getDomicilio().getDepto();
			String email = p.getEmail();
			String fechaCumple = p.getFechaCumple().toString();
			String etiq = p.getEtiqueta().getTipoEtiqueta();
			String zodiaco = p.getSignoZodiaco().getSigno();

			Object[] fila = { nombre, tel, localidad,codPostal,provincia,pais, calle, altura, piso, depa, email, fechaCumple, etiq, zodiaco };
			this.getModelPersonas().addRow(fila);
		}
	}
}
