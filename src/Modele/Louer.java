package Modele;

public class Louer {

	private Locataire locataire;
	private Bien bien;
	private String dateDebut;
	private int nbMois;
	private double loyerTTC;
	private double provision_chargeMens_TTC;
	private double cautionTTC;
	private String bail;
	private String etat_lieux;
	private String dateDerniereRegularisation;
	private int loyerPaye;
	private ICC icc;
	private double montantReelPaye;

	public Louer(Locataire locataire, Bien bien, String dateDebut, int nbMois, double loyerTTC,
			double provision_chargeMens_TTC, double cautionTTC, String bail, String etat_lieux, String dateDerniereRegularisation,
			int loyerPaye, ICC icc, double montantReelPaye) {
		this.locataire = locataire;
		this.bien = bien;
		this.dateDebut = dateDebut;
		this.nbMois = nbMois;
		this.loyerTTC = loyerTTC;
		this.provision_chargeMens_TTC = provision_chargeMens_TTC;
		this.cautionTTC = cautionTTC;
		this.bail = bail;
		this.etat_lieux = etat_lieux;
		this.dateDerniereRegularisation = dateDerniereRegularisation;
		this.loyerPaye = loyerPaye;
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

	public double getLoyerTTC() {
		return loyerTTC;
	}

	public double getProvision_chargeMens_TTC() {
		return provision_chargeMens_TTC;
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

	public String getDateDerniereRegularisation() {
		return dateDerniereRegularisation;
	}

	public int getLoyerPaye() {
		return loyerPaye;
	}

	public ICC getIcc() {
		return icc;
	}

	public double getMontantReelPaye() {
		return montantReelPaye;
	}

}