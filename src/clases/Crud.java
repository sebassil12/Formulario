package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.Conexion;

public class Crud {
    public static void ingresarPC(String disco_duro, String procesador, String ram) {
        String sql = "INSERT INTO tbl_computador (disco_duro, procesador, ram) VALUES (?, ?, ?)";

        try{
        	Connection conexion;        
            conexion=Conexion.getConector();
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, disco_duro);
            pstmt.setString(2, procesador);
            pstmt.setString(3, ram);
            pstmt.executeUpdate();
            System.out.println("REGISTRO ALMACENADO EXITOSAMENTE");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void actualizarPC(int id, String disco_duro, String procesador, String ram) {
        String sql = "UPDATE tbl_computador SET disco_duro = ?, procesador = ?, ram = ? WHERE id = ?";

        try {
            Connection conexion = Conexion.getConector();
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, disco_duro);
            pstmt.setString(2, procesador);
            pstmt.setString(3, ram);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            System.out.println("REGISTRO ACTUALIZADO EXITOSAMENTE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void eliminarPC(int id) {
        String sql = "DELETE FROM tbl_computador WHERE id = ?";

        try {
            Connection conexion = Conexion.getConector();
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("REGISTRO ELIMINADO EXITOSAMENTE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listarPCs() {
        String sql = "SELECT * FROM tbl_computador";

        try {
            Connection conexion = Conexion.getConector();
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String disco_duro = rs.getString("disco_duro");
                String procesador = rs.getString("procesador");
                String ram = rs.getString("ram");
                System.out.println("ID: " + id + ", Disco Duro: " + disco_duro + ", Procesador: " + procesador + ", RAM: " + ram);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
