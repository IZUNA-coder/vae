package Controller;

import javafx.event.EventHandler;
import Vue.AppliVAE;
import javafx.event.ActionEvent ;


public class ControllerLienInscription implements EventHandler<ActionEvent>{
    
    private AppliVAE appli;
    
    public ControllerLienInscription(AppliVAE appli){
        this.appli = appli;
    }
    
    @Override
    public void handle(ActionEvent event){
        System.out.println("Bouton Inscription cliqué");
        this.appli.afficheFenetreInscription();
    }
}
