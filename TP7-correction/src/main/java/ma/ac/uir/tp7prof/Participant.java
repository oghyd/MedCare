/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.ac.uir.tp7prof;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author Najib
 */
public class Participant {
    
    private String cne;
    private String nom;
    private String prenom; 
    private String profession; 
    private String civilite; 
    private String email;
    private int age; 
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Participant() {
    }
    
    public String getCne() {
        return cne;
    }

    
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getProfession() {
        return profession;
    }

    public String getCivilite() {
        return civilite;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }
    

    public Participant(String cne, String nom, String prenom, String profession, String civilite, String email, int age) {
        this.cne = cne;
        this.nom = nom;
        this.prenom = prenom;
        this.profession = profession;
        this.civilite = civilite;
        this.email = email;
        this.age = age;
    }
    
    public Participant(int id, String cne, String nom, String prenom, String profession, String civilite, String email, int age) {
        this.id = id;
        this.cne = cne;
        this.nom = nom;
        this.prenom = prenom;
        this.profession = profession;
        this.civilite = civilite;
        this.email = email;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Participant{" + "cne=" + cne + ", nom=" + nom + ", prenom=" + prenom + ", profession=" + profession + ", civilite=" + civilite + ", email=" + email + ", age=" + age + '}';
    }
    
public boolean addParticipant() {
    boolean res = false;
    try {
        // 1step 
        java.sql.Connection conn = Connection.connect();
        
        String req = "INSERT INTO participants "
               + "(cne, nom, prenom, profession, civilite, email, age) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstmt = conn.prepareStatement(req);

        // Remplir les paramètres
        pstmt.setString(1, this.cne);
        pstmt.setString(2, this.nom);
        pstmt.setString(3, this.prenom);
        pstmt.setString(4, this.profession);
        pstmt.setString(5, this.civilite);
        pstmt.setString(6, this.email);
        pstmt.setInt(7, this.age);

        // Exécuter la requête
        int etat = pstmt.executeUpdate();
        if (etat == 1) {
            res = true;
        }
        pstmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return res;
    }
    
    //récupérer la liste des particpants
	public static LinkedList<Participant> getAllParticipants(){
            
		LinkedList<Participant> listeP = new LinkedList<Participant>();
		String req = "SELECT * FROM participants ";
                Participant p = null;
		try{
			java.sql.Connection conn = Connection.connect();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,  
                                                ResultSet.CONCUR_UPDATABLE);
 			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(req);
			while (rs.next()) {
                               int id = rs.getInt(1);
                               String cne = rs.getString(2);
                               String nom = rs.getString(3);
                               String prenom = rs.getString(4);
                               String profession = rs.getString(5);
                               String civilite  = rs.getString(6);
                               String email = rs.getString(7);
                               int age = rs.getInt(8);
			       p = new Participant(id, cne, nom, prenom, profession, 
                                       civilite, email, age);
                               listeP.add(p);       
			}
			conn.close();
			rs.close();
			stmt.close();
                       
			}catch(SQLException e){
				System.out.println("Problème durant la récupération de la liste "
                                        + "des participants");
			    System.out.println("SQLException: " + e.getMessage());
			    System.out.println("SQLState: "     + e.getSQLState());
			    System.out.println("VendorError: "  + e.getErrorCode());
			}
		return listeP; 
	}
        //
        public static boolean supprimerParticipant(int id){
		boolean res = false;
		int resultat1 = 0;
		String req = "DELETE " +
				     "FROM Participants " +
				     "WHERE id = "+ id ;
		try{
			java.sql.Connection conn = Connection.connect();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,   ResultSet.CONCUR_UPDATABLE);
			resultat1 = stmt.executeUpdate(req);
			if (resultat1 != 0) res = true;
			conn.close();
			stmt.close();
		}
		catch (SQLException e){
                    System.out.println("Problème de suppression du participant ");
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: "     + e.getSQLState());
		    System.out.println("VendorError: "  + e.getErrorCode());
		}
		
		return res;
	} 
        
        // MAJ d'un particpant 
        public  boolean updateParticpant(){
            
		boolean res = false;
		int resultat1 = 0;
		String req = "UPDATE Participant SET " +
                                "cne = '"        + this.cne         + "', " +
                                "nom = '"        + this.nom         + "', " +
                                "prenom = '"     + this.prenom      + "', " +
                                "profession = '" + this.profession  + "', " +
                                "civilite   = '" + this.civilite    + "', " +
                                "email      = '" + this.email       + "', " +
                                "age   = "       + this.age         + " " +
				"WHERE id = "    + this.id ;
                
                System.out.println("Req " + req);
		
                try{
			java.sql.Connection conn = Connection.connect();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,   ResultSet.CONCUR_UPDATABLE);
			resultat1 = stmt.executeUpdate(req);
			if (resultat1 != 0) res = true;
			conn.close();
			stmt.close();	
		}
		catch (SQLException e){
		
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: "     + e.getSQLState());
		    System.out.println("VendorError: "  + e.getErrorCode());
		}
		
		return res;
	}	
    
    public static DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }
        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }

}
