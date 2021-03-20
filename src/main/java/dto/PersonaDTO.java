package dto;

import java.time.LocalDate;

public class PersonaDTO {
	//private LocalDate fechaCumple;
	private int idPersona;
	private String nombre;
	private String telefono;
	private Domicilio domicilio;
	private String email;
	private String etiqueta;

	public PersonaDTO(int idPersona, String nombre, String telefono,Domicilio domicilio,String email,String etiqueta) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		//this.fechaCumple = fechaCumple;
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
			this.nombre = telefonoNuevo;
		else
			throw new RuntimeException("El telefono no puede ser vacio");
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}
	
	public void setDomicilio(Domicilio domNuevo) {
		this.domicilio = domNuevo;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String emailNuevo) {
		this.email = emailNuevo;
	}

	public String getEtiqueta() {
		return etiqueta;
	}
	
	public void setEtiqueta(String nuevaEtiqueta) {
		this.etiqueta = nuevaEtiqueta;
	}

}