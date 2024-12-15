package Vue;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;

public class Utils {
	// Méthode pour créer un champ de texte avec une bordure spécifique
	public static JTextField creerTextField(String title, int x, int y, int width, int height, JPanel panel) {
		JTextField textField = new JTextField();
		textField.setColumns(10);
		textField.setBorder(new TitledBorder(new LineBorder(new Color(0, 102, 204)), title, TitledBorder.LEADING,
				TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		textField.setBounds(x, y, width, height);
		panel.add(textField);
		return textField;
	}

	// Méthode pour créer un bouton avec des caractéristiques spécifiques
	public static JButton creerBouton(JPanel panel, String texte, int x, int y, int width, int height) {
		JButton bouton = new JButton(texte);
		bouton.setForeground(Color.WHITE);
		bouton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bouton.setBackground(new Color(0, 102, 204));
		bouton.setBounds(x, y, width, height);
		panel.add(bouton);
		return bouton;
	}

	// Méthode pour créer un JLabel avec des caractéristiques spécifiques
	public static JLabel creerLabel(String text, int x, int y, int width, int height, int fontSize, JPanel panel) {
		JLabel label = new JLabel(text);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
		label.setBounds(x, y, width, height);
		panel.add(label);
		return label;
	}
}