package Controller;

import javafx.event.EventHandler;
import Modele.Vente;
import Modele.BD.EnchereBd;
import Vue.AppliVAE;
import javafx.scene.input.MouseEvent;

public class ControllerBtnObjAVendre implements EventHandler<MouseEvent> {
    private AppliVAE appli;
    private Vente vente;
    private EnchereBd enchereBd;

    public ControllerBtnObjAVendre(AppliVAE appli,Vente vente, EnchereBd enchereBd) {
        this.appli = appli;
        this.vente = vente;
        this.enchereBd = enchereBd;

    }
    @Override
    public void handle(MouseEvent event) {
        try{
            this.vente.setLesEncheres(this.enchereBd.getEncheresVente(vente));

        }
        catch(Exception e){
            System.out.println("erreur");
        }
        this.appli.afficheFentreObjet(this.vente);
        
    }
}
