	package principal;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import banco.Banco;
import login.LoginFrame;
import pessoa.CadastroFisica;
import pessoa.CadastroJuridica;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame{
	
	//LoginFrame login = new LoginFrame();
	private JPanel contentPane;
	public Principal() {
		super("Trabalho Banco de Dados");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setResizable(false);
        Container c = getContentPane();
        getContentPane().setLayout(null);

        Banco.getConexao();
         
        /*contentPane = new JPanel();
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        setContentPane(contentPane);
        contentPane.setLayout(null);*/
       // login.setBounds(-10008, -10031, 215, 190);
        
        //getContentPane().add(login);
        
        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBounds(130, 11, 15, 449);
        getContentPane().add(separator);
        
        JLabel lbl1 = new JLabel("");
        lbl1.setBounds(310, 110, 100, 100);
        getContentPane().add(lbl1);
        lbl1.setIcon(new ImageIcon("image\\bomba2.png"));
        
        JLabel lbl2 = new JLabel("");
        lbl2.setBounds(420, 110, 100, 100);
        getContentPane().add(lbl2);
        lbl2.setIcon(new ImageIcon("image\\enchedor.png"));
        
        JLabel lbl3 = new JLabel("");
        lbl3.setBounds(310, 221, 100, 100);
        getContentPane().add(lbl3);
        lbl3.setIcon(new ImageIcon("image\\oleo.png"));
        
        JLabel lbl4 = new JLabel("");
        lbl4.setBounds(420, 221, 100, 100);
        getContentPane().add(lbl4);
        lbl4.setIcon(new ImageIcon("image\\carro.png"));
        
        JButton btnLogin = new JButton("LOGIN");
        btnLogin.addActionListener(e -> {
        	LoginFrame login = new LoginFrame();
            login.setVisible(true);
            /*mudando cor de uma string
             * 
             *     String texto = "<html><font color= #0000FF >OLA MUNDO</font></html>";
            		JOptionPane.showMessageDialog(null, texto);
             */
        });
        btnLogin.setBounds(10, 11, 110, 23);
        getContentPane().add(btnLogin);
        
        JButton btnFisica = new JButton("FISICA");
        btnFisica.addActionListener(e -> {
        	CadastroFisica cadastroFisica = new CadastroFisica();
            cadastroFisica.setVisible(true);
        	
        });
        btnFisica.setBounds(10, 45, 110, 23);
        getContentPane().add(btnFisica);
        
        JButton btnJuridico = new JButton("JURIDICA");
        btnJuridico.addActionListener(e -> {
        	CadastroJuridica cadastroJuridica = new CadastroJuridica();
            cadastroJuridica.setVisible(true);
        	
        });
        btnJuridico.setBounds(10, 79, 110, 23);
        getContentPane().add(btnJuridico);
        
	}
	 public static void main(String[] args) {
	        new Principal().setVisible(true);
	}   
}
