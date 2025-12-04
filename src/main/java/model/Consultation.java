package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hhgyd
 */
public class Consultation {
     private int id;
    private Date dateConsultation;
    private String description;
    private double prix;
    private Patient patient;

    public Consultation(int id, Date dateConsultation, String description, double prix, Patient patient) {
        this.id = id;
        this.dateConsultation = dateConsultation;
        this.description = description;
        this.prix = prix;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public Date getDateConsultation() {
        return dateConsultation;
    }

    public String getDescription() {
        return description;
    }

    public double getPrix() {
        return prix;
    }

    public Patient getPatient() {
        return patient;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    

    
}
