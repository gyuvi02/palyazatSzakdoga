package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import okatok.Oktato;
import okatok.OktatoLekerdezes;
import okatok.OktatoModosito;
import org.gyula.App;

import java.io.IOException;

public class oktatoValasztoController {
    OktatoLekerdezes oktatoLekerdezes = new OktatoLekerdezes();
    Oktato kivalasztottOktato = new Oktato();

    @FXML
    ListView oktatoNevek;

    @FXML
    Button kilepesGomb;

    public void initialize() {
        oktatoNevek.getItems().setAll(oktatoLekerdezes.oktatoNevek("összes"));
        oktatoNevek.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

    @FXML
    public void nevValaszto() throws IOException {
        kilep();
        oktatoModositoController modosito = new oktatoModositoController();
        modosito.oktatoAtado((Oktato) kivalasztottOktato.oktatoLetolto(oktatoNevek.getSelectionModel().getSelectedItems().get(0).toString()));

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Pályázatkezelő program");
        stage.setScene(new Scene(App.loadFXML("/org/gyula/oktatoFXML/oktatoModosito")));
        stage.show();

    }


}
