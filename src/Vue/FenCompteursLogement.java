package Vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Controleur.Afficher.GestionFenCompteursLogement;

public class FenCompteursLogement extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private GestionFenCompteursLogement gestionClicFenCompLog;


	/**
	 * Create the frame.
	 */
	public FenCompteursLogement() {
		
		this.gestionClicFenCompLog = new GestionFenCompteursLogement(this);
		
		getContentPane().setBackground(new Color(255, 255, 255));
        getContentPane().setLayout(null);
		
		 // JTable pour "Bien"
        JTable tabMesCompteursLogements = new JTable();
        tabMesCompteursLogements.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null},
            },
            new String[] {
                "ID Compteur", "Type Compteur", "Prix abonnement"
            }
        ));
        JScrollPane scrollPaneCompteursLogements = new JScrollPane(tabMesCompteursLogements);
        scrollPaneCompteursLogements.setBounds(23, 77, 580, 367);
        scrollPaneCompteursLogements.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        getContentPane().add(scrollPaneCompteursLogements);
        
        JLabel lblMesCompteursLogement = new JLabel("Mes Compteurs Logements");
        lblMesCompteursLogement.setHorizontalAlignment(SwingConstants.CENTER);
        lblMesCompteursLogement.setFont(new Font("Sylfaen", Font.PLAIN, 16));
        lblMesCompteursLogement.setBackground(Color.WHITE);
        lblMesCompteursLogement.setBounds(194, 27, 216, 21);
        getContentPane().add(lblMesCompteursLogement);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.WHITE);
        separator.setBackground(new Color(31, 151, 83));
        separator.setBounds(183, 46, 225, 2);
        getContentPane().add(separator);
        
        RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
        btnAnnuler.addActionListener(this.gestionClicFenCompLog);
        btnAnnuler.setBounds(53, 472, 115, 21);
        getContentPane().add(btnAnnuler);
        
        RoundedButton btnAfficherRelevé = new RoundedButton("Afficher les relevés", 20);
        btnAfficherRelevé.addActionListener(this.gestionClicFenCompLog);
        btnAfficherRelevé.setBounds(194, 472, 182, 21);
        getContentPane().add(btnAfficherRelevé);
        
        RoundedButton btnAjouterRelevé = new RoundedButton("Ajouter un relevé", 20);
        btnAjouterRelevé.addActionListener(this.gestionClicFenCompLog);
        btnAjouterRelevé.setBounds(400, 472, 140, 21);
        getContentPane().add(btnAjouterRelevé);
        
		setBounds(100, 100, 669, 550);

	}
}
