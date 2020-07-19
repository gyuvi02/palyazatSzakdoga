package org.gyula;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import palyazatkezelo.MongoAccess;

import java.io.IOException;


public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Pályázatkezelő program");
        scene = new Scene(loadFXML("kezdooldal"));
        stage.setScene(scene);
        stage.getIcons().add(new Image("/org/gyula/images/egyetemlogo.png"));
        stage.show();
        try {
            MongoAccess.getConnection();
        } catch (Exception e) {
            System.out.println("Problema a kapcsolattal");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Hiba!");
            alert.setHeaderText("Sajnos valami probléma van az internet kapcsolattal");
            alert.setContentText("Az alkalmazás most leáll, kérem, próbálja meg később!");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(MongoAccess.class.getResource("/org/gyula/dialogCSS.css").toExternalForm());
            dialogPane.getStyleClass().add("/org/gyula/dialogCSS.css");
            alert.showAndWait();
            System.exit(0);
        }
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        InternetEllenorzo.ellenoriz();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}