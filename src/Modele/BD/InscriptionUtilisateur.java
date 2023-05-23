package Modele.BD;

// import sql
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// improt pour le check email
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Modele.Utilisateur;


public class InscriptionUtilisateur {
    private Utilisateur user;
    private Connection connection;

    public InscriptionUtilisateur(Utilisateur u, Connection c){
        this.user=u;
        this.connection=c;
    }

    public boolean checkExistingUsername() {
        // Vérifier si l'identifiant existe déjà dans la base de données
        // Utilisez votre logique de requête SQL pour effectuer la vérification
        // Retournez true si l'identifiant existe déjà, sinon retournez false
    
        if (this.connection == null) {
            System.out.println("La connexion à la base de données n'est pas établie");
            return false;
        }
    
        String query = "SELECT COUNT(*) FROM UTILISATEUR WHERE pseudout = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, this.user.getUsername());
    
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

    public boolean checkPassword(){
        String password = user.getPassword();
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

    public boolean checkDonnéeVide(){
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty()){
            return true;
        }
        return false;
    }

    public boolean checkEmail(){
        String email = user.getEmail();
        // Pattern pour vérifier le format de l'adresse email
        String emailPattern = "^[A-Za-z][A-Za-z0-9._-]*@[A-Za-z0-9]+([.-][A-Za-z0-9]+)*\\.[A-Za-z]{2,}$";
        
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        
        return matcher.matches();
    }

    public boolean ajouterUtilisateur() {
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

            String email = idut + "@gmail.com";
            String activeUt = "O";
            int idRole = 2;
    
            statement.setInt(1, idut);
            statement.setString(2, String.valueOf(this.user.getUsername()));
            statement.setString(3, String.valueOf(email));
            statement.setString(4, String.valueOf(this.user.getPassword()));
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
}