package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.ValidadorObjetos;

public class VentanaAMEtiqueta extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaAMEtiqueta INSTANCE;
	
	private JTextField txtTipoEtiqueta;
	private JButton btnAgregarEtiqueta;
	private JButton btnEditarEtiqueta;

	public static VentanaAMEtiqueta getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaAMEtiqueta();
			return new VentanaAMEtiqueta();
		} else
			return INSTANCE;
	}
	
	private VentanaAMEtiqueta() {
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 345, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307,300);
		contentPane.add(panel);
		panel.setLayout(null);
		
		inicializarElemsVisuales(panel);
		
		
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}
	
	private void inicializarElemsVisuales(JPanel panel) {
		
		JLabel tipoEtiqueta = new JLabel("Tipo Etiqueta:");
		tipoEtiqueta.setBounds(10, 11, 113, 14);
		panel.add(tipoEtiqueta);
		
		txtTipoEtiqueta = new JTextField();
		txtTipoEtiqueta.setBounds(133, 11, 164, 20);
		panel.add(txtTipoEtiqueta);
		txtTipoEtiqueta.setColumns(10);
		
		btnAgregarEtiqueta = new JButton("Agregar");
		btnAgregarEtiqueta.setBounds(110, 200, 89, 23);
		btnAgregarEtiqueta.setVisible(true);
		panel.add(btnAgregarEtiqueta);
		
		btnEditarEtiqueta = new JButton("Editar");
		btnEditarEtiqueta.setBounds(110, 200, 89, 23);
		btnEditarEtiqueta.setVisible(false);
		panel.add(btnEditarEtiqueta);
		
		ValidadorObjetos.aplicarKeyListener(this.txtTipoEtiqueta,"\\w",20);
		
		
	}
	
	public JTextField getTxtTipoEtiqueta() {
		return this.txtTipoEtiqueta;
	}
	
	public JButton getBtnAgregarEtiqueta() {
		btnEditarEtiqueta.setVisible(false);
		btnAgregarEtiqueta.setVisible(true);
		return btnAgregarEtiqueta;
	}

	public JButton getBtnEditarEtiqueta() {
		btnAgregarEtiqueta.setVisible(false);
		btnEditarEtiqueta.setVisible(true);
		return btnEditarEtiqueta;
	}
	
	public void resetearVista() {
		this.txtTipoEtiqueta.setText(null);
		this.dispose();
	}
}
