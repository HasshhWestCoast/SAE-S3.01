package Modele;

public class RegularisationCharge {
    private String bien;
    private String periodeDu;
    private String periodeAu;
    private double chargeReelle;
    private double ordres;
    private double totalCharges;
    private double restantDues;
    private double totalProvisions;
    private double reste;

    // Constructor
    public RegularisationCharge(String bien, String periodeDu, String periodeAu, double chargeReelle,
                                 double ordres, double totalCharges, double restantDues,
                                 double totalProvisions, double reste) {
        this.bien = bien;
        this.periodeDu = periodeDu;
        this.periodeAu = periodeAu;
        this.chargeReelle = chargeReelle;
        this.ordres = ordres;
        this.totalCharges = totalCharges;
        this.restantDues = restantDues;
        this.totalProvisions = totalProvisions;
        this.reste = reste;
    }

    // Getters and Setters
    public String getBien() {
        return bien;
    }

    public void setBien(String bien) {
        this.bien = bien;
    }

    public String getPeriodeDu() {
        return periodeDu;
    }

    public void setPeriodeDu(String periodeDu) {
        this.periodeDu = periodeDu;
    }

    public String getPeriodeAu() {
        return periodeAu;
    }

    public void setPeriodeAu(String periodeAu) {
        this.periodeAu = periodeAu;
    }

    public double getChargeReelle() {
        return chargeReelle;
    }

    public void setChargeReelle(double chargeReelle) {
        this.chargeReelle = chargeReelle;
    }

    public double getOrdres() {
        return ordres;
    }

    public void setOrdres(double ordres) {
        this.ordres = ordres;
    }

    public double getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(double totalCharges) {
        this.totalCharges = totalCharges;
    }

    public double getRestantDues() {
        return restantDues;
    }

    public void setRestantDues(double restantDues) {
        this.restantDues = restantDues;
    }

    public double getTotalProvisions() {
        return totalProvisions;
    }

    public void setTotalProvisions(double totalProvisions) {
        this.totalProvisions = totalProvisions;
    }

    public double getReste() {
        return reste;
    }

    public void setReste(double reste) {
        this.reste = reste;
    }
}
