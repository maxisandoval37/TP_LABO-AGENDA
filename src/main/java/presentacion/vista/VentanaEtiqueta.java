package presentacion.vista;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import dto.EtiquetaDTO;

public class VentanaEtiqueta extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaEtiqueta INSTANCE;
	
	private JTable tablaEtiquetas;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private DefaultTableModel modelEtiquetas;
	private String[] nombreColumnas = {" Id ","Tipo Etiqueta"};
	
	
	public static VentanaEtiqueta getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaEtiqueta();
			return new VentanaEtiqueta();
		} else
			return INSTANCE;
	}
	
	private VentanaEtiqueta() {
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
		
		modelEtiquetas = new DefaultTableModel(null,nombreColumnas);
		tablaEtiquetas = new JTable(modelEtiquetas);
		
		tablaEtiquetas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaEtiquetas.getColumnModel().getColumn(0).setResizable(false);
		tablaEtiquetas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaEtiquetas.getColumnModel().getColumn(1).setResizable(false);
		
		spEtiquetas.setViewportView(tablaEtiquetas);
		
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

	public DefaultTableModel getModelEtiquetas() {
		return modelEtiquetas;
	}

	public JTable getTablaEtiquetas() {
		return tablaEtiquetas;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
	
	public void llenarTabla(List<EtiquetaDTO> etiquetasEnTabla) {
		this.getModelEtiquetas().setRowCount(0); //Para vaciar la tabla
		this.getModelEtiquetas().setColumnCount(0);
		this.getModelEtiquetas().setColumnIdentifiers(this.getNombreColumnas());

		for (EtiquetaDTO et : etiquetasEnTabla){
			String id = Integer.toString(et.getId());
			String nombre = et.getTipoEtiqueta();

			Object[] fila = {id,nombre};
			this.getModelEtiquetas().addRow(fila);
		}
	}

	public void cerrar() {
		this.dispose();
	}
	
}
