package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import presentacion.controlador.ValidadorObjetos;

public class VentanaAMLocalidad extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaAMLocalidad INSTANCE;
	
	private JTextField txtCP;
	private JTextField txtPais;
	private JTextField txtProvincia;
	private JTextField txtLocalidad;
	
	private JButton btnAgregarLocalidad;
	private JButton btnEditarLocalidad;
	
	public static VentanaAMLocalidad getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaAMLocalidad();
			return new VentanaAMLocalidad();
		} else
			return INSTANCE;
	}
	
	private VentanaAMLocalidad() {
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 450,400);
		contentPane.add(panel);
		panel.setLayout(null);
		
		inicializarElemsVisuales(panel);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}
	
	private void inicializarElemsVisuales(JPanel panel) {

		JLabel lbcp = new JLabel("Cod Postal");
		lbcp.setBounds(10, 10, 113, 14);
		panel.add(lbcp);
		
		JLabel lbPais = new JLabel("Pais");
		lbPais.setBounds(10, lbcp.getY()+60, 113, 14);
		panel.add(lbPais);
		
		JLabel lbProvincia = new JLabel("Provincia");
		lbProvincia.setBounds(10, lbPais.getY()+60, 113, 14);
		panel.add(lbProvincia);
		
		JLabel lbLocalidad = new JLabel("Localidad");
		lbLocalidad.setBounds(10, lbProvincia.getY()+60, 113, 14);
		panel.add(lbLocalidad);
		
		this.txtCP = new JTextField();
		this.txtCP.setBounds(133, 10, 164, 20);
		panel.add(this.txtCP);
		this.txtCP.setColumns(10);
		
		this.txtPais = new JTextField();
		this.txtPais.setBounds(133, txtCP.getY()+60, 164, 20);
		panel.add(this.txtPais);
		this.txtPais.setColumns(10);
		
		this.txtProvincia = new JTextField();
		this.txtProvincia.setBounds(133, txtPais.getY()+60, 164, 20);
		panel.add(this.txtProvincia);
		this.txtProvincia.setColumns(10);
		
		this.txtLocalidad = new JTextField();
		this.txtLocalidad.setBounds(133, txtProvincia.getY()+60, 164, 20);
		panel.add(this.txtLocalidad);
		this.txtLocalidad.setColumns(10);

		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.setBounds(150, 300, 90, 23);
		btnAgregarLocalidad.setVisible(true);
		panel.add(btnAgregarLocalidad);

		btnEditarLocalidad = new JButton("Editar");
		btnEditarLocalidad.setBounds(150, 300, 90, 23);
		btnEditarLocalidad.setVisible(false);
		panel.add(btnEditarLocalidad);
		
		ValidadorObjetos.aplicarKeyListener(this.txtCP,"\\d",4);
		ValidadorObjetos.aplicarKeyListener(this.txtLocalidad,"\\w",20);
		ValidadorObjetos.aplicarKeyListener(this.txtProvincia,"\\w",20);
		ValidadorObjetos.aplicarKeyListener(this.txtPais,"\\w",20);
	}
	
	
	
	public JTextField getTxtCodPostal() {
		return txtCP;
	}
	
	public JTextField getTxtPais() {
		return txtPais;
	}

	public JTextField getTxtProvincia() {
		return txtProvincia;
	}

	public JTextField getTxtLocalidad() {
		return txtLocalidad;
	}

	public JButton getBtnAgregarLocalidad() {
		btnEditarLocalidad.setVisible(false);
		btnAgregarLocalidad.setVisible(true);
		return btnAgregarLocalidad;
	}

	public JButton getBtnEditarLocalidad() {
		btnAgregarLocalidad.setVisible(false);
		btnEditarLocalidad.setVisible(true);
		return btnEditarLocalidad;
	}

	public void resetearVista() {
		this.txtPais.setText(null);
		this.txtProvincia.setText(null);
		this.txtLocalidad.setText(null);
		this.dispose();
	}
	
}
