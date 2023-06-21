package Controller;

import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;

import Modele.Vente;
import Modele.BD.GestionVentes;
import Vue.AppliVAE;
import javafx.event.ActionEvent ;


public class ControllerBtnFavoris implements EventHandler<ActionEvent>{
    
    private AppliVAE appli;
    private GestionVentes gestionVentes;
    private Vente v;
    
    public ControllerBtnFavoris(AppliVAE appli, GestionVentes gestionVentes, Vente v){
        this.appli = appli;
        this.gestionVentes = gestionVentes;
        this.v = v;
    }
    
    @Override
    public void handle(ActionEvent event){
        ToggleButton btnFavoris = (ToggleButton) event.getSource();
        System.out.println("Bouton Vente Favoris cliqué");
        System.out.println("Vente : " + this.v.getIdentifiant());
        System.out.println("Utilisateur : " + this.appli.getUtilisateur().getId());
        ImageView imgFavori = new ImageView(new Image("file:ressources/img/imgFavori.png"));
        imgFavori.setFitHeight(30);
        imgFavori.setFitWidth(30);
        ImageView imgNonFavori = new ImageView(new Image("file:ressources/img/nonFavori.png"));
        imgNonFavori.setFitHeight(30);
        imgNonFavori.setFitWidth(30);
        if (btnFavoris.isSelected()) {
            // Le bouton favori est sélectionné
            btnFavoris.setGraphic(imgFavori);
            try {
                this.gestionVentes.ajouterVenteFavorite(this.appli.getUtilisateur().getId(), this.v.getIdentifiant());
                System.out.println("Vente ajoutée aux favoris");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Le bouton favori n'est pas sélectionné
            btnFavoris.setGraphic(imgNonFavori);
            try {
                this.gestionVentes.supprimerVenteFavorite(this.appli.getUtilisateur().getId(), this.v.getIdentifiant());
                System.out.println("Vente suprrimée des favoris");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
}

