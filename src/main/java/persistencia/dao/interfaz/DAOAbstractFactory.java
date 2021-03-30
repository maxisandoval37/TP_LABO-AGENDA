package persistencia.dao.interfaz;

public interface DAOAbstractFactory {
	
	public PersonaDAO createPersonaDAO();

	public EtiquetaDAO createEtiquetaDAO();
	
	public DomicilioDAO createDomicilioDAO();
	
	public LocalidadDAO createLocalidadDAO();
	
	public SignoZodiacoDAO createSignoZodiacoDAO();
}
