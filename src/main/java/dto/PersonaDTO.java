package dto;

import java.time.LocalDate;

public class PersonaDTO {
	private LocalDate fechaCumple;
	private int idPersona;
	private String nombre;
	private String telefono;
	private DomicilioDTO domicilio;
	private String email;
	private EtiquetaContactoDTO etiqueta;

	public PersonaDTO(int idPersona, String nombre, String telefono,DomicilioDTO domicilio,String email,EtiquetaContactoDTO etiqueta,LocalDate fechaCumple) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.fechaCumple = fechaCumple;
		this.domicilio  = domicilio;
		this.email = email;
		this.etiqueta = etiqueta;
	}

	public int getIdPersona() {
		return this.idPersona;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombreNuevo) {//agregar limite de caracteres
		if (nombreNuevo.length() >0)
			this.nombre = nombreNuevo;
		else
			throw new RuntimeException("El nombre no puede ser vacio");
	}

	public String getTelefono() {
		return this.telefono;
	}
	
	public void setTelefono(String telefonoNuevo) {//agregar limite de caracteres
		if (telefonoNuevo.length() >0)
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
		this.email = emailNuevo;
	}

	public EtiquetaContactoDTO getEtiqueta() {
		return etiqueta;
	}
	
	public void setEtiqueta(EtiquetaContactoDTO nuevaEtiqueta) {
		this.etiqueta = nuevaEtiqueta;
	}

	public LocalDate getFechaCumple() {
		return fechaCumple;
	}
	
	public void setFechaCumple(LocalDate fechaCumple) {
		this.fechaCumple = fechaCumple;
	}

}