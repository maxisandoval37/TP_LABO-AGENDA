package main;

import modelo.Agenda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Vista;
import presentacion.vista.VistaEtiqueta;

public class Main {

	public static void main(String[] args) {
		Vista vista = new Vista();
		VistaEtiqueta vistaEtiqueta = new VistaEtiqueta(); 
		Agenda modelo = new Agenda(new DAOSQLFactory());
		Controlador controlador = new Controlador(vista,vistaEtiqueta, modelo);
		controlador.inicializar();
	}
}
