package presentacion.vista;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import dto.LocalidadDTO;

public class VentanaLocalidad extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaLocalidad INSTANCE;
	
	private JTable tablaLocalidades;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private DefaultTableModel modelLocalidades;
	private String[] nombreColumnas = {"Codigo Postal","Pais", "Provincia", "Nombre Localidad"};
	
	public static VentanaLocalidad getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaLocalidad();
			return new VentanaLocalidad();
		} else
			return INSTANCE;
	}
	
	private VentanaLocalidad() {
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1000, 720);
		contentPane.add(panel);
		panel.setLayout(null);
		
		inicializarElemsVisuales(panel);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}
	
	private void inicializarElemsVisuales(JPanel panel) {
		JScrollPane spEtiquetas = new JScrollPane();
		spEtiquetas.setBounds(10, 11, panel.getWidth()-10, 540);
		panel.add(spEtiquetas);
		
		modelLocalidades = new DefaultTableModel(null,nombreColumnas);
		tablaLocalidades = new JTable(modelLocalidades);
		
		tablaLocalidades.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaLocalidades.getColumnModel().getColumn(0).setResizable(false);
		tablaLocalidades.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaLocalidades.getColumnModel().getColumn(1).setResizable(false);
		
		spEtiquetas.setViewportView(tablaLocalidades);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 600, 89, 23);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 600, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, 600, 89, 23);
		panel.add(btnBorrar);
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

	public DefaultTableModel getModelLocalidades() {
		return modelLocalidades;
	}

	public JTable getTablaLocalidades() {
		return tablaLocalidades;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
	
	public void llenarTabla(List<LocalidadDTO> localidadesEnTabla) {
		this.getModelLocalidades().setRowCount(0); //Para vaciar la tabla
		this.getModelLocalidades().setColumnCount(0);
		this.getModelLocalidades().setColumnIdentifiers(this.getNombreColumnas());

		for (LocalidadDTO loc : localidadesEnTabla){
			String id = Integer.toString(loc.getIdCodPostal());
			String pais = loc.getPais();
			String provincia = loc.getProvincia();
			String nombreLocalidad = loc.getNombreLocalidad();

			Object[] fila = {id,pais,provincia,nombreLocalidad};
			this.getModelLocalidades().addRow(fila);
		}
	}

	public void cerrar() {
		this.dispose();
	}
	
}
