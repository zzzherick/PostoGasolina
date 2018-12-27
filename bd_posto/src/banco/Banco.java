package banco;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.print.attribute.AttributeSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleContext;

import avaliacao.Avaliacao;
import login.Login;
import pessoa.Fisica;
import pessoa.Juridica;

public class Banco {
    
    private static String host = "localhost";
    private static String banco = "bancoposto";
    private static String usuario = "root";
    private static String senha = "";
    private static String url = "jdbc:mysql://" + host + "/" + banco + "?useSSL=false&useTimezone=true&serverTimezone=UTC";
    //"jdbc:mysql://" + HOST + "/" + BANCO + "?useSSL=false";
    private static Connection conexao = null;
    
    private Banco(){
    }
    
    public static Connection getConexao(){
        try{
            System.out.println(url);
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch(SQLException ex){
            System.out.println("Erro: " + ex.getMessage());
        }
        if(conexao != null) {
            System.out.println("Conectado com sucessso!");
        } else {
            System.out.println("Não foi possivel realizar conexão");
        }
        return conexao;
    }
    
    public static boolean fecharConexao(){
        if(conexao != null){
            try {
                conexao.close();
                System.out.println("Conexão fechada");
                return true;
            } catch(SQLException e){
                System.out.println("Erro:" + e.getMessage());
            }
        }
        return false;
    }
    
    public static void inserirPessoaFisica(Fisica pessoaFisica) throws SQLException{
        
        try{
        	//cadastro de login
        	String sql = "INSERT INTO login(usuario, senha, tipo) VALUES(?, ?, ?)";
        	
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, pessoaFisica.getUsuario());
            stmt.setString(2, pessoaFisica.getSenha());
            stmt.setInt(3, 1);
            
            stmt.execute();
            
            ResultSet rs = stmt.getGeneratedKeys();
	        rs.next();
	        int idGerado = rs.getInt(1);
	        pessoaFisica.setIdLogin(idGerado);
	        
	        stmt.close(); 

	        /*
        	sql = "SET FOREIGN_KEY_CHECKS=0";
        	stmt = conexao.prepareStatement(sql);

            stmt.execute();
            stmt.close(); 
        	*/
            //cadastro de pessoa
            sql = "INSERT INTO pessoa(nome, email, telefone, login_idLogin) VALUES(?, ?, ?, ?)";
            
        	stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        stmt.setString(1, pessoaFisica.getNome());	
	        stmt.setString(2, pessoaFisica.getEmail());
	        stmt.setString(3, pessoaFisica.getTelefone());
	        stmt.setInt(4, pessoaFisica.getIdLogin());
	        stmt.execute();
	
	        rs = stmt.getGeneratedKeys();
	        rs.next();
	        idGerado = rs.getInt(1);
	        pessoaFisica.setIdPessoa(idGerado);
	        stmt.close();
	        
	        //cadastro pessoa fisica
            sql = "INSERT INTO fisica(cpf, pessoa_idPessoa) VALUES(?, ?)";
            
            stmt = conexao.prepareStatement(sql);
	        stmt.setString(1, pessoaFisica.getCpf());	
	        stmt.setInt(2, pessoaFisica.getIdPessoa());
	        stmt.execute();
	        stmt.close(); 
        } catch (SQLException ex){
            System.out.println("Erro: "+ ex.getMessage());
        }
        
        JOptionPane.showMessageDialog(null,"Usuário Cadastrado",
                    "INFO", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void inserirPessoaJuridica(Juridica pessoaJuridica) throws SQLException{
    	try{
        	//cadastro de login

        	String sql = "INSERT INTO login(usuario, senha, tipo) VALUES(?, ?, ?)";
        	
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, pessoaJuridica.getUsuario());
            stmt.setString(2, pessoaJuridica.getSenha());
            stmt.setInt(3, 2);
            
            stmt.execute();
            
            ResultSet rs = stmt.getGeneratedKeys();
	        rs.next();
	        int idGerado = rs.getInt(1);
	        pessoaJuridica.setIdLogin(idGerado);
	        
	        stmt.close();
            
            //cadastro de pessoa
            sql = "INSERT INTO pessoa(nome, email, telefone, login_idLogin) VALUES(?, ?, ?, ?)";
            
        	stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        stmt.setString(1, pessoaJuridica.getNome());	
	        stmt.setString(2, pessoaJuridica.getEmail());
	        stmt.setString(3, pessoaJuridica.getTelefone());
	        stmt.setInt(4, pessoaJuridica.getIdLogin());
	        stmt.execute();
	        
	        rs = stmt.getGeneratedKeys();
	        rs.next();
	        idGerado = rs.getInt(1);
	        pessoaJuridica.setIdPessoa(idGerado);
	        stmt.close();
	        
	        //cadastro de preços
            sql = "INSERT INTO preco(gasolinaComum, gasolinaAditivada) VALUES(?, ?)";
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        stmt.setFloat(1, pessoaJuridica.getGasolinaComum());	
	        stmt.setFloat(2, pessoaJuridica.getGasolinaAditivada());
	        stmt.execute();
	        
	        rs = stmt.getGeneratedKeys();
	        rs.next();
	        idGerado = rs.getInt(1);
	        pessoaJuridica.setIdPreco(idGerado);
	        stmt.close();
            
	        //cadastro endereco
	        sql = "INSERT INTO endereco(cidade, bairro, rua, numero, complemento) VALUES(?, ?, ?, ?, ?)";
            
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        stmt.setString(1, pessoaJuridica.getCidade());	
	        stmt.setString(2, pessoaJuridica.getBairro());	
	        stmt.setString(3, pessoaJuridica.getRua());	
	        stmt.setString(4, pessoaJuridica.getNumero());
	        stmt.setString(5, pessoaJuridica.getComplemento());
	        stmt.execute();
	        
	        rs = stmt.getGeneratedKeys();
	        rs.next();
	        idGerado = rs.getInt(1);
	        pessoaJuridica.setIdEndereco(idGerado);
	        stmt.close();
	        
	        //cadastro pessoa juridica
            sql = "INSERT INTO juridica(cnpj, preco_idPreco, endereco_idEndereco, pessoa_idPessoa) VALUES(?, ?, ?, ?)";
            
            stmt = conexao.prepareStatement(sql);
	        stmt.setString(1, pessoaJuridica.getCnpj());	
	        stmt.setInt(2, pessoaJuridica.getIdPreco());	
	        stmt.setInt(3, pessoaJuridica.getIdEndereco());	
	        stmt.setInt(4, pessoaJuridica.getIdPessoa());
	        stmt.execute();
	        stmt.close(); 
        } catch (SQLException ex){
            System.out.println("Erro: "+ ex.getMessage());
        }
        
        JOptionPane.showMessageDialog(null,"Posto Cadastrado",
                    "INFO", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static boolean validarLogin(Login login){
        String query = "SELECT * FROM login";
        
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                String usuario = rs.getString("usuario");
                String senha = rs.getString("senha");
                
                if(usuario.equals(login.getUsuario()) && senha.equals(login.getSenha())){
                    return true;
                }
            }
        } catch(Exception e){
            System.out.println("Erro: "+ e.getMessage());
        }
        return false;
    }

    public static void listarPosto(JTextArea textArea, JComboBox<String> comboBox){
        String query = "\r\n" + 
        		"SELECT pes.nome, ende.cidade, ende.bairro, ende.rua, pre.gasolinaComum, pre.gasolinaAditivada From juridica jur\r\n" + 
        		"Inner join preco pre On jur.preco_idPreco = pre.idPreco\r\n" +
        		"Inner join pessoa pes On jur.pessoa_idPessoa  = pes.idPessoa\r\n" + 
        		"Inner join endereco ende On jur.endereco_idEndereco = ende.idEndereco";
        
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            String texto = "";
            while(rs.next()){
                String nome = rs.getString("pes.nome");
                String endeCidade = rs.getString("ende.cidade");
                String endeBairro = rs.getString("ende.bairro");
                String endeRua = rs.getString("ende.rua");
                float gasolinaComum = rs.getFloat("pre.gasolinaComum");
                float gasolinaAditivada = rs.getFloat("pre.gasolinaAditivada");
                
                texto = "" + texto + ""+ nome + "\nEndereço: "+ endeCidade +
                		", "+ endeBairro +", "+ endeRua +
                		"\n\nGasolina Comum: "+ gasolinaComum +
                		"\nGasolina Aditivada: "+ gasolinaAditivada +
                		"\n\n----------\n\n";
                
                textArea.setText(texto);
                
                comboBox.addItem(nome);
            }
        } catch(Exception e){
            System.out.println("Erro: "+ e.getMessage());
        }
    }

