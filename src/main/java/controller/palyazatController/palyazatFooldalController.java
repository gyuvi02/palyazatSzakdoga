package controller.palyazatController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.gyula.App;
import palyazatok.Palyazat;
import palyazatok.PalyazatLekerdezesek;

public class palyazatFooldalController {
//    PalyazatLekerdezesek palyazatLekerdezesek = new PalyazatLekerdezesek();

    @FXML
    private Button visszaPalyazatrol;

    @FXML
    private Button szerkesztoGomb;

    @FXML
    private Button reszletekGomb;

    @FXML
    private Button kulcsszoGomb;

    @FXML
    private TextField kulcsszoField;

    @FXML
    private Button kezdoGomb;

    @FXML
    private Button zaroGomb;

    @FXML
    private Button kPluszFGomb;

    @FXML
    private Button oneroNelkul;

    @FXML
    private Button kilepesGomb;


    @FXML
    private void initialize() {
        kulcsszoGomb.setDisable(true);
    }

    @FXML
    private void uresMezo() {
        boolean disableButtons = kulcsszoField.getText().trim().isEmpty();
        kulcsszoGomb.setDisable(disableButtons);
    }


    @FXML
    private void ujPalyazat() throws Exception {
        Stage dialog = new Stage();
        dialog.setTitle("Új pályázat létrehozása");
        Scene scene = new Scene(App.loadFXML("/org/gyula/palyazatFXML/ujPalyazat"));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(scene);
        dialog.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        dialog.show();
    }

    @FXML
    private void palyazatTorles() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/palyazatFXML/palyazatTorles.fxml"));
        Parent palyazatValasztoParent = loader.load();
        Scene palyazatValasztoScene = new Scene(palyazatValasztoParent);
        palyazatListaController controller = loader.getController();
        controller.adatTranszferTorles();
        Stage stage = new Stage();
        stage.setTitle("\"A pályázatok listája\"");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(palyazatValasztoScene);
        stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        stage.show();

    }

    @FXML
    private void palyazatListazo(ActionEvent event) throws IOException {
        if (event.getSource().equals(szerkesztoGomb)) {
            ujAblak("palyazatListaSzerkeszto", "szerkeszto");
        } else if (event.getSource().equals(reszletekGomb)) {
            ujAblak("palyazatListaKezdo", "reszletek");
        } else if (event.getSource().equals(kPluszFGomb)) {
            ujAblak("palyazatListaKezdo", "kPluszF");
        } else if (event.getSource().equals(kulcsszoGomb)) {
            ujAblak("palyazatListaKezdo", "kulcsszo");
        } else if (event.getSource().equals(oneroNelkul)) {
            ujAblak("palyazatListaKezdo", "onero");
        }
    }

    private void ujAblak(String fx, String adatTranszfer) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/palyazatFXML/" + fx + ".fxml"));
        Parent palyazatValasztoParent = loader.load();
        Scene palyazatValasztoScene = new Scene(palyazatValasztoParent);
        palyazatListaController controller = loader.getController();
        switch (adatTranszfer) {
            case "szerkeszto" : controller.adatTranszferOsszes(); break;
            case "reszletek" : controller.adatTranszferOsszes(); break;
            case "kPluszF" : controller.adatTranszferKpluszF(); break;
            case "kulcsszo" : controller.adatTranszferKulcsszo(kulcsszoField.getText()); break;
            case "onero" : controller.adatTranszferOnero();
        }
        Stage stage = new Stage();
        stage.setTitle("A pályázatok listája");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(palyazatValasztoScene);
        stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        stage.show();
    }

    @FXML
    private void kezdoDatum(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/palyazatFXML/palyazatKezdoDatum.fxml"));
        Parent palyazatValasztoParent = loader.load();
        Scene palyazatValasztoScene = new Scene(palyazatValasztoParent);
        palyazatKezdoDatumController controller = loader.getController();

        if (event.getSource().equals(kezdoGomb)) {
            System.out.println(event.getSource().equals(kezdoGomb));
        controller.adatTranszferKezdo("megkezdett", "kezdo");
        } else {
        controller.adatTranszferKezdo("befejeződött", "zaro");
        }
        Stage stage = new Stage();
        stage.setTitle("Válassza ki a dátumokat");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(palyazatValasztoScene);
        stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        stage.show();
    }

    @FXML
    public void kulcsszavasKereses(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/palyazatFXML/palyazatListaKezdo.fxml"));
        Parent palyazatValasztoParent = loader.load();
        Scene palyazatValasztoScene = new Scene(palyazatValasztoParent);
        palyazatListaController controller = loader.getController();
        controller.adatTranszferKulcsszo(kulcsszoField.getText());
        kulcsszoField.clear();
        Stage stage = new Stage();
        stage.setTitle("A kereses eredménye");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(palyazatValasztoScene);
        stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        stage.show();

    }

    @FXML
    private void visszaKezdooldalra() throws Exception {
        App.setRoot("kezdooldal");
    }

    @FXML
    private void menuKilepes() {
        App.exit();
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }
}