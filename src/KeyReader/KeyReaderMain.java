package KeyReader;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class KeyReaderMain extends Application {

    @FXML
    Button btnSign;

    @FXML
    TextField tfSignedBy;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("keyreader.fxml"));
        primaryStage.setTitle("Key Reader");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public void initializeControls(){
        btnSign = new Button();
        tfSignedBy = new TextField();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
