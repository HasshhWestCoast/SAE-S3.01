package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class ajoutloca extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ajoutloca frame = new ajoutloca();
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
    public ajoutloca() {
        JFrame fenetre = new JFrame("Ajout Locataire");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel panneauPrincipal = new JPanel();
        panneauPrincipal.setLayout(new BorderLayout());

        // Titre
        JPanel panneauTitre = new JPanel(new BorderLayout());
        JLabel etiquetteTitre = new JLabel("Ajout Locataire", JLabel.CENTER);
        etiquetteTitre.setFont(new Font("SansSerif", Font.BOLD, 20));
        etiquetteTitre.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panneauTitre.add(etiquetteTitre, BorderLayout.CENTER);

        // Ligne sous le titre
        JSeparator separateur = new JSeparator();
        panneauTitre.add(separateur, BorderLayout.SOUTH);

        panneauPrincipal.add(panneauTitre, BorderLayout.NORTH);

        // Formulaire
        JPanel panneauFormulaire = new JPanel();
        panneauFormulaire.setLayout(new GridBagLayout());
        panneauFormulaire.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Réduction de l'espace entre les composants
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Ajout des champs du formulaire
        ajouterChampFormulaire(panneauFormulaire, gbc, "Nom :", 0);
        JTextField champNom = new JTextField(25);
        ajouterInputFormulaire(panneauFormulaire, gbc, champNom, 0);

        ajouterChampFormulaire(panneauFormulaire, gbc, "Prénom :", 1);
        JTextField champPrenom = new JTextField(25);
        ajouterInputFormulaire(panneauFormulaire, gbc, champPrenom, 1);

        ajouterChampFormulaire(panneauFormulaire, gbc, "Téléphone :", 2);
        JTextField champTelephone = new JTextField(25);
        ajouterInputFormulaire(panneauFormulaire, gbc, champTelephone, 2);

        ajouterChampFormulaire(panneauFormulaire, gbc, "Mail :", 3);
        JTextField champMail = new JTextField(25);
        ajouterInputFormulaire(panneauFormulaire, gbc, champMail, 3);

        ajouterChampFormulaire(panneauFormulaire, gbc, "Date de naissance :", 4);
        JFormattedTextField champDateNaissance = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
        champDateNaissance.setValue(new Date());
        ajouterInputFormulaire(panneauFormulaire, gbc, champDateNaissance, 4);

        ajouterChampFormulaire(panneauFormulaire, gbc, "Quotité :", 5);
        JTextField champQuotite = new JTextField(25);
        ajouterInputFormulaire(panneauFormulaire, gbc, champQuotite, 5);

        panneauPrincipal.add(panneauFormulaire, BorderLayout.CENTER);

        // Boutons
        JPanel panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton boutonRetour = new JButton("Retour");
        boutonRetour.setFont(new Font("SansSerif", Font.BOLD, 14));
        boutonRetour.setFocusPainted(false);
        boutonRetour.setBackground(new Color(255, 69, 0));
        boutonRetour.setForeground(Color.WHITE);
        panneauBoutons.add(boutonRetour);

        JButton boutonAjouter = new JButton("Ajouter");
        boutonAjouter.setFont(new Font("SansSerif", Font.BOLD, 14));
        boutonAjouter.setFocusPainted(false);
        boutonAjouter.setBackground(new Color(0, 128, 0));
        boutonAjouter.setForeground(Color.WHITE);
        panneauBoutons.add(boutonAjouter);

        panneauPrincipal.add(panneauBoutons, BorderLayout.SOUTH);

        fenetre.add(panneauPrincipal);
        fenetre.setSize(600, 400);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
    }

    /**
     * Ajoute un JLabel au formulaire.
     */
    private void ajouterChampFormulaire(JPanel panel, GridBagConstraints gbc, String texte, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(texte), gbc);
    }

    /**
     * Ajoute un composant input au formulaire.
     */
    private void ajouterInputFormulaire(JPanel panel, GridBagConstraints gbc, JTextField champ, int row) {
        gbc.gridx = 1;
        gbc.gridy = row;
        panel.add(champ, gbc);
    }
}
