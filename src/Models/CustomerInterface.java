package Models;

import java.sql.SQLException;

public interface CustomerInterface {

    void addCustomer(Customer customer) throws SQLException;

    Customer searchCustomers(String customerID) throws SQLException;
}
