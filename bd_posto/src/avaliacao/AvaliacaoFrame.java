package avaliacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import banco.Banco;
import login.Login;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Container;

public class AvaliacaoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textComentario;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AvaliacaoFrame frame = new AvaliacaoFrame(null, null);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param login 
	 * @param posto 
	 * @param c 
	 * @throws SQLException 
	 */
	public AvaliacaoFrame(Login login, String posto) throws SQLException {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		textComentario = new JTextField();
		textComentario.setBounds(10, 149, 315, 20);
		contentPane.add(textComentario);
		textComentario.setColumns(10);
		
		JButton btnAvaliar = new JButton("AVALIAR");
		btnAvaliar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Avaliacao avaliacao = new Avaliacao();
				avaliacao.setComentario(textComentario.getText());
				
				Banco.avaliar(login, posto, avaliacao);
				try {
					Banco.listarAvaliacao(textArea, login, posto);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAvaliar.setBounds(335, 148, 89, 23);
		contentPane.add(btnAvaliar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 180, 414, 2);
		contentPane.add(separator);
		
		JLabel lblPosto = new JLabel("");
		lblPosto.setForeground(Color.GREEN);
		lblPosto.setBounds(10, 11, 300, 14);
		contentPane.add(lblPosto);
		
		JLabel lblContato = new JLabel("");
		lblContato.setBounds(10, 111, 283, 14);
		contentPane.add(lblContato);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 136, 414, 2);
		contentPane.add(separator_1);
		
		JLabel lblImagem = new JLabel("Imagem");
		lblImagem.setBounds(378, 11, 46, 14);
		contentPane.add(lblImagem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 193, 414, 131);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 128, 414, 122);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		contentPane.add(scrollPane);
		
		JLabel lblEndereco = new JLabel("Endereco");
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
