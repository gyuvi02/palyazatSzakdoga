package org.gyula.controller.oktatoController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.gyula.okatok.Oktato;
import org.gyula.okatok.OktatoLekerdezes;
import org.gyula.okatok.OktatoModosito;
import org.gyula.palyazatok.PalyazatiTemak;

import java.util.ArrayList;

public class OktatoPalyazatiModositoController {
    Oktato aktualisOktato = new Oktato();
    ArrayList<String> aktualisLista = new ArrayList<>();
    PalyazatiTemak palyazatiTemak = new PalyazatiTemak();
//    ArrayList<String> maradek = new ArrayList<>(palyazatiTemak.temaLetolt()); //igy csak a kari temak kozul lehet valasztani, de inkabb az osszes pafi.hu temabol lehessen
    ArrayList<String> maradek = new ArrayList<>(palyazatiTemak.pafiTemaLetolt()); //igy a maradek az osszes lehetseges pafi temat tartalmazza
//    ArrayList<String> relevansTemak = new PalyazatiTemak().temaLetolt(); //az eppen aktualis kari kutatasi temak letoltese

    @FXML
    public Button mentesGomb;

    @FXML
    private Button kilepesGomb;

    @FXML
    private ListView<String> oktatoLista;

    @FXML
    private ListView<String> teljesLista;

    @FXML
    private Label oktatoNev;

    public OktatoPalyazatiModositoController() throws InterruptedException {
    }

    //    @FXML
//    private Button hozzaad;
//
//    @FXML
//    private Button elvesz;
//
    public void initialize() {
    }

    @FXML
    private void uresMezo() {
        boolean disableButtons = oktatoLista.getItems().isEmpty();
        mentesGomb.setDisable(disableButtons);
        if (disableButtons) {
            oktatoLista.setPlaceholder(new Label("Legalább egy kategóriát \n ki kell választani!"));
        }
    }

    @FXML
    public void pTemaTranszfer(Oktato oktato) {
        aktualisOktato = oktato;
        aktualisLista = new ArrayList<>(Oktato.oktatoLetolto(oktato.getNev()).getPalyazatiTema());//itt nem a peldanybol kell kiolvasni, hanem az adatbazisbol
        oktatoNev.setText(oktato.getNev() + "\n pályázati témái");
        oktatoLista.getItems().setAll(aktualisLista);
        maradek.removeAll(aktualisLista);
//        teljesLista.getItems().setAll(maradek);
        listaMegjelenito();
    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

    @FXML
    private void pluszTema() {
        if (!teljesLista.getSelectionModel().getSelectedItems().isEmpty() && !maradek.isEmpty()) {
            maradek.remove(teljesLista.getSelectionModel().getSelectedItem());
            aktualisLista.add(teljesLista.getSelectionModel().getSelectedItem());
            listaMegjelenito();
        }
    }

    @FXML
    private void minuszTema() {
        if (!oktatoLista.getSelectionModel().getSelectedItems().isEmpty() && !aktualisLista.isEmpty()) {
            aktualisLista.remove(oktatoLista.getSelectionModel().getSelectedItem());
            maradek.add(oktatoLista.getSelectionModel().getSelectedItem());
            listaMegjelenito();
            uresMezo();
        }
    }

    @FXML
    private void listaMegjelenito() {
        maradek.sort(String.CASE_INSENSITIVE_ORDER);
        aktualisLista.sort(String.CASE_INSENSITIVE_ORDER);
        teljesLista.getItems().setAll(maradek);
        oktatoLista.getItems().setAll(aktualisLista);
    }

    @FXML
    private void mentes() throws InterruptedException {
        OktatoModosito modosito = new OktatoModosito();
        modosito.tombFrissito("palyazatiTema", aktualisOktato.getNev(), aktualisLista );
        OktatoLekerdezes oktatoLekerdezes = new OktatoLekerdezes();
        PalyazatiTemak ujTemaLista = new PalyazatiTemak();
        ujTemaLista.setTemak(oktatoLekerdezes.kutatasiTemak("Minden tanszék", "palyazat"));//itt kerem le az osszes tanszek palyazati temait
        ujTemaLista.temafeltolt(); //ezzel feltoltjuk a Temak collectoionbe az aktualis listat, ezt hasznalja majd az RSS parser
        mentesDialog();
        kilep();
    }

    private void mentesDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mentés");
        alert.setHeaderText(aktualisOktato.getNev() );
        alert.setContentText("pályázati témáit elmentettük");
        alert.getDialogPane().getScene().getStylesheets().add("org/gyula/dialogCSS.css");
        alert.showAndWait();
    }


}
