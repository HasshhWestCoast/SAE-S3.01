package Modele;

import java.util.Objects;

public class Entreprise {

	private String siret;
	private String nom;
	private String adresse;
	private String codepostal;
	private String ville;
	private String mail;
	private String telephone;
	private String iban;

	public Entreprise(String siret, String nom, String adresse, String codepostal, String ville, String mail, String telephone,
			String iban) {
		this.siret = siret;
		this.nom = nom;
		this.adresse = adresse;
		this.codepostal = codepostal;
		this.ville = ville;
		this.mail = mail;
		this.telephone = telephone;
		this.iban = iban;
	}

	public String getcodepostal() {
		return codepostal;
	}

	public void setcodepostal(String codepostal) {
		this.codepostal = codepostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	@Override
	public int hashCode() {
		return Objects.hash(siret);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Entreprise)) {
			return false;
		}
		Entreprise other = (Entreprise) obj;
		return Objects.equals(siret, other.siret);
	}

	@Override
	public String toString() {
		return "Entreprise [siret=" + siret + ", nom=" + nom + ", adresse=" + adresse + ", codepostal=" + codepostal
				+ ", ville=" + ville + ", mail=" + mail + ", telephone=" + telephone + ", iban=" + iban + "]";
	}
	
	
	
	

}