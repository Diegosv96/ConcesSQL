package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    public Connection getConnection(){

        if(connection == null){
            createConnection();
        }

        return connection;
    }

    private void createConnection() {

        String url = "jdbc:mysql://127.0.0.1:3306/concesionario";

        try {
            connection = DriverManager.getConnection(url,"root","");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Conexión creada");
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
