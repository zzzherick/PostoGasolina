package avaliacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import banco.Banco;
import login.Login;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AvaliacaoPosto extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AvaliacaoPosto frame = new AvaliacaoPosto(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param posto 
	 * @param login 
	 * @throws SQLException 
	 */
	public AvaliacaoPosto(Login login, String posto) throws SQLException {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 136, 414, 2);
		contentPane.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 149, 414, 131);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JLabel lblPosto = new JLabel("ponto");
		lblPosto.setForeground(Color.GREEN);
		lblPosto.setBounds(10, 11, 300, 14);
		contentPane.add(lblPosto);
		
		JLabel lblContato = new JLabel("contato");
		lblContato.setBounds(10, 111, 283, 14);
		contentPane.add(lblContato);
		
		JLabel lblImagem = new JLabel("Imagem");
		lblImagem.setBounds(378, 11, 46, 14);
		contentPane.add(lblImagem);
		
		JLabel lblEndereco = new JLabel("endereco");
		lblEndereco.setBounds(10, 36, 300, 14);
		contentPane.add(lblEndereco);
		
		JLabel lblComum = new JLabel("comum");
		lblComum.setBounds(10, 61, 283, 14);
		contentPane.add(lblComum);
		
		JLabel lblAditivada = new JLabel("aditivada");
		lblAditivada.setBounds(10, 86, 283, 14);
		contentPane.add(lblAditivada);
		
		Banco.listarAvaliacao(textArea, login, posto);
		Banco.infoPosto(posto, lblPosto, lblContato, lblEndereco, lblComum, lblAditivada);
	}
}
