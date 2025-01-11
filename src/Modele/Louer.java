package Modele;

import java.util.Objects;

public class Louer {

	private Locataire locataire;
	private Bien bien;
	private String dateDebut;
	private String dateSortie;
	private int nbMois;
	private int loyePayer;
	private double loyerMensTTC;
	private double provision_chargeMoisTTC;
	private double cautionTTC;
	private String bail;
	private String etat_lieux;
	private ICC icc;
	private double montantReelPaye;

	public Louer(String dateDebut, String dateSortie, int nbMois, int loyePayer, double loyerMensTTC, double provision_chargeMoisTTC, double cautionTTC, String bail, 
			String etat_lieux, double montantReelPaye, Locataire locataire,  ICC icc, Bien bien) {
		
		this.locataire = locataire;
		this.dateSortie = dateSortie;
		this.bien = bien;
		this.dateDebut = dateDebut;
		this.nbMois = nbMois;
		this.loyePayer = loyePayer;
		this.loyerMensTTC = loyerMensTTC;
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

	public String getDateSortie() {
		return dateSortie;
	}

	public double getLoyerMensTTC() {
		return loyerMensTTC;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bien.getIdBien(), dateDebut, locataire.getIdLocataire());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Louer)) {
			return false;
		}
		Louer other = (Louer) obj;
		return Objects.equals(bien.getIdBien(), other.bien.getIdBien()) && Objects.equals(dateDebut, other.dateDebut)
				&& Objects.equals(locataire.getIdLocataire(), other.locataire.getIdLocataire());
	}

	@Override
	public String toString() {
		return "Louer [locataire=" + locataire + ", bien=" + bien + ", dateDebut=" + dateDebut + ", dateSortie="
				+ dateSortie + ", nbMois=" + nbMois + ", loyePayer=" + loyePayer + ", loyerMensTTC=" + loyerMensTTC
				+ ", provision_chargeMoisTTC=" + provision_chargeMoisTTC + ", cautionTTC=" + cautionTTC + ", bail="
				+ bail + ", etat_lieux=" + etat_lieux + ", icc=" + icc + ", montantReelPaye=" + montantReelPaye + "]";
	}

}