package Vue;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Controller.ControlleFiltreRecherche;
import Controller.ControllerBtnFavoris;
import Controller.ControllerRechercheTempsReel;
import Controller.ControllerRechercheVenteUser;
import Modele.Categorie;
import Modele.Vente;
import Modele.BD.GestionVentes;
import Modele.BD.PhotoBD;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;

public class FenetreRechercheEnchere extends BorderPane {
    private Button btnVAE;
    private Button btnProfil;
    private Button btnRetour;
    private Button btnDeco;
    private GestionVentes gestionVentes;
    private PhotoBD photoBD;
    private AppliVAE appli;
    private TextField recherche;
    private ControllerRechercheTempsReel controleur;
    private CheckBox favCheckBox;
    private Button btnVendre;
    private List<RadioButton> filtreCateg;

    public FenetreRechercheEnchere(AppliVAE appli, Button btnRetour, Button btnDeco, GestionVentes gestionVentes, PhotoBD photoBD, TextField navBar, Button btnVendre,Button btnProfil,Button btnVAE) {
        super();
        this.init(appli, btnRetour, btnDeco, gestionVentes, photoBD,navBar,btnVendre,btnProfil,btnVAE);

        List<Vente> listeVente = null;
        try {
            listeVente = gestionVentes.getVenteRecherche(this.appli.getRecherche());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        this.setStyle("-fx-background-image: url('file:./ressources/img/background3.png')");
        this.setTop(this.hautDePage());
        this.setCenter(this.pagePrincipal(listeVente,this.appli.getRecherche()));
    }

    public void init(AppliVAE appli, Button btnRetour, Button btnDeco, GestionVentes gestionVentes, PhotoBD photoBD, TextField navBar,Button btnVendre, Button btnProfil,Button btnVAE) {
        this.btnRetour = btnRetour;
        this.btnVendre = btnVendre;
        this.controleur = new ControllerRechercheTempsReel(this, gestionVentes);
        this.favCheckBox = new CheckBox("Favoris");
        this.filtreCateg = new ArrayList<>();
        this.filtreCateg.add(new RadioButton("Tout"));
        for(String cat : Categorie.nomCategorie){
            Categorie c = new Categorie(cat);
            this.filtreCateg.add(new RadioButton(c.getNom()));
        }
        this.appli = appli;
        this.recherche = navBar;
        this.photoBD = photoBD;
        this.gestionVentes = gestionVentes;
        this.btnDeco = btnDeco;
        this.btnRetour.setId("btnConnexion");
        this.btnDeco.setId("btnRetour");
        this.btnRetour.setMinWidth(300);
        this.btnRetour.setMinHeight(40);
        this.btnDeco.setMinWidth(300);
        this.btnDeco.setMinHeight(40);
        this.btnVAE = btnVAE;
        this.btnVAE.setFont(Font.loadFont("file:ressources/fonts/PlayfairDisplay.ttf", 50));
        this.btnVAE.setStyle("-fx-background-color: transparent;");
        ImageView img = new ImageView(new Image("file:ressources/img//PageAccueil/imgprofile.png"));
        this.btnProfil = new Button(null, img);
        img.setFitHeight(50);
        img.setFitWidth(50);
        this.btnProfil = btnProfil;
        this.btnProfil.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20px;");

    }

    private BorderPane pagePrincipal(List<Vente> listeVente, String recherche) {
        BorderPane page = new BorderPane();
        page.setPadding(new Insets(20, 20, 20, 20));
        Label txtRecherche = new Label("Recherche pour '" + recherche +  "' :");
        txtRecherche.setFont(Font.loadFont("file:ressources/fonts/PlayfairDisplay.ttf", 40));

        

        page.setTop(txtRecherche);
        page.setLeft(menuGauche());
        page.setCenter(pageResultat(listeVente));

        BorderPane.setMargin(txtRecherche, new Insets(0, 0, 20, 0));

        return page;
    }

    private ScrollPane pageResultat(List<Vente> listeVente) {
        ScrollPane page = new ScrollPane();
        // retirer l'affichage de la scrollbar verticale
        // page.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        page.setStyle("-fx-background-color: transparent;");
        page.setPadding(new Insets(20));

        GridPane resultat = new GridPane();
        resultat.setHgap(50);
        resultat.setVgap(20);

        int nbVente = listeVente.size();
        if (nbVente > 15) {
            nbVente = 15;
        }

        for (int i = 0; i < nbVente; i++) {
            Vente v = listeVente.get(i);
            resultat.add(creerAffichageVente(v), i % 3, i / 3);

        }

        page.setContent(resultat);

        return page;
    }

    public GridPane creerAffichageVente(Vente v) {
        GridPane encHBox = new GridPane();
        encHBox.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20px; -fx-border-color: black; -fx-border-radius: 1em;");
        encHBox.setMinSize(450, 200);
        encHBox.setPadding(new Insets(20));
        encHBox.setVgap(20);
        encHBox.setHgap(30);
        encHBox.add(new Label(v.getObjet().getCategorie().getNom()), 0, 0);
        encHBox.add(new Label(v.getObjet().getNom()), 0, 1);
        // Utiliser un TextFlow pour la description
        TextFlow descriptionFlow = new TextFlow();
        descriptionFlow.setMaxWidth(200);
        descriptionFlow.setPrefHeight(100);
        descriptionFlow.setStyle("-fx-line-spacing: 5px;");

        Text descriptionText = new Text(v.getObjet().getDescription());
        descriptionText.setStyle("-fx-text-alignment: justify;");
        descriptionFlow.getChildren().add(descriptionText);

        encHBox.add(descriptionFlow, 0, 2, 1, 2);
        Button btnEncherir = new Button("ENCHERIR");
        btnEncherir.setId("btnConnexion");
        btnEncherir.setMaxWidth(200);
        encHBox.add(btnEncherir, 0, 4);
        btnEncherir.setCursor(Cursor.HAND);

        // Créer un ToggleButton pour le bouton favori
        ToggleButton btnFavori = new ToggleButton();
        btnFavori.setCursor(Cursor.HAND);
        btnFavori.setStyle("-fx-background-color: transparent;");
        ImageView imgFavori = new ImageView(new Image("file:ressources/img/imgFavori.png"));
        imgFavori.setFitHeight(30);
        imgFavori.setFitWidth(30);
        ImageView imgNonFavori = new ImageView(new Image("file:ressources/img/nonFavori.png"));
        imgNonFavori.setFitHeight(30);
        imgNonFavori.setFitWidth(30);
        try {
            if (gestionVentes.estFavoris(this.appli.getUtilisateur().getId(), v.getIdentifiant())) {
                // La vente est dans les favoris
                btnFavori.setSelected(true);
                btnFavori.setGraphic(imgFavori);
            } else {
                // La vente n'est pas dans les favoris
                btnFavori.setSelected(false);
                btnFavori.setGraphic(imgNonFavori);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        // Ajouter un événement pour gérer le changement d'état du bouton favori
        btnFavori.setOnAction(new ControllerBtnFavoris(appli, gestionVentes, v));

        encHBox.add(btnFavori, 3, 0);

        ImageView imgEnch=null;
        try {
            imgEnch = new ImageView(new Image(photoBD.getUrlPhoto(v.getIdObjet())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        imgEnch.setFitHeight(170);
        imgEnch.setFitWidth(170);
        imgEnch.setPreserveRatio(true);
        encHBox.add(imgEnch, 1, 1, 3, 4);

        return encHBox;
    }

    private VBox menuGauche() {
        VBox menu = new VBox();
        ScrollPane menuScroll = new ScrollPane();
        menuScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        menuScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        menuScroll.setStyle(
                "-fx-background-color: transparent; -fx-background-color: #e3e1dd; -fx-background-radius: 20px;");

        menu.setAlignment(Pos.TOP_CENTER);
        menu.setSpacing(10);
        menu.setPadding(new Insets(0, 0, 20, 0));
        menuScroll.setContent(menuFiltre());
        menu.getChildren().addAll(this.btnRetour, menuScroll, this.btnDeco);
        BorderPane.setAlignment(this.btnDeco, Pos.BOTTOM_CENTER);

        return menu;
    }

    public VBox menuFiltre() {
        VBox menuFiltre = new VBox();
        menuFiltre.setSpacing(20);
        menuFiltre.setMinSize(300, 400);
        menuFiltre.setPadding(new Insets(20, 20, 20, 20));

        VBox etatEnch = new VBox();
        etatEnch.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 6px;");
        etatEnch.setPadding(new Insets(10, 10, 10, 10));
        etatEnch.setSpacing(5);
        Label titreEtatEnch = new Label("ETAT DE L'ENCHERE");
        ToggleGroup groupDate = new ToggleGroup();
        RadioButton checkBox0 = new RadioButton("Tout");
        RadioButton checkBox1 = new RadioButton("Bientôt terminées");
        RadioButton checkBox2 = new RadioButton("Mis en ligne récemment");
        RadioButton checkBox3 = new RadioButton("Terminées");

        checkBox0.setToggleGroup(groupDate);
        checkBox1.setToggleGroup(groupDate);
        checkBox2.setToggleGroup(groupDate);
        checkBox3.setToggleGroup(groupDate);

        checkBox0.setSelected(true);

        etatEnch.getChildren().addAll(titreEtatEnch, checkBox0,checkBox1, checkBox2, checkBox3);

        GridPane prixEnch = new GridPane();
        prixEnch.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 6px;");
        prixEnch.setPadding(new Insets(10, 10, 10, 10));
        prixEnch.setVgap(5);
        prixEnch.setHgap(20);

        Label titrePrixEnch = new Label("PRIX DE L'ENCHERE");
        TextField prixMin = new TextField();
        prixMin.setMaxWidth(160);
        prixMin.setPromptText("Prix min.");
        TextField prixMax = new TextField();
        prixMax.setPromptText("Prix max.");
        prixMax.setMaxWidth(160);

        ImageView imgValdier = new ImageView(new Image("file:ressources/img/valider.png"));
        imgValdier.setFitHeight(30);
        imgValdier.setFitWidth(30);
        Button btnValider = new Button(null, imgValdier);
        btnValider.setCursor(Cursor.HAND);
        btnValider.setStyle("-fx-background-color: transparent;");

        prixEnch.add(titrePrixEnch, 0, 0);
        prixEnch.add(prixMin, 0, 1);
        prixEnch.add(prixMax, 0, 2);
        prixEnch.add(btnValider, 1, 1, 2, 2);

        VBox filtreEnch = new VBox();
        filtreEnch.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 6px;");
        filtreEnch.setPadding(new Insets(10, 10, 10, 10));
        filtreEnch.setSpacing(5);
        Label titrefiltreEnch = new Label("FILTRER LES ENCHERES");
        this.favCheckBox.setOnAction(new ControlleFiltreRecherche(this, gestionVentes, appli));

        filtreEnch.getChildren().addAll(titrefiltreEnch, favCheckBox);

        VBox catEnch = new VBox();
        catEnch.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 6px;");
        catEnch.setPadding(new Insets(10, 10, 10, 10));
        catEnch.setSpacing(5);
        Label titreCatEnch = new Label("FILTRER LES CATEGORIES");
        catEnch.getChildren().add(titreCatEnch);
        ToggleGroup groupCateg = new ToggleGroup();
        for(RadioButton cb : this.filtreCateg) {
        	cb.setOnAction(new ControlleFiltreRecherche(this, gestionVentes, appli));
            cb.setToggleGroup(groupCateg);
            catEnch.getChildren().add(cb);
        }

        groupCateg.selectToggle(this.filtreCateg.get(0));

        menuFiltre.getChildren().addAll(etatEnch, prixEnch, filtreEnch, catEnch);

        return menuFiltre;
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
        this.recherche.setPromptText("Rechercher par nom d'objet");
        this.recherche.setStyle(
                "-fx-background-color: transparent;  -fx-background-radius: 50px; -fx-prompt-text-fill: black; -fx-border-color: black; -fx-border-radius: 3em;");
        contientBarre.setMaxWidth(800);
        this.recherche.setPrefHeight(40);
        this.recherche.setFont(new Font(20));
        this.recherche.textProperty().addListener((observable, oldValue, newValue) -> {
        // Appeler la méthode de recherche du contrôleur avec la nouvelle valeur de recherche
        this.controleur.rechercher(newValue);
        });
        Button loupe = new Button("");
        ImageView imgLoupe = new ImageView(new Image("file:ressources/img/PageAccueil/loupe.png"));
        imgLoupe.setFitHeight(30);
        imgLoupe.setFitWidth(30);
        loupe.setGraphic(imgLoupe);
        loupe.setStyle("-fx-background-color: transparent;");
        loupe.setCursor(Cursor.HAND);
        loupe.setOnAction(new ControllerRechercheVenteUser(appli, this.recherche));
        // mettre loupe tout à droite
        StackPane.setAlignment(loupe, Pos.CENTER_RIGHT);
        contientBarre.getChildren().addAll(this.recherche, loupe);
        return contientBarre;
    }

    public void mettreAJourResultats(List<Vente> listeVente, String recherche) {

        this.setCenter(this.pagePrincipal(listeVente,recherche));
    }

    public void setTextNavBar(String text){
        this.recherche.setText(text);
    }

    public String getTextNavBar(){
        return this.recherche.getText();
    }

    public void setSelectedBtnFavoris(boolean selected){
        this.favCheckBox.setSelected(selected);
    }


    public void setSelectedBtnCategorie(String nomCategorie, boolean selected){
        for(RadioButton cb : this.filtreCateg){
            if(cb.getText().equals(nomCategorie)){
                cb.setSelected(selected);
            }
        }
    }

    public boolean getIsSelectedBtnFavoris(){
        return this.favCheckBox.isSelected();
    }

    public boolean getIsSelectedBtnCategorie(String nomCategorie){
        for(RadioButton cb : this.filtreCateg){
            if(cb.getText().equals(nomCategorie)){
                return cb.isSelected();
            }
        }
        return false;
    }

}
