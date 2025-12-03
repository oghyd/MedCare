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
public class Medecin {
    
    private int idMedecin;
    private String prenomM;
    private String nomM;
    private boolean disponible; //default boolean value is false

    public Medecin(int idMedecin, String prenomM, String nomM, boolean disponible) {
        this.idMedecin = idMedecin;
        this.prenomM = prenomM;
        this.nomM = nomM;
        this.disponible = disponible;
    }

    public Medecin(String prenomM, String nomM, boolean disponible) {
        this.prenomM = prenomM;
        this.nomM = nomM;
        this.disponible = disponible;
    }

    
    
    
}
