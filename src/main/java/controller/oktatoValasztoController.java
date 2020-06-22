package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
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
    ListView<String> oktatoNevek;

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
    public void nevValaszto(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/oktatoFXML/oktatoModosito.fxml"));
        Parent oktatoValasztoParent = loader.load();

        Scene oktatoValasztoScene = new Scene(oktatoValasztoParent);

        oktatoModositoController controller = loader.getController();
        controller.adatTranszfer(kivalasztottOktato.oktatoLetolto(oktatoNevek.getSelectionModel().getSelectedItems().get(0).toString()));

        kilep();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Oktatói adatok módosítása");
        stage.setScene(oktatoValasztoScene);
        stage.show();
    }


}
