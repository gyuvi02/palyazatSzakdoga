package controller.felhivasController;

import felhivasok.Felhivas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.ArrayList;

public class felhivasReszletekController {

    @FXML
    private Button kilepesGomb;

    @FXML
    public TextArea reszletek;

    @FXML
    public void adatTranszfer(String felhivas) {
        Felhivas hivas = new Felhivas();
        ArrayList<Felhivas> lista =new Felhivas().felhivasLetolto(felhivas);
        reszletek.setText(lista.size() + " felhívást találtam ezzel a címmel:\n\n" +
                hivas.toStingHelyett(lista));
        reszletek.setWrapText(true);

    }

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

}
