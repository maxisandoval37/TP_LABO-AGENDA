package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.DomicilioDTO;
import dto.EtiquetaDTO;
import dto.PersonaDTO;
import dto.SignoZodiacoDTO;

public class PersonaDAOSQL implements PersonaDAO {
	private static final String insert = "INSERT INTO personas(idPersona, nombre, telefono, email, idDomicilio, idEtiqueta, fechaCumple, idSigno) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String deleteAddressInUse = "DELETE FROM domicilios WHERE idDomicilio = ?";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String update = "UPDATE personas SET Nombre = ?, Telefono = ?, Email = ?, idDomicilio = ?, idEtiqueta = ?, FechaCumple = ?, idSigno = ? WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";
	private static final String findTagFK = "SELECT tipoEtiqueta FROM Etiquetas WHERE idEtiqueta = ?";
	private static final String findAddressFK = "SELECT * FROM domicilios WHERE idDomicilio = ?";
	private static final String findSignoFK = "SELECT * FROM signos WHERE idSigno = ?";

	@Override
	public boolean insert(PersonaDTO persona) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			statement.setString(4, persona.getEmail());
			statement.setInt(5, persona.getDomicilio().getId());
			statement.setInt(6, persona.getEtiqueta().getId());
			statement.setString(7, persona.getFechaCumple().toString());
			statement.setInt(8, persona.getSignoZodiaco().getIdSigno());

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
	
	private void deleteAddressInUse(int id) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();

		try {
			statement = conexion.prepareStatement(deleteAddressInUse);
			statement.setInt(1, id);
			if (statement.executeUpdate() > 0) {
				conexion.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean delete(PersonaDTO persona_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isdeleteExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		deleteAddressInUse(persona_a_eliminar.getDomicilio().getId());
		return isdeleteExitoso;
	}

	@Override
	public boolean update(int id_a_editar,PersonaDTO persona_nueva) {
		boolean ret = false;
		PreparedStatement statement;

		Connection conexion = Conexion.getConexion().getSQLConexion();

		try {
			statement = conexion.prepareStatement(update);
			statement.setString(1, persona_nueva.getNombre());
			statement.setString(2, persona_nueva.getTelefono());
			statement.setString(3, persona_nueva.getEmail());
			statement.setInt(4, persona_nueva.getDomicilio().getId());
			statement.setInt(5, persona_nueva.getEtiqueta().getId());
			statement.setString(6, persona_nueva.getFechaCumple().toString());
			statement.setInt(7, persona_nueva.getSignoZodiaco().getIdSigno());
			statement.setInt(8, id_a_editar);

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				ret = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public List<PersonaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				personas.add(getPersonaDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personas;
	}

	private PersonaDTO getPersonaDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("idPersona");
		String nombre = resultSet.getString("Nombre");
		String tel = resultSet.getString("Telefono");
		String fechaCumple = resultSet.getString("FechaCumple");
		String email = resultSet.getString("Email");
		
		EtiquetaDTO etiqueta = getEtiquetaById(resultSet.getInt("idEtiqueta"));
		DomicilioDTO domicilio = getDomiciliobyId(resultSet.getInt("idDomicilio"));
		SignoZodiacoDTO signo = getSignoZodiacoById(resultSet.getInt("idSigno"));

		if (fechaCumple.equals("")) {
			return new PersonaDTO(id, nombre, tel, domicilio, email, etiqueta,null,signo);
		}
		
		LocalDate auxFecha = LocalDate.parse(fechaCumple);
		return new PersonaDTO(id, nombre, tel, domicilio, email, etiqueta,auxFecha,signo);
	}
	
	private DomicilioDTO getDomiciliobyId(int id) throws SQLException{ 
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		DomicilioDTO DomHallado = null;
		Conexion conexion = Conexion.getConexion();
		
		try {
			statement = conexion.getSQLConexion().prepareStatement(findAddressFK);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next() ) {
				String calle = resultSet.getString("Calle");
				int altura = resultSet.getInt("Altura");
				int piso = resultSet.getInt("Piso");;
				int departamento = resultSet.getInt("Departamento");
				int idCodPostal = resultSet.getInt("codPostal");
				
				DomHallado = new DomicilioDTO(id,calle,altura,piso,departamento,DomicilioDAOSQL.getLocalidadById(idCodPostal));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return DomHallado;
	}
	
	private EtiquetaDTO getEtiquetaById(int id) throws SQLException{
		
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		EtiquetaDTO etiquetaHallada = null;
		Conexion conexion = Conexion.getConexion();
		
		try {
			statement = conexion.getSQLConexion().prepareStatement(findTagFK);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next() ) {
				etiquetaHallada = new EtiquetaDTO(id,resultSet.getString("tipoEtiqueta"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return etiquetaHallada;
	}
	
	private SignoZodiacoDTO getSignoZodiacoById(int id) throws SQLException{
		
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		SignoZodiacoDTO signoHallado = null;
		Conexion conexion = Conexion.getConexion();
		
		try {
			statement = conexion.getSQLConexion().prepareStatement(findSignoFK);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next() ) {
				signoHallado = new SignoZodiacoDTO(resultSet.getString("tipoSigno"),id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return signoHallado;
	}
	
}