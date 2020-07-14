package controller.oktatoController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import okatok.Oktato;
import okatok.OktatoModosito;


import java.io.IOException;


public class oktatoModositoController {

    Oktato kivalasztott = new Oktato();

    @FXML
    Button kilepesGomb;

    @FXML
    private  TextField oktatoNev;

    @FXML
    private TextField oktatoEmail;

    @FXML
    private TextField oktatoHonlap;

    @FXML
    ComboBox<String> oktatoTanszek;

    public void initialize() {


    }

    public void adatTranszfer(Oktato oktato) {
        kivalasztott = oktato;
        oktatoNev.setText(oktato.getNev());
        oktatoEmail.setText(oktato.getEmail());
        oktatoHonlap.setText(oktato.getHonlap());
        oktatoTanszek.setValue(oktato.getTanszek());
    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

    @FXML
    private void mentes() {
        OktatoModosito oktatoModosito = new OktatoModosito();
        oktatoModosito.oktatoUjNev(kivalasztott.getNev(), oktatoNev.getText());
        oktatoModosito.oktatoUjTanszek(kivalasztott.getNev(), oktatoTanszek.getSelectionModel().getSelectedItem());
        oktatoModosito.oktatoUjEmail(kivalasztott.getNev(), oktatoEmail.getText());
        oktatoModosito.oktatoUjHonlap(kivalasztott.getNev(), oktatoHonlap.getText());
        mentesDialog();
        kilep();
    }

    private void mentesDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mentés");
        alert.setHeaderText(kivalasztott.getNev());
        alert.setContentText("adatainak módosítását elmentettem");
        alert.getDialogPane().getScene().getStylesheets().add("org/gyula/dialogCSS.css");
        alert.showAndWait();
    }


    @FXML
    private void kutatasiTemak(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/oktatoFXML/oktatoKutatasiModosito.fxml"));
        Parent oktatoKTemaParent = loader.load();

        Scene oktatoKutatasiScene = new Scene(oktatoKTemaParent);

        oktatoKutatasiModositoController controller2 = loader.getController();
        controller2.kTemaTranszfer(kivalasztott);
//        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //ha ugyanazon a Stage-en akarom megjeleniteni
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Kutatási témák módosítása");
        stage.setScene(oktatoKutatasiScene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private void palyazatiTemak(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/oktatoFXML/oktatoPalyazatiModosito.fxml"));
        Parent oktatoKPalyazatParent = loader.load();

        Scene oktatoPalyazatiScene = new Scene(oktatoKPalyazatParent);

        OktatoPalyazatiModositoController controller2 = loader.getController();
        controller2.pTemaTranszfer(kivalasztott);
//        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //ha ugyanazon a Stage-en akarom megjeleniteni
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Pályázati témák módosítása");
        stage.setScene(oktatoPalyazatiScene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
