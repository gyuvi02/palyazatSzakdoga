package controller.oktatoController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import palyazatok.PalyazatiTemak;

import java.io.IOException;

public class oktatoPalyazatiTemaController {
    PalyazatiTemak palyazatiTemak = new PalyazatiTemak();

    @FXML
    private Button kilepesGomb;

    @FXML
    private Button temaValaszto;

    @FXML
    public ListView<String> temaLista;

    @FXML
    public void initialize() {
        temaLista.getItems().setAll(palyazatiTemak.temaLetolt());
        temaValaszto.setDisable(true);
        temaLista.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private void nincsValasztas() {
        boolean disableButtons = temaLista.getSelectionModel().isEmpty();
        temaValaszto.setDisable(disableButtons);
    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

    @FXML
    private void temaValaszto() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/oktatoFXML/oktatoLista.fxml"));
        Parent oktatoValasztoParent = loader.load();
        Scene oktatoValasztoScene = new Scene(oktatoValasztoParent);

        oktatoListaController controller = loader.getController();
        controller.adatTranszfer(temaLista.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Oktatók a választott pályázati témával");
//        stage.setX(370.0);
        stage.setScene(oktatoValasztoScene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        stage.show();
        kilep();

    }
}
