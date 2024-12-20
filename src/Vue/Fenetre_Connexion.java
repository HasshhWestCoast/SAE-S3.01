package Vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class Fenetre_Connexion extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldUtilisateur;
    private JPasswordField textFieldMdp;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Fenetre_Connexion frame = new Fenetre_Connexion();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Fenetre_Connexion() {
        // Configuration de la fenêtre principale
        setTitle("Connexion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 250);
        setLocationRelativeTo(null);

        // Panneau principal
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        // Titre en haut (NORTH)
        JLabel lblTitre = new JLabel("Connexion ");
        lblTitre.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTitre.setForeground(new Color(0, 128, 0));
        lblTitre.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(lblTitre, BorderLayout.NORTH);

        // Panneau central pour le formulaire
        JPanel panelFormulaire = new JPanel();
        panelFormulaire.setLayout(new GridLayout(4, 1, 10, 5)); // 4 lignes, 1 colonne avec espacement
        panelFormulaire.setBackground(Color.WHITE);
        contentPane.add(panelFormulaire, BorderLayout.CENTER);

        // Label et champ pour le nom d'utilisateur
        JLabel lblUtilisateur = new JLabel("Nom d'utilisateur :");
        lblUtilisateur.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panelFormulaire.add(lblUtilisateur);

        textFieldUtilisateur = new JTextField();
        panelFormulaire.add(textFieldUtilisateur);

        // Label et champ pour le mot de passe
        JLabel lblMdp = new JLabel("Mot de passe :");
        lblMdp.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panelFormulaire.add(lblMdp);

        textFieldMdp = new JPasswordField();
        panelFormulaire.add(textFieldMdp);

        // Panneau du bouton de connexion (SOUTH)
        JPanel panelBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBouton.setBackground(Color.WHITE);
        contentPane.add(panelBouton, BorderLayout.SOUTH);

        JButton btnConnexion = new JButton("Connexion");
        panelBouton.add(btnConnexion);
    }
}
