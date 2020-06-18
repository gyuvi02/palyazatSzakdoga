package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import okatok.Oktato;

public class oktatoModositoController {

    Oktato kivalasztott = new Oktato();

    @FXML
    Button kilepesGomb;

    public void initialize() {
    }

    @FXML
    public void oktatoAtado(Oktato oktato) {
        kivalasztott = oktato;
        initialize();
    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

}
