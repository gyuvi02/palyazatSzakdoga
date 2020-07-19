package org.gyula;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import palyazatkezelo.MongoAccess;

import java.net.URL;
import java.net.URLConnection;

public class InternetEllenorzo {
    public static void ellenoriz()
    {
        try {
            URL url = new URL("https://www.mongodb.com/");
            URLConnection connection = url.openConnection();
            connection.connect();

            System.out.println("A kapcsolat rendben");
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Hiba!");
            alert.setHeaderText("Úgy tűnik, megszakadt a kapcsolat az adatbázissal.");
            alert.setContentText("Az alkalmazás most leáll, kérem, próbálja meg később!");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(MongoAccess.class.getResource("/org/gyula/dialogCSS.css").toExternalForm());
            dialogPane.getStyleClass().add("/org/gyula/dialogCSS.css");
            alert.showAndWait();
            System.exit(0);
        }
    }
}
