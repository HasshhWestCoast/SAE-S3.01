package Modele;

import java.util.Objects;

public class Impot {

	private String idImpot; 
	private double montant;
	private String annee;

	public Impot(String idImpot, double montant, String annee) {
		this.idImpot = idImpot;
		this.montant = montant;
		this.annee = annee;
	}

	public String getIdImpot() {
		return this.idImpot;
	}

	public void setIdImpot(String idImpot) {
		this.idImpot = idImpot;
	}

	public double getMontant() {
		return this.montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getAnnee() {
		return this.annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idImpot);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Impot)) {
			return false;
		}
		Impot other = (Impot) obj;
		return Objects.equals(idImpot, other.idImpot);
	}

	@Override
	public String toString() {
		return "Impôt [idImpot=" + idImpot + ", montant=" + montant + ", annee=" + annee + "]";
	}
	
	

}