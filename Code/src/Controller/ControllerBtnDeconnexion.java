package Controller;

import javafx.event.EventHandler;
import Vue.AppliVAE;
import javafx.event.ActionEvent ;


public class ControllerBtnDeconnexion implements EventHandler<ActionEvent>{
    
    private AppliVAE appli;
    
    public ControllerBtnDeconnexion(AppliVAE appli){
        this.appli = appli;
    }
    
    @Override
    public void handle(ActionEvent event){
        System.out.println("Bouton Déconnexion cliqué");
        if(this.appli.confirmationPopUp("Êtes-vous sûr de vouloir vous déconnecter ?","Cliquez sur OK pour vous déconnecter.")){
            this.appli.afficheFenetreConnexion();
        }
    }
}
