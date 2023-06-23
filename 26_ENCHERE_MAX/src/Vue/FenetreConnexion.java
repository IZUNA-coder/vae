package Vue;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

//imports pour observer la checkbox
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class FenetreConnexion extends BorderPane {
    
    private Button btnConnexion;
    private Button btnQuitter;
    private Button btnInscrire;
    private TextField id;
    private PasswordField password;
    private TextField passwordMontrer;
    private CheckBox showPassword;
    private Button btnFullscreen;
    /**
     * Constructeur de la fenêtre de connexion
     * @param btnConnexion
     * @param btnQuitter
     * @param btnInscire
     * @param id
     * @param password
     * @param passwordMontrer
     * @param showPassword
     * @param btnFullscreen
     */
    public FenetreConnexion(Button btnConnexion, Button btnQuitter, Button btnInscire,TextField id,PasswordField password,TextField passwordMontrer,CheckBox showPassword,Button btnFullscreen){
        super();
        this.id = id;
        this.password = password;
        this.btnConnexion = btnConnexion;
        this.btnQuitter = btnQuitter;
        this.btnInscrire = btnInscire;
        this.passwordMontrer =passwordMontrer;
        this.showPassword=showPassword;
        this.btnFullscreen=btnFullscreen;
          
        this.setCenter(this.center());

        // Ajouter un écouteur d'événements sur la case à cocher
        showPassword.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                // Mettre à jour le texte du PasswordField
                if (newValue) {
                    // Montrer mdp
                    String text = password.getText();
                    password.setVisible(false);
                    passwordMontrer.setVisible(true);
                    passwordMontrer.setText(text);
                } else {
                    // Cacher mdp
                    String text = passwordMontrer.getText();
                    password.setVisible(true);
                    passwordMontrer.setVisible(false);
                    password.setText(text);
                }
            }
        });
    }
    
    /**
     * Méthode qui retourne le centre de la fenêtre de connexion
     * @return VBox
     */
    private VBox center() {
        VBox vb = new VBox();
        this.setId("fenetreConnexion");

        //titre SAE
        HBox titre = new HBox();
        Label lbl = new Label("VAE");
        lbl.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 120));
        lbl.setStyle("-fx-text-fill: #FFFFFF;");
        titre.setAlignment(Pos.CENTER);
        titre.getChildren().add(lbl);

        // Menu
        GridPane menu = new GridPane();
        HBox boxBoutons = new HBox();
        Text textConnexion = new Text("CONNEXION");
        textConnexion.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 30));

        //Position des éléments
        boxBoutons.getChildren().addAll(this.btnInscrire,this.btnConnexion);
        boxBoutons.setSpacing(50);

        menu.add(textConnexion,0,0,12,1);
        menu.add(this.id,0,1,12,1);
        menu.add(this.password,0,2,12,1);
        menu.add(this.passwordMontrer,0,2,12,1);
        menu.add(this.showPassword,0,3);
        menu.add(boxBoutons,0,4,12,1);

        GridPane.setHalignment(textConnexion, HPos.CENTER);
        menu.setVgap(20);
        menu.setAlignment(Pos.CENTER);
        menu.setMaxWidth(500);
        menu.setPadding(new Insets(20,0,20,0));

        // Styles des éléments
        this.btnConnexion.setFont(Font.loadFont("file:./ressources/fonts/Inter-Bold.ttf", 13)); // style police d'écriture
        this.btnInscrire.setFont(Font.loadFont("file:./ressources/fonts/Inter-Bold.ttf", 13)); // style police d'écriture
        this.btnQuitter.setFont(Font.loadFont("file:./ressources/fonts/Inter-Bold.ttf", 13)); // style police d'écriture

        this.btnConnexion.setId("btnConnexion");
        this.btnInscrire.setId("btnInscrire");
        this.btnQuitter.setId("btnQuitter");
        this.id.setId("id");
        this.password.setId("password");
        this.passwordMontrer.setId("passwordShow");
        this.showPassword.setId("checkbox");
        menu.setId("menu");

        this.btnConnexion.setCursor(Cursor.HAND);
        this.btnInscrire.setCursor(Cursor.HAND);
        this.btnQuitter.setCursor(Cursor.HAND);
        //

        ImageView imgFullscreen = new ImageView(new Image("file:ressources/img/fullscreen.png"));
        imgFullscreen.setId("imgFullscreen");
        imgFullscreen.setFitWidth(30);
        imgFullscreen.setFitHeight(30);

        this.btnFullscreen.setGraphic(imgFullscreen);
        this.btnFullscreen.setId("btnFullscreen");
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(30);
        vb.getChildren().addAll(titre,menu,this.btnQuitter,this.btnFullscreen);
        return vb;
    }

}
