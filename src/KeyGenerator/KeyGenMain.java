package KeyGenerator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Bart van Keersop on 6/20/2016.
 */
public class KeyGenMain extends Application {
    KeyGenController controller;

        @Override
        public void start(Stage primaryStage) throws Exception{
            controller = new KeyGenController();
            Parent root = FXMLLoader.load(getClass().getResource("keygen.fxml"));
            primaryStage.setTitle("Key Generator");
            primaryStage.setScene(new Scene(root, 300, 275));
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
}
