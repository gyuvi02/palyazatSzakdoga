package controller.palyazatController;

import controller.felhivasController.felhivasOktatoPalyazatiTemaController;
import controller.felhivasController.felhivasReszletekController;
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
import javafx.stage.Screen;
import javafx.stage.Stage;
import palyazatok.Palyazat;

import java.io.IOException;
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
    private Button kivalsztSzerkesztesre;

    @FXML
    private void initialize() {
        palyazatLista.getItems().setAll(Palyazat.PalyazatokListaja());
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

        if (event.getSource().equals(torles)) {
            megerositesDialog();
            kilep();
        }
        if (event.getSource().equals(kivalsztSzerkesztesre)) {
            FXMLLoader loader = new FXMLLoader();
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
