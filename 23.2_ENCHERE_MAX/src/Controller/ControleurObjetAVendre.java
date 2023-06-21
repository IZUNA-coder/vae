package Controller;

import Modele.Vente;
import Vue.FenetreObjAVendre;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurObjetAVendre implements EventHandler<ActionEvent> {

    private FenetreObjAVendre fenetre;


    public ControleurObjetAVendre(FenetreObjAVendre fenetre) {
        this.fenetre = fenetre;

    }

    public void handle(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (button.getText().contains("Accepter")){
            try{
                button.setVisible(false);
                this.fenetre.popUpOffreAccepter();
            } catch (Exception e){
                System.out.println(e);
            }
            System.out.println("Accepter");
        }
        if(button.getText().contains("Annuler")){
            try{
                button.setVisible(false);
                this.fenetre.popUpOffreRefuser();
            } catch (Exception e){
                System.out.println(e);
            }
            System.out.println("Annuler");
        }
    }  
    
}