package model;

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
public class Patient {
    
    private int idPatient;
    private String nomP;
    private String phoneP;
    private String mailP;
    
    //auto id++ should be done on the DB

    //iyvuct

    public Patient(String cneP, String prenomP, String nomP, String phoneP, int ageP) {
        this.cneP = cneP;
        this.prenomP = prenomP;
        this.nomP = nomP;
        this.phoneP = phoneP;
        this.ageP = ageP;
    }
    
    public Patient(int idPatient, String cneP, String prenomP, String nomP, String phoneP, int ageP) {
        this.idPatient = idPatient;
        this.cneP = cneP;
        this.prenomP = prenomP;
        this.nomP = nomP;
        this.phoneP = phoneP;
        this.ageP = ageP;
    }


    public int getIdPatient() {
        return idPatient;
    }

    public String getCneP() {
        return cneP;
    }

    public String getPrenomP() {
        return prenomP;
    }

    public String getNomP() {
        return nomP;
    }

    public String getPhoneP() {
        return phoneP;
    }

    public int getAgeP() {
        return ageP;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public void setCneP(String cneP) {
        this.cneP = cneP;
    }

    public void setPrenomP(String prenomP) {
        this.prenomP = prenomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public void setPhoneP(String phoneP) {
        this.phoneP = phoneP;
    }

    public void setAgeP(int ageP) {
        this.ageP = ageP;
    }
    
    @Override
    public String toString() {
        return "Patient{ " + "ID patient = " + idPatient + ", cne = " + cneP 
                + ", prénom = " + prenomP + ", nom = " + nomP + ", age = " + ageP + " }";
    }
    
    
}
