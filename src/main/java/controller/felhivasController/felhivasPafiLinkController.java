package controller.felhivasController;

import felhivasok.FelhivasParser;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import palyazatok.PalyazatiTemak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class felhivasPafiLinkController {
    ArrayList<String> aktualisLista = new ArrayList<String>(Collections.EMPTY_SET);
    PalyazatiTemak palyazatiTemak = new PalyazatiTemak();
    ArrayList<String> maradek = new ArrayList<>(palyazatiTemak.pafiTemaLetolt()); //igy a maradek az osszes lehetseges pafi temat tartalmazza

    @FXML
    private Button kilepesGomb;

    @FXML
    private ListView<String> teljesLista;

    @FXML
    private ListView<String> kivalasztottLista;

//    @FXML
//    private Button hozzaad;
//
//    @FXML
//    private Button elvesz;
//
    @FXML
    private TextField cim;

    @FXML
    private Button feltoltes;

    @FXML
    private void initialize() {
        kivalasztottLista.setPlaceholder(new Label("Legalább egy kategóriát \n ki kell választani!")); //hogy elkeruljuk a Nullpointert
        listaMegjelenito();
        feltoltes.setDisable(true);
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
            uresMezo();
        }
    }

    @FXML
    private void minuszTema() {
        if (!kivalasztottLista.getSelectionModel().getSelectedItems().isEmpty() && !aktualisLista.isEmpty()) {
            aktualisLista.remove(kivalasztottLista.getSelectionModel().getSelectedItem());
            maradek.add(kivalasztottLista.getSelectionModel().getSelectedItem());
            listaMegjelenito();
            uresMezo(); //ezzel biztoitom, hogy a feltoltesgomb aktivalodjon, ha nem ures
        }
    }

    @FXML
    private void listaMegjelenito() {
        maradek.sort(String.CASE_INSENSITIVE_ORDER);
        aktualisLista.sort(String.CASE_INSENSITIVE_ORDER);
        teljesLista.getItems().setAll(maradek);
        kivalasztottLista.getItems().setAll(aktualisLista);
    }

    @FXML
    private void uresMezo() {
        boolean disableButtons = cim.getText().trim().isEmpty() || aktualisLista.isEmpty();
        feltoltes.setDisable(disableButtons);
    }

    @FXML
    private void feltoltes() throws IOException {
        FelhivasParser felhivasParser = new FelhivasParser();
        if (felhivasParser.felhivasLinkbol(cim.getText(), new ArrayList<>(kivalasztottLista.getItems()))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Megerősítés");
            alert.setHeaderText("A felhívást hozzáadtuk az adatbázishoz");
            alert.setContentText("Az OK gombot megnyomva visszatér a felhívások oldalra");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/org/gyula/dialogCSS.css").toExternalForm());
            dialogPane.getStyleClass().add("/org/gyula/dialogCSS.css");
            alert.showAndWait();
            kilep();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Hiba!");
            alert.setHeaderText("Nem sikerült feltölteni a felhívást");
            alert.setContentText("Ellenőrizze az internet kapcsolatot és a bemásolt linket! Az is lehet a hiba oka, hogy a felhívás már benne van az adatbázisba");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/org/gyula/dialogCSS.css").toExternalForm());
            dialogPane.getStyleClass().add("/org/gyula/dialogCSS.css");
            alert.showAndWait();
        }

    }

}
