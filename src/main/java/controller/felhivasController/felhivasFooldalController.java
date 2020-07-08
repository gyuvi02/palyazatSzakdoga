package controller.felhivasController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.gyula.App;

import java.io.IOException;

public class felhivasFooldalController {
    String fx;
    String title;


    @FXML
    private Button visszaFelhivasrol;

    @FXML
    private Button kilepesGomb;

    @FXML
    private Button felhivasOktato;

    @FXML
    private void oktatoValaszto(ActionEvent event) throws IOException {
        if (event.getSource().equals(felhivasOktato)) {
            fx = "felhivasOktatoFXML";
            title = "";
        }
        Stage dialog = new Stage();
        dialog.setTitle("Oktató kiválasztása " + title);
        Scene scene = new Scene(App.loadFXML("/org/gyula/felhivasFXML/" + fx));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(scene);
        dialog.show();

    }

    @FXML
    private void visszaKezdooldalra() throws IOException {
        App.setRoot("kezdooldal");
    }

    @FXML
    private void menuKilepes() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

}
