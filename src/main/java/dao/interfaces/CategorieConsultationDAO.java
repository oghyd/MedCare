/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao.interfaces;

import java.sql.SQLException;
import java.util.LinkedList;
import model.CategorieConsultation;

/**
 *
 * @author hhgyd
 */
public interface CategorieConsultationDAO {
    
    // CREATE
    boolean createCategorieConsultation(CategorieConsultation categorie) throws SQLException;

    // READ
    CategorieConsultation findById(int id) throws SQLException;
    LinkedList<CategorieConsultation> findAll() throws SQLException;

    // UPDATE
    boolean update(CategorieConsultation categorie) throws SQLException;

    // DELETE
    boolean delete(int id) throws SQLException;
    
}
