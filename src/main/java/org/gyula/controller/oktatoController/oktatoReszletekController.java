package org.gyula.controller.oktatoController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.gyula.okatok.Oktato;

public class oktatoReszletekController {

    Oktato kivalasztott = new Oktato();

    @FXML
    private Button kilepesGomb;

    @FXML
    public TextArea reszletek;

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

    public void adatTranszfer(Oktato oktato) {
        kivalasztott = oktato;
        reszletek.setText(kivalasztott.toString());
        reszletek.setWrapText(true);
    }


}
