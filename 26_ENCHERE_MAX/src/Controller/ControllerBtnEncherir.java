package Controller;

import Modele.Vente;
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
                Vente vente = this.fenetre.getVente();
                Double montantSaisi = this.fenetre.getMontantSaisie();
                Double encheremax = vente.getEnchereMax().getPrix();
                if(montantSaisi > encheremax){
                    encheremax = montantSaisi;
                    System.out.println("Enchere max : " + encheremax);
                    vente.getEnchereMax().setPrix(encheremax);
                    this.fenetre.popUpOffreAccepter(encheremax);
                    this.fenetre.setTextEnchereMax(this.fenetre.getMontantSaisie());
                }  else{
                    this.fenetre.popUpOffreRefuser(encheremax);
                }
            } catch (Exception e){
                System.out.println(e);
            }
        }
        if(button.getText().contains("Annuler")){
            try{
                System.out.println("Annuler");
            } catch (Exception e){
                System.out.println(e);
            }
            System.out.println("Annuler");
        }
    }  
    
}