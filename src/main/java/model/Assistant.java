package model;
//iyvuct

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
public class Assistant {
    
    private int idAssistant;
    private String prenomA;
    private String nomA;

    public Assistant(int idAssistant, String prenomA, String nomA) {
        this.idAssistant = idAssistant;
        this.prenomA = prenomA;
        this.nomA = nomA;
    }
    
    public Assistant(String prenomA, String nomA) {
        this.prenomA = prenomA;
        this.nomA = nomA;
    }

    public int getIdAssistant() {
        return idAssistant;
    }

    public String getPrenomA() {
        return prenomA;
    }

    public String getNomA() {
        return nomA;
    }

    public void setIdAssistant(int idAssistant) {
        this.idAssistant = idAssistant;
    }

    public void setPrenomA(String prenomA) {
        this.prenomA = prenomA;
    }

    public void setNomA(String nomA) {
        this.nomA = nomA;
    }
    
    
    
    
}
