package org.gyula.controller.oktatoController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.gyula.okatok.Oktato;
import org.gyula.okatok.OktatoLekerdezes;

import java.io.IOException;
import java.util.Optional;

public class oktatoValasztoController {

    OktatoLekerdezes oktatoLekerdezes = new OktatoLekerdezes();
    Oktato kivalasztottOktato = new Oktato();

    @FXML
    private Button nevValasztoSzerkeszt;

    @FXML
    private Button nevValasztoTorol;

    @FXML
    private Button nevValasztoReszletek;

    @FXML
    public Button nevValasztoAktvitas;

    @FXML
    private ListView<String> oktatoNevek;

    @FXML
    private Button kilepesGomb;

//    @FXML
//    private Button oktatoReszlet;
//
    public void initialize() {
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
    public void nevValaszto(ActionEvent event) throws IOException, InterruptedException {
//        kivalasztottOktato = kivalasztottOktato.oktatoLetolto(oktatoNevek.getSelectionModel().getSelectedItems().get(0));
        FXMLLoader loader = new FXMLLoader();
        if (event.getSource().equals(nevValasztoSzerkeszt)) {
            loader.setLocation(getClass().getResource("/org/gyula/oktatoFXML/oktatoModosito.fxml"));
            Parent oktatoValasztoParent = loader.load();
            Scene oktatoValasztoScene = new Scene(oktatoValasztoParent);

            oktatoModositoController controller = loader.getController();
            controller.adatTranszfer(Oktato.oktatoLetolto(oktatoNevek.getSelectionModel().getSelectedItems().get(0)));
            Stage stage = new Stage();
//            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Oktatói adatok módosítása");
//            stage.setX(280);//ezzel kezilg allitom nagyjabol kozepre, de kell lenni mas megoldasnak, hogy ne az elozo ablak bal szelehez igazitsa, hanem kozepre, mint a tobbi ablakot
            stage.setScene(oktatoValasztoScene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
            stage.show();
            kilep();

        } else if (event.getSource().equals(nevValasztoTorol)) {
            megerositesDialog();
        } else if (event.getSource().equals(nevValasztoReszletek)) {
            loader.setLocation(getClass().getResource("/org/gyula/oktatoFXML/oktatoReszletek.fxml"));
            Parent oktatoValasztoParent = loader.load();
            Scene oktatoValasztoScene = new Scene(oktatoValasztoParent);

            oktatoReszletekController controller = loader.getController();
            controller.adatTranszfer(Oktato.oktatoLetolto(oktatoNevek.getSelectionModel().getSelectedItems().get(0)));
            Stage stage = new Stage();
//            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Részletes oktatói adatok");
//            stage.setX(370.0);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(oktatoValasztoScene);
            stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
            stage.show();

        } else if (event.getSource().equals(nevValasztoAktvitas)) {
            loader.setLocation(getClass().getResource("/org/gyula/oktatoFXML/oktatoAktivitas.fxml"));
            Parent oktatoValasztoParent = loader.load();
            Scene oktatoValasztoScene = new Scene(oktatoValasztoParent);

            oktatoAktivitasController controller = loader.getController();
            controller.adatTranszfer(oktatoNevek.getSelectionModel().getSelectedItem());
//            Stage stage = new Stage();
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Oktatók pályázati aktivitása");
            stage.setScene(oktatoValasztoScene);
            stage.setX((Screen.getPrimary().getBounds().getMaxX() - oktatoValasztoScene.getWidth())/2);
//            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
            stage.show();

        }
    }

    private void megerositesDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Megerősítés");
        alert.setHeaderText("Biztos benne, hogy törli a következő oktatót:");
        alert.setContentText(oktatoNevek.getSelectionModel().getSelectedItem());
        alert.getDialogPane().getScene().getStylesheets().add("org/gyula/dialogCSS.css");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println(kivalasztottOktato.getEmail());
            System.out.println(Oktato.oktatoLetolto(oktatoNevek.getSelectionModel().getSelectedItems().get(0)).oktatoTorol(kivalasztottOktato.getEmail()));
            kilep();
        }
    }
}
