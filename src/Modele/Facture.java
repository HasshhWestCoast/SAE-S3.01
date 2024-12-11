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
	private int acompteVerse;
	private Immeuble immeuble;
	private Bien bien;
	private Entreprise entreprise;

	public Facture(String IdFacture, String dateEmission, String datePaiement, double montantReelVerse, double montant,String modePaiement, 
			String numeroDevis,	String designation, int acompteVerse, Immeuble immeuble, Bien bien, Entreprise entreprise) {
		
		this.IdFacture = IdFacture;
		this.dateEmission = dateEmission;
		this.datePaiement = datePaiement;
		this.modePaiement = modePaiement;
		this.numeroDevis = numeroDevis;
		this.designation = designation;
		this.montantReelVerse = montantReelVerse;
		this.montant = montant;
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

	public int getacompteVerse() {
		return acompteVerse;
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

}