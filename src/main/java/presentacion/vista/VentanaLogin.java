package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaLogin extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaLogin INSTANCE;
	
	private JTextField txtUsuario;
	private JTextField txtContrasena;
	private JButton btnIngresar;
	private JButton btnRecordarContrasena;
	
	public static VentanaLogin getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaLogin();
			return new VentanaLogin();
		} else
			return INSTANCE;
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}
	
	private VentanaLogin() {
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
	
	private void inicializarElemsVisuales(JPanel panel) {
		JLabel labelUser = new JLabel("Usuario: ");
		labelUser.setBounds(10, 11, 113, 14);
		panel.add(labelUser);
		
		JLabel labelPass = new JLabel("Contrasena: ");
		labelPass.setBounds(10, labelUser.getY()+70, 113, 14);
		panel.add(labelPass);
	
		txtUsuario = new JTextField();
		txtUsuario.setBounds(133, 11, 164, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContrasena = new JTextField();
		txtContrasena.setBounds(133, txtUsuario.getY()+70, 164, 20);
		panel.add(txtContrasena);
		txtContrasena.setColumns(10);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(40, 200, 89, 23);
		panel.add(btnIngresar);

		btnRecordarContrasena = new JButton("Recordar");
		btnRecordarContrasena.setBounds(180, 200, 89, 23);
		panel.add(btnRecordarContrasena);
		
		JLabel labelRecordarPass = new JLabel("<html><body><center><h4>¿Olvidaste la<br> Contrasena?</h4></center></body></html>");
		labelRecordarPass.setBounds(btnRecordarContrasena.getX()+7, btnRecordarContrasena.getY()-35, 200, 25);
		panel.add(labelRecordarPass);
	}
	
	public void resetearVista() {
		this.txtUsuario.setText(null);
		this.txtContrasena.setText(null);
		this.dispose();
	}
	
	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public JTextField getTxtContrasena() {
		return txtContrasena;
	}
	
	public JButton getBtnIngresar() {
		return btnIngresar;
	}

	public void recordarContrasena() {
		this.btnRecordarContrasena.addActionListener(a -> mostrarUserPass());
	}
	
	private void mostrarUserPass() {
		String datos = "<html><body><center><h2>USER: grupo_ms<br><br>PASS: 1234</h2></center></body></html>";
		JOptionPane.showMessageDialog(null, datos);
	}
}
