package main;

import javax.swing.JOptionPane;
import modelo.Agenda;
import persistencia.conexion.GenerarBD;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Vista;

public class Main {

	public static void main(String[] args) {
		try {
			GenerarBD.runScriptCrearDB();
			Vista vista = new Vista();
			Agenda modelo = new Agenda(new DAOSQLFactory());
			Controlador controlador = new Controlador(vista, modelo);
			controlador.inicializar();
		}

		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Debes iniciar el servicio de MySQL para continuar!");
			System.exit(0);
		}
	}
}
