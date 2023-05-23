package Modele.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modele.Utilisateur;


public class ConnexionUtilisateur {
    private Utilisateur user;
    private Connection connection;

    public ConnexionUtilisateur(Utilisateur u, Connection c){
        this.user=u;
        this.connection=c;
    }


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

    public boolean checkDonnéeVide(){
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty()){
            return true;
        }
        return false;
    }
}
