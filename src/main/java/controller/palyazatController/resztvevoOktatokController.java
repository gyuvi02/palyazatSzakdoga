package controller.palyazatController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import okatok.Oktato;
import okatok.OktatoLekerdezes;
import okatok.OktatoModosito;
import palyazatok.Palyazat;
import palyazatok.PalyazatLekerdezesek;
import palyazatok.PalyazatiTemak;

import java.util.ArrayList;

public class resztvevoOktatokController {
    PalyazatLekerdezesek palyazatLekerdezesek = new PalyazatLekerdezesek();
    OktatoLekerdezes oktatoLekerdezes = new OktatoLekerdezes();
    Palyazat palyazat = new Palyazat();
    ArrayList<String> aktualisLista = new ArrayList<>();
    ArrayList<String> maradek = new ArrayList<>();


    @FXML
    private Button kilepesGomb;

    @FXML
    private Button hozzaad;

    @FXML
    private Button elvesz;

    @FXML
    private ListView<String> teljesLista;

    @FXML
    private ListView<String> resztvevoLista;

    @FXML
    public void adatTranszfer(Palyazat atadottPalyazat) {
        maradek = oktatoLekerdezes.oktatoNevsor("összes");
        palyazat = atadottPalyazat;
        aktualisLista = new ArrayList<>(palyazatLekerdezesek.osszesResztvevo(palyazat.getPalyazatCim()));//itt nem a peldanybol kell kiolvasni, hanem az adatbazisbol
        resztvevoLista.getItems().setAll(aktualisLista);
        maradek.removeAll(aktualisLista);
        teljesLista.getItems().setAll(maradek);
        listaMegjelenito();
    }

    @FXML
    private void pluszOktato() {
        if (!teljesLista.getSelectionModel().getSelectedItems().isEmpty() && !maradek.isEmpty()) {
            maradek.remove(teljesLista.getSelectionModel().getSelectedItem());
            aktualisLista.add(teljesLista.getSelectionModel().getSelectedItem());
            listaMegjelenito();
        }
    }

    @FXML
    private void minuszOktato() {
        if (!resztvevoLista.getSelectionModel().getSelectedItems().isEmpty() && !aktualisLista.isEmpty()) {
            aktualisLista.remove(resztvevoLista.getSelectionModel().getSelectedItem());
            maradek.add(resztvevoLista.getSelectionModel().getSelectedItem());
            listaMegjelenito();
        }
    }

    @FXML
    private void listaMegjelenito() {
        maradek.sort(String.CASE_INSENSITIVE_ORDER);
        aktualisLista.sort(String.CASE_INSENSITIVE_ORDER);
        teljesLista.getItems().setAll(maradek);
        resztvevoLista.getItems().setAll(aktualisLista);
    }

    @FXML
    private void mentes() {
        palyazat.resztvevoFrissito(palyazat.getPalyazatCim(), aktualisLista);
        mentesDialog();
        kilep();
    }

    private void mentesDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mentés");
        alert.setContentText("A pályázat résztvevőinek listáját elmentettük");
        alert.getDialogPane().getScene().getStylesheets().add("org/gyula/dialogCSS.css");
        alert.showAndWait();
    }




    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

}
