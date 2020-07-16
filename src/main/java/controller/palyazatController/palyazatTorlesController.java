package controller.palyazatController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import palyazatok.Palyazat;

public class palyazatTorlesController {

    @FXML
    private Button kilepesGomb;

    @FXML
    private ListView<String> palyazatLista;

    @FXML
    private void initialize() {
        palyazatLista.getItems().setAll(Palyazat.PalyazatokListaja());
        palyazatLista.getSelectionModel().selectFirst();
    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

    private void palyazatTorleshez(){

    }

}
