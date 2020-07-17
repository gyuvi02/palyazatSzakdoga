package controller.oktatoController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.gyula.App;


import java.io.IOException;

public class oktatoFooldalController {
    String tanszek;

    String fx;
    String title;

    @FXML
    private Button oktatoReszlet;

    @FXML
    private Button kilepesGomb;

    @FXML
    private Button oktatoSzerk;

    @FXML
    private Button oktatoTorles;

    @FXML
    private Button oktatoAktivitas;

    @FXML
    private MenuButton menuKutatas;

    @FXML
    private Button oktatoPalyazat;

    @FXML
    private Button oktatoFelhivas;

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
        dialog.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
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
        stage.setTitle("Tanszéki témakörök");
        stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        stage.setScene(oktatoValasztoScene);
        stage.show();
    }

    @FXML
    private void oktatoSzerkeszto(ActionEvent event) throws IOException {
        if (event.getSource().equals(oktatoSzerk)) {
            fx = "oktatoValasztoSzerkesztFXML";
            title = " - szerkesztés";
        } else if (event.getSource().equals(oktatoTorles)) {
            fx = "oktatoValasztoTorlesFXML";
            title = " - törlés";
        } else if (event.getSource().equals(oktatoReszlet)) {
            fx = "oktatoValasztoReszletekFXML";
            title = " - adatok megtekintése";
        } else if (event.getSource().equals(oktatoAktivitas)) {
            fx = "oktatoValasztoAktivitasFXML";
            title = " - pályázati részvétel";
        }

        Stage dialog = new Stage();
        dialog.setTitle("Oktató kiválasztása " + title);
        Scene scene = new Scene(App.loadFXML("/org/gyula/oktatoFXML/" + fx));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        dialog.setScene(scene);
        dialog.show();
    }

    @FXML
    private void tanszekAktivitas(ActionEvent event) throws IOException {
        MenuItem source = (MenuItem) event.getSource();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/oktatoFXML/oktatoTanszekiAktivitas.fxml"));
        Parent oktatoValasztoParent = loader.load();
        Scene oktatoValasztoScene = new Scene(oktatoValasztoParent);
        oktatoTanszekiAktivitasController controller = loader.getController();
        controller.adatTranszfer(source.getText());

        Stage stage = new Stage();
        stage.setTitle("Tanszéki pályázatok");
        stage.setScene(oktatoValasztoScene);
        stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        stage.show();
    }

    @FXML
    private void oktatoPalyazatTema() throws IOException {
        Stage dialog = new Stage();
        dialog.setTitle("Keresés kari pályázati témák alapján");
        Scene scene = new Scene(App.loadFXML("/org/gyula/oktatoFXML/oktatoPalyazatiTema"));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(scene);
        dialog.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        dialog.show();
    }
}
