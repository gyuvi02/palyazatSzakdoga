package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import okatok.OktatoLekerdezes;
import org.gyula.App;


import java.io.IOException;

public class oktatoFooldalController {
    @FXML
    String fx;
    String title;

    @FXML
    private Button oktatoReszlet;

    @FXML
    private Button kilepesGomb;

    @FXML
    private Button oktatoSzerkeszto;

    @FXML
    private Button oktatoTorles;

    @FXML
    MenuButton oktatoTanszek;

    @FXML
    private void visszaKezdooldalra() throws IOException {
        App.setRoot("kezdooldal");
    }

    @FXML
    private void menuKilepes() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

    @FXML
    private void tanszekLekerdezes(ActionEvent event) {
        MenuItem source = (MenuItem) event.getSource();
        OktatoLekerdezes oktatoLekerdezes = new OktatoLekerdezes();
        System.out.println(oktatoLekerdezes.oktatoNevsor(source.getText()));
    }

    @FXML
    private void ujOktato() throws IOException {
//        App.setRoot("/org/gyula/oktatoFXML/ujOktato");
        Stage dialog = new Stage();
        dialog.setTitle("Új oktató hozzáadása");
        Scene scene = new Scene(App.loadFXML("/org/gyula/oktatoFXML/ujOktato"));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(scene);
        dialog.show();

    }

    @FXML
    private void oktatoSzerkezto(ActionEvent event) throws IOException {
        if (event.getSource().equals(oktatoSzerkeszto)) {
            fx = "oktatoValasztoFXML";
            title = "szerkesztésre";
        } else if (event.getSource().equals(oktatoTorles)) {
            fx = "oktatoValasztoTorlesFXML";
            title = "törlésre";
        } else if (event.getSource().equals(oktatoReszlet)) {
            fx = "oktatoValasztoReszletekFXML";
            title = "megtekintésre";
        }
        Stage dialog = new Stage();
        dialog.setTitle("Oktató kiválasztása " + title);
        Scene scene = new Scene(App.loadFXML("/org/gyula/oktatoFXML/" + fx));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(scene);
        dialog.show();
    }

    @FXML
    private String  oktatoValaszto()  throws IOException {
        App.setRoot("org/gyula/oktatoFXML/oktatoValasztoFXML.fxml");
        return "";
    }

}
