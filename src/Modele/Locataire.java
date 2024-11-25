package Modele;

public class Locataire {

	private String idLocataire;
	private String nom;
	private String prenom;
	private String telephone;
	private String mail;
	private String dateNaissance;

	public Locataire(String idLocataire, String nom, String prenom, String telephone, String mail,
			String dateNaissance) {
		this.idLocataire = idLocataire;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
		this.dateNaissance = dateNaissance;
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

	@Override
	public String toString() {
		return this.getIdLocataire();
		// return getNom() + " " + getPrenom();
	}

	public String getNewIdLocataire() {
		return "NEW_" + this.idLocataire;
	}

}