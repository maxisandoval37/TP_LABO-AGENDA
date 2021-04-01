package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.SignoZodiacoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.SignoZodiacoDAO;

public class SignoZodiacoDAOSQL implements SignoZodiacoDAO {
	private static final String insert = "INSERT INTO signos(idSigno, tipoSigno) VALUES(?, ?)";
	private static final String readall = "SELECT * FROM signos";
	
	@Override
	public boolean insert(SignoZodiacoDTO signo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, signo.getIdSigno());
			statement.setString(2, signo.getSigno());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return isInsertExitoso;
	}
	@Override
	public void insertGenericSigno() {
		if (readAll().size() == 0) {
			
			SignoZodiacoDTO signoAux1 = new SignoZodiacoDTO("Aries",1);
			insert(signoAux1);

			SignoZodiacoDTO signoAux2 = new SignoZodiacoDTO("Tauro",2);
			insert(signoAux2);
			
			SignoZodiacoDTO signoAux3 = new SignoZodiacoDTO("Geminis",3);
			insert(signoAux3);
			
			SignoZodiacoDTO signoAux4 = new SignoZodiacoDTO("Cancer",4);
			insert(signoAux4);
			
			SignoZodiacoDTO signoAux5 = new SignoZodiacoDTO("Leo",5);
			insert(signoAux5);
			
			SignoZodiacoDTO signoAux6 = new SignoZodiacoDTO("Virgo",6);
			insert(signoAux6);
			
			SignoZodiacoDTO signoAux7 = new SignoZodiacoDTO("Libra",7);
			insert(signoAux7);
			
			SignoZodiacoDTO signoAux8 = new SignoZodiacoDTO("Escorpio",8);
			insert(signoAux8);
			
			SignoZodiacoDTO signoAux9 = new SignoZodiacoDTO("Sagitario",9);
			insert(signoAux9);
			
			SignoZodiacoDTO signoAux10 = new SignoZodiacoDTO("Capricornio",10);
			insert(signoAux10);
			
			SignoZodiacoDTO signoAux11 = new SignoZodiacoDTO("Acuario",11);
			insert(signoAux11);
			
			SignoZodiacoDTO signoAux12 = new SignoZodiacoDTO("Piscis",12);
			insert(signoAux12);
		}
	}
	
	
	@Override
	public List<SignoZodiacoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<SignoZodiacoDTO> personas = new ArrayList<SignoZodiacoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				personas.add(getSignoZodiacoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personas;
	}

	private SignoZodiacoDTO getSignoZodiacoDTO(ResultSet resultSet) throws SQLException {
		return new SignoZodiacoDTO(resultSet.getString("tipoSigno"),resultSet.getInt("idSigno"));
	}

}
