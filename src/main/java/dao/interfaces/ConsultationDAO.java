/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao.interfaces;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Map;
import model.Consultation;

/**
 *
 * @author hhgyd
 */
public interface ConsultationDAO {

    // CREATE
    boolean createConsultation(Consultation consultation) throws SQLException;

    // READ
    Consultation findConsultationById(int id) throws SQLException;
    LinkedList<Consultation> findAllConsultations() throws SQLException;

    // UPDATE
    boolean updateConsultation(Consultation consultation) throws SQLException;

    // DELETE
    boolean deleteConsultation(int id) throws SQLException;
    
    // FILTERS
    LinkedList<Consultation> findByDate(LocalDate date) throws SQLException;
    LinkedList<Consultation> findByPatient(int patientId) throws SQLException;
    LinkedList<Consultation> findByStatus(String status) throws SQLException;   // optional
    
    // AVAILABILITY
    boolean isSlotAvailable(int medecinId, LocalDateTime dateHeure) throws SQLException;
    
    // PAYMENT
    boolean markAsPaid(int consultationId, LocalDate datePaiement) throws SQLException;
    
    // DOCTOR VIEW
    LinkedList<Consultation> findDailyForDoctor(int medecinId, LocalDate date) throws SQLException;
    
    // MONTHLY REPORTING
    int countConsultationsForMonth(int year, int month) throws SQLException;

    double totalRevenueForMonth(int year, int month) throws SQLException;

    Map<LocalDate, Double> dailyRevenueForMonth(int year, int month) throws SQLException;

    
}
