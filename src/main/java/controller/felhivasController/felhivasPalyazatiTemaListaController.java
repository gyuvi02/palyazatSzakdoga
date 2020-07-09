package controller.felhivasController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import palyazatok.PalyazatiTemak;

import java.io.IOException;

public class felhivasPalyazatiTemaListaController {
    PalyazatiTemak palyazatiTemak = new PalyazatiTemak();

    @FXML
    private ListView kategoriaLista;

    @FXML
    private Button kilepesGomb;

    @FXML
    private Button felhivasValaszto;

    @FXML
    public void initialize() {
        kategoriaLista.getItems().setAll(palyazatiTemak.temaLetolt());
        felhivasValaszto.setDisable(true);
        kategoriaLista.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private void nincsValasztas() {
        boolean disableButtons = kategoriaLista.getSelectionModel().isEmpty();
        felhivasValaszto.setDisable(disableButtons);
    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

    @FXML
    private void kategoriaValaszto(ActionEvent event) throws IOException {
        String kivalasztottKategoria = kategoriaLista.getSelectionModel().getSelectedItem().toString();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/felhivasFXML/felhivasOktatoPalyazatiTema.fxml"));
        Parent kategoriaValasztoParent = loader.load();
        Scene kategoriaValasztoScene = new Scene(kategoriaValasztoParent);
        felhivasOktatoPalyazatiTemaController controller = loader.getController();
        controller.adatKategoria(kivalasztottKategoria);
//        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.setTitle("A kategóriába tarozó felhívások - " + kivalasztottKategoria );
//        stage.setX(280);//ezzel kezilg allitom nagyjabol kozepre, de kell lenni mas megoldasnak, hogy ne az elozo ablak bal szelehez igazitsa, hanem kozepre, mint a tobbi ablakot
        stage.setScene(kategoriaValasztoScene);
        stage.show();



    }


}
