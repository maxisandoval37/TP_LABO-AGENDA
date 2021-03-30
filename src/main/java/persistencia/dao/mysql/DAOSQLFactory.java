package persistencia.dao.mysql;

import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.DomicilioDAO;
import persistencia.dao.interfaz.EtiquetaDAO;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.SignoZodiacoDAO;

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
	
	public LocalidadDAO createLocalidadDAO() {
		return new LocalidadDAOSQL();
	}
	
	public SignoZodiacoDAO createSignoZodiacoDAO() {
		return new SignoZodiacoDAOSQL();
	}
}
