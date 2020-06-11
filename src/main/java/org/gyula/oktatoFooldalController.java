package org.gyula;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


import java.io.IOException;

public class oktatoFooldalController {
    @FXML
    Button visszaOktatokrol;

    @FXML
    private void visszaKezdooldalra() throws IOException {
        App.setRoot("kezdooldal");
    }

}
