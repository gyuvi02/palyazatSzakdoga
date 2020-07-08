package controller.oktatoController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import okatok.OktatoLekerdezes;

public class oktatoListaController {

    @FXML
    private Button kilepesGomb;

    @FXML
    private ListView nevLista;

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }


    public void adatTranszfer(String tema) {
        OktatoLekerdezes oktatoLekerdezes = new OktatoLekerdezes();
        nevLista.getItems().setAll(oktatoLekerdezes.palyazatiTemaKereso(tema));
    }
}
