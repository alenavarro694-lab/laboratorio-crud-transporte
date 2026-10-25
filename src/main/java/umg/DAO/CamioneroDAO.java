package umg.DAO;

import umg.modelo.Camionero;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

public class CamioneroDAO {

    public boolean insertar(Camionero c) {
        // Usamos 'Apellidos' en plural
        String sql = "INSERT INTO camionero (Nombre, Apellidos, Telefono, Direccion, Salario, Poblacion) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellidos());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getDireccion());
            
            if (c.getSalario() != null) {
                ps.setDouble(5, c.getSalario().doubleValue());
            } else {
                ps.setDouble(5, 0.0);
            }
            
            ps.setString(6, c.getPoblacion());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error SQL en Insertar: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean actualizar(Camionero c) {
        String sql = "UPDATE camionero SET Nombre = ?, Apellidos = ?, Telefono = ?, Direccion = ?, Salario = ?, Poblacion = ? WHERE idCamionero = ?";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellidos());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getDireccion());
            
            if (c.getSalario() != null) {
                ps.setDouble(5, c.getSalario().doubleValue());
            } else {
                ps.setDouble(5, 0.0);
            }
            
            ps.setString(6, c.getPoblacion());
            ps.setInt(7, c.getIdCamionero());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error SQL en Actualizar: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM camionero WHERE idCamionero = ?";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error SQL en Eliminar: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<Camionero> listar() {
        List<Camionero> lista = new ArrayList<>();
        // Asegúrate de que en tu base de datos la columna se llame Apellidos (o cámbiala aquí si en tu BD es Apellido)
        String sql = "SELECT idCamionero, Nombre, Apellidos, Telefono, Direccion, Salario, Poblacion FROM camionero";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Camionero c = new Camionero();
                c.setIdCamionero(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setApellidos(rs.getString(3));
                c.setTelefono(rs.getString(4));
                c.setDireccion(rs.getString(5));
                
                double sal = rs.getDouble(6);
                c.setSalario(BigDecimal.valueOf(sal));
                
                c.setPoblacion(rs.getString(7));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}