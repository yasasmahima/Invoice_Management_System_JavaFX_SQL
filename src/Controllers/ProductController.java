package Controllers;

import DatabasePackage.ConnectionClass;
import Models.Product;
import Models.ProductsInterface;

import java.sql.*;
import java.util.ArrayList;

public class ProductController implements ProductsInterface {

    ConnectionClass connectionClass=new ConnectionClass();
    Connection sqlServerConnection=connectionClass.getConnection();

    ArrayList<Product> allProducts=new ArrayList<>();
    DataIO getData=new DataIO();

    @Override
    public void addProducts(Product product) throws SQLException {
        allProducts.add(product);
        PreparedStatement statement = null;

            statement=sqlServerConnection.prepareStatement("INSERT INTO Products(productName,productDescription,purshasePrice,sellingPrice,quantity)VALUES (?,?,?,?,?)");
//            statement.setString(1,product.getProductId());
            statement.setString(1,product.getProductName());
            statement.setString(2,product.getProductDescription());
            statement.setDouble(3,product.getPurchasePrice());
            statement.setDouble(4,product.getSellingPrice());
            statement.setInt(5,product.getQuantity());
            statement.executeUpdate();
            System.out.println("Successfully Added Product To List");

        System.out.println();
    }

    @Override
    public Product searchProducts(String productId) throws SQLException {

            Statement stm=sqlServerConnection.createStatement();
            ResultSet productResultSet=stm.executeQuery("EXEC searchProduct '" + productId + "'");
            Product product=new Product(productResultSet.getString("productId"),productResultSet.getString("productName"),productResultSet.getString("productDescription"),productResultSet.getDouble("purshasePrice"),productResultSet.getDouble("sellingPrice"),productResultSet.getInt("quantity"));
            return product;
    }

    @Override
    public void deleteProducts(int productId) throws SQLException {

            Statement stm=sqlServerConnection.createStatement();
            ResultSet productResultSet=stm.executeQuery("EXEC searchProduct '" + productId + "'");

            if(productResultSet.next()){

                String deleteProduct="EXEC deleteProduct '"+productId +"'";
                Statement deleteStatement = sqlServerConnection.createStatement();
                deleteStatement.executeUpdate(deleteProduct);
                System.out.println("Successfully Deleted Product From the Database");

            }else{
                System.out.println("This Product is Not Available in the Database");
                throw new IllegalArgumentException();
            }
    }

    @Override
    public void editProducts(Product product,int id) throws SQLException {

        Statement stm=sqlServerConnection.createStatement();
        ResultSet productResultSet=stm.executeQuery("EXEC searchProduct '" + id + "'");

        if(productResultSet.next()) {

            PreparedStatement statement = null;
            statement = sqlServerConnection.prepareStatement("EXEC UpdateTable ?,?,?,?,?,?");
            statement.setInt(1, id);
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getProductDescription());
            statement.setDouble(4, product.getPurchasePrice());
            statement.setDouble(5, product.getSellingPrice());
            statement.setInt(6, product.getQuantity());
            statement.executeUpdate();

        }else{
            System.out.println("This Product is Not Available in the Database");
            throw new IllegalArgumentException();
        }


    }
}
