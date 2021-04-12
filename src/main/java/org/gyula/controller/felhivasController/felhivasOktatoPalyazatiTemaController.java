package org.gyula.controller.felhivasController;

import org.gyula.felhivasok.FelhivasLekerdezes;
import org.gyula.felhivasok.LegutobbiFelhivasok;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class felhivasOktatoPalyazatiTemaController {
    FelhivasLekerdezes felhivasLekerdezes = new FelhivasLekerdezes();

    @FXML
    private ListView<String> felhivasLista;

    @FXML
    private Button kilepesGomb;

    @FXML
    private Button felhivasValaszto;

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }


    public void adatTranszfer(String nev) {
        felhivasLista.getItems().setAll(felhivasLekerdezes.resztvevokAlapjan(nev));
        felhivasLista.getSelectionModel().select(0);
    }

    public void adatLegutobbi() throws InterruptedException {
        LegutobbiFelhivasok legutobbi = new LegutobbiFelhivasok();
        felhivasLista.getItems().setAll(legutobbi.legutobbiLekerdezes());
        felhivasLista.getSelectionModel().selectFirst();
    }

    public void kulcsszoKereso(String kereso) {
        ArrayList<String> lista = felhivasLekerdezes.kulcsszavakFelhivas(kereso);
        if (lista.isEmpty()) {
            felhivasLista.getItems().setAll("Nem találtam a keresőkifejezésnek megfelelő felhívást");
            felhivasValaszto.setDisable(true);
        } else {
            felhivasLista.getItems().setAll(lista);
            felhivasLista.getSelectionModel().select(0);
        }
    }

    @FXML
    public void adatKategoria(String kategoria) {
        ArrayList<String> lista = felhivasLekerdezes.palyazatiKategoriaAlapjan(kategoria);
        if (lista.isEmpty()) {
            lista.add("Jelenleg nincs ilyen felhívás az adatbázisban");
            felhivasValaszto.setDisable(true);
        }
            felhivasLista.getItems().setAll(lista);
            felhivasLista.getSelectionModel().select(0);

    }

    @FXML
    public void adatDatum(LocalDate datum) {
        felhivasLista.getItems().setAll(felhivasLekerdezes.kesobbiHataridok(datum));

    }

//    @FXML
//    public void osszesFelhivas() {
//        felhivasLista.getItems().setAll(felhivasLekerdezes.felhivasListak());
//        felhivasLista.getSelectionModel().select(0);
//    }

    @FXML
    private void felhivasValaszto() throws IOException, InterruptedException {
        String kivalasztottFelhivas = felhivasLista.getSelectionModel().getSelectedItem().toString();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/felhivasFXML/felhivasReszletek.fxml"));
        Parent felhivasValasztoParent = loader.load();
        Scene felhivasValasztoScene = new Scene(felhivasValasztoParent);
        felhivasReszletekController controller = loader.getController();
        controller.adatTranszfer(kivalasztottFelhivas);
//        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.setTitle("A felhívás részletei - " + kivalasztottFelhivas);
        stage.setScene(felhivasValasztoScene);
//        stage.setX((Screen.getPrimary().getBounds().getMaxX() - felhivasValasztoScene.getWidth())/2);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        stage.show();
    }
}
