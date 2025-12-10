package dao.impl;

import dao.interfaces.UtilisateurDAO;
import db.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import model.Role;
import model.Utilisateur;

public class UtilisateurDAOImpl implements UtilisateurDAO {

    @Override
    public boolean createUtilisateur(Utilisateur u) throws SQLException {
        boolean create = false;
        String nom = u.getNom();
        String prenom = u.getPrenom();
        String login = u.getLogin();
        String password = u.getPassword();
        Role role = u.getRole();
        String specialite = u.getSpecialite();

        // ✅ Correction : bon nom de table + colonnes correctes
        String SQL = "INSERT INTO Utilisateur (nom, prenom, login, password, role, specialite, disponible) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            pstm.setString(1, nom);
            pstm.setString(2, prenom);
            pstm.setString(3, login);
            pstm.setString(4, password);
            pstm.setString(5, role.name());
            pstm.setString(6, specialite);      // null for assistant
            pstm.setBoolean(7, u.isDisponible()); // default true

            int etat = pstm.executeUpdate();

            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    u.setId(rs.getInt(1));
                }
            }

            if (etat == 1) {
                create = true;
                System.out.println("Utilisateur ajouté avec succès : " + u);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Insert error !");
        }

        return create;
    }

    @Override
    public Utilisateur findMedecinById(int id) throws SQLException {

        String SQL = "SELECT id, nom, prenom, role, specialite, disponible "
                   + "FROM Utilisateur "
                   + "WHERE id = ? AND role = 'MEDECIN'";

        try (java.sql.Connection conn = Connection.connect();
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

        String SQL = "SELECT id, nom, prenom, role, login, password "
                   + "FROM Utilisateur "
                   + "WHERE id = ? AND role = 'ASSISTANT'";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)) {

            pstm.setInt(1, id);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {

                    Utilisateur u = new Utilisateur();
                    u.setId(rs.getInt("id"));
                    u.setNom(rs.getString("nom"));
                    u.setPrenom(rs.getString("prenom"));
                    u.setRole(Role.ASSISTANT);
                    u.setLogin(rs.getString("login"));
                    u.setPassword(rs.getString("password"));
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
    public LinkedList<Utilisateur> getAllMedecins() throws SQLException {

        String SQL = "SELECT id, nom, prenom, role, specialite, disponible "
                   + "FROM Utilisateur "
                   + "WHERE role = 'MEDECIN'";

        LinkedList<Utilisateur> UserList = new LinkedList<>();

        try (java.sql.Connection conn = Connection.connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SQL)) {

            while (rs.next()) {
                Utilisateur u = new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        Role.valueOf(rs.getString("role").toUpperCase()),
                        rs.getString("specialite"),
                        rs.getBoolean("disponible")
                );
                UserList.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("getAll medecins error!");
        }

        return UserList;
    }

    @Override
    public LinkedList<Utilisateur> getAllAssistants() throws SQLException {

        // ❌ Correction : manque espace avant FROM → erreur SQL
        String SQL = "SELECT id, nom, prenom, role, login, password "
                   + "FROM Utilisateur "
                   + "WHERE role = 'ASSISTANT'";

        LinkedList<Utilisateur> UserList = new LinkedList<>();

        try (java.sql.Connection conn = Connection.connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SQL)) {

            while (rs.next()) {
                Utilisateur u = new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        Role.ASSISTANT
                );
                u.setLogin(rs.getString("login"));
                u.setPassword(rs.getString("password"));

                UserList.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("getAll assistants error!");
        }

        return UserList;
    }

    @Override
    public boolean updateUtilisateur(Utilisateur u) throws SQLException {

        boolean update = false;

       String SQL = "UPDATE Utilisateur "
        + "SET nom = ?, prenom = ?, login = ?, password = ?, role = ?, specialite = ?, disponible = ? "
        + "WHERE id = ?";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)) {

            pstm.setString(1, u.getNom());
            pstm.setString(2, u.getPrenom());
            pstm.setString(3, u.getLogin());
            pstm.setString(4, u.getPassword());
            pstm.setString(5, u.getRole().name());
            pstm.setString(6, u.getSpecialite());
            pstm.setBoolean(7, u.isDisponible());
            pstm.setInt(8, u.getId());

            int etat = pstm.executeUpdate();
            if (etat == 1) update = true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Update utilisateur error!");
        }

        return update;
    }

    @Override
    public boolean deleteUtilisateur(int id) throws SQLException {

        String SQL = "DELETE FROM Utilisateur WHERE id = ?";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)) {

            pstm.setInt(1, id);

            int rows = pstm.executeUpdate();
            return rows == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Delete utilisateur error!");
            return false;
        }
    }

    @Override
    public Utilisateur findByLoginPassword(String login, String password) throws SQLException {

        String SQL = "SELECT * FROM Utilisateur WHERE login = ? AND password = ?";

        try (java.sql.Connection conn = Connection.connect();
             PreparedStatement pstm = conn.prepareStatement(SQL)) {

            pstm.setString(1, login);
            pstm.setString(2, password);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {

                    Utilisateur u = new Utilisateur();
                    u.setId(rs.getInt("id"));
                    u.setNom(rs.getString("nom"));
                    u.setPrenom(rs.getString("prenom"));
                    u.setRole(Role.valueOf(rs.getString("role")));
                    u.setSpecialite(rs.getString("specialite"));
                    u.setDisponible(rs.getBoolean("disponible"));
                    u.setLogin(login);
                    u.setPassword(password);

                    return u;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Login/password selection error!");
        }

        return null;
    }

}
