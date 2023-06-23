package Controller;

import Modele.BD.GestionUtilisateurs;
import Vue.AppliVAE;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ControllerModifierMDPUtilisateur implements EventHandler<ActionEvent> {
    private AppliVAE appli;
    private GestionUtilisateurs gestionUtilisateur;
    public ControllerModifierMDPUtilisateur(AppliVAE appli, GestionUtilisateurs gestionUtilisateur) {
        this.appli = appli;
        this.gestionUtilisateur = gestionUtilisateur;
    }
    @Override
    public void handle(ActionEvent event) {
        String nouveauMDP = this.appli.getTextTfModifMDP();
        if(this.gestionUtilisateur.checkPassword(nouveauMDP) == false){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de mot de passe");
            alert.setContentText("Votre mot de passe doit contenir au moins 8 caractères, dont une majuscule, une minuscule, un chiffre et un caractère spécial.");
            alert.showAndWait();
            return;
        }
        System.out.println("Modification du mot de passe : " + nouveauMDP);
        this.appli.getUtilisateur().setPassword(nouveauMDP);
        this.gestionUtilisateur.editPassword(this.appli.getUtilisateur().getId(), nouveauMDP);
        // faire une popup qui indique que le mot de passe a bien été modifié
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Modification du mot de passe");
        alert.setHeaderText("Changement de mot de passe");
        alert.setContentText("Votre mot de passe a bien été modifié !\nVotre nouveau mot de passe : "+this.appli.getUtilisateur().getPassword());
        alert.showAndWait();
    }
}
