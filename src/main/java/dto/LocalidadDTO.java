package dto;

public class LocalidadDTO {

	private Integer idCodPostal;
	private String Pais;
	private String Provincia;
	private String NombreLocalidad;
	
	public LocalidadDTO(Integer codPostal, String Pais,String Provincia, String NombreLocalidad) {
		this.idCodPostal = codPostal;
		this.Pais = Pais;
		this.Provincia = Provincia;
		this.NombreLocalidad = NombreLocalidad;
	}

	public Integer getIdCodPostal() {
		return idCodPostal;
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
		if ((this.idCodPostal+"").length()==0 || this.NombreLocalidad.length()==0 || this.Provincia.length()==0 || this.Pais.length()==0)
			return "";
		return this.NombreLocalidad + " - " + this.Provincia + " - " + this.Pais;
	}
	
}
