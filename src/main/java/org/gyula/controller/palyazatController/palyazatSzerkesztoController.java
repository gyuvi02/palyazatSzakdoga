package org.gyula.controller.palyazatController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.gyula.palyazatok.Palyazat;

import java.io.IOException;

public class palyazatSzerkesztoController {
    Palyazat palyazat = new Palyazat();

//    String fx = "palyazatSzerkeszto2";
//
//    @FXML
//    private Button egyKetto;
//
//    @FXML
//    private Button kettoHarom;
//
//    @FXML
//    private Button haromKetto;
//
//    @FXML
//    private Button kettoEgy;
//
    @FXML
    private TextField cimField;

    @FXML
    private ComboBox<String> fazisModosito;

    @FXML
    private TextArea leirasArea;

    @FXML
    private TextField kodField;

    @FXML
    private TextField beadasField;

    @FXML
    private ComboBox<String> kfBox;

    @FXML
    private Button kilepesGomb;

    @FXML
    private Button mentes1;

    @FXML
    private void initialize() {
    }

    @FXML
    private void uresMezo() {
        boolean disableButtons = cimField.getText().trim().isEmpty();
        mentes1.setDisable(disableButtons);
    }

    @FXML
    public void adatTranszfer(Palyazat atadottPalyazat){
        palyazat = atadottPalyazat;
        cimField.setText(palyazat.getPalyazatCim());
        fazisModosito.setValue(palyazat.getAktualisFazis());
        leirasArea.setText(palyazat.getLeiras());
        leirasArea.setWrapText(true);
        kodField.setText(palyazat.getFelhivasKod());
        beadasField.setText(palyazat.getBeadasEve());
        kfBox.setValue(palyazat.getKplusF());

        cimField.setCursor(Cursor.DEFAULT);
        leirasArea.setCursor(Cursor.DEFAULT);
        kodField.setCursor(Cursor.DEFAULT);
        beadasField.setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void elsoScene(ActionEvent event) throws IOException {
        mezoUpdate();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/palyazatFXML/palyazatSzerkeszto2.fxml"));
        Parent palyazatValasztoParent = loader.load();
        Scene palyazatSzerkeszto2Scene = new Scene(palyazatValasztoParent);
        palyazatSzerkeszto2Controller controller = loader.getController();
        controller.adatTranszfer(palyazat);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(palyazatSzerkeszto2Scene);
        stage.setTitle("A pályázat szerkesztése");
        stage.setScene(palyazatSzerkeszto2Scene);
        stage.setX((Screen.getPrimary().getBounds().getMaxX() - palyazatSzerkeszto2Scene.getWidth())/2);
        stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        stage.show();

    }

    private void mezoUpdate() {
        palyazat.setPalyazatCim(cimField.getText());
        palyazat.setAktualisFazis(fazisModosito.getValue());
        palyazat.setLeiras(leirasArea.getText());
        palyazat.setFelhivasKod(kodField.getText());
        palyazat.setBeadasEve(beadasField.getText());
        palyazat.setKplusF(kfBox.getValue());
    }

    @FXML
    private void palyazatMentes() {
        mezoUpdate();
        palyazat.PalyazatFrissito();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Megerősítés");
        alert.setHeaderText("Módosítottuk a következő pályázatot:");
        alert.setContentText(palyazat.getPalyazatCim());
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/org/gyula/dialogCSS.css").toExternalForm());
        dialogPane.getStyleClass().add("/org/gyula/dialogCSS.css");
        alert.showAndWait();
        kilep();
    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

}
