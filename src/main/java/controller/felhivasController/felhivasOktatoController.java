package controller.felhivasController;

import controller.oktatoController.oktatoAktivitasController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import okatok.Oktato;
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
        controller.adatLegutobbi();

//            kilep();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        Stage stage = new Stage();
        stage.setTitle("Felhívások "  + oktatoNevek.getSelectionModel().getSelectedItems().get(0) + " számára");
        stage.setScene(oktatoValasztoScene);
        stage.setX(350);//ezzel kezilg allitom nagyjabol kozepre, de kell lenni mas megoldasnak, hogy ne az elozo ablak bal szelehez igazitsa, hanem kozepre, mint a tobbi ablakot
        stage.show();


    }
}
