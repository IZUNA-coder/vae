package Vue;

import java.sql.SQLException;
import java.util.List;

import javafx.animation.TranslateTransition;
import Controller.ControllerBtnEncherir;
import Controller.ControllerBtnObjAVendre;
import Controller.ControllerRetour;
import Modele.Enchere;
import Modele.Vente;
import Modele.BD.EnchereBd;
import Modele.BD.GestionVentes;
import Modele.BD.PhotoBD;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class FenetreObjAVendre extends BorderPane{

    private String objet;
    private String montant;
    private GestionVentes gestionVentes;
    private Vente vente;
    private Button btnRetour;
    private EnchereBd enchereBd;
    private PhotoBD photoBd;
    private Hyperlink voirProfil;
    private TextField montantTextField;
    private double montantSaisi;
    private TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000));
    private int appuiScrollGauche = 0;
    private int appuiScrollDroit = 0;
    private int nbAutresEncheres = 30;
    private AppliVAE appli;
    private Label meilleurEnchere;

    
    public FenetreObjAVendre(AppliVAE appli,GestionVentes gestionVente, Vente vente,Button btnRetour, EnchereBd enchereBd, PhotoBD photoBd, Hyperlink voirProfil){
        super();
        this.appli = appli;
        this.vente = vente;
        this.gestionVentes = gestionVente;
        this.btnRetour = btnRetour;
        this.photoBd = photoBd;
        this.enchereBd = enchereBd;
        this.voirProfil = voirProfil;
        this.setStyle("-fx-border-color: #D3D3D3; -fx-border-width: 5px;");
        this.ajouteTop(this);
        this.centre();
        this.bas();
        this.ajouteCentreDroit();
        this.setPadding(new Insets(10, 50, 10, 10) );// top - right - bottom - left
        this.montant = this.vente.getPrixBase() + "€";
        this.appli.setVendeur(this.gestionVentes.getVendeur(vente.getIdentifiant()));
        System.out.println(this.appli.getVendeur().getUsername());
        this.montantSaisi = 0.0;
    }

    public void ajouteTop(BorderPane bd) {
        //Création du bouton retour
        HBox hb = new HBox();
        //Création du chemin en hyperliens
        Hyperlink encheres = new Hyperlink("Enchères");// créer une hyperlien
        Label label = new Label(">");
        Hyperlink hyperlink2 = new Hyperlink("Catégorie");// créer une hyperlien
        Label label2 = new Label(">");
        Hyperlink hyperlink3 = new Hyperlink(this.vente.getObjet().getCategorie().getNom());// créer une hyperlien
        Label label3 = new Label(">");
        Hyperlink hyperlink4 = new Hyperlink(this.vente.getObjet().getNom());// créer une hyperlien
        //Ajout des éléments dans la HBox
        hb.getChildren().addAll(btnRetour, encheres, label, hyperlink2, label2, hyperlink3, label3, hyperlink4);
        HBox.setMargin(btnRetour, new Insets(0, 20, 0, 20));// top - right - bottom - left
        hb.setAlignment(Pos.CENTER_LEFT);
        bd.setTop(hb);
    }
    public void centre(){
        BorderPane bd = new BorderPane();
        bd.setLeft(ajouteCentreGauche(this));
        bd.setRight(ajouteCentreDroit());
        this.setCenter(bd);
    }
    public void bas(){
        HBox hb = new HBox();
        VBox vb = new VBox();
        Label autre = new Label("Autres enchères en cours :");
        autre.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-underline: true;");//souligner le texte
        autre.setPadding(new Insets(10, 0, 10, 0));// top - right - bottom - left
        HBox hb1 = new HBox();
        int idCat=this.vente.getObjet().getCategorie().getIdentifiant();
        try {
            List<Vente> listeVente = this.gestionVentes.getVenteParCategorie(idCat);
            int ind= listeVente.size();
            if(ind>nbAutresEncheres){
                ind =nbAutresEncheres;}
            for(int i = 0; i < ind; i++){
                    Vente v = listeVente.get(i);
                    GridPane gp1 = boiteDroite(v);
                    gp1.setOnMouseClicked(new ControllerBtnObjAVendre(this.appli, v));
                    gp1.setCursor(Cursor.HAND);
                    gp1.setMaxWidth(200);
                    hb1.getChildren().add(gp1);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        hb1.setSpacing(20);
        ScrollPane sp1 = new ScrollPane(hb1);
        sp1.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");


        ScrollPane customScrollPane = new ScrollPane();
        customScrollPane.setContent(hb1);
        customScrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        customScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        

        Button scrollLeftButton = new Button("\u25C4");
        Button scrollRightButton = new Button("\u25BA");
        scrollRightButton.setCursor(Cursor.HAND);
        scrollLeftButton.setCursor(Cursor.HAND);



        scrollLeftButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 70px;");
        scrollLeftButton.setPadding(new Insets(150, 50, 0, 0));// top - right - bottom - left
        scrollRightButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 70px;");
        scrollRightButton.setPadding(new Insets(150, 0, 0, 50));// top - right - bottom - left

        scrollLeftButton.setOnAction(e -> {
            //empecher le clic si on est au debut
            if(this.appuiScrollGauche==0){
                return;
            }
            this.appuiScrollGauche++;
            this.appuiScrollDroit--;
            translateTransition.setNode(hb1);
            translateTransition.setByX(1098);
            translateTransition.setDuration(Duration.millis(600));
            translateTransition.play();
        });

        scrollRightButton.setOnAction(e -> {
            if(this.appuiScrollDroit>this.nbAutresEncheres/2){
                return;
            }
            this.appuiScrollGauche--;
            this.appuiScrollDroit++;
            translateTransition.setNode(hb1);
            translateTransition.setByX(-1098);
            translateTransition.setDuration(Duration.millis(600));
            translateTransition.play();
        });

        VBox scrollPaneContainer = new VBox(customScrollPane);
        customScrollPane.setMaxWidth(1100);

        VBox vbvendeur = new VBox();
        HBox hbvendeur = new HBox();
        Label ven = new Label("  Vendeur :  " + this.gestionVentes.getVendeur(this.vente.getIdentifiant()).getUsername());
        ven.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-underline: true;");
        ImageView marteauVendeur = new ImageView(new Image("file:./ressources/img/sells.png"));
        marteauVendeur.setFitWidth(25);
        marteauVendeur.setPreserveRatio(true);
        this.voirProfil.setPadding(new Insets(0, 0, 0, 20));// top - right - bottom - left
        hbvendeur.getChildren().addAll(marteauVendeur, ven, this.voirProfil);


        

        HBox hbvendeur2 = new HBox();
        Label membreDepuis = new Label("Membre depuis le 01/01/2000");
        membreDepuis.setAlignment(Pos.CENTER);
        membreDepuis.setPadding(new Insets(20, 0, 0, 20));// top - right - bottom - left
        ImageView imageVendeur = new ImageView(new Image("file:./ressources/img/PageAccueil/imgprofile.png"));
        imageVendeur.setFitWidth(100);
        imageVendeur.setPreserveRatio(true);
        hbvendeur2.getChildren().addAll(imageVendeur, membreDepuis);
        vbvendeur.getChildren().addAll(hbvendeur, hbvendeur2);
        vbvendeur.setAlignment(Pos.CENTER);
        vbvendeur.setPadding(new Insets(0, 0, 0, 100));// top - right - bottom - left
        


        vb.getChildren().addAll(autre, scrollPaneContainer);
        hb.getChildren().addAll(scrollLeftButton, vb, scrollRightButton, vbvendeur);

        this.setBottom(hb);
    }

    private VBox ajouteCentreDroit() {
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        BorderPane bp = ajouteDroite();
        vb.getChildren().addAll(bp);
        return vb;
    }

    private VBox ajouteCentreGauche(BorderPane bd) {
        VBox vb = new VBox();
        vb.setPadding(new Insets(0,0,0,50));
        vb.setAlignment(Pos.CENTER);
        HBox billetOffre = new HBox();
        ImageView billet = new ImageView(new Image("file:./ressources/img/billet.png"));
        billet.setFitWidth(60);
        billet.setPreserveRatio(true);

        this.montantTextField = new TextField();
        this.montantTextField.setTextFormatter(
            new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?\\d*(\\.\\d*)?")) { // Regex pour les nombres décimaux
                return change;
            } else {
                return null;
            }
        }));
        
        Label offre = new Label(" OFFRE");
        offre.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;");
        billetOffre.getChildren().addAll(billet, offre);


        this.meilleurEnchere =null;

        List<Enchere> list = null;
        try {
            list = this.enchereBd.getEncheresVente(this.vente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(list);
        if (!list.isEmpty()){
            this.vente.setLesEncheres(list);
            System.out.println(this.vente.getEnchereMax().getPrix());
            meilleurEnchere = new Label("Meilleur enchère : " + this.vente.getEnchereMax().getPrix() + "€");
        } else{
            System.out.println("pas d'enchere");
            meilleurEnchere = new Label("Aucune encheres");
        }

        
        meilleurEnchere.setStyle("-fx-font-size: 20px;");
        HBox hb1 = new HBox();
        Label montant = new Label("Montant : ");
        
        hb1.getChildren().addAll(montant, this.montantTextField);
        hb1.setStyle("-fx-background-color: #D3D3D3; -fx-border-radius: 15px; -fx-background-radius: 15px; -fx-text-fill: black; -fx-padding: 10px");
        hb1.setMaxWidth(300);
        hb1.setAlignment(Pos.CENTER);
        hb1.setSpacing(5);
        
        HBox time = new HBox();
        Label tempsRestant = new Label("   2j 3h 4m 5s");
        tempsRestant.setStyle("-fx-font-size: 20px;");
        ImageView horloge = new ImageView(new Image("file:./ressources/img/chrono.png"));
        horloge.setFitWidth(60);
        horloge.setPreserveRatio(true);
        time.getChildren().addAll(horloge, tempsRestant);
        time.setAlignment(Pos.CENTER);
        
        HBox boutons = new HBox();
        Button annuler = new Button("Annuler");
        Button accepter = new Button("Accepter");
        annuler.setCursor(Cursor.HAND);
        annuler.setStyle("-fx-border-color: #87CEEB; -fx-border-width: 3px; -fx-background-color: white; -fx-text-fill: #87CEEB; -fx-font-size: 25px; -fx-font-weight: bold; -fx-border-radius: 15px; -fx-background-radius: 15px;");
        annuler.setOnAction(new ControllerRetour(this.appli));
        accepter.setStyle("-fx-border-color: #87CEEB; -fx-border-width: 5px; -fx-background-color: #87CEEB; -fx-text-fill: white; -fx-font-size: 25px; -fx-font-weight: bold; -fx-border-radius: 15px; -fx-background-radius: 15px;");
        accepter.setCursor(Cursor.HAND);
        accepter.setOnAction(new ControllerBtnEncherir(this));
        boutons.setSpacing(50);
        boutons.getChildren().addAll(annuler, accepter);
        vb.getChildren().addAll(billetOffre, meilleurEnchere, time, hb1, boutons);
        billetOffre.setAlignment(Pos.CENTER);
        vb.setAlignment(Pos.CENTER);
        
        BorderPane.setAlignment(vb, Pos.CENTER);
        boutons.setAlignment(Pos.CENTER);
        vb.setSpacing(20);
        vb.setPadding(new Insets(0, 100, 0, 125));// top - right - bottom - left
        return vb;

    }
    
    public BorderPane ajouteDroite() {
        BorderPane gp = new BorderPane();
        gp.setPadding(new Insets(10, 10, 10, 10));

        VBox vbox = new VBox();

        VBox vb = new VBox();
        ImageView image=null;
        try {
            image = new ImageView(new Image(this.photoBd.getUrlPhoto(this.vente.getIdObjet())));
            image.setFitHeight(500);
            image.setFitWidth(500);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        image.setFitHeight(350);
        image.setPreserveRatio(true);
        image.setSmooth(true);
        image.setCache(true);
        vb.setAlignment(Pos.CENTER);
        


        Label nbEncheres = new Label(" Nombre d'enchères : " + 78);
        nbEncheres.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        vb.getChildren().addAll(image, nbEncheres);


        Text titre = new Text(this.vente.getObjet().getNom());
        titre.setWrappingWidth(400);
        titre.setStyle("-fx-font-size: 55px; -fx-font-weight: bold;");
        Label prixDB = new Label("Prix de base : " + this.vente.getPrixBase() + "€");
        prixDB.setStyle("-fx-font-size: 20px; -fx-font-color: #8A8884;");
        Label description = new Label("Description :");
        description.setPadding(new Insets(20, 0, 15, 0));//top right bottom left
        description.setStyle("-fx-font-size: 20px; -fx-underline: true; -fx-font-weight: bold;");
        Text descriptionText = new Text(this.vente.getObjet().getDescription());
        descriptionText.setWrappingWidth(400);
        descriptionText.setStyle("-fx-font-size: 15px; -fx-alignment: justify;");
        vbox.setPadding(new Insets(0, 50, 0, 0));// top - right - bottom - left
        vbox.getChildren().addAll(titre, prixDB, description, descriptionText);

        gp.setRight(vb);
        gp.setLeft(vbox);
        gp.setPadding(new Insets(75, 0, 0, 0));// top - right - bottom - left
        return gp;
    }

    public GridPane boiteDroite(Vente vente) {
        // Création de la GridPane
        GridPane gp = new GridPane();
        gp.setHgap(15);
        gp.setPadding(new Insets(10, 10, 10, 10));// top - right - bottom - left
        GridPane.setMargin(gp, new Insets(0, 50, 0, 0));
        // Créartion des Label et de l'image
        Label categorie = new Label(vente.getObjet().getCategorie().getNom());
        Label titre = new Label(vente.getObjet().getNom());
        Text description = new Text(vente.getObjet().getDescription());
        Label status = new Label(vente.getStatut().getNom());
        Label prix = new Label(vente.getPrixBase() + "€");
        ImageView image=null;
        try {
            image = new ImageView(new Image(this.photoBd.getUrlPhoto(vente.getIdObjet())));
            image.setFitWidth(200);
            image.setFitHeight(200);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // STYLE
        status.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #FFFFFF; -fx-background-color: #80CEE1; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 5px;");
        gp.setStyle("-fx-border-color: #D3D3D3; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 5px;");
        description.setStyle("-fx-text-alignment: justify;");
        description.setWrappingWidth(300);
        prix.setStyle("-fx-font-size: 20px; -fx-font-color: #FF0000;");
        // Padding
        titre.setPadding(new Insets(0, 0, 10, 0));// top - right - bottom - left
        // Ajout des éléments à la GridPane
        gp.add(categorie, 0, 0);
        gp.add(titre, 0, 1);
        gp.add(description, 0, 2);
        gp.add(status, 0, 3);
        gp.add(prix, 1, 3);
        gp.add(image, 1, 2);// colonne, ligne, colspan, rowspan

        return gp;
    }

    //Setter qui permettrait de modifier le texte de la meilleure enchère par le nouveau montant
    public void setTextEnchereMax(double montantNew){
        this.meilleurEnchere.setText("Meilleur enchère : " + montantNew + "€");
    }


    public Alert popUpOffreAccepter(){
        this.montantSaisi = Double.parseDouble(this.montantTextField.getText());
        Double encheremax = this.vente.getEnchereMax().getPrix();
        if(this.montantSaisi > encheremax){
            encheremax = this.montantSaisi;
            System.out.println("Enchere max : " + encheremax);
            this.vente.getEnchereMax().setTextEnchereMax(encheremax);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acceptation de l'offre");
        alert.setHeaderText("Félicitations !");
        alert.setContentText("Vous avez accepter l'offre de " + encheremax + " pour " + vente.getObjet().getNom());

        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(400);
        
        alert.showAndWait();
                
        return alert;
    }
    public double getMontantSaisie(){
        return this.montantSaisi;
    }




}
