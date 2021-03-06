package main;

import javax.swing.JOptionPane;
import modelo.Agenda;
import net.sf.jasperreports.engine.util.Pair;
import persistencia.conexion.Conexion;
import persistencia.conexion.GenerarBD;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.VentanaLogin;
import presentacion.vista.Vista;

public class Main {
	
	private static Pair<String, String> usuarioLogin;
	private static VentanaLogin ventanaLogin;
	
	private static void capturarUserPass() {
		String user = ventanaLogin.getTxtUsuario().getText();
		String pass =  ventanaLogin.getTxtContrasena().getText();
		usuarioLogin = new Pair<String, String>(user,pass);
	}
	
	private static void iniciarAplicacionPrimeraVez() {
		capturarUserPass();
		try {
			if (Conexion.getUsuarioRegistrado().equals(usuarioLogin)) {
				GenerarBD.runScriptCrearDB();
				ventanaLogin.resetearVista();
				Conexion.recordarUsuario();
				Vista vista = new Vista();
				Agenda modelo = new Agenda(new DAOSQLFactory());
				Controlador controlador = new Controlador(vista, modelo);
				controlador.inicializar();
			} else
				JOptionPane.showMessageDialog(null, "Usuario o Contrasena INCORRECTO");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Debes iniciar el servicio de MySQL para continuar!");
			System.exit(0);
		}
	}
	
	private static void iniciarAplicacionUserGuardado() {
		try {
			GenerarBD.runScriptCrearDB();
			Conexion.getUsuarioRegistrado();
			Vista vista = new Vista();
			Agenda modelo = new Agenda(new DAOSQLFactory());
			Controlador controlador = new Controlador(vista, modelo);
			controlador.inicializar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Debes iniciar el servicio de MySQL para continuar!");
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		if (!Conexion.hayContrasenaGuardada()) {
			ventanaLogin = VentanaLogin.getInstance();
			ventanaLogin.mostrarVentana();
			ventanaLogin.getBtnIngresar().addActionListener(a -> iniciarAplicacionPrimeraVez());
			ventanaLogin.recordarContrasena();
		}
		else {
			iniciarAplicacionUserGuardado();
		}
	}
}
