package dto;

import java.time.LocalDate;

public class PersonaDTO {
	
	private int idPersona;
	private String nombre;
	private String telefono;
	private DomicilioDTO domicilio;
	private String email;
	private EtiquetaDTO etiqueta;
	private LocalDate fechaCumple;
	private SignoZodiacoDTO signoZodiaco;

	public PersonaDTO(int idPersona, String nombre, String telefono,DomicilioDTO domicilio,String email,EtiquetaDTO etiqueta,LocalDate fechaCumple,SignoZodiacoDTO signoZodiaco) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.fechaCumple = fechaCumple;
		this.domicilio  = domicilio;
		this.email = email;
		this.etiqueta = etiqueta;
		this.signoZodiaco = signoZodiaco;
	}

	public int getIdPersona() {
		return this.idPersona;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombreNuevo) {
		if (nombreNuevo.length() >0)
			this.nombre = nombreNuevo;
		else
			throw new RuntimeException("El nombre no puede ser vacio");
	}

	public String getTelefono() {
		return this.telefono;
	}
	
	public void setTelefono(String telefonoNuevo) {
		if (telefonoNuevo.length() > 0)
			this.telefono = telefonoNuevo;
		else
			throw new RuntimeException("El telefono no puede ser vacio");
	}

	public DomicilioDTO getDomicilio() {
		return domicilio;
	}
	
	public void setDomicilio(DomicilioDTO domNuevo) {
		this.domicilio = domNuevo;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String emailNuevo) {
		if (emailNuevo.length() > 0)
			this.email = emailNuevo;
		else
			throw new RuntimeException("El telefono no puede ser vacio");
	}

	public EtiquetaDTO getEtiqueta() {
		return etiqueta;
	}
	
	public void setEtiqueta(EtiquetaDTO nuevaEtiqueta) {
		this.etiqueta = nuevaEtiqueta;
	}
	
	public LocalDate getFechaCumple() {
		return fechaCumple;
	}

	public void setSignoZodiaco(SignoZodiacoDTO signoZodiaco) {
		this.signoZodiaco = signoZodiaco;
	}

	public void setFechaCumple(LocalDate fechaCumple) {
		this.fechaCumple = fechaCumple;
	}

	public SignoZodiacoDTO getSignoZodiaco() {
		return signoZodiaco;
	}
}