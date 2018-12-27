package menu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.border.TitledBorder;

import avaliacao.AvaliacaoFrame;
import banco.Banco;
import login.Login;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import java.awt.Choice;
import java.awt.Button;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MenuUsuario extends JFrame {

	private JPanel contentPane;
//	private AvaliacaoFrame avaliacaoFrame = new AvaliacaoFrame();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUsuario frame = new MenuUsuario(null);
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
	 * @param c 
	 */
	public MenuUsuario(Login login) {
		//this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 480, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 334, 211);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 0, 4, 22);
		//contentPane.add(textArea);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(344, 11, 110, 22);
		contentPane.add(comboBox);

		scrollPane.setViewportView(textArea);

		contentPane.add(scrollPane);
		//contentPane.add(avaliacaoFrame);
		
		textArea.setEditable(false);

		Banco.listarPosto(textArea, comboBox);
		
		JButton btnAvaliar = new JButton("AVALIAR");
		btnAvaliar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String posto = (String) comboBox.getSelectedItem();
				
				try {
					AvaliacaoFrame avaliacaoFrame = new AvaliacaoFrame(login, posto);
					avaliacaoFrame.setVisible(true);
					//avaliacaoFrame.setVisible(true);
					//avaliacaoFrame.avaliacao(login, posto);
					//avaliacaoFram/(login, posto).setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAvaliar.setBounds(344, 44, 110, 23);
		contentPane.add(btnAvaliar);
	}
}
