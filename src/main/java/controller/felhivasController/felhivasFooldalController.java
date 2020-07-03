package controller.felhivasController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.gyula.App;

import java.io.IOException;

public class felhivasFooldalController {

    @FXML
    Button visszaFelhivasrol;

    @FXML
    Button kilepesGomb;

    @FXML
    private void visszaKezdooldalra() throws IOException {
        App.setRoot("kezdooldal");
    }

    @FXML
    private void menuKilepes() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

}
