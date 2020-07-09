package controller.felhivasController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    private Button felhivasLegutobbi;

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
    private void legutobbiFelhivas() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/felhivasFXML/felhivasOktatoPalyazatiTema.fxml"));
        Parent felhivasValasztoParent = loader.load();
        Scene felhivasValasztoScene = new Scene(felhivasValasztoParent);

        felhivasOktatoPalyazatiTemaController controller = loader.getController();
        controller.adatLegutobbi();

        Stage dialog = new Stage();
        dialog.setTitle("A legutóbbi pályázati felhívások");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(felhivasValasztoScene);
        dialog.show();
    }

    @FXML
    private void ketegoriaKereses() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/felhivasFXML/felhivasPalyazatiTemaLista.fxml"));
        Parent felhivasValasztoParent = loader.load();
        Scene felhivasValasztoScene = new Scene(felhivasValasztoParent);
//        felhivasPalyazatiTemaListaController controller = loader.getController();
//        controller.adatLegutobbi();
        Stage dialog = new Stage();
        dialog.setTitle("A pályázati kategóriák");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(felhivasValasztoScene);
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
