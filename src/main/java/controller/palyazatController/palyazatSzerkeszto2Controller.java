package controller.palyazatController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import palyazatok.Palyazat;

import java.io.IOException;

public class palyazatSzerkeszto2Controller {
    Palyazat palyazat = new Palyazat();

    @FXML
    private Button kettoEgy;

    @FXML
    private Button kettoHarom;

    @FXML
    private TextField osszkoltsegField;

    @FXML
    private TextField oneroField;

    @FXML
    private TextField tamogatasField;

    @FXML
    private TextField azonositoField;

    @FXML
    private TextField szerzodesszamField;

    @FXML
    private TextArea megjegyzesArea;

    @FXML
    private Button kilepesGomb;

    @FXML
    private Button mentes2;

    @FXML
    private void uresMezo() {//ezzel ellenorzom, hogy csak szamjegyek legyenek
        boolean disableButtons = osszkoltsegField.getText().trim().isEmpty() || oneroField.getText().trim().isEmpty() ||
                tamogatasField.getText().isEmpty() || szamE(osszkoltsegField.getText()) || szamE(oneroField.getText()) ||
                szamE(tamogatasField.getText());
        mentes2.setDisable(disableButtons);
        kettoEgy.setDisable(disableButtons);
        kettoHarom.setDisable(disableButtons);
        }

    @FXML
    public void adatTranszfer(Palyazat atadottPalyazat) {
        palyazat = atadottPalyazat;
        osszkoltsegField.setText(palyazat.getTervezettOsszkoltseg().toString());
        oneroField.setText(palyazat.getOnero().toString());
        tamogatasField.setText(palyazat.getIgenyeltTamogatas().toString());
        azonositoField.setText(palyazat.getDEazonosito());
        szerzodesszamField.setText(palyazat.getSzerzodesSzam());
        megjegyzesArea.setText(palyazat.getMegjegyzes());
        megjegyzesArea.setWrapText(true);

        oneroField.setCursor(Cursor.DEFAULT);
        tamogatasField.setCursor(Cursor.DEFAULT);
        azonositoField.setCursor(Cursor.DEFAULT);
        szerzodesszamField.setCursor(Cursor.DEFAULT);
        megjegyzesArea.setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void masodikScene(ActionEvent event) throws IOException {
        mezoUpdate();
        Scene palyazatSzerkesztoScene;
        FXMLLoader loader = new FXMLLoader();
        if (event.getSource().equals(kettoEgy)) { //ha az "elozo" gombot nyomtuk meg
            loader.setLocation(getClass().getResource("/org/gyula/palyazatFXML/palyazatSzerkeszto.fxml"));
            Parent palyazatValasztoParent = loader.load();
            palyazatSzerkesztoScene = new Scene(palyazatValasztoParent);
            palyazatSzerkesztoController controller = loader.getController();
            controller.adatTranszfer(palyazat);

        } else {//a masik lehetoseg csak az, hogy a "kovetkezo" gombot nyomtuk meg
            loader.setLocation(getClass().getResource("/org/gyula/palyazatFXML/palyazatSzerkeszto3.fxml"));
            Parent palyazatValasztoParent = loader.load();
            palyazatSzerkesztoScene = new Scene(palyazatValasztoParent);
            palyazatSzerkeszto3Controller controller = loader.getController();
            controller.adatTranszfer(palyazat);
        }
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(palyazatSzerkesztoScene);
        stage.setTitle("A pályázat szerkesztése");
        stage.setScene(palyazatSzerkesztoScene);
        stage.setX((Screen.getPrimary().getBounds().getMaxX() - palyazatSzerkesztoScene.getWidth())/2);
        stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        stage.show();

    }

    private void mezoUpdate() {
        palyazat.setTervezettOsszkoltseg(Double.parseDouble(osszkoltsegField.getText())); //itt mar mindenkeppen szamjegyek lesznek
        palyazat.setOnero(Double.parseDouble(oneroField.getText()));
        palyazat.setIgenyeltTamogatas(Double.parseDouble(tamogatasField.getText()));
        palyazat.setDEazonosito(azonositoField.getText());
        palyazat.setSzerzodesSzam(szerzodesszamField.getText());
        palyazat.setMegjegyzes(megjegyzesArea.getText());
    }

    @FXML
    private void palyazatMentes() {
        mezoUpdate();
        palyazat.PalyazatFrissito();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Megerősítés");
        alert.setHeaderText("Módosítottuk a következő pályázatot:");
        alert.setContentText(palyazat.getPalyazatCim());
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/org/gyula/dialogCSS.css").toExternalForm());
        dialogPane.getStyleClass().add("/org/gyula/dialogCSS.css");
        alert.showAndWait();
        kilep();
    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

    private static boolean szamE(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

}
