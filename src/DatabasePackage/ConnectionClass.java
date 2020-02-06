package DatabasePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

        //   connected to sql server
        Connection sqlServerConnection;
        public  Connection getConnection() {

            String connectionUrl = "jdbc:sqlserver://LAPTOP-PG2CGQUG; database=WinheProject;integratedSecurity=true";

            try {
                sqlServerConnection = DriverManager.getConnection(connectionUrl);

            } catch (SQLException e) {
                System.out.println("Problem with connecting to the database");
//                e.printStackTrace();
            }

            return sqlServerConnection;
        }
}





////////////////////////////////// Coba to Connect Different programming languages ////////////////////////////////