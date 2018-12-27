package avaliacao;

public class Avaliacao {
	int idAvaliacao;
	String comentario;
	int idJuridica;
	
	public Avaliacao(int idAvaliacao, String comentario, int idJuridica) {
        this.idAvaliacao = idAvaliacao;
        this.comentario = comentario;
        this.idJuridica = idJuridica;
    }
	public Avaliacao() {
		
	}
	
	public int getIdAvaliacao() {
		return idAvaliacao;
	}
	public void setIdAvaliacao(int idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getIdJuridica() {
		return idJuridica;
	}
	public void setIdJuridica(int idJuridica) {
		this.idJuridica = idJuridica;
	}
}
