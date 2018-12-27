package pessoa;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import banco.Banco;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class CadastroJuridica extends JFrame {
	private JTextField textNomePosto;
	private JTextField textLogin;
	private JTextField textEmail;
	private JPasswordField passwordSenha;
	private JPasswordField passwordConfirmaSenha;
	private JTextField textGasolinaComum;
	private JTextField textGasolinaAditivada;
	private JTextField textCidade;
	private JTextField textBairro;
	private JTextField textRua;
	private JTextField textNumero;
	private JTextField textComplemento;
	
	private MaskFormatter maskFormatter;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroJuridica frame = new CadastroJuridica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 * Create the frame.
	 */
	public CadastroJuridica() {
		setBounds(100, 100, 370, 442);
		getContentPane().setLayout(null);
		
		JLabel lblNomePosto = new JLabel("Nome do Posto:");
		lblNomePosto.setBounds(88, 13, 113, 14);
		lblNomePosto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomePosto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblNomePosto);
		
		textNomePosto = new JTextField();
		textNomePosto.setBounds(211, 11, 125, 20);
		textNomePosto.setColumns(10);
		getContentPane().add(textNomePosto);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(155, 37, 46, 14);
		lblLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblLogin);
		
		textLogin = new JTextField();
		textLogin.setBounds(211, 36, 125, 20);
		textLogin.setColumns(10);
		getContentPane().add(textLogin);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(155, 62, 46, 14);
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(211, 61, 125, 20);
		textEmail.setColumns(10);
		getContentPane().add(textEmail);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(139, 87, 62, 14);
		lblTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblTelefone);
		
		try {
            maskFormatter = new MaskFormatter("(##) #####-####");
            maskFormatter.setPlaceholderCharacter('_');
        } catch (ParseException pex) {
        }
		JFormattedTextField textTelefone = new JFormattedTextField(maskFormatter);
		textTelefone.setBounds(211, 86, 125, 20);
		textTelefone.setColumns(10);
		getContentPane().add(textTelefone);
		
		JLabel lblCNPJ = new JLabel("CNPJ:");
		lblCNPJ.setBounds(155, 112, 46, 14);
		lblCNPJ.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCNPJ.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblCNPJ);
		
		try {
            maskFormatter = new MaskFormatter("##.###.###/####-##");
            maskFormatter.setPlaceholderCharacter('_');
        } catch (ParseException pex) {
        }
		JFormattedTextField textCnpj = new JFormattedTextField(maskFormatter);
		textCnpj.setBounds(211, 111, 125, 20);
		textCnpj.setColumns(10);
		getContentPane().add(textCnpj);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(155, 314, 46, 14);
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblSenha);
		
		passwordSenha = new JPasswordField();
		passwordSenha.setBounds(211, 313, 125, 20);
		getContentPane().add(passwordSenha);
		
		JLabel lblConfirmaSenha = new JLabel("Confirma Senha:");
		lblConfirmaSenha.setBounds(88, 339, 113, 14);
		lblConfirmaSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmaSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblConfirmaSenha);
		
		passwordConfirmaSenha = new JPasswordField();
		passwordConfirmaSenha.setBounds(211, 338, 125, 20);
		getContentPane().add(passwordConfirmaSenha);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(101, 369, 100, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnCancelar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomePosto = textNomePosto.getText();
		        String login = textLogin.getText();
		        String email = textEmail.getText();
		        String telefone = textTelefone.getText();
		        String cnpj = textCnpj.getText();
		        
		        float gasolinaComum = Float.parseFloat(textGasolinaComum.getText());
		        float gasolinaAditivada = Float.parseFloat(textGasolinaAditivada.getText());
		        
		        String cidade = textCidade.getText();
		        String bairro = textBairro.getText();
		        String rua = textRua.getText();
		        String numero = textNumero.getText();
		        String complemento = textComplemento.getText();
		        
		        //verificar
		        //int idPreco = 0;
		        
		        String senha = new String(passwordSenha.getPassword());
		        String confirmaSenha = new String(passwordConfirmaSenha.getPassword());
		        
		        if(senha.equals(confirmaSenha)) {
		        	Juridica pessoaJuridica = new Juridica(login, senha, nomePosto, email, telefone, cnpj, gasolinaComum,
		        			gasolinaAditivada, cidade, bairro, rua, numero, complemento);
		        	
			        textNomePosto.setText("");
			        textLogin.setText("");
			        textEmail.setText("");
			        textTelefone.setText("");
			        textCnpj.setText("");
			        textCidade.setText("");
			        textBairro.setText("");
			        textRua.setText("");
			        textNumero.setText("");
			        textComplemento.setText("");
			        passwordSenha.setText("");
			        passwordConfirmaSenha.setText("");
			        
			        try {
						Banco.inserirPessoaJuridica(pessoaJuridica);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
		        } else {
		        	JOptionPane.showMessageDialog(null, "Senha diferente!!!");
		        }
			}
		});
		btnCadastrar.setBounds(211, 369, 100, 23);
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnCadastrar);
		
		JLabel lblGasolinaComum = new JLabel("Gasolina Comum $:");
		lblGasolinaComum.setBounds(51, 137, 150, 14);
		lblGasolinaComum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGasolinaComum.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblGasolinaComum);
		
		textGasolinaComum = new JTextField();
		textGasolinaComum.setColumns(10);
		textGasolinaComum.setBounds(211, 136, 125, 20);
		getContentPane().add(textGasolinaComum);
		
		JLabel lblGasolinaAditivada = new JLabel("Gasolina Aditivada $:");
		lblGasolinaAditivada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGasolinaAditivada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGasolinaAditivada.setBounds(51, 163, 150, 14);
		getContentPane().add(lblGasolinaAditivada);
		
		textGasolinaAditivada = new JTextField();
		textGasolinaAditivada.setColumns(10);
		textGasolinaAditivada.setBounds(211, 162, 125, 20);
		getContentPane().add(textGasolinaAditivada);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCidade.setBounds(139, 188, 62, 14);
		getContentPane().add(lblCidade);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBairro.setBounds(139, 213, 62, 14);
		getContentPane().add(lblBairro);
		
		JLabel lblRua = new JLabel("Rua:");
		lblRua.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRua.setBounds(155, 238, 46, 14);
		getContentPane().add(lblRua);
		
		JLabel lblNumero = new JLabel("N\u00FAmero:");
		lblNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumero.setBounds(139, 263, 62, 14);
		getContentPane().add(lblNumero);
		
		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComplemento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblComplemento.setBounds(101, 288, 100, 14);
		getContentPane().add(lblComplemento);
		
		textCidade = new JTextField();
		textCidade.setColumns(10);
		textCidade.setBounds(211, 187, 125, 20);
		getContentPane().add(textCidade);
		
		textBairro = new JTextField();
		textBairro.setColumns(10);
		textBairro.setBounds(211, 212, 125, 20);
		getContentPane().add(textBairro);
		
		textRua = new JTextField();
		textRua.setColumns(10);
		textRua.setBounds(211, 237, 125, 20);
		getContentPane().add(textRua);
		
		textNumero = new JTextField();
		textNumero.setColumns(10);
		textNumero.setBounds(211, 262, 125, 20);
		getContentPane().add(textNumero);
		
		textComplemento = new JTextField();
		textComplemento.setColumns(10);
		textComplemento.setBounds(211, 287, 125, 20);
		getContentPane().add(textComplemento);

	}
}
