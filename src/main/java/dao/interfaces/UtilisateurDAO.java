package dao.interfaces;

import java.sql.SQLException;
import java.util.LinkedList;
import model.Utilisateur;

/**
 *
 * @author hhgyd
 */
public interface UtilisateurDAO {
    boolean createUtilisateur(Utilisateur u) throws SQLException;

    Utilisateur findMedecinById(int id) throws SQLException;
    
    Utilisateur findAssistantById(int id) throws SQLException;

    LinkedList<Utilisateur> getAllMedecins() throws SQLException;

    LinkedList<Utilisateur> getAllAssistants() throws SQLException;

    boolean updateUtilisateur(Utilisateur u) throws SQLException;

    boolean deleteUtilisateur(int id) throws SQLException;

    Utilisateur findByLoginPassword(String login, String password) throws SQLException;
}

    