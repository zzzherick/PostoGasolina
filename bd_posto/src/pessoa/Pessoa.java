package pessoa;

import login.Login;

public class Pessoa extends Login{
	private int idPessoa;
	private String nome;
	private String email;
	private String telefone;

	public Pessoa(String usuario, String senha, String nome, String email, String telefone) {
		super(usuario, senha);
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}	
	
	public int getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(int id) {
		this.idPessoa = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
