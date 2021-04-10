package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import net.sf.jasperreports.engine.util.Pair;

public class Conexion {
	public static Conexion instancia;
	private Connection connection;
	private Logger log = Logger.getLogger(Conexion.class);
	private String url = "jdbc:mysql://localhost:3306/agenda";
	private static Pair<String, String> usuarioRegistrado = new Pair<String, String>("grupo_ms", "1234");

	private Conexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(url,usuarioRegistrado.first(),usuarioRegistrado.second());
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
	
	public static Pair<String, String> getUsuarioRegistrado() {
		return usuarioRegistrado;
	}
}
