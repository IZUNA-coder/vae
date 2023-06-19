import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.File;

public class FenetreVente extends BorderPane{
    

    private Button btnProfil;
    private StackPane barredeRecherche;
    private TextField nom;
    private Button btnValider;
    private TextField prixDeBase;
    private TextField prixMinimum;
    private DatePicker dateDebut;
    private DatePicker dateFin;
    private TextArea description;
    private FileChooser fc;
    private File imagechoisi;
    private ImageView image;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;


    public FenetreVente(){
        super();
        this.nom = new TextField();
        this.nom.setPromptText("Que vendez-vous ?");
        this.prixDeBase = new TextField();
        this.prixDeBase.setPromptText("Prix de base (prix en €)");
        this.prixMinimum = new TextField();
        this.prixMinimum.setPromptText("Prix minimum (prix en €)");
        this.dateDebut = new DatePicker();
        this.dateDebut.setPromptText("Date de début");
        this.dateFin = new DatePicker();
        this.dateFin.setPromptText("Date de fin");
        this.description = new TextArea();
        this.description.setPromptText("Description");
        this.btnValider = new Button("Confirmer La Vente");
        this.btnValider.setStyle("-fx-background-color: #51E679; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-weight: bolder; -fx-font-size: 15px;");
        this.imagechoisi = new File("");
        this.image = new ImageView(new Image("file:./ressources/img/file.png" ));
        this.image.setFitHeight(200);
        this.image.setFitWidth(200);

        this.btnValider.setOnAction(e -> {
            System.out.println("Nom : " + this.getNom());
            System.out.println("Prix de base : " + this.getPrixDeBase());
            System.out.println("Prix minimum : " + this.getPrixMinimum());
            System.out.println("Date de début : " + this.getDateDebut());
            System.out.println("Date de fin : " + this.getDateFin());
            System.out.println("Description : " + this.getDescription());
            System.out.println("Image : " + this.getLienImage());
            System.out.println("Categorie : " + this.getCategorie());
           });
        
        this.setTop(this.Top());
        this.setCenter(this.Center());
        // mettre une image en background
        this.setStyle("-fx-background-image: url('file:./ressources/img/background1.jpg'); -fx-background-size: cover;");
    }

