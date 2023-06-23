package Controller;

import Modele.BD.GestionUtilisateurs;
import Vue.AppliVAE;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ControllerSupprimerCompte implements EventHandler<ActionEvent> {
    private AppliVAE appli;
    private GestionUtilisateurs gestionUtilisateur;
    public ControllerSupprimerCompte(AppliVAE appli, GestionUtilisateurs gestionUtilisateur) {
        this.appli = appli;
        this.gestionUtilisateur = gestionUtilisateur;
    }
    @Override
    public void handle(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Suppression du compte");
        alert.setContentText("Êtes vous sûr de vouloir supprimer votre compte ?");
        if (alert.showAndWait().get().getText().equals("OK")) {
            this.gestionUtilisateur.supprimeUtilisateur(this.appli.getUtilisateur().getId());
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppression du compte");
            alert.setContentText("Votre compte a bien été supprimé");
            this.appli.afficheFenetreConnexion();
        } else {
            this.appli.afficheFenetreProfil();
        }
    }
}
