package org.gyula.controller.palyazatController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.gyula.palyazatok.Palyazat;
import org.gyula.palyazatok.PalyazatLekerdezesek;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class palyazatListaController {
    Palyazat palyazat = new Palyazat();

    @FXML
    private Button kilepesGomb;

    @FXML
    private ListView<String> palyazatLista;

    @FXML
    private Button torles;

    @FXML
    private Button kivalasztSzerkesztesre;

    @FXML
    private Button kivalasztReszletekre;

    @FXML
    private Button kivalasztKezdo;

    @FXML
    private void initialize() {
    }

    @FXML
    public void adatTranszfer(ArrayList<String> lista) {
        if (lista.isEmpty()) {
            lista.add("Nincs ilyen pályázat");
            kivalasztReszletekre.setDisable(true);
        }
            palyazatLista.getItems().setAll(lista);
            palyazatLista.getSelectionModel().selectFirst();
    }

    @FXML
    public void adatTranszferOsszes() {
        palyazatLista.getItems().setAll(Palyazat.PalyazatokListaja());
        palyazatLista.getSelectionModel().selectFirst();
    }

    @FXML
    public void kezdoDatumTranszfer(ArrayList<String> lista) {
        if (lista.isEmpty()) {
            lista.add("Nincs ilyen pályázat");
            kivalasztKezdo.setDisable(true);
        }
        palyazatLista.getItems().setAll(lista);
        palyazatLista.getSelectionModel().selectFirst();
    }

    @FXML
    public void adatTranszferTorles() {
        palyazatLista.getItems().setAll(Palyazat.PalyazatokListaja());
        palyazatLista.getSelectionModel().selectFirst();
    }

    @FXML
    public void adatTranszferKpluszF() {
        ArrayList<String> lista = new ArrayList<>(PalyazatLekerdezesek.kPlusFPalyazatok());
        if (lista.isEmpty()) {
            lista.add("Nincs ilyen pályázat");
            kivalasztKezdo.setDisable(true);
        }
        palyazatLista.getItems().setAll(lista);
        palyazatLista.getSelectionModel().selectFirst();
    }

    @FXML
    public void adatTranszferKulcsszo(String kereso) {
        ArrayList<String> lista = new ArrayList<>(PalyazatLekerdezesek.kulcsszavakPalyazat(kereso));
        if (lista.isEmpty()) {
            lista.add("Nincs ilyen pályázat");
            kivalasztKezdo.setDisable(true);
        }
        palyazatLista.getItems().setAll(lista);
        palyazatLista.getSelectionModel().selectFirst();
    }

    @FXML
    public void adatTranszferOnero() {
        ArrayList<String> oneroNelkul = new ArrayList<>(PalyazatLekerdezesek.oneroNelkul());
        if (oneroNelkul.isEmpty()) {
            oneroNelkul.add("Nincs ilyen pályázat");
            kivalasztKezdo.setDisable(true);
        }
        palyazatLista.getItems().setAll(oneroNelkul);
        palyazatLista.getSelectionModel().selectFirst();
    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

    @FXML
    private void palyazatKivalaszto(ActionEvent event) throws IOException {
        Palyazat kivalasztottPalyazat = palyazat.PalyazatLetolto(palyazatLista.getSelectionModel().getSelectedItem());
        FXMLLoader loader = new FXMLLoader();
        if (event.getSource().equals(torles)) {
            megerositesDialog();
            kilep();
        }
        if (event.getSource().equals(kivalasztSzerkesztesre)) {
            palyazatLista.getItems().setAll(Palyazat.PalyazatokListaja());
            palyazatLista.getSelectionModel().selectFirst();
            loader.setLocation(getClass().getResource("/org/gyula/palyazatFXML/palyazatSzerkeszto.fxml"));
            Parent palyazatValasztoParent = loader.load();
            Scene palyazatValasztoScene = new Scene(palyazatValasztoParent);
            palyazatSzerkesztoController controller = loader.getController();
            controller.adatTranszfer(kivalasztottPalyazat);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//            Stage stage = new Stage();
            stage.setTitle("A pályázat szerkesztése");
            stage.setScene(palyazatValasztoScene);
            stage.setX((Screen.getPrimary().getBounds().getMaxX() - palyazatValasztoScene.getWidth())/2);
            stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
            stage.show();
        }
        if (event.getSource().equals(kivalasztReszletekre) || event.getSource().equals("kulcsszoGomb") ||
                event.getSource().equals(kivalasztKezdo)) {
            palyazatLista.getItems().setAll(Palyazat.PalyazatokListaja());
            palyazatLista.getSelectionModel().selectFirst();
            loader.setLocation(getClass().getResource("/org/gyula/palyazatFXML/palyazatReszletek.fxml"));
            Parent palyazatValasztoParent = loader.load();
            Scene palyazatValasztoScene = new Scene(palyazatValasztoParent);
            palyazatReszletekController controller = loader.getController();
            controller.adatTranszfer(kivalasztottPalyazat);
            Stage stage = new Stage();
//            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("A pályázat részletei");
            stage.setScene(palyazatValasztoScene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
            stage.show();
        }
    }

    private void megerositesDialog() {
        String kivalasztottPalyazat = palyazatLista.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Megerősítés");
        alert.setHeaderText("Biztos benne, hogy törli a következő pályázatot:");
        alert.setContentText(palyazatLista.getSelectionModel().getSelectedItem());
        alert.getDialogPane().getScene().getStylesheets().add("org/gyula/dialogCSS.css");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            palyazat.PalyazatTorlo(kivalasztottPalyazat);
            initialize();
        }
    }

}
