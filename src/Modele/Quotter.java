package Modele;

import java.util.Objects;

public class Quotter {

	private Logement logement;
	private Quotite quotite;
	private int pourcentage;
	
	public Quotter(int pourcentage, Logement logement, Quotite quotite) {
		this.pourcentage = pourcentage;
		this.logement = logement;
		this.quotite = quotite;
	}

	public Logement getLogement() {
		return logement;
	}
	public void setLogement(Logement logement) {
		this.logement = logement;
	}
	public Quotite getQuotite() {
		return quotite;
	}
	public void setQuotite(Quotite quotite) {
		this.quotite = quotite;
	}
	public int getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(logement.getIdLogement(), pourcentage, quotite.getTypeQuotite());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Quotter)) {
			return false;
		}
		Quotter other = (Quotter) obj;
		return Objects.equals(logement.getIdLogement(), other.logement.getIdLogement()) && pourcentage == other.pourcentage
				&& Objects.equals(quotite.getTypeQuotite(), other.quotite.getTypeQuotite());
	}
	
	@Override
	public String toString() {
		return "Quotter [logemenet=" + logement + ", quotite=" + quotite + ", pourcentage=" + pourcentage + "]";
	}

}
