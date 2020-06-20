package org.gyula;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class kezdooldalController {

    @FXML
    private Button kilepesGomb;


    @FXML
    private void felhivasFooldalra() throws IOException {
        App.setRoot("/org/gyula/felhivasFXML/felhivasFooldal");
    }

    @FXML
    private void palyazatFooldalra() throws IOException {
        App.setRoot("/org/gyula/palyazatFXML/palyazatFooldal");
    }

    @FXML
    private void oktatoFooldalra() throws IOException {
        App.setRoot("/org/gyula/oktatoFXML/oktatoFooldal");
    }

    @FXML
    private void menuKilepes() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

}
