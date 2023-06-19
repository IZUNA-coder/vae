import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.scene.image.ImageView;

public class VueUtilisateur extends Application {

    private String dateCreation;
    private int nbEncheresConclues;
    private String email;
    private String telephone;

    @Override
    public void start(Stage primaryStage) throws Exception {
        init();

        BorderPane bd = bd();

        Scene scene = new Scene(bd, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Vue Utilisateur");

        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());

        primaryStage.show();
    }

    public void init() {
        this.dateCreation = "fevrier 2023";
        this.nbEncheresConclues = 10;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public BorderPane bd() {
        BorderPane bd = new BorderPane();
        bd.setStyle("-fx-border-color: #D3D3D3; -fx-border-width: 5px;");
        ajouteTop(bd);
        ajouteCentre(bd);
        ajouteDroite(bd);
        bd.setPadding(new Insets(10, 50, 10, 10));// top - right - bottom - left
        return bd;
    }

    public void ajouteTop(BorderPane bd) {
        HBox hb = new HBox();
        Button btn = new Button("  Retour  ");
        btn.setStyle("-fx-background-color: #ff5050; -fx-text-fill: #FFFFFF; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 15px; -fx-border-color: #FFFFFF; -fx-border-width: 1px; -fx-padding: 5px;");
        btn.setPadding(new Insets(0, 20, 0, 0));// top - right - bottom - left

        Hyperlink hyperlink = new Hyperlink("Accueil");// créer une hyperlien
        Label label = new Label(">");
        Hyperlink hyperlink2 = new Hyperlink("Profils");// créer une hyperlien
        Label label2 = new Label(">");
        Hyperlink hyperlink3 = new Hyperlink("Amaël Maserati");// créer une hyperlien

        hb.getChildren().addAll(btn, hyperlink, label, hyperlink2, label2, hyperlink3);
        hb.setMargin(btn, new Insets(0, 20, 0, 20));// top - right - bottom - left
        hb.setAlignment(Pos.CENTER_LEFT);
        bd.setTop(hb);
    }

    public void ajouteDroite(BorderPane bd) {
        VBox vb = new VBox();
        vb.setSpacing(20);
        vb.getChildren().addAll(boiteDroite(), boiteDroite(), ajouteVoirPLus());
        bd.setRight(vb);
    }

    public GridPane boiteDroite() {
        // Création de la GridPane
        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(10, 10, 10, 10));// top - right - bottom - left
        GridPane.setMargin(gp, new Insets(0, 50, 0, 0));
        // Créartion des Label et de l'image
        Label categorie = new Label("Catégorie");
        Label titre = new Label("Fauteuil");
        Text description = new Text("Ce fauteuil est l'équilibre parfait entre confort, style et sophistication. Revétu d'un luxueux cuir vintage de haute qualit\u00E9, il ajoute une touche d'\u00E9l\u00E9gance intemporelle \u00E0 tout int\u00E9rieur.");
        Label status = new Label("EN COURS");
        Label prix = new Label("200 €");
        ImageView image = new ImageView(new Image("file:./img/fauteuil.jpeg"));
        // STYLE
        status.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #FFFFFF; -fx-background-color: #80CEE1; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 5px;");
        gp.setStyle("-fx-border-color: #D3D3D3; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 5px;");
        description.setStyle("-fx-text-alignment: justify;");
        description.setWrappingWidth(450);
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

        ImageView image = new ImageView(new Image("file:./img/marteau_profil.png"));
        image.setFitHeight(50);
        image.setFitWidth(50);
        image.setPreserveRatio(true);
        image.setSmooth(true);
        image.setCache(true);

        Label titre = new Label("Amaël Maserati", image);
        titre.setStyle("-fx-font-weight: bold; -fx-font-size: 60px; -fx-underline: true;");
        Label type = new Label("Vendeur");
        type.setStyle("-fx-font-size: 25px; -fx-text-fill: #D3D3D3;");

        Label description = new Label("Description");
        Text contenuDescription = new Text(
                "Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assemb et, plus récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker.Le Lorem Ipsum est simplement du faux texte employ\u00E9 dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les ann\u00E9es 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour r\u00E9aliser un livre sp\u00E9cimen de polices de texte. Il n'a pas fait que survivre cinq si\u00E8cles, mais s'est aussi adapt\u00E9 \u00E0 la bureautique informatique, sans que son contenu n'en soit modifi\u00E9. Il a \u00E9t\u00E9 popularis\u00E9 dans les ann\u00E9es 1960 gr\u00E2ce \u00E0 la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus r\u00E9cemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker.");

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
        ImageView image = new ImageView("file:./img/image.png");
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
        contacter.setStyle("-fx-background-color: #00ffff; -fx-text-fill: #FFFFFF; -fx-font-size: 10px; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 5px; -fx-font-size: 20px;");
        contacter.setPadding(new Insets(15, 0, 0, 25));// top - right - bottom - left
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
        
        VBox vbox = new VBox();
        Label email = new Label("Adresse email : ");
        Label telephone = new Label("Téléphone : ");

        HBox hb = new HBox();
        Label emailTxt = new Label(this.email);
        Label telephoneTxt = new Label(this.telephone);
        hb.getChildren().addAll(email, emailTxt);
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