package Controller;

import Vue.AppliVAE;
import Modele.Utilisateur;
import Modele.BD.GestionUtilisateurs;

import java.sql.SQLException;
import javafx.event.ActionEvent ;
import javafx.event.EventHandler;
import javafx.collections.ObservableList;

public class ControllerRechecherUsers implements EventHandler<ActionEvent>{
    
    private AppliVAE appli;
    private GestionUtilisateurs gestionUsers;
    private ObservableList<Utilisateur> listUtilisateurs;
    
    public ControllerRechecherUsers(AppliVAE appli,GestionUtilisateurs gestionUsers,ObservableList<Utilisateur> utilisateurs){
        this.appli = appli;
        this.gestionUsers=gestionUsers;
        this.listUtilisateurs = utilisateurs;
    }
    
    @Override
    public void handle(ActionEvent event) {
        System.out.println("Bouton Rechercher Users cliqué");
        // ...
        try {
            this.listUtilisateurs.clear(); // Effacer les données précédentes

            for (Utilisateur user : this.gestionUsers.listeDesUtilisateurs(this.appli.getSearch())) {
                this.listUtilisateurs.add(user);
            }
            
            this.appli.setUtilisateursTable(this.listUtilisateurs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}