package Modele;

public class Entreprise {

	private String siret;
	private String nom;
	private String adresse;
	private String cp;
	private String ville;
	private String mail;
	private String telephone;
	private String iban;

	public Entreprise(String siret, String nom, String adresse, String cp, String ville, String mail, String telephone,
			String iban) {
		this.siret = siret;
		this.nom = nom;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.mail = mail;
		this.telephone = telephone;
		this.iban = iban;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
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

}