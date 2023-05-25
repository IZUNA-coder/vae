package Controller;

import Modele.Utilisateur;
import Modele.BD.GestionUtilisateurs;
import Vue.AppliVAE;
import javafx.event.ActionEvent ;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;

public class ControllerBtnActifUser implements EventHandler<ActionEvent> {
    private AppliVAE appli;
    private Utilisateur user;
    private GestionUtilisateurs gestionUtilisateurs;
    private TableView<Utilisateur> table;

    public ControllerBtnActifUser(AppliVAE appli,Utilisateur user,GestionUtilisateurs gestionUtilisateurs, TableView<Utilisateur> table) {
        this.appli=appli;
        this.gestionUtilisateurs = gestionUtilisateurs;
        this.table = table;
        this.user=user;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Bouton Changer Actif User cliqué");
        if(this.appli.confirmationPopUp("Êtes-vous sûr de vouloir mettre à jour l'attribut actif de l'utilisateur ?","Cliquez sur OK pour valider.")){
            boolean newValue = !this.user.getActif();
            this.user.setActif(newValue);
            gestionUtilisateurs.updateActifValue(this.user.getId(), newValue);
            table.refresh();
        }
    }
}
