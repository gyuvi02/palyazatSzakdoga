package controller.palyazatController;

import javafx.collections.FXCollections;
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
import okatok.Oktato;
import okatok.OktatoLekerdezes;
import palyazatok.Palyazat;
import palyazatok.PalyazatLekerdezesek;
import palyazatok.PalyazatiResztvevok;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class palyazatSzerkeszto3Controller {
    Palyazat palyazat = new Palyazat();
    PalyazatiResztvevok resztvevok = new PalyazatiResztvevok();
    PalyazatLekerdezesek palyazatLekerdezesek = new PalyazatLekerdezesek();
    String szakmaiVezetoAktualis;


    private LocalDate kezdet;
    private LocalDate veg;

    private String szakmaiVezeto;
    private String projektmenedzser;
    private String kezelo; //A kezelo nem oktato, o nem szerepel az adatbazisban
    private ArrayList<String> resztvevoEmberek;

    @FXML
    private TextField kezeloField;


    @FXML
    private DatePicker kezdoDatum;

    @FXML
    private DatePicker zaroDatum;


    @FXML
    private Button haromKetto;

    @FXML
    private ComboBox<String> szakmaiCombo;

    @FXML
    private TextField szakmaiField;

    @FXML
    private ComboBox<String> menedzserCombo;

    @FXML
    private TextField menedzserField;

    @FXML
    private Button kilepesGomb;

    @FXML
    private Button mentes3;


    @FXML
    public void adatTranszfer(Palyazat atadottPalyazat) throws IOException {
        palyazat = atadottPalyazat;
        OktatoLekerdezes oktatoLekerdezes = new OktatoLekerdezes();
        ArrayList<String> oktatoLista = new ArrayList<>((oktatoLekerdezes.oktatoNevsor("összes")));//kiolvasom az oktatok aktualis listajat
        oktatoLista.add("Egyéb"); //hozzaadok egy lehetoseget, ha nem sajat oktato a szakmai vezeto
        szakmaiCombo.setItems(FXCollections.observableArrayList(oktatoLista)); //beolvasom a ComboBoxba a listat
        szakmaiCombo.setValue(palyazat.getResztvevok().getSzakmaiVezeto()); //belepeskor az legyen kivalasztva, aki az adatbazisban meg van adva
        menedzserCombo.setItems(FXCollections.observableArrayList(oktatoLista));
        menedzserCombo.setValue(palyazat.getResztvevok().getProjektmenedzser());
        kezeloField.setText(palyazat.getResztvevok().getKezelo());
        kezdoDatum.setValue(palyazat.getKezdet());
        zaroDatum.setValue(palyazat.getVeg());

        kezeloField.setCursor(Cursor.DEFAULT);
        szakmaiField.setCursor(Cursor.DEFAULT);
        menedzserField.setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void egyebKivalasztott(ActionEvent event) {
        if (szakmaiCombo.getValue().equals("Egyéb")) {
            szakmaiField.setVisible(true);
        }
        if (menedzserCombo.getValue().equals("Egyéb")) {
            menedzserField.setVisible(true);
        }
    }

    @FXML
    private void resztvevoSzerkeszto() throws IOException {
        Scene palyazatSzerkesztoScene;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/palyazatFXML/resztvevoOktatok.fxml"));
        Parent palyazatValasztoParent = loader.load();
        palyazatSzerkesztoScene = new Scene(palyazatValasztoParent);
        resztvevoOktatokController controller = loader.getController();
        controller.adatTranszfer(palyazat);
        Stage stage = new Stage();
        stage.setScene(palyazatSzerkesztoScene);
        stage.setTitle("A résztvevő oktatók");
        stage.setScene(palyazatSzerkesztoScene);
//        stage.setX((Screen.getPrimary().getBounds().getMaxX() - palyazatSzerkesztoScene.getWidth())/2);
        stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        stage.show();
    }

    @FXML
    private void harmadikScene(ActionEvent event) throws IOException {
        mezoUpdate();
        Scene palyazatSzerkesztoScene;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/gyula/palyazatFXML/palyazatSzerkeszto2.fxml"));
        Parent palyazatValasztoParent = loader.load();
        palyazatSzerkesztoScene = new Scene(palyazatValasztoParent);
        palyazatSzerkeszto2Controller controller = loader.getController();
        controller.adatTranszfer(palyazat);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(palyazatSzerkesztoScene);
        stage.setTitle("A pályázat szerkesztése");
        stage.setScene(palyazatSzerkesztoScene);
        stage.setX((Screen.getPrimary().getBounds().getMaxX() - palyazatSzerkesztoScene.getWidth())/2);
        stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        stage.show();
    }


    private void mezoUpdate() {
        if (!szakmaiField.getText().isEmpty()) {//csak akkor lehet nem ures, ha az Egyeb kategoriat valasztottuk, de ha nem irtunk be semmit, azt ki kell zarni
        resztvevok.setSzakmaiVezeto(szakmaiField.getText());
        }else {
            resztvevok.setSzakmaiVezeto(szakmaiCombo.getValue());
        }

        if (!menedzserField.getText().isEmpty()) {
        resztvevok.setSzakmaiVezeto(menedzserField.getText());
        }else {
            resztvevok.setProjektmenedzser(menedzserCombo.getValue());
        }
        resztvevok.setKezelo(kezeloField.getText());
        resztvevok.setResztvevoEmberek(palyazatLekerdezesek.osszesResztvevo(palyazat.getPalyazatCim()));//ezt mar frissitettuk a resztvevoSzerkeszto
                                            // metodussal egy kulon ablakban, de ha itt nem adom hozza, akkor a vegso mentesnel az eltunik
                                            //nem lehet a palyazat peldanybol lekerdezni, abban a regi allapot van, kozvetlenul a MongoDB-bol kell
        palyazat.setResztvevok(resztvevok);
        palyazat.setKezdet(kezdoDatum.getValue());
        palyazat.setVeg(zaroDatum.getValue());
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


}
