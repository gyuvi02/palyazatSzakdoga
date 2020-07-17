package controller.felhivasController;

import felhivasok.Felhivas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class felhivasReszletekController {

    String felhivasLink;

    @FXML
    private Button kilepesGomb;

    @FXML
    public TextArea reszletek;

    @FXML
    private Hyperlink hyperlink;

    @FXML
    private Label pafiLink;

    @FXML
    public void adatTranszfer(String felhivas) {
        ArrayList<Felhivas> lista = new Felhivas().felhivasLetolto(felhivas);
        reszletek.setText(lista.size() + " felhívást találtam ezzel a címmel:\n\n" +
                Felhivas.toStingHelyettFelhivas(lista));
        reszletek.setWrapText(true);
        felhivasLink = lista.get(0).getFelhivasLink();
        hyperlink.setText(lista.get(0).getFelhivasCim());
        reszletek.setWrapText(true);

    }

    @FXML
    private void felhivasLink() throws IOException {
        URI myUri = URI.create(felhivasLink);
        Desktop.getDesktop().browse(myUri);
    }


    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

}
