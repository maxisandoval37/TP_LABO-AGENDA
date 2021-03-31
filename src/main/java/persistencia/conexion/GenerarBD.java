package persistencia.conexion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;

public class GenerarBD {

	public static void runScriptCrearDB() {
		Logger log = Logger.getLogger(Conexion.class);

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root","");
			ScriptRunner sr = new ScriptRunner(connection);
			Reader reader = new BufferedReader(new FileReader("sql/scriptAgenda.sql"));
			sr.runScript(reader);
			log.info("La base de datos se ha creado correctamente!");
		}

		catch (SQLException | FileNotFoundException e) {
				log.error(e.getMessage());
		}
	}

}
