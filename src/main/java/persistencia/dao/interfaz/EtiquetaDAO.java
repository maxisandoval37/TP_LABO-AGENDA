package persistencia.dao.interfaz;

import java.util.List;
import dto.EtiquetaDTO;

public interface EtiquetaDAO {
	
	public boolean insert(EtiquetaDTO etiqueta);

	public boolean delete(EtiquetaDTO etiqueta_a_eliminar);
	
	public boolean update(int id_a_editar,EtiquetaDTO etiqueta);
	
	public void insertGenericTags();
	
	public List<EtiquetaDTO> readAll();
}
