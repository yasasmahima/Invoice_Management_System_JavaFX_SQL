package sample;

import Controllers.ConsoleApplication;
import Controllers.CustomerController;
import Controllers.ProductController;
import Models.Customer;
import Models.Date;
import Models.Gender;
import com.sun.corba.se.impl.io.TypeMismatchException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.AddCustmer.AddCustomer;
import sample.AddProducts.AddProducts;
import sample.AddedSucess.SuccessCustomer;
import sample.AddedSucess.SucessProduct;
import sample.Customers.Customers;
import sample.DeleteSuccess.DeleteSuccess;
import sample.EditProducts.EditProducts;
import sample.EditSuccess.EditSucess;
import sample.Home.Main;
import sample.Products.Product;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

public class Controller  {

    Stage primaryStage=new Stage();
    @FXML private javafx.scene.control.Button products;
    @FXML private javafx.scene.control.Button home;
    @FXML private javafx.scene.control.Button backProduct;
    @FXML private javafx.scene.control.Button customers;
    @FXML private javafx.scene.control.Button backCustomer;


//    Adding A Product
@FXML private javafx.scene.control.TextField addName;
@FXML private javafx.scene.control.TextArea addDescription;
@FXML private javafx.scene.control.TextField addPurchasePrice;
@FXML private javafx.scene.control.TextField addSellingPrice;
@FXML private javafx.scene.control.TextField addQuantity;
@FXML private javafx.scene.control.Button addedProducts;



//    Adding a Customer
@FXML private javafx.scene.control.TextField addCustomerName;
@FXML private javafx.scene.control.TextField addEmail;
@FXML private javafx.scene.control.TextField addContactNo;
@FXML private javafx.scene.control.TextArea addAddress;
@FXML private javafx.scene.control.DatePicker addDateOfBirth;
@FXML private javafx.scene.control.RadioButton maleRadio;
@FXML private javafx.scene.control.RadioButton femaleRadio;
@FXML private javafx.scene.control.Button addedCustomers;
@FXML ToggleGroup group;


//      Edit Product

    @FXML private javafx.scene.control.TextField pId;
    @FXML private javafx.scene.control.TextField pName;
    @FXML private javafx.scene.control.TextField purPrice;
    @FXML private javafx.scene.control.TextField sellPrice;
    @FXML private javafx.scene.control.TextField quantity;
    @FXML private javafx.scene.control.TextArea  pDesc;
    @FXML private javafx.scene.control.Button  editPro;

//    Delete Products

    @FXML private javafx.scene.control.Button  deletedProducts;
    @FXML private javafx.scene.control.TextField deletedId;





