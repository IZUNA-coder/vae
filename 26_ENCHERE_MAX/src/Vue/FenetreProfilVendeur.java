package Vue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modele.Utilisateur;
import Modele.Vente;
import Modele.BD.GestionVentes;
import Modele.BD.PhotoBD;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FenetreProfilVendeur extends BorderPane {

    private String dateCreation;
    private int nbEncheresConclues;
    private String email;
    private String telephone;
    private Button btnRetour;
    private Utilisateur vendeur;
    private Hyperlink hyperlinkAccueil;
    private PhotoBD photoBd;
    private GestionVentes gestionVentes;


    public FenetreProfilVendeur(Hyperlink hyperlinkAccueil, Button btnRetour, Utilisateur vendeur, PhotoBD photoBd, GestionVentes gestionVentes) {
        super();
        this.gestionVentes = gestionVentes;
        this.hyperlinkAccueil=hyperlinkAccueil;
        this.btnRetour = btnRetour;
        this.vendeur = vendeur;
        this.photoBd = photoBd;
        this.email = vendeur.getEmail();
        this.dateCreation = "fevrier 2023";
        this.nbEncheresConclues = 10;
        this.setStyle("-fx-border-color: #D3D3D3; -fx-border-width: 5px;");
        this.ajouteTop(this);
        this.ajouteCentre(this);
        this.ajouteDroite(this);
        this.setPadding(new Insets(10, 50, 10, 10));// top - right - bottom - left
        
    }

    public void ajouteTop(BorderPane bd) {
        HBox hb = new HBox();

        Label label = new Label(">");
        Hyperlink hyperlink2 = new Hyperlink("Profils");// créer une hyperlien
        Label label2 = new Label(">");
        Hyperlink hyperlink3 = new Hyperlink(vendeur.getUsername());// créer une hyperlien

        hb.getChildren().addAll(btnRetour, this.hyperlinkAccueil, label, hyperlink2, label2, hyperlink3);
        HBox.setMargin(btnRetour, new Insets(0, 20, 0, 20));// top - right - bottom - left
        hb.setAlignment(Pos.CENTER_LEFT);
        bd.setTop(hb);
    }

    public void ajouteDroite(BorderPane bd) {
        VBox vb = new VBox();
        vb.setSpacing(20);
        List<Vente> listeVenteParUtilisateur = new ArrayList<Vente>();
        System.out.println("listeVente est été initialisé");
        int idUser = this.vendeur.getId();
        System.out.println(idUser);
        try {
            System.out.println("Passage dans le try");
            listeVenteParUtilisateur = this.gestionVentes.getVenteParUtilisateur(idUser);
        } catch (SQLException e) {
            System.out.println("Il y a un probleme pour récupérer la liste du vendeur");;
        }
        for(int i = 0; i < listeVenteParUtilisateur.size(); i++) {
            Vente vente = null;
            System.out.println("Passage dans la boucle");
            if(listeVenteParUtilisateur.isEmpty()) {
                System.out.println("La liste est vide");
            }
            else{
                vente = listeVenteParUtilisateur.get(i);
            }
            GridPane gp = boiteDroite(vente);
            vb.getChildren().add(gp);
        }
        bd.setRight(vb);
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

    public void ajouteCentre(BorderPane bd) {
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(description(), infosProfil());
        bd.setCenter(hb);
    }

    public VBox description() {
        VBox vb = new VBox();

        ImageView image = new ImageView(new Image("file:./ressources/img/VueUtilisateur/marteau_profil.png"));
        image.setFitHeight(50);
        image.setFitWidth(50);
        image.setPreserveRatio(true);
        image.setSmooth(true);
        image.setCache(true);

        Label titre = new Label(vendeur.getUsername(), image);
        titre.setStyle("-fx-font-weight: bold; -fx-font-size: 60px; -fx-underline: true;");
        Label type = new Label("Vendeur");
        type.setStyle("-fx-font-size: 25px; -fx-text-fill: #D3D3D3;");

        Label description = new Label("Description");
        Text contenuDescription = new Text("Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assemb et, plus récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker.Le Lorem Ipsum est simplement du faux texte employ\u00E9 dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les ann\u00E9es 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour r\u00E9aliser un livre sp\u00E9cimen de polices de texte. Il n'a pas fait que survivre cinq si\u00E8cles, mais s'est aussi adapt\u00E9 \u00E0 la bureautique informatique, sans que son contenu n'en soit modifi\u00E9. Il a \u00E9t\u00E9 popularis\u00E9 dans les ann\u00E9es 1960 gr\u00E2ce \u00E0 la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus r\u00E9cemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker.");

        contenuDescription.setWrappingWidth(500);
        description.setPadding(new Insets(10, 0, 15, 0));// top - right - bottom - left
        // justifier le texte
        contenuDescription.setStyle("-fx-text-alignment: justify; -fx-font-size: 20px;");
        contenuDescription.setLineSpacing(1);
        description.setStyle("-fx-font-size: 35px");// permet d'agrandir la taille de la police

        vb.getChildren().addAll(titre, type, description, contenuDescription);
        return vb;
    }

    public VBox infosProfil() {
        VBox vb = new VBox();

        vb.setPadding(new Insets(0, 0, 0, 35));// top - right - bottom - left
        ImageView image = new ImageView("file:./ressources/img/VueUtilisateur/image.png");
        image.setStyle("-fx-font-color: #FF0000;");
        image.setFitHeight(350);
        image.setFitWidth(350);
        image.setPreserveRatio(true);

        Label membre = new Label("Membre depuis " + this.dateCreation);
        membre.setStyle("-fx-font-size: 20px;");
        membre.setCenterShape(true);
        membre.setPadding(new Insets(15, 0, 0, 25));// top - right - bottom - left
        membre.setAlignment(Pos.CENTER);
        Label nbEncheresConclues = new Label(this.nbEncheresConclues + " encheres conclues");
        nbEncheresConclues.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        nbEncheresConclues.setAlignment(Pos.CENTER);
        nbEncheresConclues.setPadding(new Insets(15, 0, 15, 0));// top - right - bottom - left
        Button contacter = new Button("Contacter");
        contacter.setId("search");
        contacter.setCursor(Cursor.HAND);
        contacter.setAlignment(Pos.CENTER);
        // Appuyer sur le bouton contacter nous ouvre la popUpInfosProfil qui nous donne les informations de l'utilisateur
        contacter.setOnAction(e -> popUpInfosProfil());
        vb.getChildren().addAll(image, membre, nbEncheresConclues, contacter);
        vb.setAlignment(Pos.TOP_CENTER);
        return vb;
    }
    public HBox ajouteVoirPLus(){
        HBox hb = new HBox();
        Label troispoint = new Label("...");
        troispoint.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Hyperlink voirPlus = new Hyperlink("Voir plus");
        voirPlus.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        hb.getChildren().addAll(troispoint, voirPlus);
        hb.setAlignment(Pos.TOP_RIGHT);//droite
        hb.setPadding(new Insets(-20, 0, 0, 0));// top - right - bottom - left
        return hb;
    }
    public Alert popUpInfosProfil(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informations Utilisateurs");
        alert.setHeaderText("Voici les différentes informations de l'utilisateur : ");
        alert.initOwner(this.getScene().getWindow());
        
        VBox vbox = new VBox();
        Label email = new Label("Adresse email : " + this.email);
        Label telephone = new Label("Téléphone : ");

        HBox hb = new HBox();
        Label telephoneTxt = new Label(this.telephone);
        hb.getChildren().addAll(email);
        HBox hb2 = new HBox();
        hb2.getChildren().addAll(telephone, telephoneTxt);
        vbox.getChildren().addAll(hb, hb2);
        vbox.setSpacing(30);
        vbox.setPadding(new Insets(10, 10, 10, 10));// top - right - bottom - left
        

        alert.getDialogPane().setContent(vbox);
        alert.showAndWait();
        return alert;
    }

}