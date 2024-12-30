package Modele;

import java.util.Objects;

public class assurance {

	private String numeroPolice;
	private float montant;
	private String dateEcheance;
	private Logement logement;
	private Entreprise entreprise;

	public assurance(String numeroPolice, float montant, String dateEcheance, Logement logement, Entreprise entreprise) {
		this.numeroPolice = numeroPolice;
		this.montant = montant;
		this.dateEcheance = dateEcheance;
		this.logement = logement;
		this.entreprise = entreprise;
	}

	public Entreprise getEntreprise() {
		return this.entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public String getNuméroPolice() {
		return this.numeroPolice;
	}

	public void setNuméroPolice(String numeroPolice) {
		this.numeroPolice = numeroPolice;
	}

	public float getMontant() {
		return this.montant;
	}

	public void setMontant(float montantInit) {
		this.montant = montantInit;
	}

	public Logement getLogement() {
		return logement;
	}

	public void setLogement(Logement logement) {
		this.logement = logement;
	}

	public String getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(String dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numeroPolice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof assurance)) {
			return false;
		}
		assurance other = (assurance) obj;
		return Objects.equals(numeroPolice, other.numeroPolice);
	}

	@Override
	public String toString() {
		return "assurance [numeroPolice=" + numeroPolice + ", montant=" + montant + ", dateEcheance=" + dateEcheance
				+ ", immeuble=" + logement + ", entreprise=" + entreprise + "]";
	}
	
	
	
	

	


}