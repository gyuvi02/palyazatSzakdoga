package controller.palyazatController;

import controller.felhivasController.felhivasOktatoPalyazatiTemaController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import palyazatok.PalyazatLekerdezesek;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class palyazatKezdoDatumController {
    String kezdoE;

    @FXML
    private Button kilepesGomb;

    @FXML
    private Button kereses;

    @FXML
    private Label datumLabel;

    @FXML
    private DatePicker elsoDatum;

    @FXML
    private DatePicker masodikDatum;


    @FXML
    private void initialize() {
    }

    @FXML
    public void adatTranszferKezdo(String felirat, String str) throws IOException {
        elsoDatum.setValue(LocalDate.now());
        masodikDatum.setValue(LocalDate.now());
        datumLabel.setText("A két megadott dátum között " + felirat + " pályázatokat tudjuk lekérdezni");
        kezdoE = str;
    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

    @FXML
    private void kezdoKereses() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/palyazatFXML/palyazatListaKezdo.fxml"));
        Parent palyazatKezdoParent = loader.load();
        Scene palyazatKezdoScene = new Scene(palyazatKezdoParent);
        palyazatListaController controller = loader.getController();
        if (kezdoE.equals("kezdo")) {
            controller.kezdoDatumTranszfer(PalyazatLekerdezesek.kezdoEvPeriodus(elsoDatum.getValue(), masodikDatum.getValue()));
        }else {
            controller.kezdoDatumTranszfer(PalyazatLekerdezesek.vegeEvPeriodus(elsoDatum.getValue(), masodikDatum.getValue()));
        }
//        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.setTitle("A keresésnek megfelelő felhívások");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(palyazatKezdoScene);
        stage.show();
        kilep();
    }

}
