package Modele;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Facture {

	private String numero;
	private String dateEmission;
	private String datePaiement;
	private String modePaiement;
	private String numeroDevis;
	private String designation;
	private double montantReelPaye;
	private double montant;
	private int imputableLocataire;
	private Immeuble immeuble;
	private Bien bien;
	private Entreprise entreprise;

	public Facture(String numero, String dateEmission, String datePaiement, String modePaiement, String numeroDevis,
			String designation, double montantReelPaye, double montant, int imputableLocataire, Immeuble immeuble,
			Bien bien, Entreprise entreprise) {
		this.numero = numero;
		this.dateEmission = dateEmission;
		this.datePaiement = datePaiement;
		this.modePaiement = modePaiement;
		this.numeroDevis = numeroDevis;
		this.designation = designation;
		this.montantReelPaye = montantReelPaye;
		this.montant = montant;
		this.imputableLocataire = imputableLocataire;
		this.immeuble = immeuble;
		this.bien = bien;
		this.entreprise = entreprise;
	}

	public double getMontant() {
		return montant;
	}

	public String getNumero() {
		return numero;
	}

	public String getDateEmission() {
		return dateEmission;
	}

	public String getDatePaiement() {
		return datePaiement;
	}

	public String getModePaiement() {
		return modePaiement;
	}

	public String getNumeroDevis() {
		return numeroDevis;
	}

	public String getDesignation() {
		return designation;
	}

	public double getMontantReelPaye() {
		return montantReelPaye;
	}

	public int getImputableLocataire() {
		return imputableLocataire;
	}

	public Immeuble getImmeuble() {
		return immeuble;
	}

	public Bien getBien() {
		return bien;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	/**
	 * Convertit la date d'émission en objet java.sql.Date.
	 * 
	 * @return La date d'émission convertie en java.sql.Date.
	 */
	public java.sql.Date getDateEmissionAsSqlDate() {
		return parseDate(dateEmission);
	}

	/**
	 * Convertit la date de paiement en objet java.sql.Date.
	 * 
	 * @return La date de paiement convertie en java.sql.Date.
	 */
	public java.sql.Date getDatePaiementAsSqlDate() {
		return parseDate(datePaiement);
	}

	/**
	 * Parse la chaîne de caractères représentant une date au format "dd-MM-yyyy" en
	 * objet java.sql.Date.
	 * 
	 * @param dateString La chaîne de caractères représentant la date.
	 * @return La date convertie en java.sql.Date.
	 */
	private java.sql.Date parseDate(String dateString) {
		try {
			if (dateString != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date utilDate = dateFormat.parse(dateString);
				return new java.sql.Date(utilDate.getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace(); // Gérer l'exception selon vos besoins
		}
		return null;
	}

}