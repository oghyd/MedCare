/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import dao.interfaces.UtilisateurDAO;
import dao.impl.UtilisateurDAOImpl;
import java.sql.SQLException;
import model.Utilisateur;

/**
 *
 * @author idber
 */
    public class AuthService {

    private final UtilisateurDAO utilisateurDAO;

    public AuthService() {
        this.utilisateurDAO = new UtilisateurDAOImpl();
    }

    /**
     * Authentifie un utilisateur.
     * 
     * @param login    login saisi dans l'UI
     * @param password mot de passe saisi dans l'UI
     * @return Utilisateur si OK, sinon null
     */
    public Utilisateur login(String login, String password) {
        try {
            // utilise exactement la méthode existante : findByLoginPassword
            return utilisateurDAO.findByLoginPassword(login, password);

        } catch (SQLException e) {
            System.err.println("Erreur dans AuthService.login()");
            return null;
        }
    }
}