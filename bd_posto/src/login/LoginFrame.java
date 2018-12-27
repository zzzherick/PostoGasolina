package login;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import avaliacao.AvaliacaoFrame;
import pessoa.CadastroFisica;
import pessoa.CadastroJuridica;
import pessoa.Juridica;
import principal.Principal;
import menu.MenuPosto;
import menu.MenuUsuario;
import banco.Banco;

//import pessoa.Fisica;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
	private JTextField textLogin;
	private JPasswordField passwordLogin;
    private JPanel contentPane;
    
    private JCheckBox chckbxFsica;
    private boolean fisica = false;
    private JCheckBox chckbxJurdica;
    private boolean juridica = false;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	

	/**
	 * Create the frame.
	 * @param c 
	 */
	public LoginFrame() {
        //this.setClosable(true);
		setBounds(100, 100, 215, 190);
		getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogin.setBounds(10, 55, 46, 17);
		getContentPane().add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(10, 86, 46, 14);
		getContentPane().add(lblSenha);
		
		textLogin = new JTextField();
		textLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textLogin.setBounds(66, 55, 128, 20);
		getContentPane().add(textLogin);
		textLogin.setColumns(10);
		
		chckbxFsica = new JCheckBox("F\u00EDsica");
		chckbxFsica.setBounds(66, 14, 67, 23);
		getContentPane().add(chckbxFsica);
		
		chckbxJurdica = new JCheckBox("Jur\u00EDdica");
		chckbxJurdica.setBounds(129, 14, 76, 23);
		getContentPane().add(chckbxJurdica);
		
		JLabel lblPessoa = new JLabel("Pessoa:");
		lblPessoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPessoa.setBounds(10, 16, 57, 14);
		getContentPane().add(lblPessoa);
		
		passwordLogin = new JPasswordField();
		passwordLogin.setBounds(66, 85, 128, 20);
		getContentPane().add(passwordLogin);

		chckbxFsica.addActionListener(evt -> checkBox(evt));
		chckbxJurdica.addActionListener(evt -> checkBox(evt));
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//chckbxFsica.addActionListener(evt -> checkBox(evt));
			    if(fisica && juridica) {
			        JOptionPane.showMessageDialog(null,"As duas caixas estão selecionadas",
			                    "ERRO", JOptionPane.INFORMATION_MESSAGE);
			        fisica = false;
			        juridica = false;
				}
			    else if(fisica) {
			        new CadastroFisica().setVisible(true);
			    }
			    else if(juridica) {
			    	new CadastroJuridica().setVisible(true);
			    }
			    else {
			        JOptionPane.showMessageDialog(null,"Selecione Fisica ou Juridica",
			                    "INFO", JOptionPane.INFORMATION_MESSAGE);
			    }
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrar.setBounds(10, 121, 89, 23);
		getContentPane().add(btnCadastrar);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(e -> {
			Login login = new Login(textLogin.getText(), (new String(passwordLogin.getPassword())));
			
			chckbxFsica.addActionListener(evt -> checkBox(evt));
		    if(fisica && juridica) {
		        JOptionPane.showMessageDialog(null,"As duas caixas estão selecionadas",
		                    "ERRO", JOptionPane.INFORMATION_MESSAGE);
		        fisica = false;
		        juridica = false;
			}
		    else if(fisica) {
		    	if(Banco.validarLogin(login)) {
		    		//verifica se o login de fato é de pessoa fisica
		    		if(Banco.verificarLogin(login)) {
						MenuUsuario menuUsuario = new MenuUsuario(login);
						menuUsuario.setVisible(true);
		    		} else {
		    			JOptionPane.showMessageDialog(null,"Opção inválida de Login",
			                    "ERRO", JOptionPane.INFORMATION_MESSAGE);
		    		}
				} else {
					JOptionPane.showMessageDialog(null,"Login inválido",
		                    "ERRO", JOptionPane.INFORMATION_MESSAGE);
				}
		    }
		    else if(juridica) {
		    	if(Banco.validarLogin(login)) {
		    		//verifica se o login de fato é de pessoa juridica
		    		if(Banco.verificarLogin(login)) {
		    			JOptionPane.showMessageDialog(null,"Opção inválida de Login",
			                    "ERRO", JOptionPane.INFORMATION_MESSAGE);
		    		} else {
		    			MenuPosto menuPosto = new MenuPosto(login);
						menuPosto.setVisible(true);
		    		}
				} else {
					JOptionPane.showMessageDialog(null,"Login inválido",
		                    "ERRO", JOptionPane.INFORMATION_MESSAGE);
				}
		    }
		    else {
		        JOptionPane.showMessageDialog(null,"Selecione Fisica ou Juridica",
		                    "INFO", JOptionPane.INFORMATION_MESSAGE);
		    }
			//Juridica pessoaJuridica = new Juridica(login, senha, nomePosto, email, telefone, cnpj, gasolinaComum,
        	//		gasolinaAditivada, cidade, bairro, rua, numero, complemento);
			
	    });
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogin.setBounds(105, 121, 89, 23);
		getContentPane().add(btnLogin);

	}
	public void checkBox(java.awt.event.ActionEvent evt) {
        //manipulando JCheckBox
        if (chckbxFsica.isSelected()) {
        	this.fisica = true;
        }
        else {
        	this.fisica = false;
        }
        
        if (chckbxJurdica.isSelected()) {
        	this.juridica = true;
        }
        else {
        	this.juridica = false;
        }
    }

	 public static void main(String[] args) {
	       // new LoginFrame().setVisible(true);
	}   
}
