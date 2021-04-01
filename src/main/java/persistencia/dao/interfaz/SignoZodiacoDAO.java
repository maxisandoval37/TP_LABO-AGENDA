package persistencia.dao.interfaz;

import java.util.List;


import dto.SignoZodiacoDTO;

public interface SignoZodiacoDAO {
	
	public void insertGenericSigno();
	
	public List<SignoZodiacoDTO> readAll();

	public boolean insert(SignoZodiacoDTO signo);
}
