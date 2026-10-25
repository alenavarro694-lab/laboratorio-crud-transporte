/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author puchi
 */
public class ConexionDB {
   

private static final String URL = "jdbc:mysql://localhost:3307/db_paqueteria?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root"; 
    private static final String PASS = "panda123";

public static Connection abrir() throws SQLException {
return DriverManager.getConnection(URL, USER, PASS);
}
}
