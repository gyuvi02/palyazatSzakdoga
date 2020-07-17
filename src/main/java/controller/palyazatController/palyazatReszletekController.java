package controller.palyazatController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import okatok.Oktato;
import palyazatok.Palyazat;

public class palyazatReszletekController {
    Palyazat kivalasztott = new Palyazat();

    @FXML
    public TextArea reszletek;

    @FXML
    private Button kilepesGomb;

    @FXML
    private void kilep() {
        Stage ablak = (Stage) kilepesGomb.getScene().getWindow();
        ablak.close();
    }

    public void adatTranszfer(Palyazat palyazat) {
        kivalasztott = palyazat;
        reszletek.setText(Palyazat.toStringHelyettPalyazat(kivalasztott));
        reszletek.setWrapText(true);
    }

}
