package org.gyula.controller.oktatoController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.gyula.okatok.OktatoLekerdezes;

public class oktatoTanszekiKutatasokController {

    @FXML
    private Button kilepesGomb;

    @FXML
    private ListView<String> kutatasiLista;

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }


    public void adatTranszfer(String tanszek, String tomb) {
        OktatoLekerdezes oktatoLekerdezes = new OktatoLekerdezes();
        kutatasiLista.getItems().setAll(oktatoLekerdezes.kutatasiTemak(tanszek, tomb));
    }

}
