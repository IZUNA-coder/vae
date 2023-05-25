package Controller;

import Vue.AppliVAE;
import Modele.Utilisateur;
import Modele.BD.GestionUtilisateurs;
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
            if(this.appli.confirmationPopUp("Êtes-vous sûr de vouloir supprimé le compte de l'utilisateur ? (cela supprimera aussi toutes ces enchères et ces ventes d'objets)","Cliquez sur OK pour valider.")){
                this.gestionUsers.supprimeUtilisateur(id);
                this.appli.resetLastUserSelected();
                this.appli.afficherPopUpErreur(false,"Utilisateur supprimé", "Utilisateur supprimé", "Appuyer sur actualiser pour rafraîchir les données.");
                // effacer la ligne du TableView

                // à faire

                //
            }
        } else {
            // Aucune ligne sélectionnée, affichez un message d'erreur
            this.appli.afficherPopUpErreur(true, "Erreur", "Aucun utilisateur sélectionné", "Veuillez sélectionner un utilisateur à supprimer.");
        }
    }

}