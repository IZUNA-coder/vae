package Controller;

import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.event.ActionEvent ;


public class ControllerBtnFullscreen implements EventHandler<ActionEvent>{
    
    private Stage stage;
    
    public ControllerBtnFullscreen(Stage stage){
        this.stage = stage;
    }
    
    @Override
    public void handle(ActionEvent event){
        System.out.println("Bouton Fullscreen cliqué");
        if(stage.isFullScreen()){
            this.stage.setFullScreen(false);
        }else{
            this.stage.setFullScreen(true);
        }
    }
}
