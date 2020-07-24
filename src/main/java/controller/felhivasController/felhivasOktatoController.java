package controller.felhivasController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Screen;
import javafx.stage.Stage;
import okatok.OktatoLekerdezes;

import java.io.IOException;

public class felhivasOktatoController {
    OktatoLekerdezes oktatoLekerdezes = new OktatoLekerdezes();

    @FXML
    private Button kilepesGomb;

    @FXML
    private ListView<String> oktatoNevek;

    @FXML
    private void initialize() {
        oktatoNevek.getItems().setAll(oktatoLekerdezes.oktatoNevsor("összes"));
        oktatoNevek.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        oktatoNevek.getSelectionModel().select(0);

    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

    @FXML
    public void felhivasValaszto(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/felhivasFXML/felhivasOktatoPalyazatiTema.fxml"));
        Parent oktatoValasztoParent = loader.load();
        Scene oktatoValasztoScene = new Scene(oktatoValasztoParent);
        felhivasOktatoPalyazatiTemaController controller = loader.getController();
        controller.adatTranszfer(oktatoNevek.getSelectionModel().getSelectedItem());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        Stage stage = new Stage();
        stage.setTitle("Felhívások "  + oktatoNevek.getSelectionModel().getSelectedItems().get(0) + " számára");
        stage.setScene(oktatoValasztoScene);
        stage.setResizable(false);
        stage.setX((Screen.getPrimary().getBounds().getMaxX() - oktatoValasztoScene.getWidth())/2);
        stage.show();
    }
}
