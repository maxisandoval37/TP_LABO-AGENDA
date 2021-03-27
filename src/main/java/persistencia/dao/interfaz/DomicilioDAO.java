package persistencia.dao.interfaz;

import java.util.List;
import dto.DomicilioDTO;

public interface DomicilioDAO {

	public boolean insert(DomicilioDTO domicilio);

	public boolean delete(DomicilioDTO domicilio_a_eliminar);

	public boolean update(int id_a_editar, DomicilioDTO domicilio_nuevo);

	public List<DomicilioDTO> readAll();
	
}
