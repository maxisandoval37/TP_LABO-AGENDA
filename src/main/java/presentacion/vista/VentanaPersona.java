package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtLocalidad;
	private JTextField txtCalle;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepartamento;
	private JTextField txtEmail;
	private JTextField txtFechaCumple;
	private JTextField txtTipoContacto;

	private JButton btnAgregarPersona;
	private JButton btnEditarPersona;
	private static VentanaPersona INSTANCE;
	
	public static VentanaPersona getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaPersona(); 	
			return new VentanaPersona();
		}
		else
			return INSTANCE;
	}
	
	
	private VentanaPersona() 
	{
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307,500);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);
		
		JLabel lblLocalidad = new JLabel ("Localidad");
		lblLocalidad.setBounds(10,93,113,14);
		panel.add(lblLocalidad);
		
		JLabel lblCalle = new JLabel ("Calle");
		lblCalle.setBounds(10,134,113,14);
		panel.add(lblCalle);
		
		JLabel lblAltura = new JLabel ("Altura");
		lblAltura.setBounds(10,175,113,14);
		panel.add(lblAltura);
		
		JLabel lblPiso = new JLabel ("Piso");
		lblPiso.setBounds(10,216,113,14);
		panel.add(lblPiso);
		
		JLabel lblDpto = new JLabel ("Departamento");
		lblDpto.setBounds(10,257,113,14);
		panel.add(lblDpto);
		
		JLabel lblEmail = new JLabel ("Email");
		lblEmail.setBounds(10,lblDpto.getY()+41,113,14);
		panel.add(lblEmail);
		
		JLabel lblFechaCumple = new JLabel ("Fecha de Cumpleaños");
		lblFechaCumple.setBounds(10,lblEmail.getY()+41,113,14);
		panel.add(lblFechaCumple);
		
		JLabel lblTipoContacto = new JLabel ("Tipo de Contacto");
		lblTipoContacto.setBounds(10,lblFechaCumple.getY()+41,113,14);
		panel.add(lblTipoContacto);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setBounds(133, txtTelefono.getY()+41, 164, 20);
		panel.add(txtLocalidad);
		txtLocalidad.setColumns(10);
		
		txtCalle = new JTextField();
		txtCalle.setBounds(133, txtLocalidad.getY()+41, 164, 20);
		panel.add(txtCalle);
		txtCalle.setColumns(10);
		
		txtAltura = new JTextField();
		txtAltura.setBounds(133, txtCalle.getY()+41, 164, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);
		
		txtPiso = new JTextField();
		txtPiso.setBounds(133, txtAltura.getY()+41, 164, 20);
		panel.add(txtPiso);
		txtPiso.setColumns(10);
		
		txtDepartamento = new JTextField();
		txtDepartamento.setBounds(133, txtPiso.getY()+41, 164, 20);
		panel.add(txtDepartamento);
		txtDepartamento.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(133, txtDepartamento.getY()+41, 164, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtFechaCumple = new JTextField();
		txtFechaCumple.setBounds(133, txtEmail.getY()+41, 164, 20);
		panel.add(txtFechaCumple);
		txtFechaCumple.setColumns(10);
		
		txtTipoContacto = new JTextField();
		txtTipoContacto.setBounds(133, txtFechaCumple.getY()+41, 164, 20);
		panel.add(txtTipoContacto);
		txtTipoContacto.setColumns(10);
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(208, txtTipoContacto.getY()+41, 89, 23);
		btnAgregarPersona.setVisible(true);
		panel.add(btnAgregarPersona);
		
		btnEditarPersona = new JButton("Editar");
		btnEditarPersona.setBounds(208, txtTipoContacto.getY()+41, 89, 23);
		btnEditarPersona.setVisible(false);
		panel.add(btnEditarPersona);
		
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}

	public JButton getBtnAgregarPersona() 
	{
		btnEditarPersona.setVisible(false);
		btnAgregarPersona.setVisible(true);
		return btnAgregarPersona;
	}
	
	public JButton getBtnEditarPersona() 
	{
		btnAgregarPersona.setVisible(false);
		btnEditarPersona.setVisible(true);
		return btnEditarPersona;
	}

	public void cerrar() {
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.txtLocalidad.setText(null);
		this.txtCalle.setText(null);
		this.txtAltura.setText(null);
		this.txtPiso.setText(null);
		this.txtDepartamento.setText(null);
		this.txtEmail.setText(null);
		this.txtFechaCumple.setText(null);
		this.txtTipoContacto.setText(null);
		
		btnEditarPersona.setVisible(false);
		btnAgregarPersona.setVisible(true);
		this.dispose();
	}

	public JTextField getTxtLocalidad() {
		return txtLocalidad;
	}


	public JTextField getTxtCalle() {
		return txtCalle;
	}


	public JTextField getTxtAltura() {
		return txtAltura;
	}


	public JTextField getTxtPiso() {
		return txtPiso;
	}


	public JTextField getTxtDepartamento() {
		return txtDepartamento;
	}


	public JTextField getTxtEmail() {
		return txtEmail;
	}



	public JTextField getTxtFechaCumple() {
		return txtFechaCumple;
	}



	public JTextField getTxtTipoContacto() {
		return txtTipoContacto;
	}
	
	
}

