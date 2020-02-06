package sample.Products;

import DatabasePackage.ConnectionClass;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.AddProducts.AddProducts;
import sample.Controller;
import sample.EditProducts.EditProducts;
import sample.Home.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Product extends Application  {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Product.fxml"));

        primaryStage.setTitle("Product Controller");
        primaryStage.setScene(new Scene(root, 1290, 680));
        primaryStage.show();






//        Controller controller=new Controller();
//        controller.buildDataCD();

        ConnectionClass connectionClass=new ConnectionClass();
        Connection sqlServerConnection=connectionClass.getConnection();
    }


}
