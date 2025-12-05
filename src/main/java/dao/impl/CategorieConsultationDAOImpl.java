/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.interfaces.CategorieConsultationDAO;
import db.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import model.CategorieConsultation;

/**
 *
 * @author hhgyd
 */
public class CategorieConsultationDAOImpl implements CategorieConsultationDAO {

    @Override
    public boolean createCategorieConsultation(CategorieConsultation categorie) throws SQLException {
        String sql = "INSERT INTO categorie_consultation (categorie) VALUES (?)";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categorie.getCategorie());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public CategorieConsultation findCategorieConsultationById(int id) throws SQLException {
        String sql = "SELECT * FROM categorie_consultation WHERE id = ?";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new CategorieConsultation(
                        rs.getInt("id"),
                        rs.getString("categorie")
                    );
                }
            }
        }

        return null;
    
    }

    @Override
    public LinkedList<CategorieConsultation> findAllCategorieConsultation() throws SQLException {
        String sql = "SELECT * FROM categorie_consultation ORDER BY categorie ASC";

        LinkedList<CategorieConsultation> findAllCategorieConsultationList = new LinkedList<>();

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CategorieConsultation cat = new CategorieConsultation(
                    rs.getInt("id"),
                    rs.getString("categorie")
                );
                findAllCategorieConsultationList.add(cat);
            }
        }

        return findAllCategorieConsultationList;
    }

    @Override
    public boolean updateCategorieConsultation(CategorieConsultation categorie) throws SQLException {
        String sql = "UPDATE categorie_consultation SET categorie = ? WHERE id = ?";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categorie.getCategorie());
            ps.setInt(2, categorie.getId());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteCategorieConsultation(int id) throws SQLException {
        String sql = "DELETE FROM categorie_consultation WHERE id = ?";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }
    
}
