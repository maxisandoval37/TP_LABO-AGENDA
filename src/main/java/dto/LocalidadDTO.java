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

	@Override
	public String toString() {
		if (this.NombreLocalidad.length()==0 && this.Provincia.length()==0 && this.Pais.length()==0)
			return "";
		return this.NombreLocalidad + " - " + this.Provincia + " - " + this.Pais;
	}
	
}
