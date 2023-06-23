package Controller;

import Modele.BD.GestionUtilisateurs;
import Vue.AppliVAE;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ControllerModifierMailUtilisateur implements EventHandler<ActionEvent>{
    private AppliVAE appli;
    private GestionUtilisateurs gestionUtilisateur;
    public ControllerModifierMailUtilisateur(AppliVAE appli, GestionUtilisateurs gestionUtilisateur) {
        this.appli = appli;
        this.gestionUtilisateur = gestionUtilisateur;
    }
    @Override
    public void handle(ActionEvent event) {
        String nouveauMail = this.appli.getTextTfModifEmail();
        if(this.gestionUtilisateur.checkEmail(nouveauMail) == false){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de mail");
            alert.setContentText("Votre email n'est pas valide !");
            alert.showAndWait();
            return;
        }
        System.out.println("Modification du mail : " + nouveauMail);
        this.appli.getUtilisateur().setEmail(nouveauMail);
        this.gestionUtilisateur.editEmail(this.appli.getUtilisateur().getId(), nouveauMail);
        // faire une popup qui indique que le mail a bien été modifié
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Modification du mail");
        alert.setHeaderText("Changement de mail");
        alert.setContentText("Votre mail a bien été modifié !\nVotre nouveau mail : "+this.appli.getUtilisateur().getId());
        alert.showAndWait();
    }
}
