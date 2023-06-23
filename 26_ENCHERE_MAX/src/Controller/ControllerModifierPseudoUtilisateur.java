package Controller;

import Modele.BD.GestionUtilisateurs;
import Modele.Utilisateur;
import Vue.AppliVAE;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class ControllerModifierPseudoUtilisateur implements EventHandler<ActionEvent> {
    private AppliVAE appli;
    private GestionUtilisateurs gestionUtilisateur;
    private Utilisateur utilisateur;
    public ControllerModifierPseudoUtilisateur(AppliVAE appli, GestionUtilisateurs gestionUtilisateur, Utilisateur utilisateur) {
        this.appli = appli;
        this.gestionUtilisateur = gestionUtilisateur;
        this.utilisateur = utilisateur;
    }
    @Override
    public void handle(ActionEvent event) {
        String nouveauPseudo = this.appli.getTextTfModifPseudo();
        System.out.println("Modification du pseudo : " + nouveauPseudo);
        this.appli.getUtilisateur().setUsername(nouveauPseudo);
        this.gestionUtilisateur.editUsername(this.utilisateur.getId(), nouveauPseudo);
        // faire une popup qui indique que le pseudo a bien été modifié
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Modification du pseudo");
        alert.setHeaderText("Changement de pseudo");
        alert.setContentText("Votre pseudo a bien été modifié !\nVotre nouveau pseudo : "+this.appli.getUtilisateur().getUsername());
        alert.showAndWait();
    }
}
