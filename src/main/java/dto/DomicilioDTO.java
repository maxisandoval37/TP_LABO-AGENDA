package dto;

public class DomicilioDTO {

	private int idDomicilio;
	private String calle;
	private int altura;
	private int piso;
	private int depto;
	private LocalidadDTO localidad;

	public DomicilioDTO(int idDomicilio, String calle, int altura, int piso, int depto, LocalidadDTO localidad) {
		this.idDomicilio = idDomicilio;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.depto = depto;
		this.localidad = localidad;
	}
	
	public int getId() {
		return idDomicilio;
	}

	public String getCalle() {
		return calle;
	}

	public int getAltura() {
		return altura;
	}

	public int getPiso() {
		return piso;
	}

	public int getDepto() {
		return depto;
	}

	public LocalidadDTO getLocalidad() {
		return localidad;
	}

}