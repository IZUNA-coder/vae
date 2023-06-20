package Vue;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.sql.SQLException;

import Modele.Utilisateur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.text.TextFlow;

import Modele.BD.GestionVentes;

public class FenetreAccueil extends BorderPane {
    private Button btnVAE;
    private Button btnProfil;
    private Utilisateur utilisateur;
    private GestionVentes gestionVentes;
    

    public FenetreAccueil(Utilisateur utilisateur, GestionVentes gestionVentes, Button btnVAE, Button btnProfil) {
        super();
        this.utilisateur = utilisateur;
        this.gestionVentes = gestionVentes;
        this.btnVAE = btnVAE;
        this.btnProfil = btnProfil;
        this.setStyle("-fx-background-image: url('file:./ressources/img/background3.png')");
        this.init();
        laPage();
        
    }

    public void init() {
        this.btnVAE.setFont(Font.loadFont("file:ressources/fonts/PlayfairDisplay.ttf", 50));
        this.btnVAE.setStyle("-fx-background-color: transparent;");
        this.btnVAE.setPadding(new Insets(0, 0, 0, 30));
        this.btnProfil.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20px;");
    }
    public BorderPane hautDePage() {
        BorderPane top = new BorderPane();
        top.setLeft(this.btnVAE);
        top.setRight(this.btnProfil);
        top.setPadding(new Insets(10, 10, 0, 0));
        return top;
    }
    public StackPane barreDeRecherche() {
        StackPane contientBarre = new StackPane();
        TextField recherche = new TextField();
        recherche.setPromptText("Rechercher");
        recherche.setStyle("-fx-background-color: #D3D3D3;  -fx-background-radius: 50px; -fx-prompt-text-fill: black;");
        recherche.setPrefWidth(800);
        recherche.setPrefHeight(40);
        recherche.setFont(new Font(20));
        Button loupe = new Button("");
        ImageView imgLoupe = new ImageView(new Image("file:ressources/img/PageAccueil/loupe.png"));
        imgLoupe.setFitHeight(30);
        imgLoupe.setFitWidth(30);
        loupe.setGraphic(imgLoupe);
        loupe.setStyle("-fx-background-color: transparent;");
        // mettre loupe tout à droite
        StackPane.setAlignment(loupe, Pos.CENTER_RIGHT);
        contientBarre.getChildren().addAll(recherche, loupe);
        return contientBarre;
    }
    public HBox hautDuCentre() {
        HBox topCenter = new HBox();
        topCenter.getChildren().add(this.barreDeRecherche());
        topCenter.setAlignment(Pos.CENTER);
        return topCenter;
    }
    public BorderPane centreDePage() {
        BorderPane center = new BorderPane();
        center.setTop(this.hautDuCentre());
        center.setCenter(this.contientBDREtSous());
        center.setPadding(new Insets(0, 0, 30, 30));
        return center;
    }
    public BorderPane sousBarreDeRecherche() {
        BorderPane sousBarre = new BorderPane();
        HBox elements = new HBox();
        elements.setAlignment(Pos.CENTER);
        elements.setPadding(new Insets(10));
        elements.setSpacing((800));
        Text bonjour = new Text("Bonjour, " + this.utilisateur.getUsername() + " !");
        bonjour.setFont(Font.loadFont("file:ressources/fonts/PlayfairDisplay.ttf", 30));
        bonjour.setStyle("-fx-font-size: 25px;");
        Button vosEncheres = new Button("VOS ENCHERES");
        vosEncheres.setStyle("-fx-background-color: #51D1E6; -fx-text-fill: #FFFFFF; -fx-background-radius: 30px; -fx-font-weight: bolder; -fx-font-size: 15px;");
        elements.getChildren().addAll(bonjour, vosEncheres);
        sousBarre.setCenter(elements);
        return sousBarre;
    }
    public HBox lesEncheres() {
        HBox lesEncheres = new HBox();
        TilePane encheres = new TilePane();
        encheres.setPadding(new Insets(10));
        try {
            for (int i = 0; i < 6; ++i) {
                HBox enchere = new HBox();
                enchere.setSpacing(10);
                enchere.setPadding(new Insets(10));
                VBox lesTextes = new VBox();
                Text titre = new Text(this.gestionVentes.getVente().get(i).getObjet().getNom());
                Text description = new Text(this.gestionVentes.getVente().get(i).getObjet().getDescription());
                TextFlow contientDescription = new TextFlow(description);
                contientDescription.setMaxWidth(170);
                VBox.setMargin(contientDescription, new Insets(10, 0, 0, 0));
                Button btnStatut = new Button();
                if (this.gestionVentes.getVente().get(i).getStatut().getNom().equals("A venir")) {
                    btnStatut.setText("A VENIR");
                    btnStatut.setStyle("-fx-background-color: #51D1E6; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-weight: bolder; -fx-font-size: 15px;");
                }
                else if (this.gestionVentes.getVente().get(i).getStatut().getNom().equals("En cours")) {
                    btnStatut.setText("EN COURS");
                    btnStatut.setStyle("-fx-background-color: #51E679; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-weight: bolder; -fx-font-size: 15px;");
                }
                else if (this.gestionVentes.getVente().get(i).getStatut().getNom().equals("A valider")){
                    btnStatut.setText("A VALIDER");
                    btnStatut.setStyle("-fx-background-color: #E67651; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-weight: bolder; -fx-font-size: 15px;");
                }
                else if (this.gestionVentes.getVente().get(i).getStatut().getNom().equals("Validée")) {
                    btnStatut.setText("VALIDEE");
                    btnStatut.setStyle("-fx-background-color: #E67651; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-weight: bolder; -fx-font-size: 15px;");
                }
                else {
                    btnStatut = new Button("NON CONCLUE");
                    btnStatut.setStyle("-fx-background-color: #E67651; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-weight: bolder; -fx-font-size: 15px;");
                }
                lesTextes.getChildren().addAll(titre, contientDescription, btnStatut);
                enchere.setStyle("-fx-background-color: #FFFFFF, -fx-border-color: black; -fx-border-width: 1px;");
                enchere.getChildren().addAll(lesTextes);
                encheres.getChildren().add(enchere);
                ImageView imageTest = new ImageView(new Image("file:ressources/img/PageAccueil/imagechaise.jpg"));
                imageTest.setFitHeight(170);
                imageTest.setFitWidth(150);
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
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des ventes");
        }
        return lesEncheres;
    }
    public BorderPane contientBDREtSous() {
        BorderPane contient = new BorderPane();
        contient.setTop(this.sousBarreDeRecherche());
        contient.setCenter(this.lesEncheres());
        return contient;
    }
    public void laPage() {
        this.setTop(this.hautDePage());
        this.setCenter(this.centreDePage());
    }
}

