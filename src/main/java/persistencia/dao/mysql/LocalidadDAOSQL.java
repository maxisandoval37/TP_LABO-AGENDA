package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.LocalidadDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;

public class LocalidadDAOSQL implements LocalidadDAO{

	private static final String insert = "INSERT INTO localidades(codPostal, Pais, Provincia, nombreLocalidad) VALUES(?, ?, ?, ?)";
	private static final String delete = "DELETE FROM localidades WHERE codPostal = ?";
	private static final String update = "UPDATE localidades SET Pais = ?, Provincia = ?, NombreLocalidad = ? WHERE codPostal = ?";
	private static final String readall = "SELECT * FROM localidades";
	
	@Override
	public void insertGenericLocations() {
		if (readAll().size() == 0) {
			
			LocalidadDTO locAux1 = new LocalidadDTO(1613,"Argentina","Buenos Aires","Los Polvorines");
			insert(locAux1);

			LocalidadDTO locAux2 = new LocalidadDTO(1663,"Argentina","Buenos Aires","San Miguel");
			insert(locAux2);
			
			LocalidadDTO locAux3 = new LocalidadDTO(1661,"Argentina","Buenos Aires","Bella Vista");
			insert(locAux3);
			
			LocalidadDTO locAux4 = new LocalidadDTO(4172,"Argentina","Tucuman","Simoca");
			insert(locAux4);
			
			LocalidadDTO locAux5 = new LocalidadDTO(5949,"Argentina","Cordoba","Alicia");
			insert(locAux5);
		}
	}
	
	@Override
	public boolean insert(LocalidadDTO localidad) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, localidad.getIdCodPostal());
			statement.setString(2, localidad.getPais());
			statement.setString(3, localidad.getProvincia());
			statement.setString(4, localidad.getNombreLocalidad());

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
	public boolean delete(LocalidadDTO localidad_a_eliminar) {
		PreparedStatement statement2;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement2 = conexion.prepareStatement(delete);
			statement2.setString(1, Integer.toString(localidad_a_eliminar.getIdCodPostal()));
			
			if (statement2.executeUpdate() > 0) {
				conexion.commit();
				isdeleteExitoso = true;
			}
			
		} catch (SQLException e) {
			isdeleteExitoso = false;
		}
		return isdeleteExitoso;
	}

	@Override
	public boolean update(int id_a_editar, LocalidadDTO localidad_nuevo) {
		boolean ret = false;
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();

		try {
			statement = conexion.prepareStatement(update);
			statement.setString(1, localidad_nuevo.getPais());
			statement.setString(2, localidad_nuevo.getProvincia());
			statement.setString(3, localidad_nuevo.getNombreLocalidad());
			statement.setInt(4, id_a_editar);

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
	public List<LocalidadDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				localidades.add(getLocalidadDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return localidades;
	}
	
	private LocalidadDTO getLocalidadDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("codPostal");
		String pais = resultSet.getString("Pais");
		String provincia = resultSet.getString("Provincia");
		String nombreLocalidad = resultSet.getString("nombreLocalidad");

		return new LocalidadDTO(id, pais, provincia, nombreLocalidad);
	}

}
