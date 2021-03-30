package dto;

public class SignoZodiacoDTO {
	
	private String signo;
	private int idSigno;
	
	public SignoZodiacoDTO(String signo, int idSigno) {
		this.signo = signo;
		this.idSigno = idSigno;
	}

	public String getSigno() {
		return signo;
	}

	public int getIdSigno() {
		return idSigno;
	}
	
	@Override
	public String toString() {
		return this.signo;
	}

}
