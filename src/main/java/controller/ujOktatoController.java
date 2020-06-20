package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import okatok.Oktato;
import org.gyula.App;

import java.io.IOException;
import java.util.ArrayList;

public class ujOktatoController {

    @FXML
    Button hozzaadGomb;

    @FXML
    Button kilepesGomb;

    @FXML
    TextField oNev;

    @FXML
    ComboBox<String> oTanszek;

    @FXML
    TextField oEmail;

    @FXML
    public void initialize() {
        hozzaadGomb.setDisable(true);
    }

    @FXML
    private void visszaOktatooldalra() throws IOException {
        App.setRoot("/org/gyula/oktatoFXML/oktatoFooldal");
    }

    @FXML
    private void uresMezo() {
        boolean disableButtons = oNev.getText().trim().isEmpty() || oEmail.getText().trim().isEmpty();
        hozzaadGomb.setDisable(disableButtons);
    }

    @FXML
    private void hozzaad() throws IOException{
        Oktato ujOktato = new Oktato(oNev.getText(), oTanszek.getValue(), new ArrayList<>(), oEmail.getText(),
                "", new ArrayList<>());
        if (ujOktato.oktatoFeltolto()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Megerősítés");
            alert.setHeaderText(oNev.getText() + " hozzáadva az oktatókhoz");
            alert.setContentText("Az OK gombot megnyomva visszatér az oktatói oldalra");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/org/gyula/dialogCSS.css").toExternalForm());
            dialogPane.getStyleClass().add("/org/gyula/dialogCSS.css");
            alert.showAndWait();
            visszaOktatooldalra();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Hibás email");
            alert.setHeaderText("A " + oEmail.getText() + " email cím már szerepel az adatbázisban");
            alert.setContentText("Az OK gombot megnyomva javíthatja az email címet");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/org/gyula/dialogCSS.css").toExternalForm());
            dialogPane.getStyleClass().add("/org/gyula/dialogCSS.css");
            alert.showAndWait();
        }
    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }



}
