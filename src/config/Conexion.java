package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Conexion {
    
    public static Connection getConector() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String URL="jdbc:mysql://localhost:3306/formulario";
            conexion=DriverManager.getConnection(URL,"root","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }

}