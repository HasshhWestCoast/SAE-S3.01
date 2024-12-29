package Modele;

public class Facture {

	private String IdFacture;
	private String dateEmission;
	private String datePaiement;
	private String modePaiement;
	private String numeroDevis;
	private String designation;
	private double montantReelVerse;
	private double montant;
	private double imputableLocataire;
	private double acompteVerse;
	private Logement immeuble;
	private Bien bien;
	private Entreprise entreprise;

	public Facture(String IdFacture, String dateEmission, String datePaiement, String modePaiement, String numeroDevis, String designation, double montantReelVerse, 
			double montant, double imputableLocataire, double acompteVerse, Logement immeuble, Bien bien, Entreprise entreprise) {
		
		this.IdFacture = IdFacture;
		this.dateEmission = dateEmission;
		this.datePaiement = datePaiement;
		this.modePaiement = modePaiement;
		this.numeroDevis = numeroDevis;
		this.designation = designation;
		this.montantReelVerse = montantReelVerse;
		this.montant = montant;
		this.imputableLocataire = imputableLocataire;
		this.acompteVerse = acompteVerse;
		this.immeuble = immeuble;
		this.bien = bien;
		this.entreprise = entreprise;
	}

	public double getMontant() {
		return montant;
	}

	public String getIdFacture() {
		return IdFacture;
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

	public double getmontantReelVerse() {
		return montantReelVerse;
	}

	public double getacompteVerse() {
		return acompteVerse;
	}

	public Logement getImmeuble() {
		return immeuble;
	}

	public Bien getBien() {
		return bien;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public double getImputableLocataire() {
		return imputableLocataire;
	}

}