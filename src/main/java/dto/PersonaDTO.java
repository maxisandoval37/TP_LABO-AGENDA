package dto;

import java.time.LocalDate;

public class PersonaDTO 
{
	private LocalDate fechaCumpleanios;
	private int idPersona;
	private String nombre;
	private String telefono;
	private Domicilio domicilio;
	private String email;
	private String etiqueta;
	

	public PersonaDTO(int idPersona, String nombre, String telefono)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
	}
	
	public int getIdPersona() 
	{
		return this.idPersona;
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public String getTelefono() 
	{
		return this.telefono;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public String getEmail() {
		return email;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public LocalDate getFechaCumpleanios() {
		return fechaCumpleanios;
	}

	
}
