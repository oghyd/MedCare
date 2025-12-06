/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import dao.interfaces.UtilisateurDAO;
import dao.impl.UtilisateurDAOImpl;
import model.Utilisateur;

/**
 *
 * @author idber
 */


public class AuthService {

    private final UtilisateurDAO utilisateurDAO;

    // Constructeur
    public AuthService() {
        this.utilisateurDAO = new UtilisateurDAOImpl();
    }

    /**
     * Tentative de connexion.
     * 
     * @param login    login fourni dans l’UI
     * @param password password fourni dans l’UI
     * @return Utilisateur si OK, null si identifiants invalides
     */
    public Utilisateur login(String login, String password) {
        try {
            return utilisateurDAO.findByLoginPassword(login, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur AuthService.login()");
            return null;
        }
    }
}