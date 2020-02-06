package sample.Invoice;

import DatabasePackage.ConnectionClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class Invoice extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Invoice.fxml"));

        primaryStage.setTitle("Invoice Management");
        primaryStage.setScene(new Scene(root, 1290, 680));
        primaryStage.show();

        ConnectionClass connectionClass=new ConnectionClass();
        Connection sqlServerConnection=connectionClass.getConnection();
    }
}
