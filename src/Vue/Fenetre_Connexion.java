package vue;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controleur.GestionConnexion;

public class Fenetre_Connexion extends JFrame {

    // Déclaration des composants de l'interface utilisateur
    private JPanel contentPane;
    private JTextField textFieldUtilisateur;
    private JPasswordField textFieldMdp;
    private GestionConnexion gestionClic;

    // Méthode principale pour lancer l'application
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Fenetre_Connexion frame = new Fenetre_Connexion();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Constructeur de la fenêtre de connexion
    public Fenetre_Connexion() {
        // Configuration de la fenêtre
        this.setBounds(100, 100, 450, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuration du panneau principal
        this.contentPane = new JPanel();
        this.contentPane.setBackground(Color.WHITE);
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(null);

        // Titre de la fenêtre
        JLabel lblTitre = new JLabel("Connexion");
        lblTitre.setBounds(180, 10, 100, 30);
        this.contentPane.add(lblTitre);

        // Panneau contenant les champs de saisie
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(120, 50, 200, 120);
        this.contentPane.add(panel);
        panel.setLayout(null);

        // Label et champ de saisie pour le nom d'utilisateur
        JLabel lblUtilisateur = new JLabel("Nom d'utilisateur:");
        lblUtilisateur.setBounds(10, 10, 120, 20);
        panel.add(lblUtilisateur);

        this.textFieldUtilisateur = new JTextField();
        this.textFieldUtilisateur.setBounds(10, 30, 180, 20);
        panel.add(this.textFieldUtilisateur);
        this.textFieldUtilisateur.setColumns(10);

        // Label et champ de saisie pour le mot de passe
        JLabel lblMdp = new JLabel("Mot de passe:");
        lblMdp.setBounds(10, 60, 120, 20);
        panel.add(lblMdp);

        this.textFieldMdp = new JPasswordField();
        this.textFieldMdp.setBounds(10, 80, 180, 20);
        panel.add(this.textFieldMdp);

        // Boutons de connexion et d'annulation
        JButton btnConnecter = new JButton("Se connecter");
        btnConnecter.setForeground(Color.WHITE);
        btnConnecter.setBackground(new Color(0, 102, 204));
        btnConnecter.setBounds(90, 195, 120, 30);
        this.contentPane.add(btnConnecter);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setForeground(Color.WHITE);
        btnAnnuler.setBackground(new Color(0, 102, 204));
        btnAnnuler.setBounds(220, 195, 120, 30);
        this.contentPane.add(btnAnnuler);

        // Centre la fenêtre sur l'écran
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // Initialisation de la gestion des clics pour les boutons
        this.gestionClic = new GestionConnexion(this);
        btnConnecter.addActionListener(this.gestionClic);
        btnAnnuler.addActionListener(this.gestionClic);
    }

    // Méthode pour récupérer le nom d'utilisateur
    public String getNomUtilisateur() {
        return this.textFieldUtilisateur.getText();
    }

    // Méthode pour récupérer le mot de passe
    public String getMdp() {
        return new String(this.textFieldMdp.getPassword());
    }
}