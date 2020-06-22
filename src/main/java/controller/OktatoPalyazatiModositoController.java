package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import okatok.Oktato;
import okatok.OktatoModosito;
import palyazatok.PalyazatiTemak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class OktatoPalyazatiModositoController {
    Oktato aktualisOktato = new Oktato();
    ArrayList<String> aktualisLista = new ArrayList<>();
    PalyazatiTemak palyazatiTemak = new PalyazatiTemak();
    ArrayList<String> maradek = new ArrayList<>(palyazatiTemak.temaLetolt());

    @FXML
    private Button kilepesGomb;

    @FXML
    private ListView<String> oktatoLista;

    @FXML
    private ListView<String> teljesLista;

    @FXML
    private Label oktatoNev;

    @FXML
    private Button hozzaad;

    @FXML
    private Button elvesz;

    public void initialize() {
    }

    @FXML
    public void pTemaTranszfer(Oktato oktato) {
        aktualisOktato = oktato;
        aktualisLista = new ArrayList<>(oktato.getPalyazatiTema());
//        oktatoLista.getItems().setAll(aktualisLista);
        oktatoNev.setText(oktato.getNev() + "\n pályázati témái");
        oktatoLista.getItems().setAll(aktualisLista);
        maradek.removeAll(aktualisLista);
//        teljesLista.getItems().setAll(maradek);
        listaMegjelenito();
    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }
    @FXML
    private void pluszTema() {
        if (!teljesLista.getSelectionModel().getSelectedItems().isEmpty() && !maradek.isEmpty()) {
            maradek.remove(teljesLista.getSelectionModel().getSelectedItem());
            aktualisLista.add(teljesLista.getSelectionModel().getSelectedItem());
            listaMegjelenito();
        }
    }

    @FXML
    private void minuszTema() {
        if (!oktatoLista.getSelectionModel().getSelectedItems().isEmpty() && !aktualisLista.isEmpty()) {
            aktualisLista.remove(oktatoLista.getSelectionModel().getSelectedItem());
            maradek.add(oktatoLista.getSelectionModel().getSelectedItem());
            listaMegjelenito();
        }
    }

    @FXML
    private void listaMegjelenito() {
        maradek.sort(String.CASE_INSENSITIVE_ORDER);
        aktualisLista.sort(String.CASE_INSENSITIVE_ORDER);
        teljesLista.getItems().setAll(maradek);
        oktatoLista.getItems().setAll(aktualisLista);
    }

    @FXML
    private void mentes() {
        OktatoModosito modosito = new OktatoModosito();
        modosito.tombFrissito("palyazatiTema", aktualisOktato.getNev(), aktualisLista );
        mentesDialog();
        kilep();
    }

    private void mentesDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mentés");
        alert.setHeaderText(aktualisOktato.getNev() );
        alert.setContentText("pályázati témáit elmentettem");
        alert.getDialogPane().getScene().getStylesheets().add("org/gyula/dialogCSS.css");
        alert.showAndWait();
    }


}
