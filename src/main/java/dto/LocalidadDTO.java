package dto;

public class LocalidadDTO {

	private int idLocalidad;
	private String Pais;
	private String Provincia;
	private String NombreLocalidad;
	
	public LocalidadDTO(int idLocalidad, String Pais,String Provincia, String NombreLocalidad) {
		this.idLocalidad = idLocalidad;
		this.Pais = Pais;
		this.Provincia = Provincia;
		this.NombreLocalidad = NombreLocalidad;
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}

	public String getPais() {
		return Pais;
	}

	public String getProvincia() {
		return Provincia;
	}

	public String getNombreLocalidad() {
		return NombreLocalidad;
	}
	
}