    public BorderPane Top(){
        BorderPane top = new BorderPane();
        top.setPadding(new Insets(10, 10, 10, 10));
        Label lbl = new Label("VAE");
        lbl.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 75));
        lbl.setStyle("-fx-text-fill: #000000;");
        top.setLeft(lbl);

        //bouton profil
        ImageView img = new ImageView(new Image("file:./ressources/img/user.png"));
        img.setFitHeight(50);
        img.setFitWidth(50);
        this.btnProfil = new Button(null, img);
        this.btnProfil.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20px; ");

        HBox profil = new HBox();
        profil.getChildren().addAll(this.btnProfil);
        profil.setAlignment(Pos.CENTER);


        //barre de recherche
        this.barredeRecherche = this.barreDeRecherche();
        this.barredeRecherche.setAlignment(Pos.CENTER);
        HBox hb = new HBox();
        hb.getChildren().addAll(this.barredeRecherche);
        hb.setAlignment(Pos.CENTER);

        top.setCenter(hb);    
        top.setRight(profil);


        return top;
    }

    public BorderPane Center(){

        BorderPane center = new BorderPane();

        Button btn = new Button("RETOUR");
        btn.setStyle("-fx-background-color: #E67651; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-weight: bolder; -fx-font-size: 15px;");
        btn.setPadding(new Insets(10));

        Label titre = new Label("Mise en enchère");
        titre.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 70));

        HBox hb = new HBox();
        hb.getChildren().addAll(titre);
        hb.setAlignment(Pos.CENTER);

        VBox vb = new VBox();
        vb.setPadding(new Insets(10));
        vb.getChildren().addAll(btn, hb);
        center.setTop(vb);

        this.fc = new FileChooser();
        fc.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));

        VBox droite = new VBox(this.image, this.categorie(),this.boutons());
        droite.setAlignment(Pos.TOP_CENTER);
        droite.setSpacing(5);
        droite.setPadding(new Insets(30));

        center.setCenter(this.formulaire());
        center.setRight(droite);
        center.setPadding(new Insets(30));

        return center;
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
        ImageView imgLoupe = new ImageView(new Image("file:./ressources/img/loupe.png" ));
        imgLoupe.setFitHeight(30);
        imgLoupe.setFitWidth(30);
        loupe.setGraphic(imgLoupe);
        loupe.setStyle("-fx-background-color: transparent;");
        // mettre loupe tout à droite
        StackPane.setAlignment(loupe, Pos.CENTER_RIGHT);
        contientBarre.getChildren().addAll(recherche, loupe);
        return contientBarre;
    }

    public TitledPane categorie(){

        TitledPane categorie = new TitledPane();
        categorie.setText("Catégories");
        categorie.setPadding(new Insets(10));
        categorie.collapsibleProperty().setValue(false);

        VBox vbCategorie = new VBox();
        vbCategorie.setPadding(new Insets(10));
        vbCategorie.setSpacing(10);

        ToggleGroup group = new ToggleGroup();
        this.rb1 = new RadioButton("Vêtement");
        this.rb1.setToggleGroup(group);
        this.rb2 = new RadioButton("Ustensile Cuisine");
        this.rb2.setToggleGroup(group);
        this.rb3 = new RadioButton("Meuble");
        this.rb3.setToggleGroup(group);
        this.rb4 = new RadioButton("Outil");
        this.rb4.setToggleGroup(group);
        vbCategorie.getChildren().addAll(rb1, rb2, rb3, rb4);
        categorie.setContent(vbCategorie);

        return categorie;

    }


    public GridPane formulaire(){

        GridPane gp = new GridPane();

        gp.add(new Label("Nom du produit "), 0, 0);
        gp.add(nom, 1, 0);
        gp.add(new Label("Prix de base "), 0, 1);
        gp.add(this.prixDeBase, 1, 1);
        gp.add(new Label("Prix minimum "), 0, 2);
        gp.add(this.prixMinimum, 1, 2);
        gp.add(new Label("Date de début "), 0, 3);
        gp.add(this.dateDebut, 1, 3);
        gp.add(new Label("Date de fin "), 0, 4);
        gp.add(this.dateFin, 1, 4);
        gp.add(new Label("Description "), 0, 5);
        gp.add(this.description, 1, 5);
        gp.add(this.btnValider, 1, 6);
        gp.setVgap(10);
        gp.setHgap(10);
        gp.setAlignment(Pos.CENTER);

        gp.setStyle("-fx-background-color: #B3B3B3; -fx-background-radius: 20px; -fx-opacity: 0.8;");
        return gp;

    }

    public HBox boutons(){

        HBox boutons = new HBox();

        Button btnImage = new Button("Choisir une image");

        boutons.setAlignment(Pos.CENTER);
        btnImage.setOnAction(e -> {
            this.imagechoisi = fc.showOpenDialog(null);

            if (imagechoisi != null) {
            Image lien = new Image(imagechoisi.toURI().toString());
            this.image.setImage(lien);
        }   
        });

        btnImage.setStyle("-fx-background-color: #51D1E6; -fx-text-fill: #FFFFFF; -fx-background-radius: 20px; -fx-font-weight: bolder; -fx-font-size: 15px;");
        boutons.getChildren().addAll(btnImage);

        return boutons;

    }


    public int getCategorie(){


        int categorie = 0;

        if (this.rb1.isSelected()) {
            categorie = 1;
        }
        if (this.rb2.isSelected()) {
            categorie = 2;
        }
        if (this.rb3.isSelected()) {
            categorie = 3;
        }
        if (this.rb4.isSelected()) {
            categorie = 4;
        }

        return categorie;

    }

 

    public String getNom(){
        return this.nom.getText();
    }

    public String getPrixDeBase(){
        return this.prixDeBase.getText();
    }

    public String getPrixMinimum(){
        return this.prixMinimum.getText();
    }

    public String getDateDebut(){
        return this.dateDebut.getValue().toString();
    }

    public String getDateFin(){
        return this.dateFin.getValue().toString();
    }

    public String getDescription(){
        return this.description.getText();
    }

    

    public String getLienImage(){
        return this.imagechoisi.toString();
    }

    
}