    @FXML
    public  void buttonProducts(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) products.getScene().getWindow();
        Product product=new Product();
        product.start(primaryStage);
        stage.close();

    }

    public void buttonBack(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) home.getScene().getWindow();
        Main main=new Main();
        main.start(primaryStage);
        stage.close();
    }

    public void setBackProduct(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) backProduct.getScene().getWindow();
        Product product=new Product();
        product.start(primaryStage);
        stage.close();
    }

    public void setBackCustomer(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) backCustomer.getScene().getWindow();
        Customers customers=new Customers();
        customers.start(primaryStage);
        stage.close();
    }


    public  void buttonCustomers(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) customers.getScene().getWindow();
        Customers customers=new Customers();
        customers.start(primaryStage);
        stage.close();

    }

    ////////////////////////////////////////////////////// Add Products To Database ////////////////////////////////////////////////////////
    static int productId=1;

    public void addProductsDatabase(ActionEvent actionEvent) {

        try {
            String productName=addName.getText();
            String productDescription=addDescription.getText();
            double purchasePrice=Double.parseDouble(addPurchasePrice.getText());
            double sellingPrice=Double.parseDouble(addSellingPrice.getText());
            int quantity=Integer.parseInt(addQuantity.getText());

            if(productName.equals("")||productDescription.equals("")||purchasePrice==0||sellingPrice==0||quantity==0){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setContentText("Please Fill Required Fields");
                alert.showAndWait();
            }else {

                Models.Product product = new Models.Product(String.valueOf(""), productName, productDescription, purchasePrice, sellingPrice, quantity);
                ProductController productController = new ProductController();
                productController.addProducts(product);
                productId += 1;

                Stage stage = (Stage) addedProducts.getScene().getWindow();
                SucessProduct sucessProduct = new SucessProduct();
                sucessProduct.start(primaryStage);
                stage.close();
                System.out.println("Added SuccessFull");

            }

        }catch (SQLException e){
            System.out.println("Cannot add to Database");
        }catch (IllegalArgumentException e){
            System.out.println("Invalid Data Types");
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Please Check Required Fields");
            alert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /////////////////////////////////////////////////  Add Customers To Database /////////////////////////////////////////

    public void addCustomersDatabase(ActionEvent actionEvent) {

        try {

            String customerName = addCustomerName.getText();
            String email = addEmail.getText();
            String address = addAddress.getText();
            String contactNo = addContactNo.getText();
            LocalDate date = addDateOfBirth.getValue();

            RadioButton selected = (RadioButton) group.getSelectedToggle();

            if (selected == null || customerName.equals("") || email.equals("") || address.equals("") || contactNo.equals("") || date.equals("")) {
                System.out.println("Invalid Values");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setContentText("Please Fill Required Fields");
                alert.showAndWait();
            } else {

                String gender = selected.getText();

                Gender customerGender = Gender.valueOf(gender.toUpperCase());

                Customer customer = new Customer(String.valueOf(""), customerName, email, address, contactNo, date, customerGender);

                CustomerController customerController = new CustomerController();
                customerController.addCustomer(customer);

                Stage stage = (Stage) addedCustomers.getScene().getWindow();
                SuccessCustomer successCustomer = new SuccessCustomer();
                successCustomer.start(primaryStage);
                stage.close();
                System.out.println("Added SuccessFull");

            }
            }catch(SQLException e){
                System.out.println("Cannot Send Data to Database");
//            e.printStackTrace();
            }catch(IllegalArgumentException e){
                System.out.println("Invalid Values");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setContentText("Please Check Required Fields");
                alert.showAndWait();
            }catch(TypeMismatchException e){
                System.out.println("Invalid Data Types");
            } catch(IOException e){
                e.printStackTrace();
            }catch(NullPointerException e){

            }
        }


        ///////////////////////////////////////////////////   Edit Details //////////////////////////////////////////

    public void editProduct(ActionEvent actionEvent) {

        try {
            int ediProductId = Integer.parseInt(pId.getText());
            String editProductName = pName.getText();
            String editDescription = pDesc.getText();
            double editPurchasePrice = Double.parseDouble(purPrice.getText());
            double editSelPrice = Double.parseDouble(sellPrice.getText());
            int editQuantity = Integer.parseInt(quantity.getText());

            if (ediProductId==0||editProductName.equals("")||editDescription.equals("")||editPurchasePrice==0||editSelPrice==0||editQuantity==0) {
                System.out.println("Invalid Values");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setContentText("Please Check Required Fields");
                alert.showAndWait();
            }else {

                Models.Product product = new Models.Product(String.valueOf(""), editProductName, editDescription, editPurchasePrice, editSelPrice, editQuantity);
                ProductController productController = new ProductController();

                try {
                    productController.editProducts(product, ediProductId);
                    Stage stage = (Stage) editPro.getScene().getWindow();
                    EditSucess editSucess=new EditSucess();
                    editSucess.start(primaryStage);
                    stage.close();
                    System.out.println("Edit Success");
                } catch (SQLException e) {
                    System.out.println("Invalid ID");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("WARNING");
                    alert.setContentText("Please Check Product ID");
                    alert.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (IllegalArgumentException e){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("WARNING");
                    alert.setContentText("Please Check Product ID\nThis is Not Exist in the Database");
                    alert.showAndWait();
                }
            }
            }catch (NumberFormatException e){
//            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Please Check Required Fields");
            alert.showAndWait();
        }
    }




    public void deleteProduct(ActionEvent actionEvent){

        try {
            int deleted=Integer.parseInt(deletedId.getText());
            ProductController productController=new ProductController();
            productController.deleteProducts(deleted);
            Stage stage = (Stage) deletedProducts.getScene().getWindow();
            DeleteSuccess deleteSuccess=new DeleteSuccess();
            deleteSuccess.start(primaryStage);
            stage.close();
            System.out.println("Delete Success");

        }catch (IllegalArgumentException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Please Check Product ID\nThis is Not Exist in the Database");
            alert.showAndWait();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}


//




