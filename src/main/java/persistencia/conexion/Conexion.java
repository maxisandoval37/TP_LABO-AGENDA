package persistencia.conexion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import net.sf.jasperreports.engine.util.Pair;

public class Conexion {
	public static Conexion instancia;
	private Connection connection;
	private Logger log = Logger.getLogger(Conexion.class);
	private String url = "jdbc:mysql://localhost:3306/agenda";
	private static Pair<String, String> usuarioRegistrado;
	private static Scanner sc;

	private Conexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(url,getUsuarioRegistrado().first(),getUsuarioRegistrado().second());
			this.connection.setAutoCommit(false);
			log.info("Conexion exitosa");
		} catch (Exception e) {
			log.error("Conexion fallida", e);
		}
	}

	public static Conexion getConexion() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() {
		return this.connection;
	}

	public void cerrarConexion() {
		try {
			this.connection.close();
			log.info("Conexion cerrada");
		} catch (SQLException e) {
			log.error("Error al cerrar la conexion!", e);
		}
		instancia = null;
	}
	
	public static boolean hayContrasenaGuardada() {
		FileInputStream fis;
		try {
			fis = new FileInputStream("login.txt");
			sc = new Scanner(fis);
			while (sc.hasNextLine()){ 
				String linea = sc.nextLine();
				if (linea.equals("*CREEDENCIALES GUARDADAS*"))
					return true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void recordarUsuario() {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			String data = "\r\n*CREEDENCIALES GUARDADAS*";
			File file = new File("login.txt");

			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static Pair<String, String> getUsuarioRegistrado() {
		FileInputStream fis;
		String user="";
		String pass="";
		try {
			fis = new FileInputStream("login.txt");
			sc = new Scanner(fis);
			while (sc.hasNextLine()){ 
				String linea = sc.nextLine();
				if (linea.contains("user:"))
					user=linea;
				if (linea.contains("pass:"))
					pass=linea;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		usuarioRegistrado = new Pair<String, String>(user.replace("user:",""), pass.replace("pass:",""));
		return usuarioRegistrado;
	}
}
