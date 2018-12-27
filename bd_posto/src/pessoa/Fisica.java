package pessoa;

public class Fisica extends Pessoa{
	private String cpf;

	public Fisica(String usuario, String senha, String nome, String email, String telefone, String cpf) {
		super(usuario, senha, nome, email, telefone);
		this.cpf = cpf;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
