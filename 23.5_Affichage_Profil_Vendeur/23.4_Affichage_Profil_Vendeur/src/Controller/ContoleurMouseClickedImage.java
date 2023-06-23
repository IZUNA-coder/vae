package Controller;

import java.io.File;

import Vue.FenetreVente;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ContoleurMouseClickedImage implements EventHandler<MouseEvent> {


    private FenetreVente fenetreVente;

    public ContoleurMouseClickedImage(FenetreVente fenetreVente) { 
    
        this.fenetreVente = fenetreVente;
    }


    @Override
    public void handle(MouseEvent event) {

        if (event.getButton() == MouseButton.PRIMARY) {
                
                this.fenetreVente.setImageChoisi(this.fenetreVente.getFc().showOpenDialog(null));
                File imagechoisi = this.fenetreVente.getImageChoisi();
                if (imagechoisi != null) {
                    Image lien = new Image(imagechoisi.toURI().toString());
                    this.fenetreVente.setImage(lien);
                    this.fenetreVente.setLabelAjout();
                }
            }
        


    }
    
}
