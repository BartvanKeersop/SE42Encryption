package KeyReader;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

public class KeyReaderMain extends Application {

    @FXML
    Button btnSign = new Button();

    @FXML
    TextField tfSignedBy = new TextField();

    private KeyReaderController controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("keyreader.fxml"));
        primaryStage.setTitle("Key Reader");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        controller = new KeyReaderController();
        sign();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void sign(){

        System.out.println("---Signing File--- \n");
        try {
            controller.sign("Bart");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("---Algorithm Error--- \n");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            System.out.println("---Invalid Key Error--- \n");
        } catch (SignatureException e) {
            e.printStackTrace();
            System.out.println("---Signature Error--- \n");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("---IO Error--- \n");
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            System.out.println("---Invalid KeySpec Error--- \n");
        }
    }
}
