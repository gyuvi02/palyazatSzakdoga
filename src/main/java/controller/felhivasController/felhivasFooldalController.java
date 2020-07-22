package controller.felhivasController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    public Button kategoriaLekerdezes;

    @FXML
    private Button felhivasOktato;

    @FXML
    public Button felhivaslegutobbi;

    @FXML
    public Button linkMegadas;

    @FXML
    private TextField kulcsszo;

    @FXML
    private Button kulcsKereso;

    @FXML
    private Button torles;

    @FXML
    private Button osszesFelhivas;

    @FXML
    private Button hatarido;

    @FXML
    private void initialize() {
        kulcsKereso.setDisable(true);
    }

    @FXML
    private void uresMezo() {
        boolean disableButtons = kulcsszo.getText().trim().isEmpty();
        kulcsKereso.setDisable(disableButtons);
    }


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
        dialog.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        dialog.show();
    }

    @FXML
    private void legutobbiFelhivas(ActionEvent event) throws IOException {
        String title = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/felhivasFXML/felhivasOktatoPalyazatiTema.fxml"));
        Parent felhivasValasztoParent = loader.load();
        Scene felhivasValasztoScene = new Scene(felhivasValasztoParent);
        felhivasOktatoPalyazatiTemaController controller = loader.getController();
        if (event.getSource().equals(felhivaslegutobbi)) {
            controller.adatLegutobbi();
            title = "A legutóbbi pályázati felhívások";
        }
        Stage dialog = new Stage();
        dialog.setTitle(title);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(felhivasValasztoScene);
        dialog.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        dialog.show();
    }

    @FXML
    private void kategoriaKereses() throws IOException {
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
        dialog.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        dialog.show();
    }

    public void kulcsszoKereso() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/felhivasFXML/felhivasOktatoPalyazatiTema.fxml"));
        Parent felhivasValasztoParent = loader.load();
        Scene felhivasValasztoScene = new Scene(felhivasValasztoParent);

        felhivasOktatoPalyazatiTemaController controller = loader.getController();
        controller.kulcsszoKereso(kulcsszo.getText());

        Stage dialog = new Stage();
        dialog.setTitle("A keresés eredménye");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(felhivasValasztoScene);
        dialog.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        dialog.show();
    }

    @FXML
    private void linkAblakhoz() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/felhivasFXML/felhivasPafiLink.fxml"));
        Parent felhivasPafiParent = loader.load();
        Scene felhivasPafiScene = new Scene(felhivasPafiParent);
        felhivasPafiLinkController controller = loader.getController();
        Stage dialog = new Stage();
        dialog.setTitle("Új felhívás hozzáadása");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(felhivasPafiScene);
        dialog.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        dialog.show();
    }

    @FXML
    private void felhivasTorles(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/felhivasFXML/felhivasTorles.fxml"));
        Parent felhivasTorlesParent = loader.load();
        Scene felhivasTorlesScene = new Scene(felhivasTorlesParent);
        felhivasTorlesController controller = loader.getController();
        if (event.getSource().equals(osszesFelhivas)) {
            controller.osszesMegjelenito();
        }
        Stage dialog = new Stage();
        dialog.setTitle("Felhívások törlése");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(felhivasTorlesScene);
        dialog.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        dialog.show();
    }

    @FXML
    public void hataridoValaszto() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/felhivasFXML/felhivasHatarido.fxml"));
        Parent felhivasHataridoParent = loader.load();
        Scene felhivasHataridoScene = new Scene(felhivasHataridoParent);
        felhivasHataridoController controller = loader.getController();
        Stage dialog = new Stage();
        dialog.setTitle("Felhívások törlése");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(felhivasHataridoScene);
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
        App.exit();
        ablak.close();
    }

}
