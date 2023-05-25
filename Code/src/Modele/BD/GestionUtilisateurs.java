package Modele.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modele.Utilisateur;

public class GestionUtilisateurs {
    private Connection connection;

    public GestionUtilisateurs(Connection c){
        this.connection=c;
    }

    public ArrayList<Utilisateur> listeDesUtilisateurs(String username) throws SQLException {
        ArrayList<Utilisateur> liste = new ArrayList<>();
        String query = "SELECT * FROM UTILISATEUR WHERE pseudout LIKE ?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, "%" + username + "%");
            ResultSet r = statement.executeQuery();
            while (r.next()) {
                liste.add(new Utilisateur(
                        r.getInt("idut"),
                        r.getString("pseudout"),
                        r.getString("emailut"),
                        r.getString("mdput"),
                        r.getString("activeut").charAt(0) == 'O',
                        r.getInt("idrole")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de requête ici
        }
        return liste;
    }

    public void supprimeUtilisateur(int idUt) {
        try {
            // 1. Supprimer toutes les enchères où l'utilisateur a enchéri
            PreparedStatement statement1 = this.connection.prepareStatement(
                    "DELETE FROM encherir WHERE idut = ?"
            );
            statement1.setInt(1, idUt);
            statement1.executeUpdate();
            statement1.close();
    
            // 2. Supprimer les enchères sur les ventes où l'objet appartient à l'utilisateur
            PreparedStatement statement2 = this.connection.prepareStatement(
                    "DELETE FROM encherir WHERE idve IN (SELECT idve FROM VENTE WHERE idob IN (SELECT idob FROM OBJET WHERE idut = ?))"
            );
            statement2.setInt(1, idUt);
            statement2.executeUpdate();
            statement2.close();
    
            // 3. Supprimer les ventes où l'objet appartient à l'utilisateur
            PreparedStatement statement3 = this.connection.prepareStatement(
                    "DELETE FROM VENTE WHERE idob IN (SELECT idob FROM OBJET WHERE idut = ?)"
            );
            statement3.setInt(1, idUt);
            statement3.executeUpdate();
            statement3.close();
    
            // 4. Supprimer tous les objets appartenant à l'utilisateur
            PreparedStatement statement4 = this.connection.prepareStatement(
                    "DELETE FROM OBJET WHERE idut = ?"
            );
            statement4.setInt(1, idUt);
            statement4.executeUpdate();
            statement4.close();
    
            // 5. Supprimer l'utilisateur lui-même
            PreparedStatement statement5 = this.connection.prepareStatement(
                    "DELETE FROM UTILISATEUR WHERE idut = ?"
            );
            statement5.setInt(1, idUt);
            statement5.executeUpdate();
            statement5.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de requête ici
        }
    }    

    public void updateActifValue(int utilisateurId, boolean newActifValue) {
        // Utilisez votre logique d'accès aux données pour mettre à jour la valeur dans la base de données
        // Vous pouvez utiliser des requêtes SQL, des appels de méthode à votre couche d'accès aux données, etc.
        // Exemple de code fictif :
        try {
            // Connexion à la base de données
            // Exécution de la requête de mise à jour
            // Exemple de requête fictive :
            String updateQuery = "UPDATE UTILISATEUR SET activeut = ? WHERE idut = ?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, newActifValue ? "O" : "N");
            statement.setInt(2, utilisateurId);
            statement.executeUpdate();
            // Fermeture de la connexion et des ressources
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des erreurs
        }
    }

    public int getNbTotUsers() {
        int res=0;
        String query = "SELECT count(idut) FROM UTILISATEUR";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            ResultSet r = statement.executeQuery();
            r.next();
            res = r.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de requête ici
        }
        return res;
    }

    public int getNbUsersActif() {
        int res=0;
        String query = "SELECT count(idut) FROM UTILISATEUR WHERE activeut='O'";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            ResultSet r = statement.executeQuery();
            r.next();
            res = r.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de requête ici
        }
        return res;
    }

    public int getNbUsersInactif() {
        int res=0;
        String query = "SELECT count(idut) FROM UTILISATEUR WHERE activeut='N'";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            ResultSet r = statement.executeQuery();
            r.next();
            res = r.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de requête ici
        }
        return res;
    }

    public int getNbTotVentes() {
        int res=0;
        String query = "SELECT count(idve) FROM VENTE";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            ResultSet r = statement.executeQuery();
            r.next();
            res = r.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de requête ici
        }
        return res;
    }

    public int getNbVentesValidee() {
        int res=0;
        String query = "SELECT count(idve) FROM VENTE NATURAL JOIN STATUT WHERE nomst='Validée'";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            ResultSet r = statement.executeQuery();
            r.next();
            res = r.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de requête ici
        }
        return res;
    }

    public int getNbVentesNonConclus() {
        int res=0;
        String query = "SELECT count(idve) FROM VENTE NATURAL JOIN STATUT WHERE nomst='Non conclue'";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            ResultSet r = statement.executeQuery();
            r.next();
            res = r.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de requête ici
        }
        return res;
    }
    
}
