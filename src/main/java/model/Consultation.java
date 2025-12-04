package model;
//iyvuct

import java.sql.Date;


/**
 *
 * @author hhgyd
 */
public class Consultation {
    
    private int idC; 
    private int idPatient;
    private int idMedecin;
    private int idcategorie;
    
    private Date dateConsultation;
    private Date datePaiement;
    private String description;
    private String status;
    private double prix;
    private boolean paid;

    //empty constructor
    public Consultation() {
    }

    public Consultation(int idPatient, int idMedecin, int idcategorie, Date dateConsultation, Date datePaiement, String description, String status, double prix, boolean paid) {
        this.idPatient = idPatient;
        this.idMedecin = idMedecin;
        this.idcategorie = idcategorie;
        this.dateConsultation = dateConsultation;
        this.datePaiement = datePaiement;
        this.description = description;
        this.status = status;
        this.prix = prix;
        this.paid = paid;
    }

    public Consultation(int idC, int idPatient, int idMedecin, int idcategorie, Date dateConsultation, Date datePaiement, String description, String status, double prix, boolean paid) {
        this.idC = idC;
        this.idPatient = idPatient;
        this.idMedecin = idMedecin;
        this.idcategorie = idcategorie;
        this.dateConsultation = dateConsultation;
        this.datePaiement = datePaiement;
        this.description = description;
        this.status = status;
        this.prix = prix;
        this.paid = paid;
    }
    
    
    // getters

    public int getIdC() {
        return idC;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public int getIdMedecin() {
        return idMedecin;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public Date getDateConsultation() {
        return dateConsultation;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public double getPrix() {
        return prix;
    }

    public boolean isPaid() {
        return paid;
    }
    
    
    // setters

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Consultation{" + "idC=" + idC + ", idPatient=" + idPatient + ", idMedecin=" + idMedecin + ", idcategorie=" + idcategorie + ", dateConsultation=" + dateConsultation + ", datePaiement=" + datePaiement + ", description=" + description + ", status=" + status + ", prix=" + prix + ", paid=" + paid + '}';
    }
        
}