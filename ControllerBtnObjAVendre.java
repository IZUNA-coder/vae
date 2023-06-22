package Controller;

import javafx.event.EventHandler;
import Modele.Vente;
import Vue.AppliVAE;
import javafx.scene.input.MouseEvent;

public class ControllerBtnObjAVendre implements EventHandler<MouseEvent> {
    private AppliVAE appli;
    private Vente vente;

    public ControllerBtnObjAVendre(AppliVAE appli,Vente vente) {
        this.appli = appli;
        this.vente = vente;
    }
    @Override
    public void handle(MouseEvent event) {
        try{
            this.vente.setLesEncheres(appli.getEnchereBd().getEncheresVente(vente));

        }
        catch(Exception e){
            System.out.println("erreur");
        }
        this.appli.afficheFentreObjet(this.vente);
        
    }
}
