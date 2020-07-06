package controller.oktatoController;

import controller.oktatoController.oktatoModositoController;
import controller.oktatoController.oktatoReszletekController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import okatok.Oktato;
import okatok.OktatoLekerdezes;

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

    @FXML
    private Button oktatoReszlet;

    public void initialize() {
        oktatoNevek.getItems().setAll(oktatoLekerdezes.oktatoNevsor("összes"));
        oktatoNevek.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

    @FXML
    public void nevValaszto(ActionEvent event) throws IOException {
        kivalasztottOktato = kivalasztottOktato.oktatoLetolto(oktatoNevek.getSelectionModel().getSelectedItems().get(0));
        FXMLLoader loader = new FXMLLoader();
        if (event.getSource().equals(nevValasztoSzerkeszt)) {
            loader.setLocation(getClass().getResource("/org/gyula/oktatoFXML/oktatoModosito.fxml"));
            Parent oktatoValasztoParent = loader.load();
            Scene oktatoValasztoScene = new Scene(oktatoValasztoParent);

            oktatoModositoController controller = loader.getController();
            controller.adatTranszfer(kivalasztottOktato.oktatoLetolto(oktatoNevek.getSelectionModel().getSelectedItems().get(0)));

//            kilep();
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Oktatói adatok módosítása");
            stage.setX(280);//ezzel kezilg allitom nagyjabol kozepre, de kell lenni mas megoldasnak, hogy ne az elozo ablak bal szelehez igazitsa, hanem kozepre, mint a tobbi ablakot
            stage.setScene(oktatoValasztoScene);
            stage.show();

        } else if (event.getSource().equals(nevValasztoTorol)) {
            megerositesDialog();
        } else if (event.getSource().equals(nevValasztoReszletek)) {
            loader.setLocation(getClass().getResource("/org/gyula/oktatoFXML/oktatoReszletek.fxml"));
            Parent oktatoValasztoParent = loader.load();
            Scene oktatoValasztoScene = new Scene(oktatoValasztoParent);

            oktatoReszletekController controller = loader.getController();
            controller.adatTranszfer(kivalasztottOktato.oktatoLetolto(oktatoNevek.getSelectionModel().getSelectedItems().get(0)));

//            kilep();
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Részletes oktatói adatok");
            stage.setX(370.0);
            stage.setScene(oktatoValasztoScene);
            stage.show();

        } else if (event.getSource().equals(nevValasztoAktvitas)) {
            loader.setLocation(getClass().getResource("/org/gyula/oktatoFXML/oktatoAktivitas.fxml"));
            Parent oktatoValasztoParent = loader.load();
            Scene oktatoValasztoScene = new Scene(oktatoValasztoParent);

            oktatoAktivitasController controller = loader.getController();
            controller.adatTranszfer(kivalasztottOktato.oktatoLetolto(oktatoNevek.getSelectionModel().getSelectedItems().get(0)));

//            kilep();
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Oktatók pályázati aktivitása");
            stage.setScene(oktatoValasztoScene);
            stage.setX(350);//ezzel kezilg allitom nagyjabol kozepre, de kell lenni mas megoldasnak, hogy ne az elozo ablak bal szelehez igazitsa, hanem kozepre, mint a tobbi ablakot
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
            System.out.println(kivalasztottOktato.oktatoLetolto(oktatoNevek.getSelectionModel().getSelectedItems().get(0)).oktatoTorol(kivalasztottOktato.getEmail()));
            kilep();
        }
    }



}
