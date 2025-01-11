package Modele;

import java.util.Objects;

public class Locataire {

	private String idLocataire;
	private String nom;
	private String prenom;
	private String telephone;
	private String mail;
	private String dateNaissance;
	private int colocataire;

	public Locataire(String idLocataire, String nom, String prenom, String telephone, String mail, String dateNaissance, int colocataire) {
		this.idLocataire = idLocataire;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
		this.dateNaissance = dateNaissance;
		this.colocataire = colocataire;
	}

	public String getIdLocataire() {
		return idLocataire;
	}

	public void setIdLocataire(String idLocataire) {
		this.idLocataire = idLocataire;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public int getColocataire() {
		return colocataire;
	}

	public void setQuotite(int colocataire) {
		this.colocataire = colocataire;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idLocataire);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Locataire)) {
			return false;
		}
		Locataire other = (Locataire) obj;
		return Objects.equals(idLocataire, other.idLocataire);
	}

	@Override
	public String toString() {
		return "Locataire [idLocataire=" + idLocataire + ", nom=" + nom + ", prenom=" + prenom + ", telephone="
				+ telephone + ", mail=" + mail + ", dateNaissance=" + dateNaissance + ", quotite=" + colocataire + "]";
	}
	
	

}