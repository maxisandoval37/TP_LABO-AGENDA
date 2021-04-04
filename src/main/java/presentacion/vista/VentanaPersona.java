package presentacion.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import dto.EtiquetaDTO;
import dto.LocalidadDTO;
import dto.SignoZodiacoDTO;
import presentacion.controlador.ValidadorObjetos;

public class VentanaPersona extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	JCheckBox checkboxDomicilio;
	JCheckBox checkboxTipoEtiqueta;
	JCheckBox checkboxSignoZodiaco;
	
	private JComboBox<LocalidadDTO> jcbLocalidades;
	private LocalidadDTO localidadSeleccionada;
	
	private JComboBox<EtiquetaDTO> jcbTipoEtiqueta;
	private EtiquetaDTO etiquetaSeleccionada;
	
	private JComboBox<SignoZodiacoDTO> jcbSignoZodiaco;
	private SignoZodiacoDTO signoZodiacoSeleccionado;
	
	private JTextField txtCalle;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepartamento;
	private JTextField txtEmail;
	private JTextField txtFechaCumple;
	
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
		setBounds(100, 100, 550, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 550,590);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, lblNombreYApellido.getY()+40, 113, 14);
		panel.add(lblTelfono);
		
		JLabel lblLocalidad = new JLabel ("Localidad");
		lblLocalidad.setBounds(10,lblTelfono.getY()+40,113,14);
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
		
		JLabel lblSignoZodiaco = new JLabel ("Signo Zodiaco");
		lblSignoZodiaco.setBounds(10,lblTipoContacto.getY()+40,113,14);
		panel.add(lblSignoZodiaco);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 11, 270, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, txtNombre.getY()+40, 270, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		comboboxTipoDireccion(panel);

		txtCalle = new JTextField();
		txtCalle.setBounds(133, jcbLocalidades.getY()+40, 270, 20);
		panel.add(txtCalle);
		txtCalle.setColumns(10);
		
		txtAltura = new JTextField();
		txtAltura.setBounds(133, txtCalle.getY()+40, 270, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);
		
		txtPiso = new JTextField();
		txtPiso.setBounds(133, txtAltura.getY()+40, 270, 20);
		panel.add(txtPiso);
		txtPiso.setColumns(10);
		
		txtDepartamento = new JTextField();
		txtDepartamento.setBounds(133, txtPiso.getY()+40, 270, 20);
		panel.add(txtDepartamento);
		txtDepartamento.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(133, txtDepartamento.getY()+40, 270, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtFechaCumple = new JTextField();
		txtFechaCumple.setBounds(133, txtEmail.getY()+40, 270, 20);
		panel.add(txtFechaCumple);
		txtFechaCumple.setColumns(10);
		
		comboBoxTipoEtiqueta(panel);
		comboBoxTipoSignoZodiaco(panel);
		
		gestionarCheckBoxDireccion(panel);
		gestionarCheckBoxTipoEtiqueta(panel);
		gestionarCheckBoxSignoZodiaco(panel);
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(208, jcbSignoZodiaco.getY()+80, 89, 23);
		btnAgregarPersona.setVisible(true);
		panel.add(btnAgregarPersona);
		
		btnEditarPersona = new JButton("Editar");
		btnEditarPersona.setBounds(208, jcbSignoZodiaco.getY()+80, 89, 23);
		btnEditarPersona.setVisible(false);
		panel.add(btnEditarPersona);
		
		this.setVisible(false);
		cambiarUsabilidadElemsDireccion(false);
		jcbTipoEtiqueta.setEnabled(false);
		jcbSignoZodiaco.setEnabled(false);
		
		ValidadorObjetos.aplicarKeyListener(this.txtNombre,"\\w|\\ |\\.",30);
		ValidadorObjetos.aplicarKeyListener(this.txtTelefono,"\\d|\\+|\\(|\\)|\\*|\\#",20);
		ValidadorObjetos.aplicarKeyListener(this.txtCalle,"\\w|\\ |\\.",20);
		ValidadorObjetos.aplicarKeyListener(this.txtAltura,"\\d",8);
		ValidadorObjetos.aplicarKeyListener(this.txtPiso,"\\d",4);
		ValidadorObjetos.aplicarKeyListener(this.txtDepartamento,"\\d",4);
		ValidadorObjetos.aplicarKeyListener(this.txtEmail,"\\w|\\W",30);
	}
	
	private void gestionarCheckBoxDireccion(JPanel panel) {
		checkboxDomicilio = new JCheckBox("Cargar Direccion");
		checkboxDomicilio.setBounds(jcbLocalidades.getX()+300,jcbLocalidades.getY(), 20, 20);
		panel.add(checkboxDomicilio);
		
		checkboxDomicilio.setSelected(false);
		checkboxDomicilio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent l) {
				if (checkboxDomicilio.isSelected())
					cambiarUsabilidadElemsDireccion(true);
				else
					cambiarUsabilidadElemsDireccion(false);
			}
		});
	}
	
	private void cambiarUsabilidadElemsDireccion(boolean visibilidad) {
		jcbLocalidades.setEnabled(visibilidad);
		txtCalle.setEnabled(visibilidad);
		txtAltura.setEnabled(visibilidad);
		txtPiso.setEnabled(visibilidad);
		txtDepartamento.setEnabled(visibilidad);
	}
	
	private void gestionarCheckBoxTipoEtiqueta(JPanel panel) {
		checkboxTipoEtiqueta = new JCheckBox("Cargar Etiqueta");
		checkboxTipoEtiqueta.setBounds(jcbTipoEtiqueta.getX()+300,jcbTipoEtiqueta.getY(), 20, 20);
		panel.add(checkboxTipoEtiqueta);
		
		checkboxTipoEtiqueta.setSelected(false);
		checkboxTipoEtiqueta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent l) {
				if (checkboxTipoEtiqueta.isSelected())
					jcbTipoEtiqueta.setEnabled(true);
				else
					jcbTipoEtiqueta.setEnabled(false);
			}
		});
	}
	
	private void gestionarCheckBoxSignoZodiaco(JPanel panel) {
		checkboxSignoZodiaco = new JCheckBox("Cargar Signo");
		checkboxSignoZodiaco.setBounds(jcbSignoZodiaco.getX()+300,jcbSignoZodiaco.getY(), 20, 20);
		panel.add(checkboxSignoZodiaco);
		
		checkboxSignoZodiaco.setSelected(false);
		checkboxSignoZodiaco.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent l) {
				if (checkboxSignoZodiaco.isSelected())
					jcbSignoZodiaco.setEnabled(true);
				else
					jcbSignoZodiaco.setEnabled(false);
			}
		});
	}
	
	private void comboboxTipoDireccion(JPanel panel) {
		jcbLocalidades = new JComboBox<LocalidadDTO>();
		jcbLocalidades.setBounds(133, txtTelefono.getY() + 40, 270, 20);
		panel.add(jcbLocalidades);

		jcbLocalidades.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent l) {
				LocalidadDTO loaux = (LocalidadDTO) jcbLocalidades.getSelectedItem();
				localidadSeleccionada =  loaux;
			}
		});
	}
	
	public void agregarLocalidadesComboBox(List<LocalidadDTO> list) {
		this.jcbLocalidades.removeAllItems();
		for (LocalidadDTO l: list) {
			this.jcbLocalidades.addItem(l);
		}
		this.jcbLocalidades.repaint();
	}
	
	private void comboBoxTipoEtiqueta(JPanel panel) {
		jcbTipoEtiqueta = new JComboBox<EtiquetaDTO>();
		jcbTipoEtiqueta.setBounds(133, txtFechaCumple.getY()+41, 270, 20);
		panel.add(jcbTipoEtiqueta);
		
		jcbTipoEtiqueta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EtiquetaDTO etaux = (EtiquetaDTO) jcbTipoEtiqueta.getSelectedItem();
				etiquetaSeleccionada = etaux;
			}
		});
	}

	public void agregarEtiquetasComboBox(List<EtiquetaDTO> list) {
		this.jcbTipoEtiqueta.removeAllItems();
		for (EtiquetaDTO i : list) {
			this.jcbTipoEtiqueta.addItem(i);
		}
		this.jcbTipoEtiqueta.repaint();
	}
	
	private void comboBoxTipoSignoZodiaco(JPanel panel) {
		jcbSignoZodiaco = new JComboBox<SignoZodiacoDTO>();
		jcbSignoZodiaco.setBounds(133, jcbTipoEtiqueta.getY()+41, 270, 20);
		panel.add(jcbSignoZodiaco);
		
		jcbSignoZodiaco.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SignoZodiacoDTO sgaux = (SignoZodiacoDTO) jcbSignoZodiaco.getSelectedItem();
				signoZodiacoSeleccionado = sgaux;
			}
		});
	}
	
	public void agregarSignoZodiacoComboBox(List<SignoZodiacoDTO> list) {
		for (SignoZodiacoDTO i : list) {
			jcbSignoZodiaco.addItem(i);
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
		this.txtCalle.setText(null);
		this.txtAltura.setText(null);
		this.txtPiso.setText(null);
		this.txtDepartamento.setText(null);
		this.txtEmail.setText(null);
		this.txtFechaCumple.setText(null);
		this.checkboxDomicilio.setSelected(false);
		this.checkboxTipoEtiqueta.setSelected(false);
		this.checkboxSignoZodiaco.setSelected(false);
		btnEditarPersona.setVisible(false);
		btnAgregarPersona.setVisible(true);
		this.dispose();
	}
	
	public LocalidadDTO getTxtLocalidad() {
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
	
	public boolean getEstadoCheckBoxDireccion() {
		return checkboxDomicilio.isSelected();
	}
	
	public boolean getEstadoCheckBoxTipoEtiqueta() {
		return checkboxTipoEtiqueta.isSelected();
	}
	
	public boolean getEstadoCheckBoxSigno() {
		return checkboxSignoZodiaco.isSelected();
	}
	
	public LocalidadDTO getLocalidadSeleccionada() {
		return this.localidadSeleccionada;
	}

	public EtiquetaDTO getEtiquetaSeleccionada() {
		return this.etiquetaSeleccionada;
	}
	
	public SignoZodiacoDTO getSignoZodiacoSeleccionado() {
		return this.signoZodiacoSeleccionado;
	}
}
