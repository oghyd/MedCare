package model;

/**
 *
 * @author hhgyd
 */
public class Utilisateur {
        
    private int id; 
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private Role role;
    
    //variables medecins
    private String specialite = null; // null for assistants
    private boolean disponible; //default boolean value is false
    

    public Utilisateur() {
    }

    
    
    //medecin
    public Utilisateur(int id, String nom, String prenom, Role role, String specialite, boolean disponible) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.specialite = specialite;
        this.disponible = disponible;
    }

    //assistant

    public Utilisateur(int id, String nom, String prenom, Role role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
    }
    
    
    //with specialité + disponible

    public Utilisateur(int id, String nom, String prenom, String login, String password, Role role, String specialite, boolean disponible) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.role = role;
        this.specialite = specialite;
        this.disponible = disponible;
    }

    
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Role getRole() {
        return role;
    }

    public String getSpecialite() {
        return specialite;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    
    public boolean isAssistant() {
        return role == Role.ASSISTANT;
    }

    public boolean isMedecin() {
        return role == Role.MEDECIN;
    }

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id = " + id + ", nom = " + nom + ", prenom = " + prenom + ", role = " + role + 
                ", specialite = " + specialite + ", disponible = " + disponible + " }";
    }
    
    
}
