package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controleur.GestionFenConnexion;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.SystemColor;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Label;

public class FenConnexion extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField UserField;
	private GestionFenConnexion gestionClicFenConnexion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenConnexion frame = new FenConnexion();
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
	public FenConnexion() {
		
		this.gestionClicFenConnexion = new GestionFenConnexion(this);
		
		try {
		    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 563);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.highlight"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(SystemColor.control);
		passwordField.setToolTipText("Password");
		passwordField.setBounds(277, 253, 237, 31);
		contentPane.add(passwordField);
		
		UserField = new JTextField();
		UserField.setBounds(277, 191, 237, 31);
		contentPane.add(UserField);
		UserField.setColumns(10);
		
		RoundedButton btnConnexion = new RoundedButton("Connexion", 20);
		btnConnexion.addActionListener(this.gestionClicFenConnexion);
		btnConnexion.setBackground(new Color(31, 153, 88));
		btnConnexion.setBounds(335, 315, 132, 31);
		contentPane.add(btnConnexion);

		
		JLabel LabelConnexion = new JLabel("Connectez-Vous !");
		LabelConnexion.setHorizontalAlignment(SwingConstants.CENTER);
		LabelConnexion.setFont(new Font("Tahoma", Font.PLAIN, 36));
		LabelConnexion.setForeground(new Color(31, 153, 88));
		LabelConnexion.setBounds(261, 93, 288, 55);
		contentPane.add(LabelConnexion);
		
		Label labelUtilisateur = new Label("Utilisateur");
		labelUtilisateur.setBounds(277, 164, 65, 21);
		contentPane.add(labelUtilisateur);
		
		Label labelMdp = new Label("Mot de passe");
		labelMdp.setBounds(277, 228, 83, 21);
		contentPane.add(labelMdp);

	}
    
	public String getValeurChLogin() {
	    return this.UserField.getText(); 
	}

	public String getValeurPasswordField() {
	    return new String(this.passwordField.getPassword()); 
	}
	
	public void actionPerformed(ActionEvent e) {
	}
}
