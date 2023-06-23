package Vue;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import java.sql.SQLException;

import Controller.ControllerBtnFavoris;
import Controller.ControllerBtnObjtEncherir;
import Modele.Enchere;
import Modele.Vente;
import Modele.BD.EnchereBd;
import Modele.BD.GestionVentes;
import Modele.BD.PhotoBD;


public class FenetreObjet extends BorderPane {
    

    private Button retour;
    private Button encherir;
    private Hyperlink voirProfil;
    private Button btnVAE;
    private Vente vente;
    private AppliVAE appli;
    private Button btnProfil;
    private EnchereBd enchereBd;
    private GestionVentes gestionVentes;
    private PhotoBD photoBD;

    /**
     * Constructeur de la classe FenetreObjet
     * @param btnProfil
     * @param retour
     * @param encherir
     * @param voirProfil
     * @param vente
     * @param enchereBd
     * @param photoBD
     * @param gv
     * @param btnVAE
     * @param appli
     */
    public FenetreObjet(Button btnProfil, Button retour, Button encherir, Hyperlink voirProfil, Vente vente,  EnchereBd enchereBd, PhotoBD photoBD,GestionVentes gv,Button btnVAE,AppliVAE appli){
        super();
        this.photoBD=photoBD;
        this.gestionVentes=gv;
        this.retour=retour;
        this.encherir=encherir;
        this.appli=appli;
        this.voirProfil=voirProfil;
        this.vente=vente;
        this.enchereBd=enchereBd;
        this.appli.setVendeur(this.gestionVentes.getVendeur(this.vente.getIdentifiant()));
        this.btnVAE = btnVAE;
        // this.btnVAE.setFont(Font.loadFont("file:ressources/fonts/PlayfairDisplay.ttf", 50));
        
        this.btnProfil = btnProfil;
        this.btnVAE.setStyle("-fx-background-color: transparent;");
        this.btnVAE.setPadding(new Insets(0, 0, 0, 30));
        
        this.btnVAE.setCursor(Cursor.HAND);
        this.btnProfil.setCursor(Cursor.HAND);
        
        this.btnProfil.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20px;");
      
        this.setTop(enHaut());
        this.setCenter(centre());

    }
    /**
     * Permet de créer le haut de la page
     * @return VBox
     */
    public VBox enHaut(){
        VBox vb= new VBox();
        vb.getChildren().addAll(this.hautDePage());
        
        return vb;
    }
    /**
     * Permet de créer le haut de la page
     * @return BorderPane
     */
    public BorderPane hautDePage() {
        BorderPane top = new BorderPane();
        top.setLeft(this.btnVAE);
        
        top.setRight(this.btnProfil);
        top.setPadding(new Insets(25, 10, 25, 10));
        return top;
    }           
    /**
     * Permet de créer le centre de la page
     * @return BorderPane
     */
    public BorderPane centre(){
        BorderPane bd= new BorderPane();
        bd.setTop(hautCentre());
        bd.setLeft(gaucheCentre());
        bd.setRight(droiteCentre());
        bd.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), Insets.EMPTY)));
        bd.setPadding(new Insets(50, 50, 50, 50));// top - right - bottom - left
        
        
        return bd;

    }
    /**
     * Permet de créer le centre haut de la page
     * @return HBox
     */
    public HBox hautCentre(){
        HBox hb= new HBox();
        Hyperlink encheres = new Hyperlink("Enchères");// créer une hyperlien
        Label label = new Label(">");
        Hyperlink hyperlink2 = new Hyperlink("Catégorie");// créer une hyperlien
        Label label2 = new Label(">");
        Hyperlink hyperlink3 = new Hyperlink(this.vente.getObjet().getCategorie().getNom());// créer une hyperlien
        Label label3 = new Label(">");
        Hyperlink hyperlink4 = new Hyperlink(this.vente.getObjet().getNom());// créer une hyperlien
        //Ajout des éléments dans la HBox
        hb.getChildren().addAll(this.retour, encheres, label, hyperlink2, label2, hyperlink3, label3, hyperlink4);
        HBox.setMargin(this.retour, new Insets(0, 20, 50, 20));// top - right - bottom - left
        hb.setAlignment(Pos.CENTER_LEFT);
        return hb;
    }
    /**
     * Permet de créer l'emplacemnt pour la partie gauche de la photo
     * @return VBox
     */
    public VBox gaucheCentre(){
        VBox v= new VBox();
        HBox h= new HBox();

        v.setAlignment(Pos.TOP_LEFT);
        

        Text titre=new Text( this.vente.getObjet().getNom());
        titre.setWrappingWidth(400);
        titre.setStyle("-fx-font-size: 55px; -fx-font-weight: bold;");
        Label desc= new Label("Description :");
        desc.setPadding(new Insets(50, 0, 25, 0));//top right bottom left
        desc.setStyle("-fx-font-size: 20px; -fx-underline: true; -fx-font-weight: bold;");
        
        Text prixBase= new Text("Prix de base : "+this.vente.getPrixBase()+" €");
        prixBase.setStyle("-fx-font-size: 20px; -fx-font-color: #8A8884;");

        //ajouter tout en bas un label pour la date de fin
        HBox dateFin = new HBox();
        int jourRestant = this.vente.getJourRestant();
        Label lbJourRestant = new Label(String.valueOf(jourRestant) + "j");
        Label textDate = new Label("Fin de l'enchère dans : ");
        // couleur rouge jour restant < 3, sinon si inférieur à 7, couleur orange, sinon vert
        if (jourRestant <= 0) {
            lbJourRestant.setStyle("-fx-text-fill: #FF0000; -fx-font-weight: bolder; -fx-font-size: 25;");
            int heureRestante = this.vente.getHeureRestantes();
            if (heureRestante <= 0) {
                lbJourRestant.setText("quelques minutes");
            } else {
                lbJourRestant.setText(String.valueOf(heureRestante) + "h");
            }
        } else if (jourRestant < 3) {
            lbJourRestant.setStyle("-fx-text-fill: #FF0000; -fx-font-weight: bolder; -fx-font-size: 25;");
        } else if (jourRestant < 7) {
            lbJourRestant.setStyle("-fx-text-fill: #FFA500; -fx-font-weight: bolder; -fx-font-size: 25;");
        } else {
            lbJourRestant.setStyle("-fx-text-fill: #008000; -fx-font-weight: bolder; -fx-font-size: 25;");
        }

        textDate.setFont(Font.loadFont("file:ressources/fonts/PlayfairDisplay.ttf", 25));
        dateFin.getChildren().addAll(textDate, lbJourRestant);
        
        Text description= new Text(this.vente.getObjet().getDescription());
        description.setWrappingWidth(400);
        description.setStyle("-fx-font-size: 15px; -fx-alignment: justify;");
        
        

        //coeur
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
            System.out.println(this.appli.getUtilisateur());
            System.out.println(this.vente.getIdentifiant());
            System.out.println(gestionVentes.estFavoris(this.appli.getUtilisateur().getId(), this.vente.getIdentifiant()));
            if (gestionVentes.estFavoris(this.appli.getUtilisateur().getId(), this.vente.getIdentifiant())) {
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
        btnFavori.setOnAction(new ControllerBtnFavoris(appli, gestionVentes, vente));

        VBox vendeur= vendeur();
        h.getChildren().addAll(titre,btnFavori);
        v.getChildren().addAll(h,prixBase,dateFin,desc,description, vendeur);
        v.setSpacing(5);
        v.setPadding(new Insets(0,0,0,100));

        return v;
    }
    /**
     * Permet de créer l'emplacemnt pour la partie vendeur
     * @return VBox
     */
    public VBox vendeur(){

        VBox vbvendeur = new VBox();
        HBox hbvendeur = new HBox();
        Label ven = new Label("Vendeur :");
        ven.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-underline: true;");
        ImageView marteauVendeur = new ImageView(new Image("file:./ressources/img/sells.png"));
        marteauVendeur.setFitWidth(25);
        marteauVendeur.setPreserveRatio(true);
        this.voirProfil.setPadding(new Insets(0, 0, 0, 20));// top - right - bottom - left
        hbvendeur.getChildren().addAll(ven, marteauVendeur, this.voirProfil);
        

        HBox hbvendeur2 = new HBox();
        Label membreDepuis = new Label("Membre depuis le 01/01/2000");
        membreDepuis.setAlignment(Pos.CENTER);
        membreDepuis.setPadding(new Insets(20, 0, 0, 20));// top - right - bottom - left
        ImageView imageVendeur = new ImageView(new Image("file:./ressources/img/PageAccueil/imgprofile.png"));
        imageVendeur.setFitWidth(100);
        imageVendeur.setPreserveRatio(true);
        hbvendeur2.getChildren().addAll(imageVendeur, membreDepuis);
        vbvendeur.getChildren().addAll(hbvendeur, hbvendeur2);
        vbvendeur.setAlignment(Pos.BOTTOM_LEFT);
        vbvendeur.setPadding(new Insets(50, 0, 0, 0));// top - right - bottom - left

       
        return vbvendeur;
    }
    /**
     * Permet de créer l'emplacemnt pour la partie droite
     * @return VBox
     */
    public VBox droiteCentre(){
        VBox vb= new VBox();
        
        HBox haut= new HBox();
        HBox bas = new HBox();

        vb.setAlignment(Pos.TOP_LEFT);

        ImageView chrono= new ImageView(new Image("file:./ressources/img/VueUtilisateur/chrono.png"));
        //this.chronometre.start();
        int nbEncheres=0;
        try{
            nbEncheres=this.enchereBd.getEncheresVente(this.vente).size();

        }
        catch(Exception e){
            System.out.println("erreur");
        }
        Text nbEnchereText= new Text(nbEncheres+" encheres");
        nbEnchereText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        String url="";
        try {
            url=this.photoBD.getUrlPhoto(this.vente.getIdObjet());
            
        } catch (Exception e) {
            System.out.println("erreur");
        }
        ImageView image= new ImageView(new Image(url));
        image.setFitHeight(550);
        image.setFitWidth(700);
        image.setPreserveRatio(true);
        image.setSmooth(true);
        image.setCache(true);

        Text meilleurEnchere= new Text("Aucunes enchères");

        try{
            Enchere eMax = this.vente.getEnchereMax();
            if (eMax != null) {
                double meilleur= this.vente.getEnchereMax().getPrix();
                meilleurEnchere= new Text("meilleure enchère : "+meilleur+" €");
            }
            
        }
        catch(Exception e){
            System.out.println("erreur");
        }
        
        meilleurEnchere.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button btnStatut= new Button();
        btnStatut.setMinWidth(200);
        btnStatut.setMinHeight(30);
        
        switch (vente.getStatut().getNom()) {
            case "A venir":
                btnStatut.setText(vente.getStatut().getNom().toUpperCase());
                btnStatut.setStyle("-fx-background-color: #146D85; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-size: 10px;");
                break;
            case "En cours":
                btnStatut.setText(vente.getStatut().getNom().toUpperCase());
                btnStatut.setStyle("-fx-background-color: #109018; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-size: 10px;");
                break;
            case "A valider":
                btnStatut.setText(vente.getStatut().getNom().toUpperCase());
                btnStatut.setStyle("-fx-background-color: #F2EF15; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-size: 10px;");
                break;
            case "Validée":
                btnStatut.setText(vente.getStatut().getNom().toUpperCase());
                btnStatut.setStyle("-fx-background-color: #51D1E6; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-size: 10px;");
                break;
            case "Non conclue":
                btnStatut.setText(vente.getStatut().getNom().toUpperCase());
                btnStatut.setStyle("-fx-background-color: #E67651; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-size: 10px;");
                break;
        }
        this.encherir.setMinWidth(200);
        this.encherir.setMinHeight(30);
        this.encherir.setStyle("-fx-background-color: #51D1E6; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-size: 10px;");
        this.encherir.setCursor(javafx.scene.Cursor.HAND);
        this.encherir.setOnAction(new ControllerBtnObjtEncherir(this.appli,this.vente));

        haut.getChildren().addAll(chrono,nbEnchereText);
        bas.getChildren().addAll(this.encherir,btnStatut);
        bas.setSpacing(25);
        vb.getChildren().addAll(haut,image,meilleurEnchere,bas);
        vb.setPadding(new Insets(0,100,0,0));
        vb.setSpacing(25);

        
        return vb;
    }


    
   
}
