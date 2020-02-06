package Models;

import java.sql.SQLException;

public interface ProductsInterface {

    void addProducts(Product product) throws SQLException;

    Product searchProducts(String productId) throws SQLException;

    void deleteProducts(int productId) throws SQLException;

    void editProducts(Product product,int id) throws SQLException;
}
