package sample.AddedSucess;

import DatabasePackage.ConnectionClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class SucessProduct extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("sucess.fxml"));
        primaryStage.setTitle("Success");
        primaryStage.setScene(new Scene(root, 1290, 680));
        primaryStage.show();

        ConnectionClass connectionClass=new ConnectionClass();
        Connection sqlServerConnection=connectionClass.getConnection();

    }
}