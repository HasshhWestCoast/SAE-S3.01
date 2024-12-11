package Modele;

import java.util.ArrayList;
import java.util.List;

public class Charge {
    private int idCharge; 
    private String designation; 
    private double montantReel; 
    private String dateCharge;
    //private double montantPrevisionnel; // ON AJOUTE CA OU PAS
    private boolean deductible; // C'EST QUOI CA 
    private Bien bien; 

    private List<Retenir> retenirList;

    public Charge(int idCharge, String designation, double montantReel, String dateCharge, ??? ???, ???boolean deductible, Bien bien) {
        this.idCharge = idCharge;
        this.designation = designation;
        this.montantReel = montantReel;
        this.dateCharge = dateCharge;
        //this.montantPrevisionnel = montantPrevisionnel;
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

    public String getdesignation() {
        return designation;
    }

    public void setdesignation(String designation) {
        this.designation = designation;
    }

    public double getMontantReel() {
        return montantReel;
    }

    public void setMontantReel(double montantReel) {
        this.montantReel = montantReel;
    }
    
    /*
    public double getMontantPrevisionnel() {
        return montantPrevisionnel;
    }

    public void setMontantPrevisionnel(double montantPrevisionnel) {
        this.montantPrevisionnel = montantPrevisionnel;
    }
	*/
    
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

}
