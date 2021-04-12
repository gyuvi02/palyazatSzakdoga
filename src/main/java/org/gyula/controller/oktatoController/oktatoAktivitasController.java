package org.gyula.controller.oktatoController;

import org.gyula.controller.palyazatController.palyazatReszletekController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.gyula.palyazatok.Palyazat;
import org.gyula.palyazatok.PalyazatLekerdezesek;

import java.io.IOException;
import java.util.ArrayList;

public class oktatoAktivitasController {
//    Oktato kivalasztott = new Oktato();
//    PalyazatLekerdezesek palyazatLekerdezes = new PalyazatLekerdezesek();
    Palyazat kivalasztottPalyazat = new Palyazat();

    @FXML
    private Button kilepesGomb;

    @FXML
    private Button palyazatReszletek;

    @FXML
    private ListView<String> aktivitasLista;

    @FXML
    public void initialize() {

    }


    public void adatTranszfer(String oktato) throws InterruptedException {
        ArrayList<String> lista = new ArrayList<>(PalyazatLekerdezesek.oktatoAktivitasCimek(oktato));
        if (lista.isEmpty()){
            palyazatReszletek.setDisable(true);
            lista.add("Nincs megjeleníthető elem");
        }
        aktivitasLista.getItems().setAll(lista);
        aktivitasLista.getSelectionModel().selectFirst();

    }

    @FXML
    public void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

//    @FXML
//    private void nincsValasztas() {
//        boolean disableButtons = aktivitasLista.getSelectionModel().isEmpty();
//        palyazatReszletek.setDisable(disableButtons);
//    }

    public void nevValaszto() throws IOException {
        kivalasztottPalyazat = kivalasztottPalyazat.PalyazatLetolto(aktivitasLista.getSelectionModel().getSelectedItems().get(0));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/palyazatFXML/palyazatReszletek.fxml"));
        Parent oktatoValasztoParent = loader.load();
        Scene oktatoValasztoScene = new Scene(oktatoValasztoParent);
        palyazatReszletekController controller = loader.getController();
        controller.adatTranszfer(kivalasztottPalyazat.PalyazatLetolto(aktivitasLista.getSelectionModel().getSelectedItems().get(0)));
        Stage stage = new Stage();
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("A pályázat részletes adatai");
        stage.setScene(oktatoValasztoScene);
        stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
//        stage.setX((Screen.getPrimary().getBounds().getMaxX() - oktatoValasztoScene.getWidth())/2);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }
}