    public static Avaliacao avaliar(Login login, String posto, Avaliacao avaliacao){
        String query = "select idPessoa from pessoa where nome = '"+ posto +"'";
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            rs.next();
            int idPessoa = rs.getInt("idPessoa");
            
            query = "SELECT idJuridica From juridica where pessoa_idPessoa = "+ idPessoa;
            rs = st.executeQuery(query);
            
            rs.next();
            String idJuridica = rs.getString("idJuridica");
            
	        avaliacao.setIdJuridica(Integer.parseInt(idJuridica));
            
	       
            String sql = "INSERT INTO avaliacao(comentario, juridica_idJuridica) VALUES (?, ?)";
            
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, avaliacao.getComentario());
            stmt.setInt(2, avaliacao.getIdJuridica());
            
            stmt.execute();
            
            rs = stmt.getGeneratedKeys();
	        rs.next();
	        int idGerado = rs.getInt(1);
	        avaliacao.setIdAvaliacao(idGerado);

	        stmt.close(); 
	        
	        query = "SELECT idLogin From login where usuario = '"+ login.getUsuario()+"'";
	        rs = st.executeQuery(query);
            
	        rs.next();
            int idLogin = rs.getInt("idLogin");
            
            query = "SELECT idFisica, pes.login_idLogin From fisica fis \r\n" + 
            		"Inner join pessoa pes On fis.pessoa_idPessoa = pes.idPessoa";
	        rs = st.executeQuery(query);
	        
