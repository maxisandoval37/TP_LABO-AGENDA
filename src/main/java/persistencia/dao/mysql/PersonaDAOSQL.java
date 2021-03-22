package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.DomicilioDTO;
import dto.EtiquetaContactoDTO;
import dto.PersonaDTO;

public class PersonaDAOSQL implements PersonaDAO {
	private static final String insert = "INSERT INTO personas(idPersona, nombre, telefono, calle, altura, piso, departamento, localidad, email, etiqueta) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String update = "UPDATE personas SET Nombre = ?, Telefono = ?, Calle = ?, Altura = ?, Piso = ?, Departamento = ?, Localidad = ?, Email = ?, Etiqueta = ? WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";

	public boolean insert(PersonaDTO persona) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			// statement.setString(4, persona.getFechaCumpleanios().format(formatter));
			statement.setString(4, persona.getDomicilio().getCalle());
			statement.setInt(5, persona.getDomicilio().getAltura());
			statement.setInt(6, persona.getDomicilio().getPiso());
			statement.setInt(7, persona.getDomicilio().getDepto());
			statement.setString(8, persona.getDomicilio().getLocalidad());
			statement.setString(9, persona.getEmail());
			statement.setString(10, persona.getEtiqueta().getTipoEtiqueta());

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
			statement.setString(3, persona_nueva.getDomicilio().getCalle());
			statement.setInt(4, persona_nueva.getDomicilio().getAltura());
			statement.setInt(5, persona_nueva.getDomicilio().getPiso());
			statement.setInt(6, persona_nueva.getDomicilio().getDepto());
			statement.setString(7, persona_nueva.getDomicilio().getLocalidad());
			statement.setString(8, persona_nueva.getEmail());
			statement.setString(9, persona_nueva.getEtiqueta().getTipoEtiqueta());
			statement.setInt(10, id_a_editar);

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
		// String fechaCumple = resultSet.getString("Fecha de Cumpleaños");
		String calle = resultSet.getString("Calle");
		String email = resultSet.getString("Email");
		
		EtiquetaContactoDTO etiqueta = new EtiquetaContactoDTO();
		etiqueta.setTipoEtiqueta(resultSet.getString("Etiqueta"));
		
		int altura = resultSet.getInt("Altura");
		int piso = resultSet.getInt("Piso");
		int departamento = resultSet.getInt("Departamento");
		String localidad = resultSet.getString("Localidad");
		DomicilioDTO domicilio = new DomicilioDTO(calle, altura, piso, departamento, localidad);
		// LocalDate auxFecha = LocalDate.parse(fechaCumple,
		// DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		return new PersonaDTO(id, nombre, tel, domicilio, email, etiqueta);
	}

}
