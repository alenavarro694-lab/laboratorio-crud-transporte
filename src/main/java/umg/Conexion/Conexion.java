package umg.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    public static Connection obtenerConexion() {
        Connection con = null;
        try {
            // 1. Reemplaza 'tu_base_de_datos' por el nombre real de tu base de datos
            // 2. Ajusta el usuario ('root') y tu contraseña si es diferente
            String url = "jdbc:mysql://localhost:3306/tu_base_de_datos?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String user = "root";
            String password = ""; // Escribe tu contraseña de MySQL aquí si tienes una
            
            con = DriverManager.getConnection(url, user, password);
            // System.out.println("Conexión exitosa"); // Opcional para probar
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return con;
    }
}