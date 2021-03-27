package persistencia.dao.mysql;

import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.DomicilioDAO;
import persistencia.dao.interfaz.EtiquetaDAO;
import persistencia.dao.interfaz.PersonaDAO;

public class DAOSQLFactory implements DAOAbstractFactory {
	
	public PersonaDAO createPersonaDAO() {
		return new PersonaDAOSQL();
	}
	
	public EtiquetaDAO createEtiquetaDAO() {
		return new EtiquetaDAOSQL();
	}
	
	public DomicilioDAO createDomicilioDAO() {
		return new DomicilioDAOSQL();
	}
}
