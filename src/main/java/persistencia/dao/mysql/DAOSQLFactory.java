package persistencia.dao.mysql;

import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.PersonaDAO;

public class DAOSQLFactory implements DAOAbstractFactory {
	public PersonaDAO createPersonaDAO() {
		return new PersonaDAOSQL();
	}

}
