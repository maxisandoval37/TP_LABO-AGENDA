package persistencia.dao.interfaz;

import java.util.List;
import dto.LocalidadDTO;

public interface LocalidadDAO {

	public boolean insert(LocalidadDTO localidad);

	public boolean delete(LocalidadDTO localidad_a_eliminar);

	public boolean update(int id_a_editar, LocalidadDTO localidad_nuevo);
	
	public void insertGenericLocations();

	public List<LocalidadDTO> readAll();
}
