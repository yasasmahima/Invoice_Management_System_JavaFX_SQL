package sample.Products;

import DatabasePackage.ConnectionClass;
import Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.AddProducts.AddProducts;
import sample.DeleteProduct.DeleteProduct;
import sample.EditProducts.EditProducts;
import sample.Home.Main;

import javax.xml.transform.Result;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GuiProductController implements Initializable {

    ConnectionClass connectionClass=new ConnectionClass();
    Connection sqlServerConnection=connectionClass.getConnection();


    @FXML
    private javafx.scene.control.TableView<Models.Product> tableView;
    @FXML private TableColumn<Product, String> UserId;
    @FXML private TableColumn<Models.Product, String> UserName;
    @FXML private TableColumn<Models.Product, String> Active;


    @FXML private javafx.scene.control.Button home;
    @FXML private javafx.scene.control.Button addProducts;
    @FXML private javafx.scene.control.Button editProducts;
    @FXML private javafx.scene.control.Button deleteProducts;





    @FXML private javafx.scene.control.TableColumn<Models.Product,String> prodId;
    @FXML private javafx.scene.control.TableColumn<Models.Product,String> proName;
    @FXML private javafx.scene.control.TableColumn<Models.Product,String> proDes;
    @FXML private javafx.scene.control.TableColumn<Models.Product,String> purPrice;
    @FXML private javafx.scene.control.TableColumn<Models.Product,String> selPrice;
    @FXML private javafx.scene.control.TableColumn<Models.Product,String> qua;
    @FXML private javafx.scene.control.TableView<Models.Product> tableProducts;

    @FXML private javafx.scene.control.TextField filterFiled;


    Stage primaryStage=new Stage();


    public void buttonBack(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) home.getScene().getWindow();
        Main main=new Main();
        main.start(primaryStage);
        stage.close();
    }

    public void addProducts(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) addProducts.getScene().getWindow();
        AddProducts addProducts=new AddProducts();
        addProducts.start(primaryStage);
        stage.close();
    }

    public void editProducts(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) editProducts.getScene().getWindow();
        EditProducts editProducts=new EditProducts();
        editProducts.start(primaryStage);
        stage.close();
    }

    public void deleteProducts(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) deleteProducts.getScene().getWindow();
        DeleteProduct deleteProduct=new DeleteProduct();
        deleteProduct.start(primaryStage);
        stage.close();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        prodId.setCellValueFactory(new PropertyValueFactory<Models.Product, String>("productId"));
        proName.setCellValueFactory(new PropertyValueFactory<Models.Product, String>("productName"));
        proDes.setCellValueFactory(new PropertyValueFactory<Models.Product, String>("productDescription"));
        purPrice.setCellValueFactory(new PropertyValueFactory<Models.Product, String>("purchasePrice"));
        selPrice.setCellValueFactory(new PropertyValueFactory<Models.Product, String>("sellingPrice"));
        qua.setCellValueFactory(new PropertyValueFactory<Models.Product, String>("quantity"));



        try {

            ////////////////////////////////////////// Filtering /////////////////////////////////////////////

            FilteredList<Product> filteredData = new FilteredList<>(parseUserList(), p -> true);

            filterFiled.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(product -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (product.getProductName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (product.getProductName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });

            System.out.println("Loading Data");
            SortedList<Product> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(tableProducts.comparatorProperty());

            tableProducts.setItems(sortedData);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private ObservableList<Product> parseUserList() throws SQLException {
//        List<Models.Product> products = new ArrayList<>();
        ObservableList<Product> products = FXCollections.observableArrayList();

        Statement statement = sqlServerConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Products");
        while (resultSet.next()) {

            Models.Product product = new Models.Product(resultSet.getString("productId"), resultSet.getString("productName"),resultSet.getString("productDescription"),resultSet.getDouble("purshasePrice"),resultSet.getDouble("sellingPrice"),resultSet.getInt("quantity"));

            products.add(product);

        }
        return products;
    }

}
