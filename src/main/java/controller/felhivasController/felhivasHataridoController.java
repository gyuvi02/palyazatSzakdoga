package controller.felhivasController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class felhivasHataridoController {

    @FXML
    private DatePicker hataridoGomb;

    @FXML
    private Button kilepesGomb;

    @FXML
    private Button kereses;

    @FXML
    private void initialize() {
        hataridoGomb.setValue(LocalDate.now());
    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

    @FXML
    private void hataridoKereso() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/felhivasFXML/felhivasOktatoPalyazatiTema.fxml"));
        Parent kategoriaValasztoParent = loader.load();
        Scene kategoriaValasztoScene = new Scene(kategoriaValasztoParent);
        felhivasOktatoPalyazatiTemaController controller = loader.getController();
        controller.adatDatum(hataridoGomb.getValue());
//        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.setTitle("Az megfelelő felhívások");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(kategoriaValasztoScene);
        stage.show();

    }

}
