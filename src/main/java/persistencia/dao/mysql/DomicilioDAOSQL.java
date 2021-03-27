package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.DomicilioDTO;
import dto.LocalidadDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DomicilioDAO;

public class DomicilioDAOSQL implements DomicilioDAO{
	
	private static final String insert = "INSERT INTO domicilios(idDomicilio, Calle, Altura, Piso, Departamento, idLocalidad) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String deleteFKForPersons = "UPDATE personas SET idDomicilio = ? WHERE idDomicilio = ?;";
	private static final String delete = "DELETE FROM domicilios WHERE idDomicilio = ?";
	private static final String update = "UPDATE domicilios SET idDomicilio = ?, Calle = ?, Altura = ?, Piso = ?, Departamento = ?, idLocalidad = ? WHERE idDomicilio = ?";
	private static final String readall = "SELECT * FROM domicilios";
	
	@Override
	public boolean insert(DomicilioDTO domicilio) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, domicilio.getId());
			statement.setString(2, domicilio.getCalle());
			statement.setInt(3, domicilio.getAltura());
			statement.setInt(4, domicilio.getPiso());
			statement.setInt(5, domicilio.getDepto());
			statement.setInt(6, 2);//domicilio.getLocalidad().getIdLocalidad());

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
	
	private void deleteFKForPersons(int fk) {
		PreparedStatement statement1;
		Connection conexion = Conexion.getConexion().getSQLConexion();

		try {
			statement1 = conexion.prepareStatement(deleteFKForPersons);
			statement1.setString(1, null);
			statement1.setInt(2, fk);

			if (statement1.executeUpdate() > 0) {
				conexion.commit();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(DomicilioDTO domicilio_a_eliminar) {
		deleteFKForPersons(domicilio_a_eliminar.getId());
	
		PreparedStatement statement2;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement2 = conexion.prepareStatement(delete);
			statement2.setString(1, Integer.toString(domicilio_a_eliminar.getId()));
			
			if (statement2.executeUpdate() > 0) {
				conexion.commit();
				isdeleteExitoso = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}

	@Override
	public boolean update(int id_a_editar, DomicilioDTO domicilio_nuevo) {
		boolean ret = false;
		PreparedStatement statement;

		Connection conexion = Conexion.getConexion().getSQLConexion();

		try {
			statement = conexion.prepareStatement(update);
			statement.setString(1, domicilio_nuevo.getCalle());
			statement.setInt(2, domicilio_nuevo.getAltura());
			statement.setInt(3, domicilio_nuevo.getPiso());
			statement.setInt(4, domicilio_nuevo.getDepto());
			statement.setInt(5, domicilio_nuevo.getLocalidad().getIdLocalidad());
			statement.setInt(6, id_a_editar);

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				ret = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<DomicilioDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<DomicilioDTO> domicilios = new ArrayList<DomicilioDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				domicilios.add(getDomicilioDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return domicilios;
	}
	
	private DomicilioDTO getDomicilioDTO(ResultSet resultSet) throws SQLException {
		int idDomicilio = resultSet.getInt("idDomicilio");
		String calle = resultSet.getString("Calle");
		int altura = resultSet.getInt("Altura");
		int piso = resultSet.getInt("Piso");
		int depa = resultSet.getInt("Departamento");
		LocalidadDTO localidad = new LocalidadDTO(1,"143 DOMIDAOSQL","143 DOMIDAOSQL","143 DOMIDAOSQL");
		
		DomicilioDTO etiqueta = new DomicilioDTO(idDomicilio,calle,altura,piso,depa,localidad);
		return etiqueta;
	}

}
