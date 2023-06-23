package Vue.Administration;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
 
public class FenetreGestionContrats extends BorderPane {
    
    private Button btnDeconnexion;
    private Button btnRetourAdmin;
    
    public FenetreGestionContrats(Button boutonDeco, Button btnRetourAdmin){
        super();
        this.btnDeconnexion = boutonDeco;
        this.btnRetourAdmin = btnRetourAdmin;

        this.btnDeconnexion.setCursor(Cursor.HAND);
        this.btnRetourAdmin.setCursor(Cursor.HAND);
        
        this.setTop(this.enHaut());     
        this.setCenter(this.center());
    }
    
    private BorderPane enHaut(){
        BorderPane bp = new BorderPane();
        Text logo = new Text("VAE");
        logo.setId("titreVAE");
        logo.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 80));

        Text titre = new Text("Gestion des contrats");
        titre.setFont(Font.loadFont("file:./ressources/fonts/PlayfairDisplay.ttf", 50   ));
        titre.setId("titreModuleAdmin");

        this.btnDeconnexion.setId("btnDeconnexion");

        this.btnRetourAdmin.setId("btnRetourAdmin");

        bp.setLeft(logo);
        bp.setCenter(titre);
        bp.setRight(this.btnDeconnexion);
        bp.setStyle("-fx-background-color:#51D1E6");
        bp.setPadding(new Insets(40,20,40,20));
        BorderPane.setAlignment(this.btnDeconnexion, Pos.CENTER);
        BorderPane.setMargin(logo, new Insets(10,30,10,10));
        BorderPane.setMargin(this.btnDeconnexion, new Insets(10,10,10,30));

        return bp;
    }

    private BorderPane center() {
        BorderPane center = new BorderPane();
        center.setPadding(new Insets(20,20,20,20));
        center.setLeft(this.btnRetourAdmin);
        return center;
    }
}
