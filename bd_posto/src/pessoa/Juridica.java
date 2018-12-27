package pessoa;

public class Juridica extends Pessoa{
	private String cnpj;
	
	private float gasolinaComum;
	private float gasolinaAditivada;
	private String cidade;
	private String bairro;
	private String rua;
	private String numero;
	private String complemento;
	
	private int idPreco;
	private int idEndereco;

	public Juridica(String usuario, String senha, String nome, String email, String telefone, String cnpj, float gasolinaComum, 
			float gasolinaAditivada, String cidade, String bairro, String rua, String numero, String complemento) {
		super(usuario, senha, nome, email, telefone);
		this.cnpj = cnpj;
		this.gasolinaComum = gasolinaComum;
		this.gasolinaAditivada = gasolinaAditivada;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
//		this.idPreco = idPreco;
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public float getGasolinaComum() {
		return gasolinaComum;
	}

	public void setGasolinaComum(float gasolinaComum) {
		this.gasolinaComum = gasolinaComum;
	}

	public float getGasolinaAditivada() {
		return gasolinaAditivada;
	}

	public void setGasolinaAditivada(float gasolinaAditivada) {
		this.gasolinaAditivada = gasolinaAditivada;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public int getIdPreco() {
		return idPreco;
	}

	public void setIdPreco(int idPreco) {
		this.idPreco = idPreco;
	}
	
	public int getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}
}
