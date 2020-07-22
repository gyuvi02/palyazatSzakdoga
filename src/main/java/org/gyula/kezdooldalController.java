package org.gyula;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.util.Duration;


public class kezdooldalController {

    @FXML
    private Button kilepesGomb;


    @FXML
    private void felhivasFooldalra() throws Exception {
        App.setRoot("/org/gyula/felhivasFXML/felhivasFooldal");
    }

    @FXML
    private void palyazatFooldalra() throws Exception {
        App.setRoot("/org/gyula/palyazatFXML/palyazatFooldal");
    }

    @FXML
    private void oktatoFooldalra() throws Exception {
        App.setRoot("/org/gyula/oktatoFXML/oktatoFooldal");
    }


    @FXML
    private void kilepes() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        App.exit();
//        PauseTransition delay = new PauseTransition(Duration.seconds(1)); //1 masodperc a kesleletetes
//        delay.setOnFinished( event -> ablak.close());
//        delay.play();
        ablak.close();
    }

}
