package org.gyula.controller.felhivasController;

import org.gyula.felhivasok.Felhivas;
import org.gyula.felhivasok.FelhivasLekerdezes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class felhivasTorlesController {
    Felhivas felhivas = new Felhivas();
    FelhivasLekerdezes felhivasLekerdezes = new FelhivasLekerdezes();
        long felhivasSzam = felhivasLekerdezes.felhivasokSzama(); //ezzel szamoljuk, hogy hany felhivasunk van, ez az utalso oldal kiszamitasanal fontos
    int sorokSzama = 14; //ennyi sort jelenitunk meg egy oldalon
    int oldalszam = 0; //ezzel tartjuk nyilvan, hogy hanyadik oldalon tartunk


    @FXML
    private ListView<String> felhivasLista;

    @FXML
    private Button kilepesGomb;

    @FXML
    private Button torlesGomb;

    @FXML
    private Button elozo;

    @FXML
    private Button kovetkezo;

    @FXML
    public void initialize() {
//        felhivasSzam = felhivasLekerdezes.felhivasListak().size(); // ez nagyon lelassitja az ablak megnyitasat
        felhivasLista.getItems().setAll(felhivasLekerdezes.felhivasListaLimited(oldalszam, sorokSzama));
        felhivasLista.getSelectionModel().select(0);
//        elozo.setDisable(true);
        elozo.setVisible(false);
    }

    public void osszesMegjelenito() {
        felhivasLista.getItems().setAll(felhivasLekerdezes.felhivasListaLimited(oldalszam, sorokSzama));
        felhivasLista.getSelectionModel().select(0);
//        elozo.setDisable(true);
        elozo.setVisible(false);
        torlesGomb.setVisible(false);
    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

    @FXML
    private void felhivasValaszto() throws IOException {
        String kivalasztottFelhivas = felhivasLista.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/felhivasFXML/felhivasReszletek.fxml"));
        Parent felhivasValasztoParent = loader.load();
        Scene felhivasValasztoScene = new Scene(felhivasValasztoParent);
        felhivasReszletekController controller = loader.getController();
        controller.adatTranszfer(kivalasztottFelhivas);
//        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("A felhívás részletei - " + kivalasztottFelhivas);
        stage.setScene(felhivasValasztoScene);
        stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        stage.show();
    }

    @FXML
    public void lepteto(ActionEvent event) {
        if (event.getSource().equals(elozo)) {
            oldalszam--;
//            kovetkezo.setDisable(false);
            kovetkezo.setVisible(true);
        } else {
            if (oldalszam > 0 && (felhivasSzam / (sorokSzama * (oldalszam + 2))) < 1) {
//                kovetkezo.setDisable(true);
                kovetkezo.setVisible(false);
            }
            oldalszam++;
//            elozo.setDisable(false);
            elozo.setVisible(true);
        }
        if (oldalszam == 0) {
//            elozo.setDisable(true);
            elozo.setVisible(false);
        }
        felhivasLista.getItems().setAll(felhivasLekerdezes.felhivasListaLimited(oldalszam, sorokSzama));
        felhivasLista.getSelectionModel().select(0);
    }

    @FXML
    public void felhivasTorles() {
        megerositesDialog();
        String kivalasztottFelhivas = felhivasLista.getSelectionModel().getSelectedItem();
        felhivas.felhivasTorol(kivalasztottFelhivas);
    }

    private void megerositesDialog() {
        String kivalasztottFelhivas = felhivasLista.getSelectionModel().getSelectedItem();
        System.out.println(kivalasztottFelhivas);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Megerősítés");
        alert.setHeaderText("Biztos benne, hogy törli a következő felhívást:");
        alert.setContentText(felhivasLista.getSelectionModel().getSelectedItem());
        alert.getDialogPane().getScene().getStylesheets().add("org/gyula/dialogCSS.css");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println(kivalasztottFelhivas + "   " + felhivas.felhivasTorol(kivalasztottFelhivas));
            initialize();
        }
    }


}
