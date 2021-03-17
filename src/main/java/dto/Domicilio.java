package dto;

public class Domicilio {
	private String calle;
	private int altura;
	private int piso;
	private int depto;
	private String localidad;
	
	public Domicilio (String calle,int altura,int piso,int depto,String localidad) {
		this.calle= calle;
		this.altura = altura;
		this.piso = piso;
		this.depto = depto;
		this.localidad = localidad;
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

	public String getLocalidad() {
		return localidad;
	}

}
