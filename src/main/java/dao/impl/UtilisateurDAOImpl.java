package dao.impl;

import dao.interfaces.UtilisateurDAO;
import db.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import model.Role;
import model.Utilisateur;
/**
 *
 * @author hhgyd
 * createUtilisateur (based on role), getById, getAllMedecin, getAllAssistants, update, delete, findByLoginPassword
 */
public class UtilisateurDAOImpl implements UtilisateurDAO{

    @Override
    public void createUtilisateur(Utilisateur u) throws SQLException {
        boolean create = false;
        String nom = u.getNom();
        String prenom = u.getPrenom();
        String login = u.getLogin();
        String password = u.getPassword();
        Role role = u.getRole();
        String specialite = u.getSpecialite();
        
        
        String SQL = "INSERT INTO Utilisateurs (nom, prenom, login, password, role)"
                + "VALUES (?, ?, ?, ?, ?)";
        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)){
            
            pstm.setString(1, nom);
            pstm.setString(2, prenom);
            pstm.setString(3, login);
            pstm.setString(4, password);
            pstm.setString(5, String.valueOf(role));
            
            if(specialite != null){
                pstm.setString(6, specialite);
            }
            
            int etat = pstm.executeUpdate();
            if(etat == 1){
                create = true;
                System.out.println("Utilisateur ajouté avec succès : " + u);
            }
                        
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Insert error !");
        } 
    }

    @Override
    public Utilisateur findMedecinById(int id) throws SQLException {
        
        String SQL = "SELECT id, nom, prenom, role, specialite, disponible"
                + " FROM Utilisateur " + " WHERE id = ? AND role = MEDECIN";
        
        try(java.sql.Connection conn = Connection.connect();
            PreparedStatement pstm = conn.prepareStatement(SQL)) {
            
            pstm.setInt(1, id);
            
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {

                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setRole(Role.valueOf(rs.getString("role")));
                u.setSpecialite(rs.getString("specialite"));
                u.setDisponible(rs.getBoolean("disponible"));

                return u;
                    
                }
            }            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Selection error !");
        }
        
        return null;     
    }
    
    @Override
    public Utilisateur findAssistantById(int id) throws SQLException {
        
         throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<Utilisateur> getAllMedecins() throws SQLException {
        
         throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<Utilisateur> getAllAssistants() throws SQLException {
        
         throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateUtilisateur(Utilisateur u) throws SQLException {
        
         throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteUtilisateur(int id) throws SQLException {
        
         
    }

    @Override
    public Utilisateur findByLoginPassword(String login, String password) throws SQLException {
        
         throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
