package dao.impl;

import dao.interfaces.ConsultationDAO;
import db.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import model.Consultation;

/**
 *
 * @author hhgyd
 */
public class ConsultationDAOImpl implements ConsultationDAO {

    @Override
    public boolean createConsultation(Consultation consultation) throws SQLException {
        
        String SQL = "INSERT INTO consultation (" +
                     "patient_id, medecin_id, categorie_id, date_consultation, date_paiement, description, status, prix, paid" +
                     ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            pstm.setInt(1, consultation.getIdPatient());
            pstm.setInt(2, consultation.getIdMedecin());
            pstm.setInt(3, consultation.getIdcategorie());

            if (consultation.getDateConsultation() != null) {
                pstm.setDate(4, Date.valueOf(consultation.getDateConsultation()));
            } else {
                pstm.setNull(4, Types.DATE);
            }

            if (consultation.getDatePaiement() != null) {
                pstm.setDate(5, Date.valueOf(consultation.getDatePaiement()));
            } else {
                pstm.setNull(5, Types.DATE);
            }

            pstm.setString(6, consultation.getDescription());
            pstm.setString(7, consultation.getStatus());
            pstm.setDouble(8, consultation.getPrix());
            pstm.setBoolean(9, consultation.isPaid());

            int affected = pstm.executeUpdate();
            if (affected == 0) return false;

            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    consultation.setIdC(rs.getInt(1));
                }
            }
            return true;
        }
    }

    @Override
    public Consultation findConsultationById(int id) throws SQLException {
        String SQL = "SELECT * FROM consultation WHERE id = ?";
        Consultation c = null;

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)) {

            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    c = mapResultSetToConsultation(rs);
                }
            }
        }
        return c;
    }

    @Override
    public LinkedList<Consultation> findAllConsultations() throws SQLException {
        String SQL = "SELECT * FROM consultation ORDER BY date_consultation DESC";
        LinkedList<Consultation> findAllConsultationsList = new LinkedList<>();

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                findAllConsultationsList.add(mapResultSetToConsultation(rs));
            }
        }

        return findAllConsultationsList;
    }

    @Override
    public boolean updateConsultation(Consultation consultation) throws SQLException {
        String SQL = "UPDATE consultation SET " +
                     "patient_id = ?, medecin_id = ?, categorie_id = ?, date_consultation = ?, date_paiement = ?, " +
                     "description = ?, status = ?, prix = ?, paid = ? " +
                     "WHERE id = ?";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)) {

            pstm.setInt(1, consultation.getIdPatient());
            pstm.setInt(2, consultation.getIdMedecin());
            pstm.setInt(3, consultation.getIdcategorie());

            if (consultation.getDateConsultation() != null) {
                pstm.setDate(4, Date.valueOf(consultation.getDateConsultation()));
            } else {
                pstm.setNull(4, Types.DATE);
            }

            if (consultation.getDatePaiement() != null) {
                pstm.setDate(5, Date.valueOf(consultation.getDatePaiement()));
            } else {
                pstm.setNull(5, Types.DATE);
            }

            pstm.setString(6, consultation.getDescription());
            pstm.setString(7, consultation.getStatus());
            pstm.setDouble(8, consultation.getPrix());
            pstm.setBoolean(9, consultation.isPaid());

            pstm.setInt(10, consultation.getIdC());

            int affected = pstm.executeUpdate();
            return affected > 0;
        }
        
    }

    @Override
    public boolean deleteConsultation(int id) throws SQLException {
        String SQL = "DELETE FROM consultation WHERE id = ?";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)) {

            pstm.setInt(1, id);
            int affected = pstm.executeUpdate();
            return affected > 0;
        }
    }

    @Override
    public LinkedList<Consultation> findByDate(LocalDate date) throws SQLException {
        String SQL = "SELECT * FROM consultation WHERE date_consultation = ? ORDER BY date_consultation";
        LinkedList<Consultation> findByDateList = new LinkedList<>();

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)) {

            pstm.setDate(1, Date.valueOf(date));
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    findByDateList.add(mapResultSetToConsultation(rs));
                }
            }
        }

        return findByDateList;
    }

    @Override
    public LinkedList<Consultation> findByPatient(int patientId) throws SQLException {
        String SQL = "SELECT * FROM consultation WHERE patient_id = ? ORDER BY date_consultation DESC";
        LinkedList<Consultation> findByPatientList = new LinkedList<>();

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)) {

            pstm.setInt(1, patientId);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    findByPatientList.add(mapResultSetToConsultation(rs));
                }
            }
        }

        return findByPatientList;
    }

    @Override
    public LinkedList<Consultation> findByStatus(String status) throws SQLException {
        String SQL = "SELECT * FROM consultation WHERE status = ? ORDER BY date_consultation DESC";
        LinkedList<Consultation> list = new LinkedList<>();

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)) {

            pstm.setString(1, status);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToConsultation(rs));
                }
            }
        }

        return list;
        
    }

    @Override
    public boolean isSlotAvailable(int medecinId, LocalDateTime dateHeure) throws SQLException {
        /*
         * Slot availability here checks if the doctor already has a consultation on the same date.
         *
         * If we later switch to times (LocalDateTime) or a duration, adapt this SQL.
         */
        String SQL = "SELECT COUNT(*) FROM consultation WHERE medecin_id = ? AND date_consultation = ? AND status != 'CANCELLED'";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)) {

            pstm.setInt(1, medecinId);
            // convert LocalDateTime to LocalDate (date part) for comparison
            pstm.setDate(2, Date.valueOf(dateHeure.toLocalDate()));
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count == 0;
                }
            }
        }

        return true;
    }
    
        
    

    @Override
    public boolean markAsPaid(int consultationId, LocalDate datePaiement) throws SQLException {
        String sql = "UPDATE consultation SET paid = 1, date_paiement = ? WHERE id = ?";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            if (datePaiement != null) {
                pstm.setDate(1, Date.valueOf(datePaiement));
            } else {
                pstm.setNull(1, Types.DATE);
            }
            pstm.setInt(2, consultationId);

            int affected = pstm.executeUpdate();
            return affected > 0;
        }
        
    }

    @Override
