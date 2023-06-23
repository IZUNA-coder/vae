package Vue;

import Controller.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class FenetreVente extends BorderPane{
    

    private Button btnProfil;
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
    private Button btnRetour;
    private TitledPane categorie;
    private Label labelAjout;

    public FenetreVente(){
        super();

        this.nom = new TextField();
        this.nom.setPromptText("Que vendez-vous ?");
        this.nom.setStyle("-fx-border-radius: 10px; -fx-background-radius: 5px; -fx-font-weight: bolder; -fx-font-size: 15px;");

    
        this.prixMinimum = new TextField();
        this.prixMinimum.setPromptText("Prix minimum (prix en €)");
        this.prixMinimum.setStyle(" -fx-border-radius: 10px;-fx-background-radius: 5px; -fx-font-weight: bolder; -fx-font-size: 15px;");
        this.prixMinimum.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?\\d*(\\.\\d*)?")) { // Regex pour les nombres décimaux
                return change;
            } else {
                return null;
            }
        }));


        this.prixDeBase = new TextField();
        this.prixDeBase.setPromptText("Prix de base (prix en €)");
        this.prixDeBase.setStyle("-fx-border-radius: 10px;-fx-background-radius: 5px; -fx-font-weight: bolder; -fx-font-size: 15px;");
        this.prixDeBase.setTextFormatter(
            new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?\\d*(\\.\\d*)?")) { // Regex pour les nombres décimaux
                return change;
            } else {
                return null;
            }
        }));


        this.btnRetour = new Button("Retour");
        this.btnRetour.setStyle(" -fx-background-radius: 10px; -fx-font-weight: bolder; -fx-font-size: 15px;");
        // faire en sorte que ça quitte l'application
        this.btnRetour.setOnAction(event -> {
            System.out.println("Retour à l'accueil");
            System.exit(0);
        });

        

        this.dateDebut = new DatePicker();
        this.dateDebut.setPromptText("Date de début");
        this.dateFin = new DatePicker();
        this.dateFin.setPromptText("Date de fin");


        this.description = new TextArea();
        this.description.setPromptText("Description");
        this.description.setStyle("-fx-border-radius: 15px; -fx-font-weight: bolder; -fx-font-size: 15px;");

        this.btnValider = new Button("Confirmer la vente");
        this.btnValider.setStyle("-fx-background-color: #51E679; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-weight: bolder; -fx-font-size: 20px;");
        this.imagechoisi = new File("./ressources/img/file.png");

        this.fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));

        this.image = new ImageView(new Image("file:./ressources/img/file.png" ));
        this.image.setFitHeight(200);
        this.image.setFitWidth(200);

        this.image.setOnDragOver(new ControleurDragImage(this));
        this.image.setOnDragDropped(new ControleurDragImage(this));
        this.image.setOnMouseClicked(new ContoleurMouseClickedImage(this));


        this.btnValider.setOnAction(new ControleurValiderAjoutVente(this));
        
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
        

        top.setLeft(lbl);
        top.setRight(profil);


        return top;
    }

    public void categorie(){

        this.categorie = new TitledPane();
        this.categorie.setText("Catégories");
        this.categorie.setPadding(new Insets(20));
        this.categorie.collapsibleProperty().setValue(false);

        VBox vbCategorie = new VBox();
        vbCategorie.setPadding(new Insets(10));
        vbCategorie.setSpacing(10);

        ToggleGroup group = new ToggleGroup(); 
        this.rb1 = new RadioButton("Vêtement"); 
        this.rb1.setToggleGroup(group); 
        this.rb1.setStyle("-fx-font-size: 15px;"); 
        this.rb2 = new RadioButton("Ustensile Cuisine"); 
        this.rb2.setToggleGroup(group); 
        this.rb2.setStyle("-fx-font-size: 15px;"); 
        this.rb3 = new RadioButton("Meuble"); 
        this.rb3.setToggleGroup(group); 
        this.rb3.setStyle("-fx-font-size: 15px;"); 
        this.rb4 = new RadioButton("Outil"); 
        this.rb4.setToggleGroup(group); 
        this.rb4.setStyle("-fx-font-size: 15px;"); 
        vbCategorie.getChildren().addAll(rb1, rb2, rb3, rb4); 
        this.categorie.setContent(vbCategorie); 
        this.categorie.setStyle("-fx-font-size: 15px;");


    }

    public BorderPane Center(){

        BorderPane center = new BorderPane();

        this.btnRetour.setStyle("-fx-background-color: #E67651; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-weight: bolder; -fx-font-size: 20px;");
        this.btnRetour.setPadding(new Insets(10));
        Label titre = new Label("Mise en enchère");
        titre.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 70));

        HBox hb = new HBox();
        hb.getChildren().addAll(titre);
        hb.setAlignment(Pos.CENTER);

        VBox vb = new VBox();
        vb.setPadding(new Insets(10));
        vb.getChildren().addAll(this.btnRetour, hb);
        center.setTop(vb);


        this.labelAjout = new Label("Veuillez déposer une image");
        this.labelAjout.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 20));
        
        this.categorie();
        VBox droite = new VBox(this.image, this.labelAjout,this.categorie);
        droite.setAlignment(Pos.TOP_CENTER);
        droite.setSpacing(5);
        droite.setPadding(new Insets(15));

        
        center.setCenter(this.formulaire());
        center.setRight(droite);
        center.setPadding(new Insets(15));

        return center;
    }

    public GridPane formulaire(){

        GridPane gp = new GridPane(); 

        
        Label lblnom = new Label("Nom du produit"); 
        lblnom.setFont(new Font("Arial", 20)); 
        gp.add(lblnom, 0, 0); 
        gp.add(this.nom, 1, 0); 

        Label lblprixDeBase = new Label("Prix de base "); 
        lblprixDeBase.setFont(new Font("Arial", 20)); 
        gp.add(lblprixDeBase, 0, 1); 
        gp.add(this.prixDeBase, 1, 1); 

        Label lblprixMinimum = new Label("Prix minimum "); 
        lblprixMinimum.setFont(new Font("Arial", 20)); 
        gp.add(lblprixMinimum, 0, 2); 
        gp.add(this.prixMinimum, 1, 2); 

        Label lbldateDebut = new Label("Date de début "); 
        lbldateDebut.setFont(new Font("Arial", 20)); 
        gp.add(lbldateDebut, 0, 3); 
        gp.add(this.dateDebut, 1, 3); 

        Label lbldateFin = new Label("Date de fin "); 
        lbldateFin.setFont(new Font("Arial", 20)); 
        gp.add(lbldateFin, 0, 4); 
        gp.add(this.dateFin, 1, 4); 

        Label lbldescription = new Label("Description "); 
        lbldescription.setFont(new Font("Arial", 20)); 
        gp.add(lbldescription, 0, 5); 
        gp.add(this.description, 1, 5); 

        gp.add(this.btnValider, 2, 6); 
        gp.setVgap(20); 
        gp.setHgap(30); 
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(20));
        gp.setStyle("-fx-background-color: rgba(211, 211, 211, 0.5); -fx-background-radius: 20px;");
        return gp;

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

    public Double getPrixDeBase(){

        try {
            Double.parseDouble(this.prixDeBase.getText());
        } catch (Exception e) {
            return 0.0;
        }
        return Double.parseDouble(this.prixDeBase.getText());
    }

    public Double getPrixMinimum(){
        try {
            Double.parseDouble(this.prixMinimum.getText());
        } catch (Exception e) {
            return 0.0;
        }
        return Double.parseDouble(this.prixMinimum.getText());
    }

    public LocalDate getDateDebut(){
        try {
            this.dateDebut.getValue().toString();
        } catch (Exception e) {
            return null;
        }
        return this.dateDebut.getValue();
    }

    public LocalDate getDateFin(){
        try {
            this.dateFin.getValue().toString();
        } catch (Exception e) {
            return null;
        }
        return this.dateFin.getValue();
    }

    public String getDescription(){
        return this.description.getText();
    }

    

    public String getLienImage(){
        
        String imageFileName = this.getNom();
        if (this.getImageChoisi().getName().contains("file")) {
            return "./ressources/img/file.png";
        }
        String extension = this.getImageChoisi().getName().substring(this.getImageChoisi().getName().lastIndexOf(".") + 1);
        String destinationPath = "./ressources/img/imgObjet/" + imageFileName + "." + extension;

    try {
        Path source = imagechoisi.toPath();
        Path destination = Path.of(destinationPath);
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        return destinationPath;
    } catch (IOException e) {
        e.printStackTrace();
        return "";
    }
        //
    }

    public void setImage(Image lien){
        this.image.setImage(lien);
    }

    public void setImageChoisi(File imagechoisi){
        this.imagechoisi = imagechoisi;
    }

    public File getImageChoisi(){
        return this.imagechoisi;
    }
    

    public FileChooser getFc(){
        return this.fc;
    }




    public Alert alertErreurChamps(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur dans le formulaire");
        alert.setContentText("Veuillez changer les champs en rouge");
        return alert;
    }


    public Alert alertErreurDate(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur dans le formulaire");
        alert.setContentText("La date de fin doit être supérieur à la date de début");
        return alert;
    }

    public Alert alertErreurPrix(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur dans le formulaire");
        alert.setContentText("Le prix minimum doit être inférieur au prix de base");
        return alert;
    }

    public void rougeDateDebut(){
        this.dateDebut.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
    }

    public void rougeDateFin(){
        this.dateFin.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
    }

    public void rougeNom(){
        this.nom.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-background-radius: 10px;");
    }

    public void rougePrixDeBase(){
        this.prixDeBase.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
    }

    public void rougePrixMinimum(){
        this.prixMinimum.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
    }

    public void rougeCategorie(){

        this.categorie.setStyle("-fx-text-fill: red ;");
        this.rb1.setStyle("-fx-text-fill: red ;");
        this.rb2.setStyle("-fx-text-fill: red ;");
        this.rb3.setStyle("-fx-text-fill: red ;");
        this.rb4.setStyle("-fx-text-fill: red ;");

    }

    public void rougeDescription(){
        this.description.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
    }

    public void vertDateDebut(){
        this.dateDebut.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
    }

    public void vertDateFin(){
        this.dateFin.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
    }

    public void vertNom(){
        this.nom.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
    }

    public void vertPrixDeBase(){
        this.prixDeBase.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
    }

    public void vertPrixMinimum(){
        this.prixMinimum.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
    }

    public void vertCategorie(){
        this.categorie.setStyle("-fx-text-fill: black ");
        this.rb1.setStyle("-fx-text-fill: black ");
        this.rb2.setStyle("-fx-text-fill: black ");
        this.rb3.setStyle("-fx-text-fill: black ");
        this.rb4.setStyle("-fx-text-fill: black ");
    }

    public void vertDescription(){
        this.description.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
    }


    public void setLabelAjout(){
        this.labelAjout.setText("Photo ajoutée");
    }



}