package persistencia.dao.interfaz;

public interface DAOAbstractFactory {
	
	public PersonaDAO createPersonaDAO();

	public EtiquetaDAO createEtiquetaDAO();
	
	public DomicilioDAO createDomicilioDAO();
	
<<<<<<< HEAD
	public LocalidadDAO createLocalidadDAO();
=======
	public SignoZodiacoDAO createSignoZodiacoDAO();
>>>>>>> 3a9f67d1962964eb51a814055284db14b8e71371
}
