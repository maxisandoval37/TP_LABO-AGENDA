package dto;

public class SignoZodiacoDTO {
	
	private String signo;
	private int idSigno;
	
	public SignoZodiacoDTO(String signo, int idSigno) {
		this.setSigno(signo);
		this.setIdSigno(idSigno);
		
	}

	public String getSigno() {
		return signo;
	}

	public void setSigno(String signo) {
		this.signo = signo;
	}

	public int getIdSigno() {
		return idSigno;
	}

	public void setIdSigno(int idSigno) {
		this.idSigno = idSigno;
	}

}
