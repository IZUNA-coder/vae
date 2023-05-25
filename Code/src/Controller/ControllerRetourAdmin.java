package Controller;

import javafx.event.EventHandler;
import Vue.AppliVAE;
import javafx.event.ActionEvent ;


public class ControllerRetourAdmin implements EventHandler<ActionEvent>{
    
    private AppliVAE appli;
    
    public ControllerRetourAdmin(AppliVAE appli){
        this.appli = appli;
    }
    
    @Override
    public void handle(ActionEvent event){
        System.out.println("Bouton Retour cliqué");
        this.appli.afficheFenetreAdmin();
    }
}
