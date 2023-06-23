package Modele.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Modele.Role;
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

    public ArrayList<Utilisateur> getUtilisateurs() throws SQLException {
        ArrayList<Utilisateur> liste = new ArrayList<>();
        String query = "SELECT * FROM UTILISATEUR";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
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

    public boolean checkPassword(String password){
        int numbers = 0;
        if(password.length()<8){
            return false;
        }
        for(int i=0; i<password.length(); i++){
            char c = password.charAt(i);
            if(Character.isDigit(c)){
                numbers+=1;
            }
        }
        if(numbers<1){
            return false;
        }
        return true;
    }

    public boolean checkEmail(String email){
        // Pattern pour vérifier le format de l'adresse email
        String emailPattern = "^[A-Za-z][A-Za-z0-9._-]*@[A-Za-z0-9]+([.-][A-Za-z0-9]+)*\\.[A-Za-z]{2,}$";
        
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        
        return matcher.matches();
    }

    public void supprimeUtilisateur(int idUt) {
        try {
            // 1. Supprimer toutes les enchères où l'utilisateur a enchéri
            PreparedStatement statement1 = this.connection.prepareStatement(
                    "DELETE FROM ENCHERIR WHERE idut = ?"
            );
            statement1.setInt(1, idUt);
            statement1.executeUpdate();
            statement1.close();
    
            // 2. Supprimer les enchères sur les ventes où l'objet appartient à l'utilisateur
            PreparedStatement statement2 = this.connection.prepareStatement(
                    "DELETE FROM ENCHERIR WHERE idve IN (SELECT idve FROM VENTE WHERE idob IN (SELECT idob FROM OBJET WHERE idut = ?))"
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
            
            // Supprimer les photos des objets de l'utilisateur
            PreparedStatement statement3bis = this.connection.prepareStatement(
                    "DELETE FROM PHOTO WHERE idob IN (SELECT idob FROM OBJET WHERE idut = ?)"
            );
            statement3bis.setInt(1, idUt);
            statement3bis.executeUpdate();
            statement3bis.close();

            // 4. Supprimer tous les objets appartenant à l'utilisateur
            PreparedStatement statement4 = this.connection.prepareStatement(
                    "DELETE FROM OBJET WHERE idut = ?"
            );
            statement4.setInt(1, idUt);
            statement4.executeUpdate();
            statement4.close();

            // supprimer les ventes favorites de l'utilisateur
            PreparedStatement statement4bis = this.connection.prepareStatement(
                    "DELETE FROM FAVORIS WHERE idut = ?"
            );
            statement4bis.setInt(1, idUt);
            statement4bis.executeUpdate();
            statement4bis.close();
    
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

    public boolean ajouterUtilisateur(Utilisateur user) {
        // Ajouter l'utilisateur dans la base de données
        // Utilisez votre logique de requête SQL pour effectuer l'insertion
        // Retournez true si l'insertion est réussie, sinon retournez false
    
        if (this.connection == null) {
            System.out.println("La connexion à la base de données n'est pas établie");
            return false;
        }
    
        String query = "INSERT INTO UTILISATEUR (idut, pseudout, emailut, mdput, activeut, idrole) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Récupérer l'ID maximum et ajouter 1
            int idut = 0;
            String getMaxIdQuery = "SELECT MAX(idut) FROM UTILISATEUR";
            try (Statement maxIdStatement = connection.createStatement();
                 ResultSet resultSet = maxIdStatement.executeQuery(getMaxIdQuery)) {
                if (resultSet.next()) {
                    int maxId = resultSet.getInt(1);
                    idut = maxId + 1;
                }
            }

            String activeUt = "O";
            int idRole = 2;
    
            statement.setInt(1, idut);
            statement.setString(2, String.valueOf(user.getUsername()));
            statement.setString(3, String.valueOf(user.getEmail()));
            statement.setString(4, String.valueOf(user.getPassword()));
            statement.setString(5, activeUt);
            statement.setInt(6, idRole);
            
    
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Inscription réussie");
                // Gérer la réussite de l'inscription ici (par exemple, afficher un message de succès à l'utilisateur)
                return true;
            } else {
                System.out.println("Erreur lors de l'inscription");
                // Gérer l'échec de l'inscription ici
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de requête ici
    }
        return false;
    }


    public void updateActifValue(int utilisateurId, boolean newActifValue) {
        // mettre à jour la valeur dans la base de données
        try {
            // Connexion à la base de données
            // Exécution de la requête de mise à jour
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

    public boolean checkExistingUsername(String username) {
        // Vérifier si l'identifiant existe déjà dans la base de données
        // Utilisez votre logique de requête SQL pour effectuer la vérification
        // Retournez true si l'identifiant existe déjà, sinon retournez false
        System.out.println("Check existing username");

        if (this.connection == null) {
            System.out.println("La connexion à la base de données n'est pas établie");
            return false;
        }
    
        String query = "SELECT COUNT(*) FROM UTILISATEUR WHERE pseudout = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
    
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

    public boolean checkExistingID(int idUser) {
        // Vérifier si l'identifiant existe déjà dans la base de données
        // Utilisez votre logique de requête SQL pour effectuer la vérification
        // Retournez true si l'identifiant existe déjà, sinon retournez false
        System.out.println("Check existing ID");

        if (this.connection == null) {
            System.out.println("La connexion à la base de données n'est pas établie");
            return false;
        }
    
        String query = "SELECT COUNT(*) FROM UTILISATEUR WHERE idut = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUser);
    
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

    public boolean checkExistingEmail(String email) {
        // Vérifier si l'identifiant existe déjà dans la base de données
        // Utilisez votre logique de requête SQL pour effectuer la vérification
        // Retournez true si l'identifiant existe déjà, sinon retournez false
        System.out.println("Check existing email");
        System.out.println(email);
    
        if (this.connection == null) {
            System.out.println("La connexion à la base de données n'est pas établie");
            return false;
        }
    
        String query = "SELECT COUNT(*) FROM UTILISATEUR WHERE emailUT = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
    
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

    public void editUsername(int idUser,String newUsername){
        try {
            // Connexion à la base de données
            // Exécution de la requête de mise à jour
            String updateQuery = "UPDATE UTILISATEUR SET pseudoUt = ? WHERE idUt = ?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, newUsername);
            statement.setInt(2, idUser);
            statement.executeUpdate();
            // Fermeture de la connexion et des ressources
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des erreurs
        }
    }

    public void editEmail(int idUser,String newEmail){
        try {
            // Connexion à la base de données
            // Exécution de la requête de mise à jour
            String updateQuery = "UPDATE UTILISATEUR SET emailut = ? WHERE idut = ?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, newEmail);
            statement.setInt(2, idUser);
            statement.executeUpdate();
            // Fermeture de la connexion et des ressources
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des erreurs
        }
    }

    public void editId(int idUser,String newId){
        try {
            // Connexion à la base de données
            // Exécution de la requête de mise à jour
            String updateQuery = "UPDATE UTILISATEUR SET idut = ? WHERE idut = ?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, newId);
            statement.setInt(2, idUser);
            statement.executeUpdate();
            // Fermeture de la connexion et des ressources
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des erreurs
        }
    }

    public void editRole(int idUser,Role newRole){
        try {
            // Connexion à la base de données
            // Exécution de la requête de mise à jour
            String updateQuery = "UPDATE UTILISATEUR SET idRole = ? WHERE idut = ?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setInt(1, newRole.getIdRole());
            statement.setInt(2, idUser);
            statement.executeUpdate();
            // Fermeture de la connexion et des ressources
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des erreurs
        }
    }

    public void editPassword(int id, String nouveauMDP) {
        try {
            // Connexion à la base de données
            // Exécution de la requête de mise à jour
            String updateQuery = "UPDATE UTILISATEUR SET mdput = ? WHERE idut = ?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, nouveauMDP);
            statement.setInt(2, id);
            statement.executeUpdate();
            // Fermeture de la connexion et des ressources
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des erreurs
        }
    }
    
}
