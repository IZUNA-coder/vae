package Controller;

import Modele.Vente;
import Vue.AppliVAE;
import Vue.FenetreObjAVendre;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControllerBtnEncherir implements EventHandler<ActionEvent> {

    private FenetreObjAVendre fenetre;


    public ControllerBtnEncherir(FenetreObjAVendre fenetre) {
        this.fenetre = fenetre;
        

    }

    public void handle(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (button.getText().contains("Accepter")){
            try{
                this.fenetre.popUpOffreAccepter();
                this.fenetre.setTextEnchereMax(this.fenetre.getMontantSaisie());
            } catch (Exception e){
                System.out.println(e);
            }
            System.out.println("Accepter");
        }
    }  
    
}