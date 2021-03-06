package KeyVerifier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KeyVerifierMain extends Application {
    KeyVerifierController controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        controller = new KeyVerifierController();
        Parent root = FXMLLoader.load(getClass().getResource("keyverifier.fxml"));
        primaryStage.setTitle("Key Verifier");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
