package presentacion.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import dto.EtiquetaDTO;
import dto.LocalidadDTO;

public class VentanaPersona extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	
	private JComboBox<String> jcbPaises;
	private JComboBox<String> jcbProvincias;
	private JComboBox<String> jcbLocalidades;
	private String localidadSeleccionada;
	
	private JTextField txtCalle;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepartamento;
	private JTextField txtEmail;
	private JTextField txtFechaCumple;
	private JComboBox<EtiquetaDTO> jcbTipoContacto;
	private EtiquetaDTO etiquetaSeleccionada;

	private JButton btnAgregarPersona;
	private JButton btnEditarPersona;
	private static VentanaPersona INSTANCE;
	
	public static VentanaPersona getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaPersona();
			return new VentanaPersona();
		} else
			return INSTANCE;
	}
	
	private VentanaPersona() {
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 345, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307,590);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, lblNombreYApellido.getY()+40, 113, 14);
		panel.add(lblTelfono);
		
		JLabel lblPais = new JLabel ("Pais");
		lblPais.setBounds(10,lblTelfono.getY()+40,113,14);
		panel.add(lblPais);
		
		JLabel lblProvincia = new JLabel ("Provincia");
		lblProvincia.setBounds(10,lblPais.getY()+40,113,14);
		panel.add(lblProvincia);
		
		JLabel lblLocalidad = new JLabel ("Localidad");
		lblLocalidad.setBounds(10,lblProvincia.getY()+40,113,14);
		panel.add(lblLocalidad);
		
		JLabel lblCalle = new JLabel ("Calle");
		lblCalle.setBounds(10,lblLocalidad.getY()+40,113,14);
		panel.add(lblCalle);
		
		JLabel lblAltura = new JLabel ("Altura");
		lblAltura.setBounds(10,lblCalle.getY()+40,113,14);
		panel.add(lblAltura);
		
		JLabel lblPiso = new JLabel ("Piso");
		lblPiso.setBounds(10,lblAltura.getY()+40,113,14);
		panel.add(lblPiso);
		
		JLabel lblDpto = new JLabel ("Departamento");
		lblDpto.setBounds(10,lblPiso.getY()+40,113,14);
		panel.add(lblDpto);
		
		JLabel lblEmail = new JLabel ("Email");
		lblEmail.setBounds(10,lblDpto.getY()+40,113,14);
		panel.add(lblEmail);
		
		JLabel lblFechaCumple = new JLabel ("Naci. (aaaa-mm-dd)");
		lblFechaCumple.setBounds(10,lblEmail.getY()+40,113,14);
		panel.add(lblFechaCumple);
		
		JLabel lblTipoContacto = new JLabel ("Tipo de Contacto");
		lblTipoContacto.setBounds(10,lblFechaCumple.getY()+40,113,14);
		panel.add(lblTipoContacto);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 11, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, txtNombre.getY()+40, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		comboboxTipoDireccion(panel);
		
		txtCalle = new JTextField();
		txtCalle.setBounds(133, jcbLocalidades.getY()+40, 164, 20);
		panel.add(txtCalle);
		txtCalle.setColumns(10);
		
		txtAltura = new JTextField();
		txtAltura.setBounds(133, txtCalle.getY()+40, 164, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);
		
		txtPiso = new JTextField();
		txtPiso.setBounds(133, txtAltura.getY()+40, 164, 20);
		panel.add(txtPiso);
		txtPiso.setColumns(10);
		
		txtDepartamento = new JTextField();
		txtDepartamento.setBounds(133, txtPiso.getY()+40, 164, 20);
		panel.add(txtDepartamento);
		txtDepartamento.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(133, txtDepartamento.getY()+40, 164, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtFechaCumple = new JTextField();
		txtFechaCumple.setBounds(133, txtEmail.getY()+40, 164, 20);
		panel.add(txtFechaCumple);
		txtFechaCumple.setColumns(10);
		
		comboBoxTipoContacto(panel);
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(208, jcbTipoContacto.getY()+40, 89, 23);
		btnAgregarPersona.setVisible(true);
		panel.add(btnAgregarPersona);
		
		btnEditarPersona = new JButton("Editar");
		btnEditarPersona.setBounds(208, jcbTipoContacto.getY()+40, 89, 23);
		btnEditarPersona.setVisible(false);
		panel.add(btnEditarPersona);
		
		this.setVisible(false);
	}
	
	private void inicializarComboBoxesDirrecion(JPanel panel) {
		jcbPaises = new JComboBox<String>();
		jcbPaises.setBounds(133, txtTelefono.getY() + 40, 164, 20);
		panel.add(jcbPaises);
		jcbPaises.addItem("Argentina");
		
		//provincias
		jcbProvincias = new JComboBox<String>();
		jcbProvincias.setBounds(133, jcbPaises.getY() + 40, 164, 20);
		panel.add(jcbProvincias);
		
		//localidades
		jcbLocalidades = new JComboBox<String>();
		jcbLocalidades.setBounds(133, jcbProvincias.getY() + 41, 164, 20);
		panel.add(jcbLocalidades);
	}
	
	private void comboboxTipoDireccion(JPanel panel) {
		inicializarComboBoxesDirrecion(panel);
		//COMO ALTERNATIVA SE PODRIA HACER UN SOLO COMBO QUE MUESTRE LOS 3 DATOS: BELLA VISTA - BS AS - ARG
		jcbPaises.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent pa) {
				//HACER CLEAR
				jcbProvincias.addItem("Buenos Aires");
				jcbProvincias.addItem("Tucuman");
				jcbProvincias.addItem("Salta");
				jcbProvincias.addItem("Tierra Del Fuego");
				jcbLocalidades.repaint();
				
				jcbProvincias.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent p) {
						//HACER CLEAR
						//SE PODRIA HACER UN SWITCH CASE CUANDO ELIJA LA PROV. 
						jcbLocalidades.addItem("Polvorines");
						jcbLocalidades.addItem("San Miguel");
						jcbLocalidades.addItem("La Plata");
						jcbLocalidades.addItem("Lujan");
						jcbLocalidades.addItem("Lanus");
						jcbLocalidades.repaint();
					}
				});
				
				jcbLocalidades.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent l) {
						localidadSeleccionada = (String) jcbLocalidades.getSelectedItem();
					}
				});
				
			}
		});
		
	}
	
	private void comboBoxTipoContacto(JPanel panel) {
		jcbTipoContacto = new JComboBox<EtiquetaDTO>();
		jcbTipoContacto.setBounds(133, txtFechaCumple.getY()+41, 164, 20);
		panel.add(jcbTipoContacto);
		
		jcbTipoContacto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EtiquetaDTO etaux = (EtiquetaDTO) jcbTipoContacto.getSelectedItem();
				etiquetaSeleccionada = etaux;
			}
		});
	}

	public void agregarEtiquetasComboBox(List<EtiquetaDTO> list) {
		for (EtiquetaDTO i : list) {
			jcbTipoContacto.addItem(i);
		}
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public JButton getBtnAgregarPersona() {
		btnEditarPersona.setVisible(false);
		btnAgregarPersona.setVisible(true);
		return btnAgregarPersona;
	}

	public JButton getBtnEditarPersona() {
		btnAgregarPersona.setVisible(false);
		btnEditarPersona.setVisible(true);
		return btnEditarPersona;
	}

	public void resetearVista() {
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		//this.txtLocalidad.setText(null);
		this.txtCalle.setText(null);
		this.txtAltura.setText(null);
		this.txtPiso.setText(null);
		this.txtDepartamento.setText(null);
		this.txtEmail.setText(null);
		this.txtFechaCumple.setText(null);
		btnEditarPersona.setVisible(false);
		btnAgregarPersona.setVisible(true);
		this.dispose();
	}
	
	public String getTxtLocalidad() {//retornar obj tipo localidad
		return localidadSeleccionada;
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
	
	public LocalidadDTO getLocalidadSeleccionada() {
		LocalidadDTO localidad = new LocalidadDTO(1,"Argentina","Buenos Aires","Lujan");
		return localidad;
	}

	public EtiquetaDTO getEtiquetaSeleccionada() {
		return etiquetaSeleccionada;
	}
}
