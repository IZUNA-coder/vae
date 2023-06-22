package Vue;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.sql.SQLException;

import Controller.ControllerBtnObjAVendre;
import Controller.ControllerRechercheVenteUser;
import Modele.Utilisateur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.text.TextFlow;

import Modele.BD.GestionVentes;
import Modele.BD.PhotoBD;

public class FenetreAccueil extends BorderPane {
    private Button btnVAE;
    private Button btnProfil;
    private AppliVAE appliVAE;
    private Utilisateur utilisateur;
    private GestionVentes gestionVentes;
    private PhotoBD photoBD;
    private Button btnVendre;

    public FenetreAccueil(AppliVAE appliVAE,Utilisateur utilisateur, GestionVentes gestionVentes, Button btnVAE, Button btnProfil, PhotoBD photoBD, Button btnVendre) {
        super();
        this.appliVAE = appliVAE;
        this.utilisateur = utilisateur;
        this.gestionVentes = gestionVentes;
        this.btnVAE = btnVAE;
        this.btnProfil = btnProfil;
        this.photoBD=photoBD;
        this.btnVendre = btnVendre;
        this.setStyle("-fx-background-image: url('file:./ressources/img/background3.png')");
        this.init();
        laPage();
        
    }

    public void init() {
        this.btnVAE.setFont(Font.loadFont("file:ressources/fonts/PlayfairDisplay.ttf", 50));
        this.btnVAE.setStyle("-fx-background-color: transparent;");
        this.btnVendre.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20px;");
        this.btnProfil.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20px;");
    }
    public BorderPane hautDePage(){
        BorderPane top = new BorderPane();
        HBox boxBtn = new HBox();
        boxBtn.setSpacing(20);
        BorderPane.setMargin(boxBtn, new Insets(20));
        top.setLeft(this.btnVAE);
        boxBtn.getChildren().addAll(this.btnVendre, this.btnProfil);
        top.setRight(boxBtn);
        top.setBottom(barreDeRecherche());
        //Positionner la barre et la loupe au centre de la fenetre
        BorderPane.setAlignment(top.getBottom(), Pos.CENTER);
        return top;
    }
    public StackPane barreDeRecherche() {
        StackPane contientBarre = new StackPane();
        TextField recherche = new TextField();
        recherche.setPromptText("Rechercher par nom d'objet");
        recherche.setStyle(
                "-fx-background-color: transparent;  -fx-background-radius: 50px; -fx-prompt-text-fill: black; -fx-border-color: black; -fx-border-radius: 3em;");
        contientBarre.setMaxWidth(800);
        recherche.setPrefHeight(40);
        recherche.setFont(new Font(20));

        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
        // Appeler la méthode de recherche du contrôleur avec la nouvelle valeur de recherche
        System.out.println("Recherche : " + newValue);
        this.appliVAE.afficheFenetreRechercheEnchere(newValue);
        });

        Button loupe = new Button("");
        ImageView imgLoupe = new ImageView(new Image("file:ressources/img/PageAccueil/loupe.png"));
        imgLoupe.setFitHeight(30);
        imgLoupe.setFitWidth(30);
        loupe.setGraphic(imgLoupe);
        loupe.setStyle("-fx-background-color: transparent;");
        loupe.setCursor(Cursor.HAND);
        loupe.setOnAction(new ControllerRechercheVenteUser(appliVAE, recherche));
        // mettre loupe tout à droite
        StackPane.setAlignment(loupe, Pos.CENTER_RIGHT);
        contientBarre.getChildren().addAll(recherche, loupe);
        return contientBarre;
    }
    public BorderPane hautDuCentre() {
        BorderPane topCenter = new BorderPane();
        topCenter.setPadding(new Insets(20,20,20,40));
        Text bonjour = new Text("Bonjour, " + this.utilisateur.getUsername() + " !");
        bonjour.setFont(Font.loadFont("file:ressources/fonts/PlayfairDisplay.ttf", 30));

        Button vosEncheres = new Button("VOS ENCHERES");
        vosEncheres.setStyle("-fx-background-color: #51D1E6; -fx-text-fill: #FFFFFF; -fx-background-radius: 30px; -fx-font-weight: bolder; -fx-font-size: 15px;");

        topCenter.setLeft(bonjour);
        topCenter.setRight(vosEncheres);

        return topCenter;
    }
    public BorderPane centreDePage() {
        BorderPane center = new BorderPane();
        center.setTop(this.hautDuCentre());
        center.setCenter(this.contientBDREtSous());
        return center;
    }

    public HBox lesEncheres() {
        HBox lesEncheres = new HBox();
        TilePane encheres = new TilePane();
        encheres.setPadding(new Insets(10));
        for (Modele.Vente v : this.gestionVentes.getVentesAleatoires()) {
            HBox enchere = new HBox();
            enchere.setSpacing(10);
            enchere.setPadding(new Insets(10));
            VBox lesTextes = new VBox();
            Text titre = new Text(v.getObjet().getNom());
            Text description = new Text(v.getObjet().getDescription());
            TextFlow contientDescription = new TextFlow(description);
            contientDescription.setMaxWidth(170);
            VBox.setMargin(contientDescription, new Insets(10, 0, 0, 0));
            Button btnStatut = new Button();
            btnStatut.setMinWidth(200);
            btnStatut.setMinHeight(30);
            btnStatut.setCursor(javafx.scene.Cursor.HAND);
            switch (v.getStatut().getNom()) {
                case "A venir":
                    btnStatut.setText(v.getStatut().getNom().toUpperCase());
                    btnStatut.setStyle("-fx-background-color: #146D85; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-size: 10px;");
                    break;
                case "En cours":
                    btnStatut.setText(v.getStatut().getNom().toUpperCase());
                    btnStatut.setStyle("-fx-background-color: #109018; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-size: 10px;");
                    break;
                case "A valider":
                    btnStatut.setText(v.getStatut().getNom().toUpperCase());
                    btnStatut.setStyle("-fx-background-color: #F2EF15; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-size: 10px;");
                    break;
                case "Validée":
                    btnStatut.setText(v.getStatut().getNom().toUpperCase());
                    btnStatut.setStyle("-fx-background-color: #51D1E6; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-size: 10px;");
                    break;
                case "Non conclue":
                    btnStatut.setText(v.getStatut().getNom().toUpperCase());
                    btnStatut.setStyle("-fx-background-color: #E67651; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-size: 10px;");
                    break;
            }
            lesTextes.getChildren().addAll(titre, contientDescription, btnStatut);
            enchere.setStyle("-fx-background-color: #FFFFFF, -fx-border-color: black; -fx-border-width: 1px;");
            enchere.getChildren().addAll(lesTextes);
            enchere.setCursor(Cursor.HAND);
            enchere.setOnMouseClicked(new ControllerBtnObjAVendre(appliVAE, v));
            encheres.getChildren().add(enchere);
            ImageView imageTest=new ImageView(new Image("file:ressources/img/imgObjet/nonphoto.jpeg"));
            try {
                photoBD.setPhoto(v);
                imageTest = new ImageView(new Image(photoBD.getUrlPhoto(v.getIdObjet())));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            imageTest.setFitHeight(250);
            imageTest.setFitWidth(250);
            imageTest.setPreserveRatio(true);
            enchere.getChildren().add(imageTest);
            enchere.setPrefWidth(300);
            enchere.setPrefHeight(190);
            encheres.setHgap(50);
            encheres.setVgap(30);
            enchere.setStyle("-fx-border-color: black;");
            VBox.setMargin(btnStatut, new Insets(110, 0, 0, 0));
            btnStatut.setPrefWidth(100);
        }
        lesEncheres.getChildren().add(encheres);
        lesEncheres.setAlignment(Pos.CENTER);
        encheres.setAlignment(Pos.CENTER);
        return lesEncheres;
    }
    
    public BorderPane contientBDREtSous() {
        BorderPane contient = new BorderPane();
        contient.setCenter(this.lesEncheres());
        return contient;
    }
    public void laPage() {
        this.setTop(this.hautDePage());
        this.setCenter(this.centreDePage());
    }
}

