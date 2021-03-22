package dto;

public class EtiquetaContactoDTO {
	
	private int id;
	private String tipoEtiqueta;

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
	public boolean equals(Object obj) {
		boolean ret = false;
		if(obj instanceof EtiquetaContactoDTO) {
			EtiquetaContactoDTO ec = (EtiquetaContactoDTO) obj;
			ret = ec.getId() == this.id;
		}
		return ret;
	}
}
