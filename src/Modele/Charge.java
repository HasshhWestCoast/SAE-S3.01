package Modele;

import java.util.ArrayList;
import java.util.List;

public class Charge {
    private int idCharge; 
    private String nom; 
    private double montantReel; 
    private double montantPrevisionnel; 
    private boolean deductible; 
    private Bien bien; 

    // Liste des associations avec Louer via Retenir
    private List<Retenir> retenirList;

    public Charge(int idCharge, String nom, double montantReel, double montantPrevisionnel, boolean deductible, Bien bien) {
        this.idCharge = idCharge;
        this.nom = nom;
        this.montantReel = montantReel;
        this.montantPrevisionnel = montantPrevisionnel;
        this.deductible = deductible;
        this.setBien(bien);
        this.retenirList = new ArrayList<>();
    }

    public int getIdCharge() {
        return idCharge;
    }

    public void setIdCharge(int idCharge) {
        this.idCharge = idCharge;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getMontantReel() {
        return montantReel;
    }

    public void setMontantReel(double montantReel) {
        this.montantReel = montantReel;
    }

    public double getMontantPrevisionnel() {
        return montantPrevisionnel;
    }

    public void setMontantPrevisionnel(double montantPrevisionnel) {
        this.montantPrevisionnel = montantPrevisionnel;
    }

    public boolean isDeductible() {
        return deductible;
    }

    public void setDeductible(boolean deductible) {
        this.deductible = deductible;
    }

	public Bien getBien() {
		return bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}
	
    public List<Retenir> getRetenirList() {
        return retenirList;
    }

    public void setRetenirList(List<Retenir> retenirList) {
        this.retenirList = retenirList;
    }

    // Méthode pour ajouter une association
    public void addRetenir(Retenir retenir) {
        this.retenirList.add(retenir);
    }

    @Override
    public String toString() {
        return "Charge{" +
                "idCharge=" + idCharge +
                ", nom='" + nom + '\'' +
                ", montantReel=" + montantReel +
                ", montantPrevisionnel=" + montantPrevisionnel +
                ", deductible=" + deductible +
                ", compteur=" + (bien != null ? bien.getIdBien(): "null") +
                ", retenirList=" + retenirList.size() + " association(s)" +
                '}';
    }

}
