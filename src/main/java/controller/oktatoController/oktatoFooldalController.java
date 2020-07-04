package controller.oktatoController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.gyula.App;


import java.io.IOException;

public class oktatoFooldalController {
    String tanszek;

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
    MenuButton menuKutatas;

    @FXML
    private void visszaKezdooldalra() throws IOException {
        App.setRoot("kezdooldal");
    }

    @FXML
    private void menuKilepes() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

//    @FXML
//    private void tanszekLekerdezes(ActionEvent event) throws IOException {
//        MenuItem source = (MenuItem) event.getSource();
//        OktatoLekerdezes oktatoLekerdezes = new OktatoLekerdezes();
//        System.out.println(oktatoLekerdezes.oktatoListak(source.getText()));
//    }

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

    //A palyazati es kutatasi temakat kozos oldalon irjuk ki, ehhez meg kell vizsgalni, melyik gombot nyomtuk meg
    @FXML
    private void tanszekiKutatasok(ActionEvent event) throws IOException {
        String tomb;
        MenuItem source = (MenuItem) event.getSource();
        switch (source.getId()) {   //a menu felirata mellett csak az id-t tudom vizsgalni, igy dontom el, hogy melyik gombbol jon
            case "1" : tomb = "kutatas"; break;
            case "2" : tomb = "kutatas"; break;
            case "3" : tomb = "kutatas"; break;
            case "4" : tomb = "kutatas"; break;
            case "5" : tomb = "kutatas"; break;
            case "6" : tomb = "kutatas"; break;
            case "7" : tomb = "kutatas"; break;
            default: tomb = "palyazat"; //ha nem az elso 7 gomb, akkor palyazat gombrol jott
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/oktatoFXML/oktatoTanszekiKutatasok.fxml"));
        Parent oktatoValasztoParent = loader.load();
        Scene oktatoValasztoScene = new Scene(oktatoValasztoParent);

        oktatoTanszekiKutatasokController controller = loader.getController();
        controller.adatTranszfer(source.getText(), tomb);

        Stage stage = new Stage();
        stage.setTitle("Kutatási kérdések");
        stage.setScene(oktatoValasztoScene);
        stage.show();
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
