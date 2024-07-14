package clases;

import POO.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import config.Conexion;

public class Crud {
    public static void insertUser(User user) {
        String sql = "INSERT INTO usuarios (nombres, apellidos, fecha_nacimiento, sexo, foto, colegio, universidad, trabajo, dato_referente) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try{
        	Connection conexion;        
            conexion=Conexion.getConector();
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, user.getNombres());
            pstmt.setString(2, user.getApellidos());
            pstmt.setDate(3, new java.sql.Date(user.getFechaNacimiento().getTime())); // Convertir Date a java.sql.Date
            pstmt.setString(4, user.getSexo());
            pstmt.setString(5, user.getFoto());
            pstmt.setString(6, user.getColegio());
            pstmt.setString(7, user.getUniversidad());
            pstmt.setString(8, user.getTrabajo());
            pstmt.setString(9, user.getDatoReferente());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(User user) {
        String sql = "UPDATE usuarios SET nombres=?, apellidos=?, fecha_nacimiento=?, sexo=?, foto=?, colegio=?, universidad=?, trabajo=?, dato_referente=? " +
                     "WHERE id=?";

        try{
            Connection conexion = Conexion.getConector();
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, user.getNombres());
            pstmt.setString(2, user.getApellidos());
            pstmt.setDate(3, new java.sql.Date(user.getFechaNacimiento().getTime())); // Convertir Date a java.sql.Date
            pstmt.setString(4, user.getSexo());
            pstmt.setString(5, user.getFoto());
            pstmt.setString(6, user.getColegio());
            pstmt.setString(7, user.getUniversidad());
            pstmt.setString(8, user.getTrabajo());
            pstmt.setString(9, user.getDatoReferente());
            pstmt.setInt(10, user.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try{
            Connection conexion = Conexion.getConector();
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                String sexo = rs.getString("sexo");
                String foto = rs.getString("foto");
                String colegio = rs.getString("colegio");
                String universidad = rs.getString("universidad");
                String trabajo = rs.getString("trabajo");
                String datoReferente = rs.getString("dato_referente");

                User user = new User(id, nombres, apellidos, fechaNacimiento, sexo, foto, colegio, universidad, trabajo, datoReferente);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static void deleteUser(int userId) {
        String sql = "DELETE FROM usuarios WHERE id=?";

        try{
            Connection conexion = Conexion.getConector();
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
