package controller.palyazatController;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.gyula.App;

public class palyazatFooldalController {

    @FXML
    Button visszaPalyazatrol;

    @FXML
    Button kilepesGomb;

    @FXML
    private void ujPalyazat() throws IOException {
        Stage dialog = new Stage();
        dialog.setTitle("Új pályázat létrehozása");
        Scene scene = new Scene(App.loadFXML("/org/gyula/palyazatFXML/ujPalyazat"));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(scene);
        dialog.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        dialog.show();
    }

    @FXML
    private void palyazatTorles() throws IOException{
        Stage dialog = new Stage();
        dialog.setTitle("Válasszon pályázatot");
        Scene scene = new Scene(App.loadFXML("/org/gyula/palyazatFXML/palyazatTorles"));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(scene);
        dialog.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        dialog.show();
    }

    @FXML
    private void palyazatSzerkeszto() throws IOException {
        Stage dialog = new Stage();
        dialog.setTitle("Válasszon pályázatot");
        Scene scene = new Scene(App.loadFXML("/org/gyula/palyazatFXML/palyazatListaSzerkeszto"));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(scene);
        dialog.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
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