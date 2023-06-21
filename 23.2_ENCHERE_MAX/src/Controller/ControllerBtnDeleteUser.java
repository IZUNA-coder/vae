package Controller;

import Vue.AppliVAE;

import java.sql.SQLException;

import Modele.Utilisateur;
import Modele.BD.GestionUtilisateurs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent ;
import javafx.event.EventHandler;
public class ControllerBtnDeleteUser implements EventHandler<ActionEvent>{
    
    private GestionUtilisateurs gestionUsers;
    private AppliVAE appli;


    public ControllerBtnDeleteUser(AppliVAE appli, GestionUtilisateurs gestionUsers) {
        this.appli = appli;
        this.gestionUsers = gestionUsers;
    }
    
    
    @Override
    public void handle(ActionEvent event) {
        System.out.println("Bouton Delete User cliqué");
        Utilisateur dernierUserSelected = this.appli.getLastUserSelected();
        // Vérifiez d'abord si une ligne est sélectionnée
        if (dernierUserSelected != null) {
            // Obtenez l'ID de l'utilisateur sélectionné
            int id = dernierUserSelected.getId();
            if(this.appli.afficherPopUpSupprimerUser(dernierUserSelected)){
                this.gestionUsers.supprimeUtilisateur(id);
                this.appli.afficherPopUpErreur(false,"Utilisateur supprimé", "Utilisateur supprimé", "Appuyer sur actualiser pour rafraîchir les données.");
                ObservableList<Utilisateur> list = FXCollections.observableArrayList();
                this.appli.resetLastUserSelected();

                try {
                    for (Utilisateur user : this.gestionUsers.listeDesUtilisateurs(this.appli.getSearch())) {
                        list.add(user);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                
                this.appli.setUtilisateursTable(list);
            }
        } else {
            // Aucune ligne sélectionnée, affichez un message d'erreur
            this.appli.afficherPopUpErreur(true, "Erreur", "Aucun utilisateur sélectionné", "Veuillez sélectionner un utilisateur à supprimer.");
        }
    }

}