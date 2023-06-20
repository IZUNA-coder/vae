package Vue;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;

public class FenetreProfil extends BorderPane {
    private BorderPane coteGauche;
    private BorderPane coteDroit;
    private Button btnVAE;

    public FenetreProfil(Button btnVAE){
        super();
        this.btnVAE = btnVAE;
        this.init();
        laPage();
        this.coteGauche.setTop(this.hautCoteGauche());
        this.coteGauche.setCenter(this.centreCoteGauche());
        this.setLeft(this.coteGauche);
        this.setRight(this.coteDroit);
    }

    public void init() {
        this.coteGauche = new BorderPane();
        this.coteGauche.setPrefWidth(700);
        this.coteDroit = new BorderPane();
    }
    public BorderPane hautCoteGauche() {
        BorderPane top = new BorderPane();
        Label pseudo = new Label("Amael");
        pseudo.setFont(new Font("file:./ressources/fonts/PlayfairDisplay.ttf", 70));
        top.setLeft(btnVAE);
        top.setRight(pseudo);
        return top;
    }
    public HBox premiereLigneInfosCentre() {
        HBox ligne = new HBox();
        ligne.setSpacing(10);
        ligne.setPadding(new Insets(10));
        Label identifiant = new Label("Identifiant      ");
        identifiant.setFont(new Font("file:./ressources/fonts/PlayfairDisplay.ttf", 15));
        TextField tfId = new TextField("Amaël");
        tfId.setFont(new Font("file:./ressources/fonts/PlayfairDisplay.ttf", 15));
        identifiant.setLabelFor(tfId);
        Button modifier = new Button("MODIFIER");
        modifier.setFont(new Font("file:./ressources/fonts/PlayfairDisplay.ttf", 15));
        modifier.setStyle("-fx-background-color: #51E679; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-weight: bolder; -fx-font-size: 15px;");
        ligne.getChildren().addAll(identifiant, tfId, modifier);
        return ligne;
    }
    public HBox deuxiemeLigneInfosCentre() {
        HBox ligne = new HBox();
        ligne.setSpacing(10);
        ligne.setPadding(new Insets(10));
        Label mdp = new Label("Mot de passe ");
        mdp.setFont(new Font("file:./ressources/fonts/PlayfairDisplay.ttf", 15));
        TextField tfId = new TextField("********");
        tfId.setFont(new Font("file:./ressources/fonts/PlayfairDisplay.ttf", 15));
        mdp.setLabelFor(tfId);
        Button modifier = new Button("MODIFIER");
        modifier.setFont(new Font("file:./ressources/fonts/PlayfairDisplay.ttf", 15));
        modifier.setStyle("-fx-background-color: #51E679; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-weight: bolder; -fx-font-size: 15px;");
        ligne.getChildren().addAll(mdp, tfId, modifier);
        return ligne;
    }
    public HBox troisiemeLigneInfosCentre() {
        HBox ligne = new HBox();
        ligne.setSpacing(10);
        ligne.setPadding(new Insets(10));
        Label mail = new Label("Mail                ");
        mail.setFont(new Font("file:./ressources/fonts/PlayfairDisplay.ttf", 15));
        TextField tfId = new TextField("amaelm45@gmail.com");
        tfId.setFont(new Font("file:./ressources/fonts/PlayfairDisplay.ttf", 15));
        mail.setLabelFor(tfId);
        Button modifier = new Button("MODIFIER");
        modifier.setFont(new Font("file:./ressources/fonts/PlayfairDisplay.ttf", 15));
        modifier.setStyle("-fx-background-color: #51E679; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-weight: bolder; -fx-font-size: 15px;");
        ligne.getChildren().addAll(mail, tfId, modifier);
        return ligne;
    }
    public BorderPane quatriemeLigneInfosCentre() {
        BorderPane ligne = new BorderPane();
        Button supprimer = new Button("SUPPRIMER MON COMPTE");
        supprimer.setFont(new Font("file:./ressources/fonts/PlayfairDisplay.ttf", 15));
        supprimer.setStyle("-fx-background-color: #E67651; -fx-text-fill: #FFFFFF; -fx-background-radius: 10px; -fx-font-weight: bolder; -fx-font-size: 15px;");
        ligne.setRight(supprimer);
        return ligne;
    }
    public VBox informationsUtilisateur() {
        VBox infos = new VBox();
        infos.setSpacing(10);
        infos.setPadding(new Insets(10));
        infos.getChildren().addAll(this.premiereLigneInfosCentre(), this.deuxiemeLigneInfosCentre(), this.troisiemeLigneInfosCentre(), this.quatriemeLigneInfosCentre());
        infos.setStyle("-fx-background-color: #D3D3D3; -fx-background-radius: 10px; -fx-opacity: 0.8;");
        return infos;
    }
    public VBox centreCoteGauche() {
        VBox centre = new VBox();
        centre.setSpacing(10);
        centre.setPadding(new Insets(10));
        Text titreCentre = new Text("Vos informations personnelles");
        titreCentre.setFont(new Font("file:./ressources/font/sPlayfairDisplay.ttf", 20));
        centre.getChildren().addAll(titreCentre, this.informationsUtilisateur());
        centre.setPadding(new Insets(70, 0, 0, 70));
        return centre;
    }
    public BorderPane coteGauchePage() {
        this.coteGauche.setTop(this.hautCoteGauche());
        this.coteGauche.setCenter(this.centreCoteGauche());
        return this.coteGauche;
    }
    public HBox contientEngrenage() {
        HBox contientEngrenage = new HBox();
        ImageView engrenage = new ImageView(new Image("file:./ressources/img/engrenage.png"));
        contientEngrenage.setAlignment(Pos.CENTER);
        contientEngrenage.getChildren().add(engrenage);
        engrenage.setFitHeight(80);
        engrenage.setFitWidth(80);
        contientEngrenage.setPadding(new Insets(20, 0, 0, 0));
        return contientEngrenage;
    }
    public VBox boutonsDuMilieu() {
        VBox boutons = new VBox();
        boutons.setSpacing(20);
        Button bouton1 = new Button("VOS ENCHERES");
        bouton1.setStyle("-fx-background-color: #51D1E6; -fx-text-fill: #FFFFFF; -fx-background-radius: 30px; -fx-font-weight: bolder; -fx-font-size: 15px;");
        Button bouton2 = new Button("FAVORIS");
        bouton2.setStyle("-fx-background-color: #51D1E6; -fx-text-fill: #FFFFFF; -fx-background-radius: 30px; -fx-font-weight: bolder; -fx-font-size: 15px;");
        Button bouton3 = new Button("VOS VENTES");
        bouton3.setStyle("-fx-background-color: #51D1E6; -fx-text-fill: #FFFFFF; -fx-background-radius: 30px; -fx-font-weight: bolder; -fx-font-size: 15px;");
        Button bouton4 = new Button("PARAMETRES");
        bouton4.setStyle("-fx-background-color: #51D1E6; -fx-text-fill: #FFFFFF; -fx-background-radius: 30px; -fx-font-weight: bolder; -fx-font-size: 15px;");
        boutons.getChildren().addAll(bouton1, bouton2, bouton3, bouton4);
        boutons.setPadding(new Insets(70, 0, 0, 0));
        return boutons;
    }
    public Button deconnexion() {
        Button deconnexion = new Button("DECONNEXION");
        deconnexion.setStyle("-fx-background-color: #E65151; -fx-text-fill: #FFFFFF; -fx-background-radius: 30px; -fx-font-weight: bolder; -fx-font-size: 15px;");
        deconnexion.setPadding(new Insets(10));
        return deconnexion;
    }
    public BorderPane coteDroitPage() {
        this.coteDroit.setStyle("-fx-background-color: #F0F0F2;");       
        this.coteDroit.setTop(this.contientEngrenage());
        this.coteDroit.setCenter(this.boutonsDuMilieu());
        this.coteDroit.setBottom(this.deconnexion());
        this.coteDroit.setPadding(new Insets(0, 0, 30, 0));
        this.coteDroit.setPrefWidth(200);
        return this.coteDroit;
    }
    public void laPage() {
        this.setLeft(this.coteGauchePage());
        this.setRight(this.coteDroitPage());
    }
}
