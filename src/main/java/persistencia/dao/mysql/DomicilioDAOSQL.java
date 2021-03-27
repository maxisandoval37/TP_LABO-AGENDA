package persistencia.dao.mysql;

import java.util.List;
import dto.DomicilioDTO;
import persistencia.dao.interfaz.DomicilioDAO;

public class DomicilioDAOSQL implements DomicilioDAO{
	
	private static final String insert = "INSERT INTO domicilios(idDomicilio, Calle, Altura, Piso, Departamento, idLocalidad) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String deleteFKForAddress = "UPDATE personas SET idDomicilio = ? WHERE idDomicilio = ?;";
	private static final String delete = "DELETE FROM domicilios WHERE idDomicilio = ?";
	private static final String update = "UPDATE domicilios SET idDomicilio = ?, Calle = ?, Altura = ?, Piso = ?, Departamento = ?, idLocalidad = ? WHERE idDomicilio = ?";
	private static final String readall = "SELECT * FROM domicilios";
	
	@Override
	public boolean insert(DomicilioDTO domicilio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(DomicilioDTO domicilio_a_eliminar) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(int id_a_editar, DomicilioDTO domicilio_nuevo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DomicilioDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
