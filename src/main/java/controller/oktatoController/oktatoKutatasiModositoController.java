package controller.oktatoController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import okatok.Oktato;
import okatok.OktatoModosito;

import java.util.ArrayList;

public class oktatoKutatasiModositoController {
    Oktato aktualisOktato = new Oktato();
    ArrayList<String> aktualisLista = new ArrayList<>();

    @FXML
    private Button kilepesGomb;

    @FXML
    private ListView<String> kutatasiLista;

    @FXML
    private Label nev;

    @FXML
    private TextField ujElem;

    public void initialize() {

    }

    @FXML
    public void kTemaTranszfer(Oktato oktato) {
        aktualisOktato = oktato;
//        aktualisLista = new ArrayList<String>(oktato.getKutatasiTema());
        aktualisLista = new ArrayList<>(oktato.oktatoLetolto(oktato.getNev()).getKutatasiTema());
        kutatasiLista.getItems().setAll(aktualisLista);
        nev.setText(oktato.getNev());
    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

    @FXML
    private void torles() {
        aktualisLista.remove(kutatasiLista.getSelectionModel().getSelectedItem());
        kutatasiLista.getItems().setAll(aktualisLista); //nem a legelegansabb megoldas, ObservableList jobb lenne, de itt egyszeruen megoldhato igy is
    }

    @FXML
    private void hozzaad() {
        aktualisLista.add(ujElem.getText().toLowerCase());
        kutatasiLista.getItems().setAll(aktualisLista);
        ujElem.clear();
    }

    @FXML
    private void mentes() {
        OktatoModosito modosito = new OktatoModosito();
        modosito.tombFrissito("kutatasiTema", aktualisOktato.getNev(), aktualisLista );
        mentesDialog();
        kilep();
    }

    private void mentesDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mentés");
        alert.setHeaderText(aktualisOktato.getNev() );
        alert.setContentText("kutatási témáit elmentettük");
        alert.getDialogPane().getScene().getStylesheets().add("org/gyula/dialogCSS.css");
        alert.showAndWait();
    }


}
