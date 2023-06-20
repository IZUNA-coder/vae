package Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class FenetreObjAVendre extends BorderPane{

    private String dateCreation;
    private int nbEncheresConclues;
    private String email;
    private String telephone;
    private String objet;

    
    public FenetreObjAVendre() {
        super();
        this.setStyle("-fx-border-color: #D3D3D3; -fx-border-width: 5px;");
        this.ajouteTop(this);
        this.ajouteCentre();
        this.setPadding(new Insets(10, 50, 10, 10));// top - right - bottom - left
        this.objet = "Chaise de bureau";
        
    }

    public void ajouteTop(BorderPane bd) {
        HBox hb = new HBox();
        Button btnRetour = new Button("Retour");
        btnRetour.setCursor(Cursor.HAND);
        btnRetour.setId("btnRetour");

        Label label = new Label(">");
        Hyperlink hyperlink2 = new Hyperlink("Catégorie");// créer une hyperlien
        Label label2 = new Label(">");
        Hyperlink hyperlink3 = new Hyperlink("Meubles");// créer une hyperlien
        Label label3 = new Label(">");
        Hyperlink hyperlink4 = new Hyperlink(this.objet);// créer une hyperlien

        hb.getChildren().addAll(btnRetour, this.hyperlinkAccueil, label, hyperlink2, label2, hyperlink3, label3, hyperlink4);
        HBox.setMargin(btnRetour, new Insets(0, 20, 0, 20));// top - right - bottom - left
        hb.setAlignment(Pos.CENTER_LEFT);
        bd.setTop(hb);
    }
    private void ajouteCentre() {
        
    }
    private void partieGauche(){
        Label offre = new Label("OFFRE :");
        Label meilleurEnchere = new Label("Meuilleur enchère : " + this.)
    }

}
