package Modele;

public class Releve {

	private String DateReleve;
	private int indexReleve;
	private Compteur compteur;
	
	public Releve(String DateReleve, int indexReleve, Compteur compteur) {
		this.DateReleve = DateReleve;
		this.indexReleve = indexReleve;
		this.compteur = compteur;
	}

	public String getDateReleve() {
		return DateReleve;
	}

	public int getIndexReleve() {
		return indexReleve;
	}

	public Compteur getCompteur() {
		return compteur;
	}
}
