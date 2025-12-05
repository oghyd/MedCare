/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao.interfaces;
import java.sql.SQLException;
import java.util.LinkedList;
import model.Patient;
/**
 *
 * @author hhgyd
 */
public interface PatientDAO {
    
    boolean createPatient(Patient patient) throws SQLException;

    // READ
    Patient findPatientById(int id) throws SQLException;
    LinkedList<Patient> findAllPatients() throws SQLException;

    // SEARCH
    LinkedList<Patient> searchPatientByName(String keyword) throws SQLException;

    // UPDATE
    boolean updatePatient(Patient patient) throws SQLException;

    // DELETE
    boolean deletePatient(int id) throws SQLException;
    
}