public boolean markAsUnpaid(int consultationId, LocalDate datePaiement) throws SQLException {
    String sql = "UPDATE consultation SET paid = 0, date_paiement = ? WHERE id = ?";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            if (datePaiement != null) {
                pstm.setDate(1, Date.valueOf(datePaiement));
            } else {
                pstm.setNull(1, Types.DATE);
            }
            pstm.setInt(2, consultationId);

            int affected = pstm.executeUpdate();
            return affected > 0;
        }
        
    }

    
    
    @Override
    public LinkedList<Consultation> findDailyForDoctor(int medecinId, LocalDate date) throws SQLException {
        String sql = "SELECT * FROM consultation WHERE medecin_id = ? AND date_consultation = ? ORDER BY date_consultation";
        LinkedList<Consultation> findDailyForDoctorList = new LinkedList<>();

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, medecinId);
            pstm.setDate(2, Date.valueOf(date));
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    findDailyForDoctorList.add(mapResultSetToConsultation(rs));
                }
            }
        }

        return findDailyForDoctorList;
    }

    @Override
    public int countConsultationsForMonth(int year, int month) throws SQLException {
String sql = "SELECT COUNT(*) FROM consultation " +
             "WHERE YEAR(date_paiement) = ? AND MONTH(date_paiement) = ? " +
             "AND paid = 1";
        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, year);
            pstm.setInt(2, month);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    @Override
    public double totalRevenueForMonth(int year, int month) throws SQLException {
        /*
         * Two possibilities:
         *  1) price stored on consultation.prix (model includes prix) -> use consultation.prix directly
         *  2) price stored on category table -> join with categorie_consultation
         *
         * Below we use consultation.prix because the model has prix.
         */
        String sql = "SELECT COALESCE(SUM(prix), 0) FROM consultation " +
             "WHERE YEAR(date_paiement) = ? AND MONTH(date_paiement) = ? " +
             "AND paid = 1";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, year);
            pstm.setInt(2, month);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }
        }
        return 0.0;
    }

    @Override
    public Map<LocalDate, Double> dailyRevenueForMonth(int year, int month) throws SQLException {
        /*
         * Same as above: use consultation.prix (model stores prix).
         */
        String sql = "SELECT date_paiement AS jour, COALESCE(SUM(prix), 0) AS total " +
             "FROM consultation " +
             "WHERE YEAR(date_paiement) = ? AND MONTH(date_paiement) = ? " +
             "AND paid = 1 " +
             "GROUP BY date_paiement ORDER BY date_paiement";


        LinkedHashMap<LocalDate, Double> map = new LinkedHashMap<>();

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, year);
            pstm.setInt(2, month);

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Date d = rs.getDate("jour");
                    LocalDate ld = d != null ? d.toLocalDate() : null;
                    double total = rs.getDouble("total");
                    if (ld != null) {
                        map.put(ld, total);
                    }
                }
            }
        }

        return map;
        
    }
    
    
    // Helper: map ResultSet -> Consultation
    private Consultation mapResultSetToConsultation(ResultSet rs) throws SQLException {
        Consultation c = new Consultation();

        // DB column "id" maps to model field idC
        c.setIdC(rs.getInt("id"));
        c.setIdPatient(rs.getInt("patient_id"));
        c.setIdMedecin(rs.getInt("medecin_id"));
        c.setIdcategorie(rs.getInt("categorie_id"));

        Date dc = rs.getDate("date_consultation");
        if (dc != null) {
            c.setDateConsultation(dc.toLocalDate());
        }

        Date dp = rs.getDate("date_paiement");
        if (dp != null) {
            c.setDatePaiement(dp.toLocalDate());
        }

        c.setDescription(rs.getString("description"));

        // status column
        c.setStatus(rs.getString("status"));

        // price on consultation row
        c.setPrix(rs.getDouble("prix"));

        c.setPaid(rs.getBoolean("paid"));

        return c;
    }
    
    
}