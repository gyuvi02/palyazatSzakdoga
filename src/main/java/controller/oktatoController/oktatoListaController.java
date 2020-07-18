package controller.oktatoController;

import controller.palyazatController.palyazatReszletekController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import okatok.Oktato;
import okatok.OktatoLekerdezes;

import java.io.IOException;
import java.util.ArrayList;

public class oktatoListaController {

    @FXML
    private Button kilepesGomb;

    @FXML
    private Button reszletekGomb;

    @FXML
    private ListView<String> nevLista;

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }


    public void adatTranszfer(String tema) {
        ArrayList<String> lista = new ArrayList<>(OktatoLekerdezes.palyazatiTemaKereso(tema));
        if (lista.isEmpty()){ //Ez elvileg lehetetlen, mert a listat az oktatok palyazati temaibol rakjuk ossze
            lista.add("Nincs ilyen oktató");
            reszletekGomb.setDisable(true);
        }
        nevLista.getItems().setAll(lista);
        nevLista.getSelectionModel().selectFirst();
    }

    @FXML
    public void reszletekOktato() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/oktatoFXML/oktatoReszletek.fxml"));
        Parent oktatoValasztoParent = loader.load();
        Scene oktatoValasztoScene = new Scene(oktatoValasztoParent);
        oktatoReszletekController controller = loader.getController();
        controller.adatTranszfer(Oktato.oktatoLetolto(nevLista.getSelectionModel().getSelectedItem()));
        Stage stage = new Stage();
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Az oktató részletes adatai");
        stage.setScene(oktatoValasztoScene);
        stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
//        stage.setX((Screen.getPrimary().getBounds().getMaxX() - oktatoValasztoScene.getWidth())/2);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }
}
