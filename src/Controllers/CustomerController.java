package Controllers;

import DatabasePackage.ConnectionClass;
import Models.Customer;
import Models.CustomerInterface;
import Models.Gender;

import java.sql.*;
import java.util.ArrayList;

public class CustomerController implements CustomerInterface {

    ConnectionClass connectionClass=new ConnectionClass();
    Connection sqlServerConnection=connectionClass.getConnection();

    ArrayList<Customer> allCustomers=new ArrayList<>();
    DataIO getData=new DataIO();

    @Override
    public void addCustomer(Customer customer) throws SQLException {

        allCustomers.add(customer);
        PreparedStatement statement = null;
        statement=sqlServerConnection.prepareStatement("INSERT INTO Customers(customerName,customerEmail,customerAddress,contactNo,dob,gender)VALUES (?,?,?,?,?,?)");
        statement.setString(1,customer.getCustomerName());
        statement.setString(2,customer.getCustomerEmail());
        statement.setString(3,customer.getCustomerAddress());
        statement.setString(4,customer.getCustomerContactNo());
        statement.setString(5,String.valueOf(customer.getCustomerDOB()));
        statement.setString(6,String.valueOf(customer.getGender()));
        statement.executeUpdate();
    }

    @Override
    public Customer searchCustomers(String customerID) throws SQLException {

        Statement stm=sqlServerConnection.createStatement();
        ResultSet customerResultSet=stm.executeQuery("EXEC searchCustomer'" + customerID + "'");

        Gender gender=Gender.valueOf(customerResultSet.getString("gender"));

        Customer customer=new Customer(customerResultSet.getString("customerId"),customerResultSet.getString("customerName"),customerResultSet.getString("customerEmail"),customerResultSet.getString("customerAddress"),customerResultSet.getString("contactNo"),gender);
        return customer;
    }
}
