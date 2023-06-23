package Controller;

import java.io.File;
import java.util.List;

import Vue.FenetreVente;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class ControleurDragImage implements EventHandler<DragEvent> {


    private FenetreVente fenetreVente;

    public ControleurDragImage(FenetreVente fenetreVente) { 
    
        this.fenetreVente = fenetreVente;
    }


    @Override
    public void handle(DragEvent event) {


        try {

            if (event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();

            Dragboard dragboard = event.getDragboard();
            boolean success = false;
            if (dragboard.hasFiles()) {
                List<File> files = dragboard.getFiles();
                this.fenetreVente.setImageChoisi(files.get(0)); 
                Image lien = new Image(this.fenetreVente.getImageChoisi().toURI().toString());
                this.fenetreVente.setImage(lien);
                this.fenetreVente.setLabelAjout();
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        
            
        } catch (Exception e) {
        }

        


    }
    
}
