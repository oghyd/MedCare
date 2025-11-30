/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.Interface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import model.Patient;
/**
 *
 * @author hhgyd
 */
public class PatientDAO {
    
    public boolean addPatient(Patient patient) {
        boolean insert = false;

        String cne = patient.getCneP();
        String prenom = patient.getPrenomP();
        String nom = patient.getNomP();
        String phone = patient.getPhoneP();
        int age = patient.getAgeP();
        
        String SQL = "INSERT INTO Patients(cne, prenom, nom, phone, age) " +
                     "VALUES(?, ?, ?, ?, ?, ?)";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)) {

            pstm.setString(1, cne);
            pstm.setString(2, prenom);
            pstm.setString(3, nom);
            pstm.setString(4, phone);
            pstm.setInt(5, age);

            int state = pstm.executeUpdate();
            if (state == 1) {
                insert = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return insert;
    }
    
    public static LinkedList<Patient> getAllPatients(){
        
        LinkedList<Patient> ListP = new LinkedList<>();
        
        String SQL = "SELECT * FROM Patients";
        //String cneP, String prenomP, String nomP, String phoneP, int ageP
        try (java.sql.Connection conn = Connection.connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SQL)) {
            
            while(rs.next()){
                Patient p = new Patient(
                        rs.getInt("idPatient"),
                        rs.getString("cneP"),
                        rs.getString("prenomP"),
                        rs.getString("nomP"),
                        rs.getString("phoneP"),
                        rs.getInt("ageP")               
                );
                
                ListP.add(p);
            }
            
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return ListP;
    }

    public boolean updatePatient(Patient patient){
        boolean update = false;
        
        String SQL = "UPDATE Patients " + 
                    "SET cneP = ?, prenomP = ?, nomP = ?, phoneP = ?, ageP = ?";
        
        String cne = patient.getCneP();
        String prenom = patient.getPrenomP();
        String nom = patient.getNomP();
        String phone = patient.getPhoneP();
        int age = patient.getAgeP();
        
        try (java.sql.Connection conn = Connection.connect();
            PreparedStatement pstm = conn.prepareStatement(SQL)) {
            
            pstm.setString(1, cne);
            pstm.setString(2, prenom);
            pstm.setString(3, nom);
            pstm.setString(4, phone);
            pstm.setInt(5, age);
            
            int state = pstm.executeUpdate();
            if(state == 1) update = true;
            
        } catch (Exception e) {
        }
        return update;
        
    }
    
    public boolean deletePatient(int idPatient){
        boolean delete = false;
                
        String SQL = "DELETE FROM participants WHERE id = ?";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)) {
            
            pstm.setInt(1, idPatient);

            int etat = pstm.executeUpdate();
            if (etat == 1) {
                delete = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return delete;
        
        
    }
    // Construction d'un TableModel à partir d'un ResultSet
    public static DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException {
            ResultSetMetaData metaData = rs.getMetaData();

            // noms de colonnes
            Vector<String> columnNames = new Vector<>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // données du tableau
            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }
}
