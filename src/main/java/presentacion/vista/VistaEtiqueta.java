package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dto.EtiquetaDTO;
import persistencia.conexion.Conexion;

public class VistaEtiqueta {
	private JFrame frame;
	private JTable tablaEtiquetas;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private DefaultTableModel modelEtiquetas;
	private String[] nombreColumnas = {" Id ","Tipo Etiqueta"};
	
	public VistaEtiqueta() 
	{
		super();
		initialize();
	}
	
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, frame.getWidth()-25, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spEtiquetas = new JScrollPane();
		spEtiquetas.setBounds(10, 11, panel.getWidth()-10, 182);
		panel.add(spEtiquetas);
		
		modelEtiquetas = new DefaultTableModel(null,nombreColumnas);
		tablaEtiquetas = new JTable(modelEtiquetas);
		
		tablaEtiquetas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaEtiquetas.getColumnModel().getColumn(0).setResizable(false);
		tablaEtiquetas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaEtiquetas.getColumnModel().getColumn(1).setResizable(false);
		
		spEtiquetas.setViewportView(tablaEtiquetas);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 228, 89, 23);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 228, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, 228, 89, 23);
		panel.add(btnBorrar);
	}
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "¿Estás seguro que quieres salir de la Agenda?",
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
}
