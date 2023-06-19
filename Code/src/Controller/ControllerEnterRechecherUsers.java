package Controller;

import Vue.AppliVAE;
import Modele.Utilisateur;
import Modele.BD.GestionUtilisateurs;

import java.sql.SQLException;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControllerEnterRechecherUsers implements EventHandler<KeyEvent>{
    
    private AppliVAE appli;
    private GestionUtilisateurs gestionUsers;
    private ObservableList<Utilisateur> utilisateurs;
    
    public ControllerEnterRechecherUsers(AppliVAE appli,GestionUtilisateurs gestionUsers){
        this.appli = appli;
        this.gestionUsers=gestionUsers;
        utilisateurs = FXCollections.observableArrayList();
    }
    
    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            System.out.println("Enter Rechercher Users cliqué");
            // ...
            try {
                utilisateurs.clear(); // Effacer les données précédentes

                for (Utilisateur user : this.gestionUsers.listeDesUtilisateurs(this.appli.getSearch())) {
                    utilisateurs.add(user);
                }
                this.appli.setUtilisateursTable(utilisateurs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}