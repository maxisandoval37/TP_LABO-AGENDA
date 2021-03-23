package dto;

public class EtiquetaDTO {
	
	private int id;
	private String tipoEtiqueta;

	public EtiquetaDTO(int id, String tipoEtiqueta) {
		this.id = id;
		this.tipoEtiqueta = tipoEtiqueta;
	}
	
	public int getId() {
		return id;
	}

	public String getTipoEtiqueta() {
		return tipoEtiqueta;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setTipoEtiqueta(String tipo) {
		this.tipoEtiqueta = tipo;
	}
	
	@Override
	public String toString() {
	    return tipoEtiqueta;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		if(obj instanceof EtiquetaDTO) {
			EtiquetaDTO ec = (EtiquetaDTO) obj;
			ret = ec.getId() == this.id;
		}
		return ret;
	}
}
