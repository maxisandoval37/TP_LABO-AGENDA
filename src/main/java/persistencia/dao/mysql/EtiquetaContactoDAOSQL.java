package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.EtiquetaContactoDTO;
import persistencia.conexion.Conexion;

public class EtiquetaContactoDAOSQL {
	
	private static final String insert = "INSERT INTO etiquetas(idEtiqueta, tipoEtiqueta) VALUES(?, ?)";
	private static final String delete = "DELETE FROM etiquetas WHERE idEtiqueta = ?";
	private static final String update = "UPDATE etiquetas SET tipoEtiqueta = ? WHERE idEtiqueta = ?";
	private static final String readall = "SELECT * FROM etiquetas";
	
	public void insertarEtiquetasGenericas() {
		if (readAll().size() == 0) {
			
			EtiquetaContactoDTO etiquetaAux = new EtiquetaContactoDTO();
			etiquetaAux.setId(1);
			etiquetaAux.setTipoEtiqueta("Personal");
			insert(etiquetaAux);
			
			etiquetaAux.setId(2);
			etiquetaAux.setTipoEtiqueta("Trabajo");
			insert(etiquetaAux);
			
			etiquetaAux.setId(3);
			etiquetaAux.setTipoEtiqueta("Escuela");
			insert(etiquetaAux);
			
			etiquetaAux.setId(4);
			etiquetaAux.setTipoEtiqueta("Gimnasio");
			insert(etiquetaAux);
		}
	}
	
	public boolean insert(EtiquetaContactoDTO etiqueta) {
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

	public boolean delete(EtiquetaContactoDTO etiqueta) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(etiqueta.getId()));
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isdeleteExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}

	public boolean update(int id_a_editar,EtiquetaContactoDTO etiqueta) {
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

	public List<EtiquetaContactoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<EtiquetaContactoDTO> etiquetas = new ArrayList<EtiquetaContactoDTO>();
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

	private EtiquetaContactoDTO getEtiquetaDTO(ResultSet resultSet) throws SQLException {
		EtiquetaContactoDTO etiqueta = new EtiquetaContactoDTO();
		etiqueta.setId(resultSet.getInt("idEtiqueta"));
		etiqueta.setTipoEtiqueta(resultSet.getString("tipoEtiqueta"));

		return etiqueta;
	}

}
