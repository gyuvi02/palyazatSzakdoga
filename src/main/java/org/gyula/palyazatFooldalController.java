package org.gyula;

import java.io.IOException;
import javafx.fxml.FXML;

public class palyazatFooldalController {

    @FXML
    private void visszaKezdooldalra() throws IOException {
        App.setRoot("kezdooldal");
    }
}