package Vue;

import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import Modele.Utilisateur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class FenetreObjet extends BorderPane {
    

    private Button retour;
    private Button encherir;
    private Button voirProfil;
    private Button btnVAE;
    private Button btnProfil;


    public FenetreObjet(Button retour, Button encherir, Button voirProfil){
        super();
        this.retour=retour;
        this.encherir=encherir;
        this.voirProfil=voirProfil;
        this.btnVAE = new Button("VAE");
        this.btnVAE.setFont(Font.loadFont("file:ressources/fonts/PlayfairDisplay.ttf", 50));
        this.btnVAE.setStyle("-fx-background-color: transparent;");
        this.btnVAE.setPadding(new Insets(0, 0, 0, 30));
        ImageView img= new ImageView("file:.Code.src.Vue.img.imgprofile.png");
        this.btnProfil = new Button(null, img);
        img.setFitHeight(50);
        img.setFitWidth(50);
        this.btnProfil = new Button(null, img);
        this.btnProfil.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20px;");
        this.setTop(enHaut());

    }
    public VBox enHaut(){
        VBox vb= new VBox();
        vb.getChildren().addAll(this.hautDePage());
        return vb;
    }

    public BorderPane hautDePage() {
        BorderPane top = new BorderPane();
        top.setLeft(this.btnVAE);
        top.setCenter(this.barreDeRecherche());
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
        ImageView imgLoupe = new ImageView(new Image("./loupe.png"));
        imgLoupe.setFitHeight(30);
        imgLoupe.setFitWidth(30);
        loupe.setGraphic(imgLoupe);
        loupe.setStyle("-fx-background-color: transparent;");
        // mettre loupe tout à droite
        StackPane.setAlignment(loupe, Pos.CENTER_RIGHT);
        contientBarre.getChildren().addAll(recherche, loupe);
        return contientBarre;
    }

    
   
}
