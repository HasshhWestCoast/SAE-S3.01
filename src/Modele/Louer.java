package Modele;

public class Louer {

	private Locataire locataire;
	private Bien bien;
	private String dateDebut;
	private int nbMois;
	private int loyePayer;
	private double provision_chargeMoisTTC;
	private double cautionTTC;
	private String bail;
	private String etat_lieux;
	private ICC icc;
	private double montantReelPaye;

	public Louer(String dateDebut, int nbMois, int loyePayer, double provision_chargeMoisTTC, double cautionTTC, String bail, 
			String etat_lieux, double montantReelPaye, Locataire locataire,  ICC icc, Bien bien) {
		
		this.locataire = locataire;
		this.bien = bien;
		this.dateDebut = dateDebut;
		this.nbMois = nbMois;
		this.loyePayer = loyePayer;
		this.provision_chargeMoisTTC = provision_chargeMoisTTC;
		this.cautionTTC = cautionTTC;
		this.bail = bail;
		this.etat_lieux = etat_lieux;
		this.icc = icc;
		this.montantReelPaye = montantReelPaye;
	}

	public Locataire getLocataire() {
		return locataire;
	}

	public Bien getBien() {
		return bien;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public int getNbMois() {
		return nbMois;
	}

	public int getloyePayer() {
		return loyePayer;
	}

	public double getprovision_chargeMoisTTC() {
		return provision_chargeMoisTTC;
	}

	public double getCautionTTC() {
		return cautionTTC;
	}

	public String getBail() {
		return bail;
	}

	public String getEtat_lieux() {
		return etat_lieux;
	}

	public ICC getIcc() {
		return icc;
	}

	public double getMontantReelPaye() {
		return montantReelPaye;
	}
	
	

}