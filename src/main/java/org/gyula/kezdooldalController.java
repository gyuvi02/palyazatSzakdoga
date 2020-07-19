package org.gyula;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


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
        ablak.close();
    }

}