	        int idFisica = 0;
	        while(rs.next()){
	        	int pessoa_idLogin = rs.getInt("pes.login_idLogin");
	        	if(pessoa_idLogin == idLogin) {
	        		idFisica = rs.getInt("idFisica");
	        		break;
	        	}
	        }
            sql = "UPDATE fisica SET avaliacao_idAvaliacao = "+ idGerado +" WHERE idFisica = "+ idFisica;
	        
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            stmt.execute();
	        stmt.close(); 
	        return avaliacao;
        } catch(Exception e){
            System.out.println("Erro: "+ e.getMessage());
        
        }
		return avaliacao;
    }

    public static void listarAvaliacao(JTextArea textArea, Login login, String posto) throws SQLException {
    	String queryAux = "\r\n" + 
    			"SELECT idJuridica FROM juridica jur\r\n" + 
    			"Inner join pessoa pes On jur.pessoa_idPessoa = pes.idPessoa\r\n" + 
    			"WHERE pes.nome = '"+ posto +"'";
    	
    	Statement stAux = conexao.createStatement();
        ResultSet rsAux = stAux.executeQuery(queryAux);
        rsAux.next();
        int idJuridica = rsAux.getInt("idJuridica");
    	
    	String query = "\r\n" + 
        		"SELECT pes.nome, pes.login_idLogin, ava.comentario From fisica fis\r\n" + 
        		"Inner join avaliacao ava On fis.avaliacao_idAvaliacao = ava.idAvaliacao\r\n" +
        		"Inner join pessoa pes On fis.pessoa_idPessoa = pes.idPessoa WHERE ava.juridica_idJuridica = "+ idJuridica;
        //try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            String texto = "";
            
            while(rs.next()){
            	int idLogin = rs.getInt("pes.login_idLogin");
                
            	String query2 = "SELECT usuario FROM login WHERE idLogin = "+ idLogin;
     
            	Statement st2 = conexao.createStatement();
            	ResultSet rs2 = st2.executeQuery(query2);
            	
            	rs2.next();
                String usuario = rs2.getString("usuario");
                String comentario = rs.getString("ava.comentario");
                
                texto = "" + texto + ""+ usuario + "\n\nAvaliação: "+ comentario +
                		"\n\n----------\n\n";
                
                textArea.setText(texto);
       //     }
        //} catch(Exception e){
         //   System.out.println("Erro: "+ e.getMessage());
        }
    }
    
    public static void infoPosto(String posto, JLabel lblPosto, JLabel lblContato, JLabel lblEndereco, JLabel lblComum, JLabel lblAditivada) {
    	String query = "\r\n" + 
        		"SELECT cnpj, pes.email, pes.telefone, ende.cidade, ende.bairro, ende.rua, pre.gasolinaComum, pre.gasolinaAditivada " +
        		"FROM juridica jur \r\n" + 
        		"Inner join preco pre On jur.preco_idPreco = pre.idPreco\r\n" +
        		"Inner join pessoa pes On jur.pessoa_idPessoa  = pes.idPessoa\r\n" + 
        		"Inner join endereco ende On jur.endereco_idEndereco = ende.idEndereco WHERE pes.nome = '"+ posto +"'";
        
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            String contato = "";
            
            rs.next();
                String nome = posto;
                String cnpj = rs.getString("cnpj");
                
                String email = rs.getString("pes.email");
                String telefone = rs.getString("pes.telefone");
                
                String endeCidade = rs.getString("ende.cidade");
                String endeBairro = rs.getString("ende.bairro");
                String endeRua = rs.getString("ende.rua");
                
                float gasolinaComum = rs.getFloat("pre.gasolinaComum");
                float gasolinaAditivada = rs.getFloat("pre.gasolinaAditivada");
                
                contato = "" + contato + "Contato: "+ email + " \\ "+ telefone;
                
                lblPosto.setText(nome + " | CNPJ: "+ cnpj);
                lblContato.setText(contato);
                lblEndereco.setText("Endereço: "+ endeCidade +", "+ endeBairro + ", "+ endeRua);
                lblComum.setText("Gasolina Comum: "+ gasolinaComum);
                lblAditivada.setText("Gasolina Aditivada: "+ gasolinaAditivada);
        } catch(Exception e){
            System.out.println("Erro: "+ e.getMessage());
        }
    }
    
    //verifica se o login é de pessoa fisica ou juridica
    public static boolean verificarLogin(Login login) {
    	String query = "SELECT tipo FROM login WHERE usuario = '"+ login.getUsuario() +"'" ;
         
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            rs.next();
            int tipo = rs.getInt("tipo");
                
            if(tipo == 1){
            	return true;
            } else {
            	return false;
            }
        }
        catch(Exception e){
            System.out.println("Erro: "+ e.getMessage());
        }
        return false;
    }
    
    public static void updateGasolina(Login login, boolean opcao, float valor) {
    	String queryAux = "SELECT idPessoa FROM pessoa pes\r\n" + 
    					  "Inner join login log On pes.login_idlogin = log.idlogin\r\n" + 
    					  "WHERE log.usuario = '"+ login.getUsuario() +"'";
    	try {
	    	Statement stAux = conexao.createStatement();
	        ResultSet rsAux = stAux.executeQuery(queryAux);
	        
	        rsAux.next();
	        int idPessoa = rsAux.getInt("idPessoa");
	        
	        String queryAux2 = "SELECT pre.idPreco from juridica jur\r\n" + 
	        				   "INNER JOIN preco pre ON jur.preco_idPreco = pre.idPreco\r\n" + 
	        				   "WHERE pessoa_idPessoa = "+ idPessoa;
	        
	        Statement stAux2 = conexao.createStatement();
	        ResultSet rsAux2 = stAux2.executeQuery(queryAux2);
	        
	        rsAux2.next();
	        int idPreco = rsAux2.getInt("pre.idPreco");
	        
	        //se opcao true, então gasolina comum
	    	if(opcao) {
	    		String sql = "UPDATE preco SET gasolinaComum = "+ valor +" WHERE idPreco = "+ idPreco;
		        
	    		PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            
	            stmt.execute();
		        stmt.close(); 
	    	}
	    	//se opcao false, então gasolina aditivada
	    	else if(!opcao){
	    		String sql = "UPDATE preco SET gasolinaAditivada = "+ valor +" WHERE idPreco = "+ idPreco;
		        
	    		PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            
	            stmt.execute();
		        stmt.close(); 
	    	}

	    	JOptionPane.showMessageDialog(null,"Valor da gasolina alterado para $"+ valor,
	                    "INFO", JOptionPane.INFORMATION_MESSAGE);
	        
    	} catch(Exception e){
    		System.out.println("Erro: "+ e.getMessage());
    	}	
    }
    
    /*
    public static void inserirFuncionario(Funcionario funcionario){
        String sql = "INSERT INTO funcionario(nome, cpf, cep, email, telefone, salario, cargo) VALUES(?, ?, ?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getCep());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getTelefone());
            stmt.setFloat(6, funcionario.getSalario());
            stmt.setString(7, funcionario.getCargo());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException ex){
            System.out.println("Erro: "+ ex.getMessage());
        }
        JOptionPane.showMessageDialog(null,"Funcionario Cadastrado",
                    "INFO", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void inserirInstrumento(Instrumento instrumento){
        String sql = "INSERT INTO instrumento(nome, marca, valor) VALUES(?, ?, ?)";
        
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, instrumento.getNomeInstrumento());
            stmt.setString(2, instrumento.getMarca());
            stmt.setFloat(3, instrumento.getValor());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex){
            System.out.println("Erro: "+ ex.getMessage());
        }
        JOptionPane.showMessageDialog(null,"Instrumento Cadastrado",
                    "INFO", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void listarCliente(ListarCliente lc){
        String query = "SELECT * FROM pessoa";
        
        lc.limparTabela();
        
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                String id = Integer.toString(rs.getInt("id")) ;
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                String cpf = rs.getString("cpf");
                String cep = rs.getString("cep");

                lc.popularTabela(id, telefone, cpf, cep, nome, email);
            }
        } catch(Exception e){
            System.out.println("Erro: "+ e.getMessage());
        }
    }
    
    public static void listarFuncionario(ListarFuncionario lf){
        String query = "SELECT * FROM funcionario";
        
        lf.limparTabela();
        
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                String id = Integer.toString(rs.getInt("id")) ;
                String telefone = rs.getString("telefone");
                String cpf = rs.getString("cpf");
                String cep = rs.getString("cep");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String salario = rs.getString("salario");
                String cargo = rs.getString("cargo");
                
                lf.popularTabela(id, telefone, cpf, cep, nome, email, salario, cargo);
            }
        } catch(Exception e){
            System.out.println("Erro: "+ e.getMessage());
        }
    }
    public static void listarInstrumento(ListarInstrumento li){
        String query = "SELECT * FROM instrumento";
        
        li.limparTabela();
        
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                String id = Integer.toString(rs.getInt("id")) ;
                String nome = rs.getString("nome");
                String marca = rs.getString("marca");
                String valor = rs.getString("valor");
                
                li.popularTabela(id, nome, marca, valor);
            }
        } catch(Exception e){
            System.out.println("Erro: "+ e.getMessage());
        }
    }
    public static void apagarFuncionario(Object id) {
        String sql = "DELETE FROM funcionario WHERE id='"+ id +"'";
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex){
            System.out.println("Erro: "+ ex.getMessage());
        }
        JOptionPane.showMessageDialog(null,"Funcionario removido",
            "INFO", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void apagarInstrumento(Object id) {
        String sql = "DELETE FROM instrumento WHERE id='"+ id +"'";
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex){
            System.out.println("Erro: "+ ex.getMessage());
        }
        JOptionPane.showMessageDialog(null,"Instrumento removido",
            "INFO", JOptionPane.INFORMATION_MESSAGE);
    }
    */
}
    
