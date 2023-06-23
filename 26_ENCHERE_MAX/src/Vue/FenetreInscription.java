package Vue;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class FenetreInscription extends BorderPane {
    
    private Button retour;
    private Button sInscrire;
    private TextField id;
    private TextField email;
    private PasswordField password;
    private PasswordField confirmPassword;
    private Button btnQuitter;
    /**
     * Constructeur de la fenêtre d'inscription
     * @param bouton
     * @param sInscrire
     * @param id2
     * @param email
     * @param password2
     * @param confirmPassword
     * @param btnQuitter
     */
    public FenetreInscription(Button bouton, Button sInscrire,TextField id2,TextField email,PasswordField password2,PasswordField confirmPassword,Button btnQuitter){
        super();
        this.id =id2;
        this.email = email;
        this.password = password2;
        this.confirmPassword = confirmPassword;
        this.retour = bouton;
        this.sInscrire = sInscrire;
        this.btnQuitter = btnQuitter;
          
        this.setCenter(this.center());
    }
    
    /**
     * Méthode qui retourne le centre de la fenêtre d'inscription
     * @return VBox
     */
    private VBox center() {
        VBox vb = new VBox();
        this.setId("fenetreInscription");

        //titre SAE
        HBox titre = new HBox();
        Label lbl = new Label("VAE");
        lbl.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 120));
        lbl.setStyle("-fx-text-fill: #FFFFFF;");
        titre.setAlignment(Pos.CENTER);
        titre.getChildren().add(lbl);
        //

        // Menu
        GridPane menu = new GridPane();
        HBox boxBoutons = new HBox();
        Text textInscription = new Text("INSCRIPTION");
        textInscription.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 30));

        //Position des éléments
        boxBoutons.getChildren().addAll(this.retour,this.sInscrire);
        boxBoutons.setSpacing(50);
        
        menu.add(textInscription,0,0,12,1);
        menu.add(id,0,1,12,1);
        menu.add(this.email,0,2,12,1);
        menu.add(this.password,0,3,12,1);
        menu.add(this.confirmPassword,0,4,12,1);
        menu.add(boxBoutons,0,5,12,1);

        GridPane.setHalignment(textInscription, HPos.CENTER);
        menu.setVgap(20);
        menu.setAlignment(Pos.CENTER);
        menu.setMaxWidth(500);
        menu.setPadding(new Insets(20,50,20,50));

        // Styles des éléments
        this.sInscrire.setFont(Font.loadFont("file:./ressources/fonts/Inter-Bold.ttf", 13)); // style police d'écriture
        this.retour.setFont(Font.loadFont("file:./ressources/fonts/Inter-Bold.ttf", 13)); // style police d'écriture

        this.sInscrire.setId("btnCreerCompte");
        this.retour.setId("btnRetour");
        this.id.setId("id");
        this.email.setId("email");
        this.password.setId("password");
        this.confirmPassword.setId("confirmPassword");

        this.sInscrire.setCursor(Cursor.HAND);
        this.retour.setCursor(Cursor.HAND);
        this.btnQuitter.setCursor(Cursor.HAND);

        menu.setId("menu");

        menu.setAlignment(Pos.CENTER);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(30);
        vb.getChildren().addAll(titre,menu,this.btnQuitter);
        return vb;
    }

}
