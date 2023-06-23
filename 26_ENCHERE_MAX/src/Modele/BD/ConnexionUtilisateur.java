package Modele.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modele.Role;
import Modele.Utilisateur;


public class ConnexionUtilisateur {
    private Utilisateur user;
    private Connection connection;
    /**
     * Constructeur de la classe ConnexionUtilisateur
     * @param u
     * @param c
     */
    public ConnexionUtilisateur(Utilisateur u, Connection c){
        this.user=u;
        this.connection=c;
    }
    /**
     * Méthode qui permet de vérifier si l'utilisateur est bien dans la base de données
     * @return
     */
    public boolean connexionUtilisateur() {
        if (this.connection == null) {
            System.out.println("La connexion à la base de données n'est pas établie");
            return false;
        }

        String query = "SELECT COUNT(*) FROM UTILISATEUR WHERE pseudout = ? AND BINARY mdput = ? AND idrole =2"; // BINARY pour respecter la casse exacte

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de requête ici
        }

        return false;
    }
    /**
     * Méthode qui permet de vérifier si l'administrateur est bien dans la base de données
     * @return
     */
    public boolean connexionAdmin() {
        if (this.connection == null) {
            System.out.println("La connexion à la base de données n'est pas établie");
            return false;
        }

        String query = "SELECT COUNT(*) FROM UTILISATEUR WHERE pseudout = ? AND BINARY mdput = ? AND idrole =1"; // BINARY pour respecter la casse exacte

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de requête ici
        }

        return false;
    }
    /**
     * Méthode qui permet de vérifier si les données sont vide
     * @return boolean
     */
    public boolean checkDonnéeVide(){
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty()){
            return true;
        }
        return false;
    }


    public Utilisateur getUser(String username) {
        if (this.connection == null) {
            System.out.println("La connexion à la base de données n'est pas établie");
            return null;
        }

        String query = "SELECT * FROM UTILISATEUR WHERE pseudout = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Utilisateur user = new Utilisateur();
                    user.setId(resultSet.getInt("idut"));
                    user.setUsername(resultSet.getString("pseudout"));
                    user.setEmail(resultSet.getString("emailut"));
                    user.setPassword(resultSet.getString("mdput"));
                    user.setActif(resultSet.getBoolean("activeut"));
                    user.setRole(new Role(resultSet.getInt("idrole")));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de requête ici
        }

        return null;
    }
}
