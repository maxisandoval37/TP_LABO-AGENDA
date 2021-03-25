package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.EtiquetaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.EtiquetaDAO;

public class EtiquetaDAOSQL implements EtiquetaDAO{
	
	private static final String insert = "INSERT INTO etiquetas(idEtiqueta, tipoEtiqueta) VALUES(?, ?)";
	private static final String deleteFKForPersons = "UPDATE personas SET idEtiqueta = ? WHERE idEtiqueta = ?;";
	private static final String delete = "DELETE FROM etiquetas WHERE idEtiqueta = ?";
	private static final String update = "UPDATE etiquetas SET tipoEtiqueta = ? WHERE idEtiqueta = ?";
	private static final String readall = "SELECT * FROM etiquetas";

	@Override
	public void insertGenericTags() {
		if (readAll().size() == 0) {
			
			EtiquetaDTO etiquetaAux1 = new EtiquetaDTO(1,"Amigo");
			insert(etiquetaAux1);

			EtiquetaDTO etiquetaAux2 = new EtiquetaDTO(2,"Personal");
			insert(etiquetaAux2);
			
			EtiquetaDTO etiquetaAux3 = new EtiquetaDTO(3,"Trabajo");
			insert(etiquetaAux3);
			
			EtiquetaDTO etiquetaAux4 = new EtiquetaDTO(4,"Escuela");
			insert(etiquetaAux4);
			
			EtiquetaDTO etiquetaAux5 = new EtiquetaDTO(5,"Gimnasio");
			insert(etiquetaAux5);
		}
	}
	
	@Override
	public boolean insert(EtiquetaDTO etiqueta) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, etiqueta.getId());
			statement.setString(2, etiqueta.getTipoEtiqueta());

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
	public boolean delete(EtiquetaDTO etiqueta) {
		deleteFKForPersons(etiqueta.getId());
		
		PreparedStatement statement2;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement2 = conexion.prepareStatement(delete);
			statement2.setString(1, Integer.toString(etiqueta.getId()));
			
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
	public boolean update(int id_a_editar,EtiquetaDTO etiqueta) {
		boolean ret = false;
		PreparedStatement statement;

		Connection conexion = Conexion.getConexion().getSQLConexion();

		try {
			statement = conexion.prepareStatement(update);
			statement.setString(1, etiqueta.getTipoEtiqueta());
			statement.setInt(2, id_a_editar);

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				ret = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public List<EtiquetaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<EtiquetaDTO> etiquetas = new ArrayList<EtiquetaDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				etiquetas.add(getEtiquetaDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return etiquetas;
	}

	private EtiquetaDTO getEtiquetaDTO(ResultSet resultSet) throws SQLException {
		EtiquetaDTO etiqueta = new EtiquetaDTO(resultSet.getInt("idEtiqueta"),resultSet.getString("tipoEtiqueta"));
		return etiqueta;
	}

}
