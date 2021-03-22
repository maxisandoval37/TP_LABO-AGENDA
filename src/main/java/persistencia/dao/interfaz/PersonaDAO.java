package persistencia.dao.interfaz;

import java.util.List;
import dto.PersonaDTO;

public interface PersonaDAO {

	public boolean insert(PersonaDTO persona);

	public boolean delete(PersonaDTO persona_a_eliminar);

	public boolean update(int id_a_editar, PersonaDTO persona_nueva);

	public List<PersonaDTO> readAll();
}
