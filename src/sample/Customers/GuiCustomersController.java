package sample.Customers;

import DatabasePackage.ConnectionClass;
import Models.Customer;
import Models.Gender;
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
import sample.AddCustmer.AddCustomer;
import sample.Home.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GuiCustomersController implements Initializable {

    ConnectionClass connectionClass=new ConnectionClass();
    Connection sqlServerConnection=connectionClass.getConnection();

    Stage primaryStage=new Stage();

    @FXML
    private javafx.scene.control.Button home;
    @FXML private javafx.scene.control.Button addCustomers;



    @FXML private TableColumn<Customer, String> cusId;
    @FXML private javafx.scene.control.TableColumn<Models.Customer,String> cusName;
    @FXML private javafx.scene.control.TableColumn<Models.Customer,String> cusMail;
    @FXML private javafx.scene.control.TableColumn<Models.Customer,String> address;
    @FXML private javafx.scene.control.TableColumn<Models.Customer,String> contact;
    @FXML private javafx.scene.control.TableColumn<Models.Customer,String> gender;
    @FXML private javafx.scene.control.TableView<Models.Customer> customerTable;


    @FXML private javafx.scene.control.TextField filterFiled;



    public void buttonBack(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) home.getScene().getWindow();
        Main main=new Main();
        main.start(primaryStage);
        stage.close();
    }


    public  void addCustomers(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) addCustomers.getScene().getWindow();
        AddCustomer addCustomer=new AddCustomer();
        addCustomer.start(primaryStage);
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cusId.setCellValueFactory(new PropertyValueFactory<Models.Customer, String>("customerID"));
        cusName.setCellValueFactory(new PropertyValueFactory<Models.Customer, String>("customerName"));
        cusMail.setCellValueFactory(new PropertyValueFactory<Models.Customer, String>("customerEmail"));
        address.setCellValueFactory(new PropertyValueFactory<Models.Customer, String>("customerAddress"));
        contact.setCellValueFactory(new PropertyValueFactory<Models.Customer, String>("customerContactNo"));
        gender.setCellValueFactory(new PropertyValueFactory<Models.Customer, String>("gender"));

        try {

            /////////////////////////////////////////// Filtering ///////////////////////////////////////////////////////////////

            FilteredList<Customer> filteredData = new FilteredList<>(parseUserList(), p -> true);

            filterFiled.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(customer -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (customer.getCustomerName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (customer.getCustomerName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });

            System.out.println("Loading Data");
            SortedList<Customer> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(customerTable.comparatorProperty());

            customerTable.setItems(sortedData);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<Customer> parseUserList() throws SQLException {

        ObservableList<Customer> customers = FXCollections.observableArrayList();

        Statement statement = sqlServerConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Customers");
        while (resultSet.next()) {
            Gender gender=Gender.valueOf(resultSet.getString("gender").toUpperCase());

            Models.Customer customer = new Models.Customer(resultSet.getString("customerId"), resultSet.getString("customerName"),resultSet.getString("customerEmail"),resultSet.getString("customerAddress"),resultSet.getString("contactNo"),gender);
            customers.add(customer);
        }
        return customers;
    }


//    private ObservableList<Customer> parseCustomerList() throws SQLException{
//        ObservableList<Customer> customerList=FXCollections.observableArrayList();
//        Statement statement=sqlServerConnection.createStatement();
//        ResultSet resultSet=statement.executeQuery("SELECT * FROM Cstomers");
//
//        while (resultSet.next()){
//            Gender gender=Gender.valueOf(resultSet.getString("gender").toUpperCase());
//
//            Models.Customer customer=new Models.Customer(())
//        }
//
//
//    }
}
