package dao.impl;

import dao.interfaces.PatientDAO;
import db.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import model.Patient;

/**
 *
 * @author hhgyd
 */
public class PatientDAOImpl implements PatientDAO{

    @Override
    public boolean createPatient(Patient patient) throws SQLException {
        
        boolean create = false;
        String SQL = "INSERT INTO patient (cne, prenom, nom, phone, age, mail) VALUES (?, ?, ?, ?, ?, ?)";
  
        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)){
            
            pstm.setString(1, patient.getCne());
            pstm.setString(2, patient.getPrenom());
            pstm.setString(3, patient.getNom());
            pstm.setString(4, patient.getPhone());
            pstm.setInt(5, patient.getAge());
            pstm.setString(6, patient.getMail());

            int etat = pstm.executeUpdate();

            // Retrieve generated ID
            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    patient.setId(rs.getInt(1));
                }
            }
            
            if(etat == 1){
                create = true;
                System.out.println("Patient ajouté avec succès : " + patient);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Insert error !");
        } 
        
        return create;
    }

    @Override
    public Patient findPatientById(int id) throws SQLException {
        
        String SQL = "SELECT id, cne, prenom, nom, phone, age, mail FROM patient WHERE id = ?";
        
        Patient p = null;

                            
        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p.setCne(rs.getString("cne"));
                    p.setPrenom(rs.getString("prenom"));
                    p.setNom(rs.getString("nom"));
                    p.setPrenom(rs.getString("phone"));
                    p.setAge(rs.getInt("age"));
                    p.setPrenom(rs.getString("mail"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Selection error !");
        }

        return p;
    }

    @Override
    public LinkedList<Patient> findAllPatients() throws SQLException {
        
        String SQL = "SELECT * FROM patient ORDER BY nom, prenom";

        LinkedList<Patient> PatientsList = new LinkedList<>();

        try (java.sql.Connection conn = Connection.connect();
             Statement pstm = conn.createStatement();
             ResultSet rs = pstm.executeQuery(SQL)) {
            
            /*
            String cne, String prenom, String nom, String phone, int age, String mail
            */

            while (rs.next()) {
                Patient p = new Patient(
                        rs.getInt("id"),
                        rs.getString("cne"),
                        rs.getString("prenom"),
                        rs.getString("nom"),
                        rs.getString("phone"),
                        rs.getInt("age"),
                        rs.getString("mail")
                        
                );
                PatientsList.add(p);
            }
        }

        return PatientsList;
    }

    @Override
    public LinkedList<Patient> searchPatientByName(String keyword) throws SQLException {
        String SQL = "SELECT * FROM patient WHERE nom LIKE ? OR prenom LIKE ? ORDER BY nom, prenom";

        LinkedList<Patient> PatientsByNameSearchList = new LinkedList<>();

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)) {

            String search = "%" + keyword + "%";
            pstm.setString(1, search);
            pstm.setString(2, search);

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Patient p = new Patient(
                        rs.getInt("id"),
                        rs.getString("cne"),
                        rs.getString("prenom"),
                        rs.getString("nom"),
                        rs.getString("phone"),
                        rs.getInt("age"),
                        rs.getString("mail")
                        
                    );
                    PatientsByNameSearchList.add(p);
                    
                }
            }
        }

        return PatientsByNameSearchList;
    }

    @Override
    public boolean updatePatient(Patient patient) throws SQLException {
        boolean update = false;
        String SQL = "UPDATE patient SET cne = ?, prenom = ?, nom = ?, phone = ?, age = ?, mail = ? WHERE id = ?";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)) {

            pstm.setString(1, patient.getCne());
            pstm.setString(2, patient.getPrenom());
            pstm.setString(3, patient.getNom());
            pstm.setString(4, patient.getPhone());
            pstm.setInt(5, patient.getAge());
            pstm.setString(6, patient.getMail());
            pstm.setInt(7, patient.getId());

            int etat = pstm.executeUpdate();
            
            if(etat == 1) update = true;
        }
        return update;
    }

    @Override
    public boolean deletePatient(int id) throws SQLException {
        boolean delete = false;
        
        String SQL = "DELETE FROM patient WHERE id = ?";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)) {

            pstm.setInt(1, id);
            
            int etat = pstm.executeUpdate();
            if (etat == 1) delete = true;
            
        }
        
        return delete;
    }
    
}
