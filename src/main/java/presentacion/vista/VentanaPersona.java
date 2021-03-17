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
	private JButton btnAgregarPersona;
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
		setBounds(100, 100, 343, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 400);
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
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(208, txtDepartamento.getY()+41, 89, 23);
		panel.add(btnAgregarPersona);
		
		
		
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
		return btnAgregarPersona;
	}

	public void cerrar()
	{
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.dispose();
	}
	
}

