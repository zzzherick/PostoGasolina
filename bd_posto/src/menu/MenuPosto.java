package menu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import avaliacao.AvaliacaoFrame;
import avaliacao.AvaliacaoPosto;
import banco.Banco;
import login.Login;
import pessoa.CadastroFisica;
import pessoa.CadastroJuridica;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.Font;

public class MenuPosto extends JFrame {

	private JPanel contentPane;
	private JTextField txtGasolina;
    private boolean comum = false;
    private boolean aditivada = false;
    private JCheckBox chckbxComum;
    private JCheckBox chckbxAditivada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPosto frame = new MenuPosto(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPosto(Login login) {
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 490, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 334, 211);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(354, 11, 110, 22);
		contentPane.add(comboBox);
		
		JButton btnAvaliaes = new JButton("AVALIA\u00C7\u00D5ES");
		btnAvaliaes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String posto = (String) comboBox.getSelectedItem();
				
				try {
					AvaliacaoPosto avaliacaoPosto = new AvaliacaoPosto(login, posto);

					avaliacaoPosto.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAvaliaes.setBounds(354, 43, 110, 23);
		contentPane.add(btnAvaliaes);
		
		JLabel lblPreco = new JLabel("Pre\u00E7o");
		lblPreco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPreco.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreco.setBounds(354, 77, 110, 28);
		contentPane.add(lblPreco);
		
		chckbxComum = new JCheckBox("Comum");
		chckbxComum.setBounds(350, 112, 97, 23);
		contentPane.add(chckbxComum);
		
		chckbxAditivada = new JCheckBox("Aditivada");
		chckbxAditivada.setBounds(350, 138, 97, 23);
		contentPane.add(chckbxAditivada);
		
		txtGasolina = new JTextField();
		txtGasolina.setBounds(354, 168, 110, 20);
		contentPane.add(txtGasolina);
		txtGasolina.setColumns(10);
		
		Banco.listarPosto(textArea, comboBox);
		
		chckbxComum.addActionListener(evt -> checkBox(evt));
		chckbxAditivada.addActionListener(evt -> checkBox(evt));
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comum && aditivada) {
			        JOptionPane.showMessageDialog(null,"As duas caixas estão selecionadas",
			                    "ERRO", JOptionPane.INFORMATION_MESSAGE);
			        comum = false;
			        aditivada = false;
				}
			    else if(comum) {
			    	Banco.updateGasolina(login, true, Float.parseFloat(txtGasolina.getText()));
			    	Banco.listarPosto(textArea, comboBox);
			    }
			    else if(aditivada) {
			    	Banco.updateGasolina(login, false, Float.parseFloat(txtGasolina.getText()));
			    	Banco.listarPosto(textArea, comboBox);
			    }
			    else {
			        JOptionPane.showMessageDialog(null,"Selecione Comum ou Aditivada",
			                    "INFO", JOptionPane.INFORMATION_MESSAGE);
			    }
				txtGasolina.setText("");
			}
		});
		btnAlterar.setBounds(354, 199, 110, 23);
		contentPane.add(btnAlterar);
		
		
		
	}
	public void checkBox(java.awt.event.ActionEvent evt) {
        //manipulando JCheckBox
        if (chckbxComum.isSelected()) {
        	this.comum = true;
        }
        else {
        	this.comum = false;
        }
        
        if (chckbxAditivada.isSelected()) {
        	this.aditivada = true;
        }
        else {
        	this.aditivada = false;
        }
    }

	 //public static void main(String[] args) {
	       // new LoginFrame().setVisible(true);
	//}   
}
